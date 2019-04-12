/**
 * @ Author zhangsf
 * @CreateTime 2019/4/10 - 4:42 PM
 */
package com.ifeng.zhangsf.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase =mongoClient.getDatabase("zhang_test");
        System.out.println("Connect to database successfully");
    }
}
