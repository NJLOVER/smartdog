package whm.core.base.quartz.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import whm.core.base.BaseService;
import whm.core.base.Utils.ConfigUtils;
import whm.core.base.quartz.service.OperLogService;
import whm.mybatis.dao.map.LogOptMapper;
import whm.mybatis.dao.model.LogOpt;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by thinkpad on 2015/11/9.
 */

@EnableAsync
@Service("operLogService")
public class OperLogServiceImpl extends BaseService implements OperLogService{
    private LogOptMapper logOptMapper;
    private ConcurrentLinkedQueue<LogOpt> queue = new ConcurrentLinkedQueue<LogOpt>();
    private static int count = 0;
    static{
        //获取配置文件里面的批量日志写入参数
        /*String confCount = ConfigUtils.getConfig("","");
        if(StringUtils.isNotEmpty(confCount)){
            count = Integer.parseInt(confCount);
        }*/
    }

    @Async
    public void writeLog(LogOpt opt){
        queue.add(opt);
        if(queue.size()> count){
            while(!queue.isEmpty()){
                LogOpt log = queue.poll();
                logOptMapper = getTemplate().getMapper(LogOptMapper.class);
                logOptMapper.insertSelective(log);
            }
        }
    }

}
