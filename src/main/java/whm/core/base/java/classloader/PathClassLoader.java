package whm.core.base.java.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * Created by thinkpad on 11/4/2016.
 */
public class PathClassLoader extends URLClassLoader {
    private String path;
    private String packageName = "whm.core.base";


    public PathClassLoader(URL[] urls) {
        super(urls);
    }
}
