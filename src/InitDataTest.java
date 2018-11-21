import static org.junit.Assert.*;

import org.junit.Test;

import redis.clients.jedis.Jedis;

//初始化秒杀商品信息和库存,并写入数据库
public class InitDataTest {

	@Test
	public void test() {
		Jedis jedis = new Jedis("192.168.192.128", 6379);
		System.out.println(jedis.ping());

		String productKey = "sk:" + 1001 + ":product";// 设置商品key
		String userKey = "sk:" + 1001 + "：user";// 设置用户key

		jedis.set(productKey, "500");// 将商品库存写入数据库

		jedis.del(userKey);//将上一次秒杀成功的用户名单删除

		String product_num = jedis.get(productKey);

		System.out.println(product_num);

		jedis.close();
	}

	@Test
	public void test1() {

		Jedis jedis = new Jedis("192.168.192.128", 6379);

		System.out.println(jedis.ping());

		String qtkey = "sk:" + 1001 + ":qt";
		String usersKey = "sk:" + 1001 + ":usr";

		jedis.set(qtkey, "300");

		jedis.del(usersKey);

		String string = jedis.get(qtkey);

		System.out.println(string);

		jedis.close();
	}
}
