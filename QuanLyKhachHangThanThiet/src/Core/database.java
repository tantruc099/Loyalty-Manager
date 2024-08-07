package Core;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import Models.Modelcateg;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class database {

   private static final String CONNECTION_STRING = "mongodb://localhost:27017"; // Địa chỉ MongoDB
    private static final String DATABASE_NAME = "KhachHang"; // Tên cơ sở dữ liệu
    private static final String COLLECTION_NAME = "loaisanpham"; // Tên bộ sưu tập
    private static final String PRODUCT_COLLECTION_NAME = "sanpham";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public database() {
        // Khởi tạo kết nối tới MongoDB
        mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    public void addCategory(String maLoaiSanPham, String ten, String moTa) {
        Document newCategory = new Document("_id", maLoaiSanPham)
                                    .append("ma_loai_san_pham", maLoaiSanPham)
                                    .append("ten", ten)
                                    .append("mo_ta", moTa);
        collection.insertOne(newCategory);
    }

    public void deleteCategory(String maLoaiSanPham) {
        collection.deleteOne(Filters.eq("ma_loai_san_pham", maLoaiSanPham));
    }

    public List<Document> getAllProducts() {
        List<Document> products = new ArrayList<>();
        MongoCollection<Document> productCollection = database.getCollection(PRODUCT_COLLECTION_NAME);
        for (Document doc : productCollection.find()) {
            products.add(doc);
        }
        return products;
    }
  public List<Document> getAllCategories() {
        List<Document> categories = new ArrayList<>();
        for (Document doc : collection.find()) {
            categories.add(doc);
        }
        return categories;
    }

    public List<String> getCategoryNames() {
        List<String> categories = new ArrayList<>();
        for (Document doc : collection.find()) {
            String categoryName = doc.getString("ten");
            categories.add(categoryName);
        }
        return categories;
    }

    public List<Document> getProductsByCategory(String categoryName) {
        List<Document> products = new ArrayList<>();
        MongoCollection<Document> productCollection = database.getCollection(PRODUCT_COLLECTION_NAME);

        // Tìm kiếm mã loại sản phẩm từ tên loại sản phẩm
        Document categoryDoc = collection.find(Filters.eq("ten", categoryName)).first();
        if (categoryDoc != null) {
            String maLoaiSanPham = categoryDoc.getString("ma_loai_san_pham");
            for (Document doc : productCollection.find(Filters.eq("ma_loai_san_pham", maLoaiSanPham))) {
                products.add(doc);
            }
        }
        return products;
    }
    public void addSanPham(Modelcateg sanPham) {
        MongoCollection<Document> collection = database.getCollection("sanpham");
        Document document = new Document("ma_san_pham", sanPham.getMaSanPham())
                .append("ten", sanPham.getTen())
                .append("mo_ta", sanPham.getMoTa())
                .append("gia", sanPham.getGia())
                .append("ma_loai_san_pham", sanPham.getMaLoaiSanPham())
                .append("ton_kho", sanPham.getTonKho());
        collection.insertOne(document);
    }
  public void deleteSanPham(String id) {
        MongoCollection<Document> collection = database.getCollection("sanpham");
        Document query = new Document("ma_san_pham", new ObjectId(id));
        collection.deleteOne(query);
    }
  public void updateSanPham(Modelcateg sanPham) {
    Document query = new Document("ma_san_pham", sanPham.getMaSanPham());
    Document update = new Document("$set", new Document("ten", sanPham.getTen())
            .append("mo_ta", sanPham.getMoTa())
            .append("gia", sanPham.getGia())
            .append("ma_loai_san_pham", sanPham.getMaLoaiSanPham())
            .append("ton_kho", sanPham.getTonKho()));
    collection.updateOne(query, update);
}

    public void close() {
        // Đóng kết nối tới MongoDB
        mongoClient.close();
    }
}
