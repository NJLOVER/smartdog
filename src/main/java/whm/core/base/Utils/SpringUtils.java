package whm.core.base.Utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by thinkpad on 2015/11/12.
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static Object getBean(String beanId){
        return applicationContext.getBean(beanId);
    }
}
