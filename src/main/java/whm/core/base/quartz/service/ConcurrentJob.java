package whm.core.base.quartz.service;

import whm.core.base.quartz.AbstractSchedulerJob;
import whm.core.base.quartz.utils.TaskUtils;
import whm.core.base.quartz.vo.ScheduleJob;

/**
 * Created by thinkpad on 2015/11/12.
 */
public class ConcurrentJob extends AbstractSchedulerJob {
    @Override
    public void exc(ScheduleJob scheduleJob) {
        TaskUtils.excute(scheduleJob);
    }
}
