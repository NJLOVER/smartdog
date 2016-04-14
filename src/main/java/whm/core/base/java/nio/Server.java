package whm.core.base.java.nio;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by thinkpad on 2015/12/10.
 */
public class Server {
    private static int port = 11345;
    private Selector selector;

    //初始化服务器设置
    public void init(){
        try{
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(port));
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){

        }
    }

    //监听selector事件
    public void listen() throws IOException {
        System.out.println("开始接受请求!");
        while(true){
            //当注册事件到达时,方法返回,否则一直阻塞
            selector.select();
            Iterator it = this.selector.selectedKeys().iterator();
            while(it.hasNext()){
                SelectionKey sk = (SelectionKey)it.next();
                it.remove();
                if(sk.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel)sk.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.write(ByteBuffer.wrap(new String("连接服务器成功!").getBytes("UTF-8")));

                    sc.register(selector,SelectionKey.OP_READ);

                }else if(sk.isReadable()){
                    readClientInfo(sk);
                }
            }
        }
    }

    public void readClientInfo(SelectionKey selectionKey) throws IOException {
        StringBuffer sb = new StringBuffer();
        SocketChannel sc = (SocketChannel)selectionKey.channel();
        ByteBuffer bb = ByteBuffer.allocate(10);
        sc.read(bb);
        byte[] data = bb.array();
        String clientInfo = new String(data,"utf-8").trim();
        System.out.println("收到客户端消息:"+clientInfo);

        String msg = "我已收到你方消息:"+clientInfo+",请知晓";
        ByteBuffer out = ByteBuffer.wrap(msg.getBytes("utf-8"));
        sc.write(out);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init();
        server.listen();
    }
}
