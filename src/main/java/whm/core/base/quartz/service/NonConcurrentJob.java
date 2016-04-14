package whm.core.base.quartz.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import whm.core.base.quartz.AbstractSchedulerJob;
import whm.core.base.quartz.utils.TaskUtils;
import whm.core.base.quartz.vo.ScheduleJob;

/**
 * Created by thinkpad on 2015/11/12.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class NonConcurrentJob extends AbstractSchedulerJob {
    @Override
    public void exc(ScheduleJob scheduleJob) {
        TaskUtils.excute(scheduleJob);
    }
}
