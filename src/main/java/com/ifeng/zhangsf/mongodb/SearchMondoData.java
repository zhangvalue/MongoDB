//package com.ifeng.zhangsf.mongodb;
//
//import com.mongodb.*;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import org.json.*;
//import java.io.*;
//import java.util.*;
//
//public class SearchMondoData {
//    public static void main(String[] args){
//        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
//        //ServerAddress()两个参数分别为 服务器地址 和 端口
//        ServerAddress serverAddress = new ServerAddress(" 1",27017);
//        List<ServerAddress> addrs = new ArrayList<>();
//        addrs.add(serverAddress);
//
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential credential = MongoCredential.createScramSha1Credential("", "", "".toCharArray());
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
//        MongoCursor<Document> cursor = collection.find().skip(0).limit(100).iterator();
////        MongoCursor<Document> cursor = collection.find(query).skip(0).iterator();
//
//
//        List<String> resultList = new LinkedList<>();
//        List<String> tableList = new ArrayList<>();
//        while (cursor.hasNext()) {
//            String  jsonString = new String();
//            jsonString = cursor.next().toJson();
//            int length = jsonString.length();
//            jsonString = "[{" + jsonString.substring(jsonString.indexOf(",") + 1, length) + "]";
//            System.out.println(jsonString);
//
//            JSONArray jsonArray = new JSONArray(jsonString);
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            try {
//                if(tableList.size() == 0) {
//                    StringBuilder stringKey = new StringBuilder();
//                    Iterator iterator = jsonObject.keys();
//                    while (iterator.hasNext()) {
//                        String key = (String) iterator.next();
//                        if(key.compareTo("ifengId") == 0){continue;}
//                        tableList.add(key);
//                        stringKey.append(key).append(',');
//                    }
//                    resultList.add(stringKey.deleteCharAt(stringKey.length()-1).toString());
//                }
//                StringBuilder stringValue = new StringBuilder();
//                for(String entry: tableList){
//                    String value = new String();
//                    if(!jsonObject.has(entry)){
//                        value = "null";
//                    }
//                    else {
//                        value = jsonObject.get(entry).toString();
//                    }
//                    stringValue.append(value).append(',');
//                }
//                resultList.add(stringValue.deleteCharAt(stringValue.length()-1).toString());
//            }
//            catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//        cursor.close();
//
//        try {
//            File csv = new File("/Users/zhangsf/data/ifeng/tem.csv");
//            OutputStreamWriter outStream = null;
//            outStream = new OutputStreamWriter(new FileOutputStream(csv), "utf-8");
//            BufferedWriter bw = new BufferedWriter(outStream);
//            for(String entry : resultList){
//                // 添加新的数据行
//                bw.write(entry.toCharArray());
//                bw.newLine();
//            }
//            bw.close();
//        }
//        catch (FileNotFoundException e) {
//            // File对象的创建过程中的异常捕获
//            e.printStackTrace();
//        } catch (IOException e) {
//            // BufferedWriter在关闭对象捕捉异常
//            e.printStackTrace();
//        }
//        System.out.println("MongoDB connect successfully: "+"mongoDatabase = " + mongoDatabase.getName());
//    }
//}