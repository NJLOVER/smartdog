package whm.core.base.java.nio.ftpNio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by thinkpad on 22/4/2016.
 */
public class ClientHandler implements NioHandler {
    private SocketChannel sc;
    private Selector selector;
    private String cmd;
    private FileChannel fileChannel = null;
    private ByteBuffer buf = ByteBuffer.allocate(1024);
    private long sum = 0;

    public ClientHandler(SocketChannel sc, Selector selector) {
        this.sc = sc;
        this.selector = selector;

        FTPNioServer.comnum++;
        System.out.println(FTPNioServer.comnum + " Client:" + sc.socket().getRemoteSocketAddress().toString() + " open");
    }

    public int init(String cmd){
        if(cmd ==null){
            return 1;
        }
        try {
            int i = FTPNioServer.AnalyCmd(cmd);
            if(i == 0){
                SelectionKey key = sc.register(selector, SelectionKey.OP_READ);
                key.attach(this);
            }else{
                SelectionKey key = sc.register(selector, SelectionKey.OP_WRITE);
                key.attach(this);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void excute(SelectionKey key) {
        if(key.isReadable()){
            proccessUpLoadCmd(key);
            return;
        }
    }

    private void proccessUpLoadCmd(SelectionKey key) {

        String s[] = this.cmd.split(":");
        String filepath = FTPNioServer.ROOT + s[1];

        try {
            int n = sc.read(buf);
            if (n >= 0) {
                sum += n;
                writeToFile(filepath, buf);
            } else {
                ReleaseResource(key);
                System.out.println(FTPNioServer.comnum + " read sum:" + sum + " Client:" + sc.socket().getRemoteSocketAddress().toString() + " close");
                FTPNioServer.comnum--;
                return;
            }
        } catch (IOException e) {
            try {
                ReleaseResource(key);
                FTPNioServer.comnum--;
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("IOException " + FTPNioServer.comnum + " Client:" + sc.socket().getRemoteSocketAddress().toString() + " close");
            return;
        }
    }

    public void writeToFile(String path,ByteBuffer buf){
        try {
            if(fileChannel == null) {
                fileChannel = new RandomAccessFile(path, "r").getChannel();
            }
            buf.flip();
            fileChannel.write(buf);
            buf.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    private int ReadFromFile(String path, ByteBuffer buf) throws IOException {

        if (fileChannel == null) {
            fileChannel = new RandomAccessFile(path, "r").getChannel();
        }

        buf.clear();
        return fileChannel.read(buf);
    }

    private void ReleaseResource(SelectionKey key) throws IOException {

        sc.close();
        key.cancel();

        if (fileChannel != null) {
            fileChannel.close();
        }
    }
}
