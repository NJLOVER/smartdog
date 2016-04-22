package whm.core.base.java.io;

import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by thinkpad on 2015/12/10.
 */
public class SocketTest {

    public static void main(String[] args) {
        Server server = new Server();
        new Thread(server).start();

        client client1 = new client();
        client1.init();

    }


    public static class client{
        public static String url = "localhost";
        public static int port= 12345;

        public void init(){
            System.out.println("启动一个客户端!");
            System.out.println("当服务端返回ok时,客户端终止!");
            while(true){
                Socket socket = null;
                try{
                    socket = new Socket(url,port);
                    DataInputStream datain = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());

                    dataout.writeUTF("this is a client info!");

                    String ret = datain.readUTF();
                    System.out.println("服务器端返回过来的是: " + ret);
                    // 如接收到 "OK" 则断开连接
                    if ("OK".equals(ret)) {
                        System.out.println("客户端将关闭连接");
                        Thread.sleep(500);
                        break;
                    }

                    dataout.close();
                    datain.close();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            socket = null;
                            System.out.println("客户端 finally 异常:" + e.getMessage());
                        }
                    }
                }
            }
        }
    }


    public static class Server implements Runnable{
        private static int port = 12345;

        public void init(){
            try {
                ServerSocket ss = new ServerSocket(port);

                while(true){
                    Socket socket = ss.accept();
                    System.out.println("有客户端请求进入");
                    new handlerThread(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void run() {
            init();
        }

        private class handlerThread implements Runnable{
            private Socket socket;

            public handlerThread(Socket sockets){
                this.socket = sockets;
                new Thread(this).start();
            }

            public void run() {
                try {
                    DataInputStream data = new DataInputStream(socket.getInputStream());
                    String clientInputStr = data.readUTF();
                    // 处理客户端数据
                    System.out.println("客户端发过来的内容:" + clientInputStr);

                    //向客户端回复一条信息
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                    System.out.print("请输入:\t");
                    String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    dataOut.writeUTF(s);

                    data.close();
                    dataOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (Exception e) {
                            socket = null;
                            System.out.println("服务端 finally 异常:" + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
