package whm.core.base.java.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thinkpad on 2015/11/30.
 */
public class XMLTest {

    public static void main(String[] args) {
        InputStream is = XMLTest.class.getResourceAsStream("Pojo.xml");
        File file = new File("F:/Pojo.xml");
        try {
            OutputStream os = new FileOutputStream(file);
            Pojo p = new Pojo();
            p.setDate(new Date());
            p.setName("123");
            Pojo2 p2 = new Pojo2();
            p2.setCount(new BigDecimal(123));
            p2.setXxx("1111");
            p.setPojo2(p2);
            List<Pojo> list = new ArrayList<Pojo>();
            list.add(p);
            XMLEncoder enc = new XMLEncoder(os);
            enc.flush();
            enc.writeObject(list);
            enc.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*InputStream is = XMLTest.class.getResourceAsStream("Pojo.xml");
        XMLDecoder decoder = new XMLDecoder(is);
        Pojo pojo = (Pojo)decoder.readObject();
        System.out.println(pojo.getName());*/
    }
}
