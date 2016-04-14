package whm.core.base.spring.PostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by thinkpad on 2015/11/17.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition definition = beanFactory.getBeanDefinition("possBean");
        MutablePropertyValues pv = definition.getPropertyValues();
        if(pv.contains("userName")){
            pv.add("userName","wanghm");
        }
    }
}
