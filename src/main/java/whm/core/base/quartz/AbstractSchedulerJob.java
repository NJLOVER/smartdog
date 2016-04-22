package whm.core.base.quartz;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.impl.jdbcjobstore.oracle.OracleDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import whm.core.base.Utils.UUIDUtils;
import whm.core.base.quartz.service.OperLogService;
import whm.core.base.quartz.utils.ConstsDef;
import whm.core.base.quartz.vo.ScheduleJob;
import whm.mybatis.dao.model.LogOpt;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


/**
 * Created by thinkpad on 2015/11/5.
 */
public abstract class AbstractSchedulerJob extends QuartzJobBean {
    private OracleDelegate oracleDelegate;
    private static Logger log = LoggerFactory.getLogger(AbstractSchedulerJob.class);

    @Resource
    private OperLogService operLogService;

    public ApplicationContext applicationContext = null;

    //实际的执行方法
    public abstract void exc(ScheduleJob scheduleJob);

    protected void executeInternal(JobExecutionContext context){
        applicationContext = getAppliCxt(context);
        Date sdate = new Date();
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get(ConstsDef.JOB_PARAM_KEY);
        log.info("jobName = "+scheduleJob.getJobName()+" jobGroup="+scheduleJob.getJobGroup()+"startDate="+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        String errMsg = "";
        try{
            //执行正在的业务逻辑方法.
            exc(scheduleJob);
        }catch (Exception e){
            e.printStackTrace();
            log.error("job"+scheduleJob.getJobName()+"执行出错,错误信息为:"+e.getMessage());
            errMsg = e.getMessage();
        }
        //todo 写入执行日志表.
        LogOpt opt = new LogOpt();
        opt.setOlogId(UUIDUtils.getUUid());
        opt.setOlogDatetime(sdate);
        opt.setOlogInfo("");
        opt.setOlogMac(getLocalIP());
        operLogService.writeLog(opt);
    }


    public static String getLocalIP(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        //System.out.println(ipAddrStr);
        return ipAddrStr;
    }



    public ApplicationContext getAppliCxt(JobExecutionContext context){
        try {
            return (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");
        } catch (SchedulerException e) {
            log.error("获取spring的applicationContext失败!原因为"+e.getMessage());
        }
        return null;
    }
}
