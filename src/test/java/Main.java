import io.github.retrooper.magnus.channel.Channel;
import io.github.retrooper.magnus.handler.server.ServerChannelHandler;
import io.github.retrooper.magnus.packet.Packet;

public class Main {
    public static void main(String[] args) {

    }

    public static class CustomChannelHandler implements ServerChannelHandler {
        @Override
        public void onChannelConnect(Channel channel) {
            Packet packet = new Packet();
            packet.writeByte((byte)0); //chat packet ID

            packet.writeString("Welcome " + channel.getAddress() + ":" + channel.getPort());
            packet.writeString("You have been assigned the ID " + channel.getID());
            channel.writeAndFlush(packet);
        }

        @Override
        public void onChannelDisconnect(Channel channel) {
            //Bye bye!
        }

        @Override
        public void onPacketRead(Channel channel, Packet packet) {
            long id = channel.getID();
            int port = channel.getPort();
            String address = channel.getAddress();

            byte packetID = packet.readByte();
            switch (packetID) {
                case 0:
                    //Chat Packet
                    String message = packet.readString();
                    //Process...
                    break;
                case 1:
                    //Client Version Info Packet
                    short localVersion = 100;
                    short protocolVersion = packet.readShort();
                    //Process...
                    if (protocolVersion != localVersion) {
                        Packet newPacket = new Packet();
                        //Packet ID 0(chat packet)
                        newPacket.writeByte((byte) 0);
                        //Write to the chat packet
                        newPacket.writeString("You are on an unsupported client version, please update your client!");
                        channel.writeAndFlush(newPacket);
                        //Later you could kick them....
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }

        @Override
        public void onPacketWrite(Channel channel, Packet packet) {

        }
    }
}
