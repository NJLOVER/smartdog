package whm.core.base.java.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by thinkpad on 2015/12/11.
 */
public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.init();
    }

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
