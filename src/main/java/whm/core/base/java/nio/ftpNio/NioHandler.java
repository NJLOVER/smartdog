package whm.core.base.java.nio.ftpNio;

import java.nio.channels.SelectionKey;

/**
 * Created by thinkpad on 22/4/2016.
 */
public interface NioHandler  {
    void excute(SelectionKey key);
}
