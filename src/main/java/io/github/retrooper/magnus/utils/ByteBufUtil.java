package io.github.retrooper.magnus.utils;

import io.netty.buffer.ByteBuf;

public class ByteBufUtil {
    public static byte[] readBytes(final ByteBuf buf) {
        byte[] data;
        int offset;
        int length = buf.readableBytes();
        if (buf.hasArray()) {
            data = buf.array();
            offset = buf.arrayOffset();
        } else {
            data = new byte[length];
            buf.getBytes(buf.readerIndex(), data);
            offset = 0;
        }
        return data;
    }
}
