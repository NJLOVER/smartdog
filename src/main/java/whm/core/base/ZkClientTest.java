package whm.core.base;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thinkpad on 2015/10/21.
 */
public class ZkClientTest {

    public static void main(String[] args) {
        ZookeeperClient baseZookeeper = new ZookeeperClient();
        try {
            baseZookeeper.cennectZk("192.168.244.129:2811");

            baseZookeeper.deletNode("/test",0);

            byte [] data = {1, 2, 3, 4, 5};
            String result = baseZookeeper.createNode("/test", data);
            System.out.println(result);

            List<String> children = baseZookeeper.getChildren("/");
            for (String child : children)
            {
                System.out.println(child);
            }
            System.out.println("--------get children ok-----------");


            byte [] nodeData = baseZookeeper.getData("/test");
            System.out.println(Arrays.toString(nodeData));
            System.out.println("--------get node data ok-----------");

            data = "test data".getBytes();
            baseZookeeper.setData("/test", data, 0);
            System.out.println("--------set node data ok-----------");

            nodeData = baseZookeeper.getData("/test");
            System.out.println(Arrays.toString(nodeData));
            System.out.println("--------get node new data ok-----------");

            baseZookeeper.closeConnect();
            System.out.println("--------close zookeeper ok-----------");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }



}
