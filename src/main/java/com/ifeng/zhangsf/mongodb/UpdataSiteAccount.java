package com.ifeng.zhangsf.mongodb;

import com.ifeng.zhangsf.Model.SpiderAccount;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class UpdataSiteAccount {
    public static void main(String[] args){
        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("zhangsf");
        MongoCollection<Document> collection  = mongoDatabase.getCollection("spider_account");
        //查询过程
        BasicDBObject query = new BasicDBObject();
        //查询出ifengId为6521990058830991589
        query.put("ifengId","6521990058830991589");

        //查询结果
        MongoCursor<Document> cursor = collection.find().skip(0).limit(10).iterator();
//        MongoCursor<Document> cursor = collection.find(query).skip(0).iterator();

        while (cursor.hasNext()) {
//            JSONObject jsonObj=JSONObject.fromObject(cursor.next());
//            SpiderAccount spiderAccount=(SpiderAccount) JSONObject.toBean(jsonObj,SpiderAccount.class);
//            System.out.println(spiderAccount.getSiteAccount());
            String  jsonString=null;
            jsonString = cursor.next().toJson();
            int length = jsonString.length();
            jsonString = "{" + jsonString.substring(jsonString.indexOf(",") + 1, length) + "";
            JSONObject jsonStr = JSONObject.fromObject(jsonString);

            String siteAccount=jsonStr.getString("siteAccount");

            String[] arr = siteAccount.split("_");
            String site=arr[0];
            String account=arr[1];

        }
        cursor.close();


        System.out.println("MongoDB connect successfully: "+"mongoDatabase = " + mongoDatabase.getName());
    }
}