/**
 * @ Author zhangsf
 * @CreateTime 2019/4/11 - 8:40 PM
 */
package com.ifeng.zhangsf.mongodb;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文档测试拓展
 */
public class MongdbDucument {

    /**
     * 有序操作 ————
     *
     * @param databaseName
     * @param collectionName
     */
    public static void sequentialOperation(String databaseName, String collectionName) {
        if (databaseName != null && !"".equals(databaseName) && collectionName != null && !"".equals(collectionName)) {
            /** MongoClient(String host, int port)：直接指定 MongoDB IP 与端口进行连接
             * 实际应用中 MongoDB 地址应该配置在配置文件中*/
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

            /**getDatabase(String databaseName)：获取指定的数据库
             * 如果此数据库不存在，则会自动创建，此时存在内存中，服务器不会存在真实的数据库文件，show dbs 命令 看不到
             * 如果再往其中添加数据，服务器则会生成数据库文件，磁盘中会真实存在，show dbs 命令 可以看到
             * */
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);

            /**获取数据库中的集合
             * 如果集合不存在，则会隐式创建，此时在内存中，MongoDB 客户端 show tables 看不到
             * 如果继续往集合插入值，则会真实写入磁盘中，show tables 会有值*/
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);

            Long documentSize = mongoCollection.countDocuments();
            System.out.println("集合中文档总数为：" + documentSize);

            /**InsertOneModel(T document)*/
            Document insertDocument = new Document("name", "insertModel");
            insertDocument.append("time", new Date().getTime());
            InsertOneModel<Document> insertOneModel = new InsertOneModel<Document>(insertDocument);

            /**UpdateOneModel(Bson filter, Bson update)*/
            Document updateDocument = new Document();
            updateDocument.append("$set", new Document("name", "updateOneModel").append("time", new Date().getTime()));
            Bson updateBson = Filters.eq("age", 2);
            UpdateOneModel<Document> updateOneModel = new UpdateOneModel<Document>(updateBson, updateDocument);

            /**DeleteOneModel(Bson filter)
             * 同理还有 DeleteManyModel<T>
             */
            Bson delBson = Filters.eq("age", 3);
            DeleteOneModel<Document> deleteOneModel = new DeleteOneModel<Document>(delBson);

            /**ReplaceOneModel(Bson filter, T replacement)
             * 同理还有 UpdateManyModel<T> extends WriteModel<T> */
            Bson repBson = Filters.eq("age", 4);
            Document replaceDocument = new Document("name", "replaceOneModel");
            replaceDocument.put("time", new Date().getTime());
            ReplaceOneModel<Document> replaceOneModel = new ReplaceOneModel<Document>(repBson, replaceDocument);

            List<WriteModel<Document>> writeModelList = new ArrayList<WriteModel<Document>>();
            writeModelList.add(insertOneModel);
            writeModelList.add(updateOneModel);
            writeModelList.add(deleteOneModel);
            writeModelList.add(replaceOneModel);

            /**bulkWrite(List<? extends WriteModel<? extends TDocument>> var1)：有序操作
             * 有一个重载的方法：bulkWrite(List<? extends WriteModel<? extends TDocument>> var1, BulkWriteOptions var2)
             * 用于设置是有序操作还是无序操作：
             * mongoCollection.bulkWrite(writeModelList, new BulkWriteOptions().ordered(false));
             * false 表示无序操作，true 表示有序操作
             * */
            mongoCollection.bulkWrite(writeModelList);
            mongoClient.close();
        }
    }

    public static void main(String[] args) {
        sequentialOperation("test", "s");
    }
}
