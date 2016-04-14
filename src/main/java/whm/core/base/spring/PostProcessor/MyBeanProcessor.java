package whm.core.base.spring.PostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by thinkpad on 2015/11/17.
 */
public class MyBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----init bran:"+beanName+"start");
        if(bean instanceof MyProcessorBean){
            System.out.println("post init before!");
            MyProcessorBean poss = (MyProcessorBean)bean;
            System.out.println("the userName = "+poss.getUserName());
            poss.setUserName("123");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----init bran:"+beanName+"end");
        if(bean instanceof MyProcessorBean){
            System.out.println("post init after!");
            MyProcessorBean poss = (MyProcessorBean)bean;
            System.out.println("the userName = "+poss.getUserName());
            poss.setUserName("456");
        }
        return bean;
    }
}
