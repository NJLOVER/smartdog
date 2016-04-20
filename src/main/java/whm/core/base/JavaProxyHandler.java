package whm.core.base;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by thinkpad on 2015/11/12.
 */
public class JavaProxyHandler implements InvocationHandler{
    private Object target;

    public JavaProxyHandler(Object target){
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target,args);
        //Object obj = MethodUtils.invokeMethod(target,method.getName(),args);
        after();
        return obj;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void before(){

    }

    public void after(){

    }
}
