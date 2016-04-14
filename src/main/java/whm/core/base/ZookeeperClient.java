package whm.core.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by thinkpad on 2015/10/21.
 */
public class ZookeeperClient implements Watcher{
    private ZooKeeper zooKeeper;
    private static final int SESSION_TIME_OUT = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void cennectZk(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host,SESSION_TIME_OUT,this);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent event)
    {
        if (event.getState() == Event.KeeperState.SyncConnected)
        {
            System.out.println("watcher received event");
            countDownLatch.countDown();
        }
    }
    public List<String> getChildren(String path) throws KeeperException,
            InterruptedException
    {
        return this.zooKeeper.getChildren(path, false);
    }


    public String createNode(String path, byte[] data) throws KeeperException,
            InterruptedException
    {
        return this.zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    public Stat setData(String path, byte [] data, int version) throws KeeperException, InterruptedException
    {
        return this.zooKeeper.setData(path, data, version);
    }

    /**
     * 根据路径获取节点数据
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public byte[] getData(String path) throws KeeperException,
            InterruptedException
    {
        return this.zooKeeper.getData(path, false, null);
    }

    /**
     * 删除节点
     *
     * @param path
     * @param version
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void deletNode(String path, int version)
            throws InterruptedException, KeeperException
    {
        this.zooKeeper.delete(path, version);
    }

    /**
     * 关闭zookeeper连接
     *
     * @throws InterruptedException
     */
    public void closeConnect() throws InterruptedException
    {
        if (null != zooKeeper)
        {
            zooKeeper.close();
        }
    }

}
