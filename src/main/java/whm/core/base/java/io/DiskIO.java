package whm.core.base.java.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by thinkpad on 2015/12/4.
 */
public class DiskIO implements Serializable{
    public static void main(String[] args) {
        //1.file
        try {
            //文件创建
            byte[] buffer = new byte[512];
            File file = new File("D:/io123.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader is = new FileReader(file);
            BufferedReader bis = new BufferedReader(is);
            FileOutputStream os = new FileOutputStream(file);
            List<String> strs = new ArrayList<String>();
            strs.add("-----io test ----");
            String s = new String("测试案例");
            strs.add("编码问题测试.");
            strs.add("2.sdfkal");

            os.flush();
            os.write(s.getBytes("utf-8"));
            for (String str : strs) {
                str +="\n";
                os.write(str.getBytes("utf-8"));
            }
            os.close();
            String ll = bis.readLine();
            while (ll != null) {
                System.out.println(ll);
                ll = bis.readLine();
            }
            is.close();
            bis.close();

            FileInputStream fis = new FileInputStream(file);
            StringBuffer sb = new StringBuffer();
            while(fis.read(buffer)!=-1){
                sb.append(new String(buffer,"utf-8"));
            }
            System.out.println(sb.toString());

            fis.close();

            //zip压缩
            String zipPath = "D:/book.zip";
            byte[] bb = new byte[512];
            ZipOutputStream zou = new ZipOutputStream(new FileOutputStream(zipPath));
            zou.setComment("hello");
            String path = "C:/Users/Public/Pictures/Sample Pictures";
            File f = new File(path);
            if(f.isDirectory()){
                File[] files = f.listFiles();
                for(File fl : files){
                    zou.putNextEntry(new ZipEntry(fl.getName()));
                    FileInputStream fi = new FileInputStream(fl);
                    while(fi.read(bb)!=-1){
                        zou.write(bb);
                    }
                    bb = new byte[512];
                    fi.close();
                }
            }
            zou.close();

            //Reader编码
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String str ="";
            while((str = br.readLine()) != null){
                System.out.println(str);
            }

            BufferedInputStream bois = new BufferedInputStream(fis);

            serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void serialize(){
        File f = new File("D:/io222.dat");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            DiskIO dis = new DiskIO();
            oos.writeObject(dis);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
