package whm.core.base.quartz.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import whm.core.base.Utils.SpringUtils;
import whm.core.base.quartz.vo.BaseJob;
import whm.core.base.quartz.vo.ScheduleJob;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by thinkpad on 2015/11/12.
 */
public class TaskUtils {
    private static Logger logger = LoggerFactory.getLogger(TaskUtils.class);

    public static void excute(ScheduleJob scheduleJob){
        Object jobBean = null;
        if(StringUtils.isNotEmpty(scheduleJob.getSpringId())){
            jobBean = SpringUtils.getBean(scheduleJob.getSpringId());
        }else{
            if(StringUtils.isNotEmpty(scheduleJob.getBeanClass())){
                try {
                    jobBean = Class.forName(scheduleJob.getBeanClass());
                } catch (ClassNotFoundException e) {

                }
            }
        }
        if(jobBean == null){
            throw new RuntimeException("could not find the real execute class!");
        }
        String methodName = scheduleJob.getMethodName();
        if(StringUtils.isEmpty(methodName)){
            throw new RuntimeException(" please check your config: method name could not be null!");
        }
        if(jobBean instanceof BaseJob){
            String data = scheduleJob.getParameterJson();
            if(StringUtils.isNotEmpty(data)){
                String getParams[] = scheduleJob.getParameterJson().replaceAll("\r|\n", "").split(";");
                Map dataMap = new HashMap();
                String key = "";
                String value = "";
                for(String param : getParams){
                    key = param;
                    value = param;
                    int idx = key.indexOf("=");
                    key = key.substring(0, idx);
                    int idx1 = value.indexOf("=");
                    value = value.substring(idx1 + 1, value.length());
                    System.out.println("key="+key);
                    System.out.println("value="+value);
                    dataMap.put(key,value);
                }
                ((BaseJob)jobBean).setDataMap(dataMap);
            }
        }
        try {
            MethodUtils.invokeMethod(jobBean,methodName,null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("please check your config:method not find!");
        }catch (Exception e) {
            throw new RuntimeException("method excute error! msg="+e.getMessage());
        }

    }
}
