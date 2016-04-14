package whm.core.base.quartz.service;

import whm.mybatis.dao.model.LogOpt;

/**
 * Created by thinkpad on 2015/11/9.
 */
public interface OperLogService {
    public void writeLog(LogOpt opt);
}
