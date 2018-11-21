import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池配置文件
 * @author psy07
 *
 */
public class JedisPoolUtils {
	private static volatile JedisPool jedisPool= null;
	
	private JedisPoolUtils() {
		
	}
	
	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtils.class) {
				if (jedisPool == null) {
					
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxTotal(200);//最大连接数
					poolConfig.setMaxIdle(150);//最大空闲连接数
					//获取连接时的最大等待毫秒数。如果超时就抛异常，默认-1
					poolConfig.setMaxWaitMillis(100*1000);
					//连接耗尽时是否阻塞。false报异常，true：阻塞直到超时。默认为true
					poolConfig.setBlockWhenExhausted(true);
					//在获取连接时检查有效性，默认为false
					poolConfig.setTestOnBorrow(true);
					
					//redis.clients.jedis.JedisPool.JedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout)
					jedisPool = new JedisPool(poolConfig,"192.168.192.128",6379,100000);
				}
			}
		}
		return jedisPool;
	}
}
