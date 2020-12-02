package io.github.retrooper.magnus.channel;

import io.github.retrooper.magnus.packet.Packet;

import java.net.InetSocketAddress;

public class Channel {
    private final int id;
    private final String address;
    private final short port;

    public Channel(final long id, final String address, final int port) {
        this.id = (int)id;
        this.address = address;
        this.port = (short)port;
    }

    public Channel(final long id, final io.netty.channel.Channel channel) {
        this(id, ((InetSocketAddress)(channel.localAddress())).getHostName(), ((InetSocketAddress)(channel.localAddress())).getPort());
    }

    public void write(Packet packet) {
        //TODO
    }

    public void flush() {
        //TODO
    }

    public void writeAndFlush(Packet packet) {
        write(packet);
        flush();
    }

    public void disconnect() {

    }

    public long getID() {
        return id & 0xffffffffL;
    }

    public String getAddress() {
        return address;
    }

    //Port as an unsigned short
    public int getPort() {
        return port & 0xffff;
    }
}
