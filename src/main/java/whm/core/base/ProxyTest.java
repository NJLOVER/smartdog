package whm.core.base;

import java.lang.reflect.Proxy;

/**
 * Created by thinkpad on 2015/11/12.
 */
public class ProxyTest {

    public static void main(String[] args) {
        MyImpl ml = new MyImpl();
        ClassLoader cl = MyInterface.class.getClassLoader();
        ClassLoader cl2 = ml.getClass().getClassLoader();
        Class<?>[] clzzs = {MyInterface.class};
        Class<?>[] clzzs2 = ml.getClass().getInterfaces();
        MyInterface mi = (MyInterface)Proxy.newProxyInstance(ml.getClass().getClassLoader(),clzzs,new JavaProxyHandler(ml));
        mi.deSomeThing("123");
    }
}
