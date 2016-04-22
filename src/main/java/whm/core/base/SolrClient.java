package whm.core.base;


import redis.clients.jedis.Jedis;

/**
 * Created by thinkpad on 2015/10/26.
 */
public class SolrClient {
    private static Jedis jedis = new Jedis("192.168.224.129:6397");

    public static void main(String[] args) {
        jedis.set("123","123");
        jedis.get("123");

    }
}
