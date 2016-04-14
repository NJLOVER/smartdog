package whm.core.base;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.BeanFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by thinkpad on 2015/11/9.
 */
public class BaseService{
    @Resource
    private SqlSession template;

    public SqlSession getTemplate() {
        return template;
    }
}
