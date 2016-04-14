package whm.core.base.spring;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import whm.core.base.spring.BeanFactory.MyBean;
import whm.core.base.spring.PostProcessor.MyProcessorBean;
import whm.mybatis.dao.map.LogOptMapper;
import whm.mybatis.dao.model.LogOpt;

import javax.annotation.Resource;
import java.applet.AppletContext;


/**
 * Created by thinkpad on 2015/11/17.
 */
public class SpringTest {
    @Resource
    private static SqlSession template;
    private static LogOptMapper logOptMapper;

    public static void main(String[] args) {
        String[] paths = {"classpath:spring/application-context.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(paths);

        ConstructTest test = (ConstructTest)context.getBean("contructTest");
        System.out.println(test.getArg1());
        System.out.println(test.getArg2());


       /* MyProcessorBean poss = (MyProcessorBean)context.getBean("possBean");
        System.out.println("the userName = "+poss.getUserName());
        //MyBean test = (MyBean)context.getBean("myBeanFactory");
        //System.out.println(test.getMsg());
        SqlSession sql = (SqlSession)context.getBean("template");
        logOptMapper = sql.getMapper(LogOptMapper.class);
        LogOpt opt = logOptMapper.selectByPrimaryKey("1997eb10723149b2ba39589253638584");*/
    }
}
