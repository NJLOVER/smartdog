package whm.core.base.java.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by thinkpad on 2015/11/30.
 */
public class Pojo {
    private String name;
    private Date date;
    private Pojo2 pojo2;

    public Pojo() {
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pojo2 getPojo2() {
        return pojo2;
    }

    public void setPojo2(Pojo2 pojo2) {
        this.pojo2 = pojo2;
    }
}
