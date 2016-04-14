package whm.core.design.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 12/4/2016.
 */
public abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void addOb(Observer observer){
        this.observers.add(observer);
    }

    public void quit(Observer observer){
        observers.remove(observer);
    }

    public void nodify(String state){
        for(Observer ob : observers){
            ob.update(state);
        }
    }
}
