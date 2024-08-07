/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Core.ModelBase;
import Models.ChartModel;
import Models.Statistics;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author AnhNhi
 */
public class StatsticService extends ModelBase {

    public Statistics GetData() {
        Statistics statistics = new Statistics();
        MongoCollection<Document> customerCollection = this.GetCollection("khachhang");
        MongoCollection<Document> orderCollection = this.GetCollection("hoadon");
        Document maxPoint = customerCollection.find(new Document()).sort(new Document("tichluy.TongDiemtichluy", -1)).first();
        if (maxPoint != null) {
            statistics.setMaxPoints(maxPoint.getEmbedded(Arrays.asList("tichluy", "TongDiemtichluy"), Integer.class));
        }
        long coutCustomer = customerCollection.countDocuments();
        statistics.setTotalLoyaltyCustomers((int) coutCustomer);
        Document groupStage = new Document("$group", new Document("_id", null)
                .append("totalAmount", new Document("$sum", "$tong_tien")));

        MongoCursor<Document> totalAmountCursor = orderCollection.aggregate(Arrays.asList(groupStage)).iterator();
        if (totalAmountCursor.hasNext()) {
            Document result = totalAmountCursor.next();
            double totalAmount = result.getDouble("totalAmount");
            statistics.setTotalRevenue(totalAmount);
        }
        return statistics;
    }

    public List<ChartModel> GetDataChart(String from, String to) {
        List<ChartModel> chartModels = new ArrayList<>();
        MongoCollection<Document> orderCollection = this.GetCollection("hoadon");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = sdf.parse(from);
            toDate = sdf.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
            return chartModels;
        }
         Document match = new Document("$match", new Document("ngay",
                new Document("$gte", fromDate)
                        .append("$lte", toDate)));

        Document group = new Document("$group",
                new Document("_id",
                        new Document("month", new Document("$month", "$ngay")))
                        .append("Total", new Document("$sum", "$tong_tien")));
        
        MongoCursor<Document> mongoCursor = orderCollection.aggregate(Arrays.asList(match, group)).iterator();
        while (mongoCursor.hasNext()) {
            
            Document doc = mongoCursor.next();
            ChartModel chartModel = new ChartModel();
            Document id = (Document) doc.get("_id");
            
            int month = id.getInteger("month");
            double total = doc.getDouble("Total");
            
            chartModel.setMonth(month);
            chartModel.setTotal(total);
            
            chartModels.add(chartModel);
        }
        return chartModels;

    }
}
