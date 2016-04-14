package whm.core.base.Utils;

import java.util.ResourceBundle;

/**
 * Created by thinkpad on 2015/11/9.
 */
public class ConfigUtils {

    public static String getConfig(String propertiesName,String key){
        ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
        return bundle.getString(key);
    }

}
