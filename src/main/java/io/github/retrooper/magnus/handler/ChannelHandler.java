package io.github.retrooper.magnus.handler;

import io.github.retrooper.magnus.channel.Channel;
import io.github.retrooper.magnus.packet.Packet;

public interface ChannelHandler {
    void onPacketRead(Channel channel, Packet packet);

    void onPacketWrite(Channel channel, Packet packet);
}
