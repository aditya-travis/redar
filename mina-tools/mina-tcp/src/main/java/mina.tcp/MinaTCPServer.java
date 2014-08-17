package mina.tcp;

import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Feng on 16/8/14.
 */
public class MinaTCPServer {

    private int port;

    private IoAcceptor acceptor;

    public MinaTCPServer(int port) throws Exception{
        this.port = port;
        this.start();
    }

    public void start() throws Exception{

        //create an acceptor
        acceptor = new NioSocketAcceptor();

        //If more complicated logic are required, please enable the Protocol Filter
        //acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomProtocolCodecFactory()));

        acceptor.setHandler(new MinaTCPServerHandler());
        try {
            acceptor.bind(new InetSocketAddress(this.port));
        } catch (IOException e) {
            System.out.println("Failed to start the MinaTCPServer");
            e.printStackTrace();
        }

        System.out.println("MinaTCPServer started at localhost:" + this.port);

       /* for (; ; ) {
            System.out.println("R: " + acceptor.getStatistics().getReadBytes() +
                    ", W: " + acceptor.getStatistics().getWrittenBytes());
            Thread.sleep(10000);
        }*/

    }

    public void stop(){
        acceptor.dispose();
    }

    private class MinaTCPServerHandler extends IoHandlerAdapter {
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            System.out.println("Received From  :" + session + "\nReceived Message:" + ((IoBuffer)message).getString(Charset.defaultCharset().newDecoder()));

            byte[] replyMessage = constructReplyMessage(message);

            replyMessage(session, replyMessage);
        }

        private byte[] constructReplyMessage(Object message) {
            return ("Message length: " + ((IoBuffer) message).array().length).getBytes();
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            System.out.println("Send To         :" + session + "\nSent Message    :" + ((IoBuffer)message).getString(Charset.defaultCharset().newDecoder()));
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            System.out.println("Client Session: " + session + " CREATED");
        }


        private void replyMessage(IoSession session, byte[] message) throws Exception {

            IoBuffer buffer = IoBuffer.allocate(message.length).setAutoExpand(true);
            buffer.put(message);
            buffer.flip();

            //When IoBuffer is written directly into the session, all the filters are no longer needed.
            session.write(buffer);
            //session.write(IOUtils.toString(message, null));

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

    private class CustomProtocolCodecFactory implements ProtocolCodecFactory {
        @Override
        public ProtocolEncoder getEncoder(IoSession session) throws Exception {
            return new CustomProtocolEncoder();
        }

        @Override
        public ProtocolDecoder getDecoder(IoSession session) throws Exception {
            return new CustomProtocolDecoder();
        }

        private class CustomProtocolEncoder implements ProtocolEncoder {
            @Override
            public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
                System.out.println("encoding the message....");

                //String messageString = ((IoBuffer) message).getString(Charset.defaultCharset().newDecoder());

                String messageString = (String) message;
                IoBuffer outbuffer = IoBuffer.allocate(messageString.length()).setAutoExpand(true);
                outbuffer.putString(String.format("%06d%s", new Object[]{ messageString.length(), messageString}), Charset.defaultCharset().newEncoder());
                outbuffer.flip();
                out.write(outbuffer);
            }

            @Override
            public void dispose(IoSession session) throws Exception {

            }
        }

        private class CustomProtocolDecoder implements ProtocolDecoder {
            @Override
            public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

                try {
                    System.out.println("decoding the message..");
                    int prefixLength = 6;

                    byte[] messageLengthBytes = new byte[prefixLength];
                    in.get(messageLengthBytes);
                    int messageLength = Integer.valueOf(IOUtils.toString(messageLengthBytes, null));

                    byte[] messageBytes = new byte[messageLength];
                    in.get(messageBytes);

                    //stripe the prefix and get the message content
                    IoBuffer newInBuffer = IoBuffer.allocate(messageBytes.length);
                    newInBuffer.put(messageBytes);
                    newInBuffer.flip();
                    out.write(newInBuffer);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
            }

            @Override
            public void dispose(IoSession session) throws Exception {

            }
        }
    }

    private static class Params{ int port=2000; }

    private static Params getParamsFromCmdLine(String[] args){
        Options options = constructCmdLineOptions();
        Params params = new Params();
        try {
            CommandLine commandLine = new BasicParser().parse(options, args);
            params.port = Integer.valueOf(commandLine.getOptionValue("p"));
        } catch (Exception e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("java -cp mina-tcp.jar" + MinaTCPServer.class + " -p <port>", options);
            throw new RuntimeException("Error Occurs", e);
        }

        return  params;
    }

    private static Options constructCmdLineOptions(){
        return new Options().addOption("p", true, "port");
    }

    public static void main(String[] args) throws Exception{

        new MinaTCPServer(getParamsFromCmdLine(args).port);
    }


}
