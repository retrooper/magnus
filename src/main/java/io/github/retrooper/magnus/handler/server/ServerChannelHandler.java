package io.github.retrooper.magnus.handler.server;

import io.github.retrooper.magnus.channel.Channel;
import io.github.retrooper.magnus.handler.ChannelHandler;

public interface ServerChannelHandler extends ChannelHandler {
    void onChannelConnect(Channel channel);

    void onChannelDisconnect(Channel channel);
}
