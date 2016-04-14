package whm.core.base.java.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by thinkpad on 2015/12/11.
 */
public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

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

        @Override
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
