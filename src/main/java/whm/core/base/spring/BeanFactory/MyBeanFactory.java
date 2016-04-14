package whm.core.base.spring.BeanFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * Created by thinkpad on 2015/11/17.
 */
@Service(value = "myBeanFactory")
public class MyBeanFactory implements FactoryBean<MyBean> {

    @Override
    public MyBean getObject() throws Exception {
        MyBean test = new MyBean();
        test.setMsg("just a test !");
        return test;
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
