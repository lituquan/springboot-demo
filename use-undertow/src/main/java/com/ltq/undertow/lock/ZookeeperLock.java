package com.ltq.undertow.lock;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperLock implements Lock {
    public static int sessionTimeout = 30 * 1000;
    public static String lockPath = "/lock";
    public ZkClient zk;
    public String empString = null;

    public ZookeeperLock(String zkaddress) {
        zk = new ZkClient(zkaddress, sessionTimeout);
        if (!zk.exists(lockPath)) { // 创建主目录
            zk.create(lockPath, "", CreateMode.PERSISTENT);
        }
    }

    @Override
    public Boolean lock(String key, String value) {
        // zk 根节点path
        String keyLockPath = lockPath + "/" + key;
        if (!zk.exists(keyLockPath)) {
            zk.create(keyLockPath, "", CreateMode.PERSISTENT);
        }
        // zk 创建顺序、临时节点
        String reqLock = keyLockPath + "/lock-";
        String result = zk.create(reqLock, value, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(result);
        List<String> childs = zk.getChildren(keyLockPath);
        Collections.sort(childs);
        empString = result;
        if (result.equals(childs.get(0))) {
            return true;
        } else {
            final CountDownLatch latch = new CountDownLatch(1);
            for (int i = 0; i < childs.size(); i++) {
                System.out.println("child " + childs.get(i));
                if (childs.get(i).equals(result)) {

                    IZkDataListener listener = new IZkDataListener() {

                        @Override
                        public void handleDataChange(String dataPath, Object data) throws Exception {

                        }

                        @Override
                        public void handleDataDeleted(String dataPath) throws Exception {
                            latch.countDown();
                        }

                    };
                    try {
                        zk.subscribeDataChanges(childs.get(i - 1), listener);
                        latch.await(); // 堵塞
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        zk.unsubscribeDataChanges(childs.get(i - 1), listener);
                    }
                }
            }
        }

        // 节点是否最小节点-- 是成功 否监听次小节点，再次取锁
        return true;
    }

    @Override
    public void unlock(String key, String value) {
        // zk连接
        // 删除临时节点
        zk.delete(empString);
    }

}