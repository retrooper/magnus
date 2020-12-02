package io.github.retrooper.magnus.packet;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Packet {
    private ByteArrayDataInput input;
    private ByteArrayDataOutput output;

    public Packet(byte[] data) {
        this.input = ByteStreams.newDataInput(data);
    }

    public Packet() {
        this.output = ByteStreams.newDataOutput();
    }


    public void writeBoolean(final boolean value) {
        output.writeBoolean(value);
    }

    public void writeByte(final byte value) {
        output.writeByte(value);
    }

    public void writeShort(final short value) {
        output.writeShort(value);
    }

    public void writeInt(final int value) {
        output.writeInt(value);
    }

    public void writeLong(final long value) {
        output.writeLong(value);
    }

    public void writeFloat(final float value) {
        output.writeFloat(value);
    }

    public void writeDouble(final double value) {
        output.writeDouble(value);
    }

    public void writeChar(final char value){
        output.writeChar(value);
    }

    public void writeString(final String value) {
        output.writeUTF(value);
    }

    public boolean readBoolean() {
        return input.readBoolean();
    }

    public byte readByte() {
        return input.readByte();
    }

    public short readShort() {
        return input.readShort();
    }

    public int readInt() {
        return input.readInt();
    }

    public float readFloat() {
        return input.readFloat();
    }

    public double readDouble() {
        return input.readDouble();
    }

    public char readChar() {
        return input.readChar();
    }

    public String readString() {
        return input.readUTF();
    }
}
