package niosocket;

import java.nio.channels.SocketChannel;

class ServerDataEvent {
    public NIOSocketServer server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(NIOSocketServer server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
