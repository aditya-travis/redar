package mina.tcp;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.io.IOUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Feng on 16/8/14.
 */
public class MinaTCPClient {

    private int port;
    private String hostname;

    private ConnectFuture connectFuture;

    public MinaTCPClient(String hostname, int port) throws Exception{
        this.hostname = hostname;
        this.port = port;
        this.start();
    }

    public void start() throws Exception{

        //create an connector
        IoConnector connector = new NioSocketConnector();

        //If more complicated logic are required, please enable the Protocol Filter
        //connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomProtocolCodecFactory()));

        connector.setHandler(new MinaTCPClientHandler());

        while(true){
            try {
                connectFuture = connector.connect(new InetSocketAddress(this.hostname, this.port));
                connectFuture.awaitUninterruptibly();
                System.out.println("MinaTCPServer started at localhost:" + this.port);
                break;

            } catch (Exception e) {
                System.out.println("Failed to start the MinaTCPServer");
                e.printStackTrace();

            }
        }
    }

    public void send(byte[] message) throws Exception{
        IoBuffer buffer = IoBuffer.allocate(message.length).setAutoExpand(true);
        buffer.put(String.format("%06d", message.length).getBytes());
        buffer.put(message);
        buffer.flip();
        connectFuture.getSession().write(buffer);
    }



    private class MinaTCPClientHandler extends IoHandlerAdapter {
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            System.out.println("Received From  :" + session + "\nReceived Message:" + ((IoBuffer)message).getString(Charset.defaultCharset().newDecoder()));
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            System.out.println("Send To         :" + session + "\nSent Message    :" + ((IoBuffer)message).getString(Charset.defaultCharset().newDecoder()));
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            System.out.println("Client Session: " + session + " CREATED");
        }

        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            cause.printStackTrace();
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            System.out.println("Client Session " + session + " OPENED");
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            System.out.println("Client Session " + session + " CLOSED");
        }

    }

    private static class Params{ String hostname = "localhost";  int port=2000; String filename; }

    private static Params getParamsFromCmdLine(String[] args){
        Options options = constructCmdLineOptions();
        Params params = new Params();
        try {
            CommandLine commandLine = new BasicParser().parse(options, args);
            params.port = Integer.valueOf(commandLine.getOptionValue("p"));
            params.hostname = commandLine.getOptionValue("hn");
            params.filename = commandLine.getOptionValue("f");
        } catch (Exception e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("java -cp mina-tcp.jar"+ MinaTCPClient.class + " -p <port>", options);
            throw new RuntimeException("Error Occurs", e);
        }

        return  params;
    }

    private static Options constructCmdLineOptions(){
        return new Options().addOption("p", "port", true, "port")
                .addOption("hn", "hostname", true, "hostname")
                .addOption("f", "file", true, "file name path")
                .addOption("h", "help", false, "print help message");
    }

    public static void main(String[] args) throws Exception{

        Params params = getParamsFromCmdLine(args);
        new MinaTCPClient(params.hostname, params.port).send(IOUtils.toByteArray(new FileInputStream(params.filename)));
    }


}
