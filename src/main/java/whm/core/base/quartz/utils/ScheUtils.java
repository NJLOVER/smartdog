package whm.core.base.quartz.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.omg.SendingContext.RunTime;
import org.quartz.*;
import whm.core.base.Utils.SpringUtils;
import whm.core.base.Utils.UUIDUtils;
import whm.core.base.quartz.service.ConcurrentJob;
import whm.core.base.quartz.service.NonConcurrentJob;
import whm.core.base.quartz.vo.ScheduleJob;

import java.util.Date;

/**
 * Created by thinkpad on 2015/11/12.
 */
public class ScheUtils {
    private static Scheduler scheduler;
    static{
        scheduler = (Scheduler)SpringUtils.getBean("scheduler");
    }

    public static boolean checkIfExit(String jobName,String jobGroup) {
        if(StringUtils.isEmpty(jobGroup)){
            jobGroup = jobName;
        }
        JobKey jobkey = new JobKey(jobName,jobGroup);
        try {
            return scheduler.checkExists(jobkey);
        } catch (SchedulerException e) {
            throw new RuntimeException("check job:"+jobName+" if exist faile!");
        }
    }

    public static void createJob(ScheduleJob scheduleJob){
        Date start = new Date();
        Date end = null;
        if(StringUtils.isEmpty(scheduleJob.getJobId())){
            scheduleJob.setJobId(UUIDUtils.getUUid());
        }
        String jobName = scheduleJob.getJobName();//生成的job名称
        String jobGroup = scheduleJob.getJobGroup();
        /*String jobClass = scheduleJob.getBeanClass();//具体的执行类
        String methodName = scheduleJob.getMethodName();//反射方法*/
        String cronExp = scheduleJob.getCronExpression();//cron表达式
        String isCon = scheduleJob.getIsConcurrent();//是否同步
        String descri = scheduleJob.getDescription();
        String sdate  = scheduleJob.getStartDate();
        String endDate = scheduleJob.getEndDate();
        String triggerType = scheduleJob.getTriggerType();

        boolean ifSync = false;
        if("1".equals(isCon)){
            ifSync = true;
        }
        Class<? extends Job> clazz = ifSync==true?ConcurrentJob.class:NonConcurrentJob.class;
        if(scheduler == null){
            throw new RuntimeException("");
        }
        if(ScheUtils.checkIfExit(jobName,jobGroup)){
            throw new RuntimeException("job could not create twice!");
        }
        if("cron".equals(triggerType) && org.apache.commons.lang3.StringUtils.isEmpty(cronExp)){
            throw new RuntimeException("cronExp could not be null if you want to create a cron trigger!");
        }
        //开始生产任务
        JobBuilder builder = JobBuilder.newJob(clazz);
        builder.withIdentity(jobName,jobGroup);
        builder.withDescription(descri);
        try{
            if(StringUtils.isNotEmpty(sdate)){
                start = DateUtils.parseDate(sdate, "yyyy-MM-dd");
            }
            if(StringUtils.isNotEmpty(endDate)){
                end = DateUtils.parseDate(endDate,"yyyy-MM-dd");
            }
            if("redo".equals(triggerType)){
                builder.storeDurably();
                JobDetail detail = builder.build();
                detail.getJobDataMap().put(ConstsDef.JOB_PARAM_KEY,scheduleJob);
                scheduler.addJob(detail, true);
            }else if("cron".equals(triggerType)){
                JobDetail detail = builder.build();
                detail.getJobDataMap().put(ConstsDef.JOB_PARAM_KEY,scheduleJob);

                CronScheduleBuilder scBuilder = CronScheduleBuilder.cronSchedule(cronExp);
                CronTrigger trigger = TriggerBuilder.newTrigger().startAt(start).withIdentity(jobName,jobGroup).endAt(end).withDescription(descri).withSchedule(scBuilder).build();
                scheduler.scheduleJob(detail,trigger);
            }
        }catch (Exception e){
            throw new RuntimeException("create job error! msg="+e.getMessage());
        }

    }

    public static TriggerKey getTriggerKey(String jobName,String jobGroup){
        return TriggerKey.triggerKey(jobName,jobGroup);
    }

    public static JobKey getJobKey(String jobName,String jobGroup){
        return new JobKey(jobName,jobGroup);
    }

    public static Trigger getTrigger(String jobName,String jobGroup) throws SchedulerException {
        return scheduler.getTrigger(getTriggerKey(jobName,jobGroup));
    }

    public static void runOnce(String jobName,String jobGroup){
        JobKey jobkey = new JobKey(jobName,jobGroup);
        try {
            scheduler.triggerJob(jobkey);
        } catch (SchedulerException e) {
            throw new RuntimeException("job:"+jobName+" run once faile!");
        }
    }

    public static void pauseJob(String jobName,String jobGroup){
        JobKey jobkey = new JobKey(jobName,jobGroup);
        try {
            scheduler.pauseJob(jobkey);
        } catch (SchedulerException e) {
            throw new RuntimeException("job:"+jobName+" pause faile!");
        }
    }

}
