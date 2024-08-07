/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Core.ModelBase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class TestModel extends ModelBase{
    public void GetData(){
    MongoCursor<Document> cursor = this.GetCollection("SV").find().iterator();
    while (cursor.hasNext()) {            
        Document doc = cursor.next();
        System.out.println(doc.toJson());
        }  
    }
}
