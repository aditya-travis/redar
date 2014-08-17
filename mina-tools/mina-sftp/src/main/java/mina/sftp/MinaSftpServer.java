package mina.sftp;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.sshd.SshServer;
import org.apache.sshd.common.util.OsUtils;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.PublickeyAuthenticator;
import org.apache.sshd.server.UserAuth;
import org.apache.sshd.server.auth.UserAuthPassword;
import org.apache.sshd.server.auth.UserAuthPublicKey;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.apache.sshd.sftp.subsystem.SftpSubsystem;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meng on 8/17/14.
 */
public class MinaSftpServer {

    private int port;
    private String keyStorePath = "/tmp/mina_sftp_keystore";
    private SshServer sshServer;

    private boolean publicKeyAuthEnabled = false;

    public MinaSftpServer(int port) {
        this.port = port;
        this.start();
    }

    public MinaSftpServer(int port, boolean publicKeyAuthEnabled) {
        this.port = port;
        this.publicKeyAuthEnabled = publicKeyAuthEnabled;
        this.start();
    }

    public void start(){

        System.out.println("Starting the sftp server at port:" + port);

        sshServer = SshServer.setUpDefaultServer();
        sshServer.setPort(port);
        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(keyStorePath));

        List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
        userAuthFactories.add(new UserAuthPassword.Factory());

        sshServer.setPasswordAuthenticator(new PasswordAuthenticator() {
            @Override
            public boolean authenticate(String username, String password, ServerSession session) {
                return "meng".equals(username) && "password".equals(password);
            }
        });

        if(this.publicKeyAuthEnabled){

            userAuthFactories.add(new UserAuthPublicKey.Factory());

            sshServer.setPublickeyAuthenticator(new PublickeyAuthenticator() {
                @Override
                public boolean authenticate(String username, PublicKey key, ServerSession session) {
                    return true;
                }
            });
        }

        sshServer.setUserAuthFactories(userAuthFactories);

        String commandShell = OsUtils.isUNIX() ? "/usr/bin/sh" : "cmd.exe";
        sshServer.setShellFactory(new ProcessShellFactory(new String[]{commandShell}));

        sshServer.setCommandFactory(new ScpCommandFactory());

        List<NamedFactory<Command>> commandFactories = new ArrayList<NamedFactory<Command>>();
        commandFactories.add(new SftpSubsystem.Factory());
        sshServer.setSubsystemFactories(commandFactories);

        try {
            sshServer.start();
            System.out.println("Started sftp server at port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void stop(){
        try {
            sshServer.stop();
            System.out.println("Stopped sftp server at port:" + port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Params{ int port=22; boolean publicKeyAuthEnabled=false; }

    private static Params getParamsFromCmdLine(String[] args){
        Options options = constructCmdLineOptions();
        Params params = new Params();
        try {
            CommandLine commandLine = new BasicParser().parse(options, args);

            if(commandLine.hasOption("p")){
                params.port = Integer.valueOf(commandLine.getOptionValue("p"));
            }

            if(commandLine.hasOption("k")){
                params.publicKeyAuthEnabled = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("java -cp mina-sftp.jar" + MinaSftpServer.class + " -p <port>", options);
            throw new RuntimeException("Error Occurs", e);
        }

        return  params;
    }

    private static Options constructCmdLineOptions(){
        return new Options().addOption("p", "port", true, "port")
                .addOption("k", "keyEnabled", false, "public key authentication enabled. default false");
    }

    public static void main(String[] args) throws Exception{

        Params params = getParamsFromCmdLine(args);
        new MinaSftpServer(params.port, params.publicKeyAuthEnabled);
    }

}
