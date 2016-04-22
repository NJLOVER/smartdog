package whm.core.base.java.nio.ftpNio;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by thinkpad on 22/4/2016.
 */
public class ServerHandler implements NioHandler{
    private ServerSocketChannel ssc;
    private Selector selector;

    public ServerHandler(ServerSocketChannel ssc, Selector selector){
        this.ssc = ssc;
        this.selector = selector;
    }

    public void excute(SelectionKey key) {
        try {
            String cmd = null;
            SocketChannel sc = ssc.accept();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            int ret = sc.read(bf);
            if(ret>0){
                byte[] bb = bf.array();
                cmd = new String(bb,"utf-8");
                int i = FTPNioServer.AnalyCmd(cmd);
                if(i != -1){
                    String s = "ack";
                    sc.write(ByteBuffer.wrap(s.getBytes("utf-8")));
                }else{
                    String s = "cmd is invalid, please check and try again!!!";
                    ByteBuffer b = ByteBuffer.wrap(s.getBytes("utf-8"));
                    sc.write(b);
                    sc.close();
                    return;
                }
            }else {
                System.out.println("client no send cmd!!!");
                sc.close();
                return;
            }

            sc.configureBlocking(false);

            ClientHandler clientHandler = new ClientHandler(sc,selector);
            clientHandler.init(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
