/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class ModelBase {
    private final String driver = "mongodb://localhost:27017";
    private final String databaseName = "KhachHang";
    MongoDatabase database;
    MongoCollection<Document> db;
    public ModelBase(){
        this.Connection();
    }
    
    private void Connection(){
       var mongoClient = MongoClients.create(this.driver);
       database =  mongoClient.getDatabase(this.databaseName);
    }
    public MongoCollection<Document> GetCollection(String collection){
        db = database.getCollection(collection);
        return db;
    }
}
