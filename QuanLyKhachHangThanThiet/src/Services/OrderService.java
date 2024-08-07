/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Core.ModelBase;
import Models.BillingDetail;
import Models.OrderDetailModel;
import Models.OrderModel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class OrderService extends ModelBase {

    public List<OrderModel> GetOrders(String phoneNumber) {
        List<OrderModel> listOrder = new ArrayList<>();
        Document query;
        if (phoneNumber.isEmpty()) {
            query = new Document();
        } else {
            query = new Document("sodienthoai", phoneNumber);
        }
        MongoCursor<Document> cursor = this.GetCollection("hoadon").
                find(query).iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            OrderModel model = new OrderModel();
            model.setOrderID(doc.getString("so_hoa_don"));
            model.setPhoneNumber(doc.getString("sodienthoai"));
            Date createAtDate = doc.getDate("ngay");
            if (createAtDate != null) {
                LocalDateTime createAt = createAtDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                model.setCreateAt(createAt);
            }
            Double totalDouble = doc.getDouble("tong_tien");
            if (totalDouble != null) {
                BigDecimal total = BigDecimal.valueOf(totalDouble);
                model.setTotal(total);
            }
            listOrder.add(model);
        }
        return listOrder;
    }

    public List<OrderDetailModel> GetProductOrder(String OrderId) {
        List<OrderDetailModel> detailModels = new ArrayList<>();
        MongoCollection<Document> orderCollection = this.GetCollection("hoadon");
        MongoCollection<Document> productCollection = this.GetCollection("sanpham");
        Document docOrder = orderCollection.find(new Document("so_hoa_don", OrderId)).first();
        List<Document> orderDetails = (List<Document>) docOrder.get("san_pham");
        orderDetails.forEach((Document detail) -> {
            String productId = detail.getString("ma_san_pham");
            Document product = productCollection.find(new Document("ma_san_pham", productId)).first();
            if (product != null) {
                OrderDetailModel detailModel = new OrderDetailModel();
                detailModel.setProductName(product.getString("ten"));
                detailModel.setPrice(product.getDouble("gia"));
                detailModel.setQuantity(detail.getInteger("so_luong"));
                detailModel.setTotalAmount(detail.getDouble("ThanhTien"));
                detailModels.add(detailModel);
            }
        });
        return detailModels;
    }

    public BillingDetail GetBillingDetailOrder(String orderId) {
        MongoCollection<Document> orderCollection = this.GetCollection("hoadon");
        Document docOrder = orderCollection.find(new Document("so_hoa_don", orderId)).first();
        BillingDetail billingDetail = new BillingDetail();
        if (docOrder != null) {
            billingDetail.setTotalAmount(docOrder.getDouble("tong_tien"));
            billingDetail.setDiscount(docOrder.getDouble("giam_gia"));
            billingDetail.setAmountToPay(docOrder.getDouble("so_tien_tra"));
        }
        return billingDetail;
    }
}
