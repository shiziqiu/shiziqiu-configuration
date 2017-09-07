package com.shiziqiu.configuration.core.zk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shiziqiu.configuration.util.Configure;
/**
 * @title : ShiZiQiuZkConfClient
 * @author : crazy
 * @date : 2017年9月6日 下午5:29:33
 * @Fun :
 */
public class ShiZiQiuZkConfClient implements Watcher{

	private static Logger logger = LoggerFactory.getLogger(ShiZiQiuZkConfClient.class);
	
	private static ZooKeeper zooKeeper;
	
	/**
	 * 常用的lock ，tryLock ，其中有个lockInterruptibly 。
	 */
	private static ReentrantLock INSTANCE_INIT_LOCK = new ReentrantLock(true);
	
	private static ZooKeeper getInstance() {
		if (null == zooKeeper) {
			try {
				/**
				 * 如果 lock 不可用，则以下代码将在 2秒后超时：
				 */
				if (INSTANCE_INIT_LOCK.tryLock(2, TimeUnit.SECONDS)) {
					try {
						zooKeeper = new ZooKeeper(Configure.ZK_ADDRESS, 20000, new Watcher() {

							@Override
							public void process(WatchedEvent event) {
								logger.info(">>>>>>>>>> shiziqiu-conf: watcher:{}", event);
								try {
									/**
									 * KeeperState.Expired
									 * 客户端和服务器在ticktime的时间周期内，是要发送心跳通知的。这是租约协议的一个实现。
									 * 客户端发送request，告诉服务器其上一个租约时间，服务器收到这个请求后，
									 * 告诉客户端其下一个租约时间是哪个时间点。当客户端时间戳达到最后一个租约时间，
									 * 而没有收到服务器发来的任何新租约时间，即认为自己下线（此后客户端会废弃这次连接，
									 * 并试图重新建立连接）。这个过期状态就是Expired状态
									 */
									if(event.getState() == Event.KeeperState.Expired) {
										zooKeeper.close();
										zooKeeper = null;
										getInstance();
									}
									String path = event.getPath();
									String key = pathToKey(path);
									if(null != key) {
										//判断该节点是否存在
										zooKeeper.exists(path, true);
										if (event.getType() == Event.EventType.NodeDeleted) {
											ShiZiQiuConfClient.remove(key);
										} else if (event.getType() == Event.EventType.NodeDataChanged) {
											String data = getPathDataByKey(key);
											ShiZiQiuConfClient.update(key, data);
										}
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						ShiZiQiuZkConfClient.createWithParent(Configure.CONF_DATA_PATH);
					} finally {
						INSTANCE_INIT_LOCK.unlock();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null == zooKeeper) {
			throw new NullPointerException(">>>>>>>>>>> shiziqiu-cache, zooKeeper is null.");
		}
		return zooKeeper;
	}
	
	/**
	 * @param path
	 * @return
	 */
	private static Stat createWithParent(String path){
		if (null == path || path.trim().length()==0) {
			return null;
		}
		
		try {
			Stat stat = getInstance().exists(path, true);
			if(null == stat) {
				if(path.lastIndexOf("/") > 0) {
					String parentPath = path.substring(0,path.lastIndexOf("/"));
					Stat parentStat = getInstance().exists(parentPath, true);
					if (null == parentStat) {
						createWithParent(parentPath);
					}
				}
				//添加节点
				zooKeeper.create(path, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			return getInstance().exists(path, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param nodePath
	 * @return
	 */
	private static String pathToKey(String nodePath){
		if (null == nodePath || nodePath.length() <= Configure.CONF_DATA_PATH.length() || !nodePath.startsWith(Configure.CONF_DATA_PATH)) {
			return null;
		}
		return nodePath.substring(Configure.CONF_DATA_PATH.length()+1, nodePath.length());
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getPathDataByKey(String key){
		String path = keyToPath(key);
		try {
			Stat stat = getInstance().exists(path, true);
			if (stat != null) {
				String znodeValue = null;
				byte[] resultData = getInstance().getData(path, true, null);
				if (resultData != null) {
					znodeValue = new String(resultData);
				}
				return znodeValue;
			} else {
				logger.info(">>>>>>>>>> znodeKey[{}] not found.", key);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String keyToPath(String nodeKey){
		return Configure.CONF_DATA_PATH + "/" + nodeKey;
	}
	
	public static String generateGroupKey(String nodeGroup, String nodeKey){
		return nodeGroup + "." + nodeKey;
	}
	
	/**
	 * @param key
	 */
	public static void deletePathByKey(String key){
		String path = keyToPath(key);
		try {
			Stat stat = getInstance().exists(path, true);
			if (stat != null) {
				getInstance().delete(path, stat.getVersion());
			} else {
				logger.info("=========== zookeeper node path not found :{}", key);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param key
	 * @param data
	 * @return
	 */
	public static Stat setPathDataByKey(String key, String data) {
		String path = keyToPath(key);
		try {
			Stat stat = getInstance().exists(path, true);
			if (stat == null) {
				createWithParent(path);
				stat = getInstance().exists(path, true);
			}
			return zooKeeper.setData(path, data.getBytes(),stat.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
