package com.viki.org.apache.curator.framework.recipies.cache;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/13 13:45
 */
public class TreeCacheExample {/*

    private static final String PATH = "/mq_monitor8";

    private static final String LEADER_PATH = "/leader";

    private static final String LOCK_PATH = "_lock";

    private static final String SUB_PATH = "/sub";

    private static final int aliveNodes = 4;

    private static volatile String THIS_PATH;

    public static void main(String[] args) throws Exception {
        final CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 3000, 10000, new ExponentialBackoffRetry(1000, 3));
        final TreeCache treeCache = new TreeCache(curatorFramework, PATH);
        Executor executor = Executors.newSingleThreadExecutor();
        final InterProcessMutex mutex = new InterProcessMutex(curatorFramework, PATH + LOCK_PATH);
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                if(event.getData() == null){
                    return;
                }
                final String path = event.getData().getPath();
                System.out.println("path\t"+path);
                mutex.acquire(10, TimeUnit.SECONDS);
                JSONObject data = client.getData().forPath(PATH) == null ? null : JSONObject.parseObject(new String(client.getData().forPath(PATH)));
                if(data == null){
                    data = new JSONObject();
                }
                if(data.getJSONObject("aliveNodes") == null){
                    data.put("aliveNodes", new JSONObject());
                }
                checkingAliveNodes(treeCache, data);
                try{
                    startup(client, data);
                     byte[] pathData = client.getData().forPath(PATH);
                    if(pathData == null || !data.toJSONString().equalsIgnoreCase(new String(pathData))){
                        client.setData().forPath(PATH, data.toJSONString().getBytes());
                    }
                }finally {
                    mutex.release();
                }
                System.out.println("data after remove:\t"+new String(client.getData().forPath(PATH)));
            }
        }, executor);
        treeCache.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String message, Throwable e) {
                e.printStackTrace();
            }
        });
        curatorFramework.start();
        Stat stat = curatorFramework.checkExists().creatingParentsIfNeeded().forPath(PATH);
        if(stat == null){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(PATH, null);
        }
        stat = curatorFramework.checkExists().creatingParentsIfNeeded().forPath(PATH+LEADER_PATH);
        if(stat == null){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(PATH+LEADER_PATH, null);
        }
        THIS_PATH = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(PATH + SUB_PATH, "starting".getBytes());
        treeCache.start();
        TimeUnit.MINUTES.sleep(10);
    }

    private static void startup(CuratorFramework client, JSONObject data) throws Exception {
        Stat stat = client.checkExists().creatingParentsIfNeeded().forPath(PATH);
        //2、一旦节点数超过指定数量X，则获取根节点下的配置data
        if(stat.getNumChildren() - 1 >= aliveNodes){
            System.out.println("超过活跃数，准备启动");
            //3、如果data中数量小于X，则获取一个分布式锁，启动当前进程
            boolean acquireLock = false;
            if(data.getJSONObject("aliveNodes").size() < aliveNodes){
                if(!data.getJSONObject("aliveNodes").keySet().contains(THIS_PATH)){
                    acquireLock = true;
                }
            }
            if(acquireLock){
                try{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            aliveRep(THIS_PATH);
                        }
                    }).start();
                    data.getJSONObject("aliveNodes").put(THIS_PATH, new AliveNodeInfo(THIS_PATH, "local"));
                    System.out.println("data\t"+data.toJSONString());
                    client.setData().forPath(THIS_PATH, "alive".getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void checkingAliveNodes(TreeCache treeCache, JSONObject data){
        Map<String, ChildData> currentChildren = treeCache.getCurrentChildren(PATH);
        Iterator<String> aliveNodes = data.getJSONObject("aliveNodes").keySet().iterator();
        String aliveNodeName;
        while (aliveNodes.hasNext()){
            aliveNodeName = aliveNodes.next();
            if(currentChildren.get(aliveNodeName.replaceAll("\\/.*\\/", "")) == null){
                aliveNodes.remove();
            }
        }
    }

    private static void aliveRep(String path){
        while (true){
            System.out.println(path + " heat beat...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/
}
