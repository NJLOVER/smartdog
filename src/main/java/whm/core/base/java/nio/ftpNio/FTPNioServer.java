package whm.core.base.java.nio.ftpNio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by thinkpad on 22/4/2016.
 */
public class FTPNioServer {
    public static int comnum = 0;
    public static String ROOT = "D:/file/";

    public static int AnalyCmd(String cmd) {
        int ret = -1;
        if (cmd.toLowerCase().startsWith("upload")) {
            ret = 0;
        } else if (cmd.toLowerCase().startsWith("download")) {
            ret = 1;
        } else if (cmd.toLowerCase().equals("ls")) {
            ret = 2;
        }
        return ret;
    }

    public static void main(String[] args) {
        try{
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);

            ServerSocket ss = ssc.socket();
            ss.bind(new InetSocketAddress(12311));

            SelectionKey skey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            skey.attach(new ServerHandler(ssc,selector));

            System.out.println("ftp server start on 12311");

            while(!Thread.interrupted()){
                int n = selector.select();
                if(n == 0){
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    NioHandler handler = (NioHandler)key.attachment();
                    handler.excute(key);
                }
            }
        }catch (Exception e){

        }

    }
}
