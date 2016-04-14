package whm.core.base.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by thinkpad on 2015/12/10.
 */
public class Client {
    private static String ip="localhost";
    private static int port = 11345;

    private Selector selector;

    public void connect() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        selector  = Selector.open();

        sc.connect(new InetSocketAddress(ip,port));
        sc.register(selector, SelectionKey.OP_CONNECT);
    }

    public void listen() throws IOException {
        selector.select();
        Iterator it = selector.selectedKeys().iterator();
        while(it.hasNext()){
            SelectionKey sk = (SelectionKey)it.next();
            if(sk.isConnectable()){
                SocketChannel sc = (SocketChannel)sk.channel();
                if(sc.isConnectionPending()){
                    sc.finishConnect();
                }
                sc.configureBlocking(false);

                sc.write(ByteBuffer.wrap(new String("客户端连接成功!").getBytes("UTF-8")));
                //发送想要的文件地址给服务端.

                sc.register(selector,SelectionKey.OP_READ);
            }else if(sk.isReadable()){
                if(read(sk)){
                    break;
                }
            }
        }
    }

    public boolean read(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel)selectionKey.channel();
        ByteBuffer bb = ByteBuffer.allocate(1024);
        sc.read(bb);
        byte[] data = bb.array();
        String clientInfo = new String(data,"utf-8").trim();
        System.out.println("收到服务端消息:"+clientInfo);
        String msg = "我已收到你方消息:"+clientInfo+",请知晓";
        ByteBuffer out = ByteBuffer.wrap(msg.getBytes("utf-8"));
        sc.write(out);
        return false;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect();
        client.listen();
    }
}
