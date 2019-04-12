/**
 * @ Author zhangsf
 * @CreateTime 2019/4/10 - 4:51 PM
 */
package com.ifeng.zhangsf.redis;

import redis.clients.jedis.Jedis;

public class RedisJDBC {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("jedis链接成功！");
        System.out.println("查看服务是否在运行"+jedis.ping());
    }
}
