package io.github.retrooper.magnus.internal.channelhandler;

import io.github.retrooper.magnus.Magnus;
import io.github.retrooper.magnus.channel.Channel;
import io.github.retrooper.magnus.handler.ChannelHandler;
import io.github.retrooper.magnus.handler.server.ServerChannelHandler;
import io.github.retrooper.magnus.packet.Packet;
import io.github.retrooper.magnus.utils.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class InternalChannelHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        long channelID = 100; //TODO newly generated channel ID

        //This created channel will be cached in a map with the netty channel identifier.
        Channel channel = new Channel(channelID, ctx.channel());
        //TODO Cache channel here...
        for (ChannelHandler handler : Magnus.channelHandlers) {
            if (handler instanceof ServerChannelHandler) {
                ServerChannelHandler serverHandler = (ServerChannelHandler) handler;
                serverHandler.onChannelConnect(new Channel(100, ctx.channel()));
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = new Channel(100, ctx.channel()); //TODO Access channel from cache

        for (ChannelHandler handler : Magnus.channelHandlers) {
            if (handler instanceof ServerChannelHandler) {
                ServerChannelHandler serverHandler = (ServerChannelHandler) handler;
                serverHandler.onChannelDisconnect(new Channel(100, ctx.channel()));
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = new Channel(100, ctx.channel()); //TODO Access channel from cache
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = ByteBufUtil.readBytes(buf);
        Packet packet = new Packet(data);
        for (ChannelHandler handler : Magnus.channelHandlers) {
            handler.onPacketRead(channel, packet);
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Channel channel = new Channel(100, ctx.channel()); //TODO Access channel from cache
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = ByteBufUtil.readBytes(buf);
        Packet packet = new Packet(data);
        for (ChannelHandler handler : Magnus.channelHandlers) {
            handler.onPacketWrite(channel, packet);
        }
    }


}
