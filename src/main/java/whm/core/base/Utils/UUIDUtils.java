package whm.core.base.Utils;

import java.util.UUID;

/**
 * Created by thinkpad on 2015/11/13.
 */
public class UUIDUtils{

    public static String getUUid(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }
}