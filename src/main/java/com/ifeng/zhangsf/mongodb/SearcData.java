//package com.ifeng.zhangsf.mongodb;
//
//import com.mongodb.*;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.util.JSON;
//import org.bson.Document;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//public class SearcData {
//    public static void main(String[] args){
//        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
//        //ServerAddress()两个参数分别为 服务器地址 和 端口
//        ServerAddress serverAddress = new ServerAddress(" ",27017);
//        List<ServerAddress> addrs = new ArrayList<>();
//        addrs.add(serverAddress);
//
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential credential = MongoCredential.createScramSha1Credential
//                ("", "", "".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<>();
//        credentials.add(credential);
//
//        //通过连接认证获取MongoDB连接
//        MongoClient mongoClient = new MongoClient(addrs,credentials);
//
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("spider");
//        MongoCollection<Document> collection  = mongoDatabase.getCollection("spider_account");
//
//        //查询过程
//        BasicDBObject query = new BasicDBObject();
//        //查询出ifengId为6521990058830991589
//        query.put("ifengId","6521990058830991589");
//
//        //查询结果
//        MongoCursor<Document> cursor = collection.find().skip(1420).limit(10).iterator();
////        MongoCursor<Document> cursor = collection.find(query).skip(0).iterator();
//
//
//        while (cursor.hasNext()) {
//            String  jsonString = new String();
//            jsonString = cursor.next().toJson();
//            System.out.println("cursor:"+jsonString);
//            int length = jsonString.length();
//            jsonString = "{" + jsonString.substring(jsonString.indexOf(",") + 1, length) + "";
//            System.out.println(jsonString);
//
//
////            MongoClient mongoClient2 = new MongoClient("localhost", 27017);
////
////            MongoDatabase database = mongoClient2.getDatabase("zhangsf");
////
////            MongoCollection<Document> collection2 = database.getCollection("spider_account");
////
////            Document document2 = Document.parse(jsonString);
////            collection2.insertOne(document2);
//        }
//        cursor.close();
//
//
//        System.out.println("MongoDB connect successfully: "+"mongoDatabase = " + mongoDatabase.getName());
//    }
//}