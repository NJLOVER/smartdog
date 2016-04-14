package whm.core.base;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by thinkpad on 2015/10/28.
 */
public class MypubSub extends JedisPubSub{
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern + "******"+channel+ "******" + message);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern+"******"+subscribedChannels);
    }
}
