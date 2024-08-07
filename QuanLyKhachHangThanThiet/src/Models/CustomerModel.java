/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Core.ModelBase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Phu
 */
public class CustomerModel extends ModelBase{
    public void addKH(Document KH)
    {
        MongoCollection<Document> collection = GetCollection("khachhang");
        collection.insertOne(KH);
    }
    public void delKH(ObjectId idKH)
    {
        MongoCollection<Document> collection = GetCollection("khachhang");
        collection.deleteOne(Filters.eq("_id", idKH));
    }
    public void upKH(ObjectId idKH,Document updatedCustomer)
    {
        MongoCollection<Document> collection = GetCollection("khachhang");
        collection.updateOne(Filters.eq("_id", idKH), new Document("$set", updatedCustomer));
    }
}
