package whm.core.base.quartz;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import whm.core.base.quartz.service.ConcurrentJob;
import whm.core.base.quartz.service.NonConcurrentJob;
import whm.core.base.quartz.utils.ConstsDef;
import whm.core.base.quartz.utils.ScheUtils;
import whm.core.base.quartz.vo.ScheduleJob;

import java.util.Date;
import java.util.UUID;

/**
 * Created by thinkpad on 2015/11/3.
 */
public class QuartzTest {
    private static Logger log = LoggerFactory.getLogger(QuartzTest.class);
    @Autowired
    public Scheduler scheduler;// 定时器
    private static String name;

    public QuartzTest(String name){
        this.name = name;
    }

}
