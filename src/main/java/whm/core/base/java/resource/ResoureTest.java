package whm.core.base.java.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by thinkpad on 2015/11/30.
 */
public class ResoureTest {
    /*
    * class.getResourceAsStream
    *
    * ClassLoader.getResourceAsStream.
    *
    * */


    public static void main(String[] args) {
        InputStream is1 = ResoureTest.class.getResourceAsStream("test1.properties");//获取当前目录的文件
        InputStream is2 = ResoureTest.class.getResourceAsStream("path/test2.properties");//获取子目录文件
        InputStream is3 = ResoureTest.class.getResourceAsStream("/whm/core/base/test3.properties");//获取其他目录文件,等同classpath
        ResoureTest test = new ResoureTest();
        InputStream is4 = test.getClass().getClassLoader().getResourceAsStream("whm/core/base/test3.properties");
        InputStream is5 = test.getClass().getClassLoader().getResourceAsStream("prop.properties");


        Properties p = new Properties();
        try {
            Enumeration<URL> urls = test.getClass().getClassLoader().getResources("prop.properties");
            while(urls.hasMoreElements()){
                URL en = urls.nextElement();
                System.out.println(en.toString());
            }
            p.load(is1);
            System.out.println(p.getProperty("a"));
            p.load(is2);
            System.out.println(p.getProperty("a"));
            p.load(is3);
            System.out.println(p.getProperty("a"));
            p.load(is4);
            System.out.println(p.getProperty("a"));
            p.load(is5);
            System.out.println(p.getProperty("endpoint"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
