package whm.core.base;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.*;

/**
 * Created by thinkpad on 2015/10/28.
 */
public class RedisClient {
    private static JedisPool pools;
    private static String baseUrl = "192.168.244.129";
    private static int port = 6379;
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(0);
        config.setMaxTotal(20);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        pools = new JedisPool(config,baseUrl,port);
    }

    public static Jedis getJedis(){
        return pools.getResource();
    }



    public static void main(String[] args) {


        Jedis jedis = getJedis();
        psubscribe(new MypubSub(),"*");

        jedis.set("mykey","something");
        jedis.expire("mykey", 1);
        System.out.println("7&&&&&&&&&&"+jedis.get("mykey"));
        //jedis.del("mykey");

        Map<String,String> map = new HashMap<String,String>();
        map.put("name","whm");
        map.put("age","18");

        jedis.hmset("myMap",map);

        System.out.printf(jedis.hget("myMap","name"));
        jedis.hset("myMap","sex","nan");
        List<String> values = jedis.hmget("myMap", "name", "age", "sex", "xxx");
        for(String value : values){
            System.out.println(value);
        }

        List mylist = new ArrayList();
        mylist.add("123");
        mylist.add("234");

        Set myset = new HashSet();
        myset.add("123");
        myset.addAll(mylist);
        jedis.sadd("fruit","apple");
        jedis.sadd("fruit","pear","banana");
        myset = jedis.smembers("fruit");

        publish();
    }

    public static void psubscribe(final JedisPubSub pubSub, final String pattern){
        new Thread(new Runnable(){
            @Override
            public void run() {
                getJedis().psubscribe(pubSub,pattern);
            }
        }).start();
    }

    public static void publish() {
        Jedis jedis = getJedis();
        jedis.publish("news.share", "ok");
        jedis.publish("news.share", "hello word");
    }
}
