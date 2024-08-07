/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Core.ModelBase;
import Models.CustomerModelData;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class CustomerService extends ModelBase {

    public CustomerModelData GetInfoCustomer(String phoneNumber) {
        CustomerModelData customer = new CustomerModelData();
        try (MongoCursor<Document> cursor = this.GetCollection("khachhang")
                .find(new Document("dien_thoai", phoneNumber)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                customer.setCustomerID(doc.getString("ma_khach_hang"));
                customer.setPhoneNumber(phoneNumber);
                customer.setCustomerName(doc.getString("ten"));
                Document tichluyDoc = (Document) doc.get("tichluy");
                if (tichluyDoc != null) {
                    Integer points = tichluyDoc.getInteger("TongDiemtichluy");
                    customer.setPoint(points != null ? points : 0); 
                } else {
                    customer.setPoint(0);
                }
            }
        }
        return customer;
    }
}
