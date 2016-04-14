package whm.core.design.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 12/4/2016.
 */
public class ConSubject extends Subject{
    private String state;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        nodify(state);
        Object[] objs = new Object[10];
        objs[0]="123";
        List<List> lists = new ArrayList<List>();
        lists.add(new ArrayList());

    }

}
