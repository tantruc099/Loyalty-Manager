/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import Core.database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Models.Modelcateg;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author MyPC
 */
public class Form_SanPham extends javax.swing.JPanel {

    private database db;
    private DefaultTableModel TableModel;
    private DefaultTableModel tableModel2;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public Form_SanPham() {
        initComponents();
        connectToMongoDB();
        db = new database();
        initializeTableModels();
        loadCategoryData();
        loadDataFromMongoDB();
    }

    private void loadCategoryData() {
        try {
            List<Document> categories = db.getAllCategories();
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong JTable
            cbb_mlasp.removeAllItems();
            for (Document doc : categories) {
                String maLoaiSanPham = doc.getString("ma_loai_san_pham");
                String tenSanPham = doc.getString("ten");
                String moTa = doc.getString("mo_ta");
                model.addRow(new Object[]{maLoaiSanPham, tenSanPham, moTa});
                cbb_mlasp.addItem(maLoaiSanPham);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeTableModels() {

        String[] columnNames = {"Mã Loai Sản Phẩm", "Tên Loại Sản Phẩm", "Mô Tả"};
        TableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(TableModel);

        String[] columnNames2 = {"Mã Sản Phẩm", "Tên", "Mô Tả", "Giá", "Mã Loại Sản Phẩm", "Tồn Kho"};
        tableModel2 = new DefaultTableModel(columnNames2, 0);
        jTable2.setModel(tableModel2);

    }

    private void connectToMongoDB() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("KhachHang");
        collection = database.getCollection("loaisanpham");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_maloaisanpham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tensp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_mota = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtxoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtgia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbb_mlasp = new javax.swing.JComboBox<>();
        txtsoluong = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmasanpham = new javax.swing.JTextField();
        txttensanpham = new javax.swing.JTextField();
        txtmota = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setText("Mã Loại Sản Phẩm");

        jLabel3.setText("Tên Loại Sản Phẩm");

        jLabel4.setText("Mô Tả");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtxoa.setText("Xóa");
        txtxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtxoaActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel9.setText("Giá");

        jLabel10.setText("Mã Loai Sản Phẩm");

        cbb_mlasp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Số lượng");

        jLabel12.setText("Mô Tả");

        jLabel7.setText("Tên Sản Phẩm");

        jLabel6.setText("Mã Sản Phẩm");

        btnadd.setText("Thêm");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btndelete.setText("Xóa");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_maloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_mota, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                        .addComponent(txt_tensp))
                                    .addComponent(jButton1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtmota, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(txtmasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbb_mlasp, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(txtxoa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(69, 69, 69)
                        .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(btnadd)
                .addGap(143, 143, 143)
                .addComponent(btndelete)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_maloaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_mota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtxoa)
                    .addComponent(jButton1))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(txtmota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbb_mlasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btndelete))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel5.setText("Danh Mục Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)))
                .addContainerGap(1525, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(701, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtxoaActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String maLoaiSanPham = (String) jTable1.getValueAt(selectedRow, 0);

            // Xóa tài liệu khỏi cơ sở dữ liệu
            try {
                collection.deleteOne(new Document("ma_loai_san_pham", maLoaiSanPham));
                JOptionPane.showMessageDialog(this, "Danh mục sản phẩm đã được xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Xóa dòng khỏi JTable
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(selectedRow);

                // Cập nhật dữ liệu trong JTable
                loadDataFromMongoDB();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa danh mục sản phẩm: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtxoaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String maLoaiSanPham = txt_maloaisanpham.getText().trim();
        String tenSanPham = txt_tensp.getText().trim();
        String moTa = txt_mota.getText().trim();

        // Kiểm tra dữ liệu đầu vào
        if (maLoaiSanPham.isEmpty() || tenSanPham.isEmpty() || moTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Document document = new Document("ma_loai_san_pham", maLoaiSanPham)
                .append("ten", tenSanPham)
                .append("mo_ta", moTa);

        // Thêm tài liệu vào cơ sở dữ liệu
        try {
            collection.insertOne(document);
            JOptionPane.showMessageDialog(this, "Danh mục sản phẩm đã được thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Xóa các trường văn bản sau khi thêm thành công
            txt_maloaisanpham.setText("");
            txt_tensp.setText("");
            txt_mota.setText("");
            loadDataFromMongoDB();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm danh mục sản phẩm: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        String maSanPham = txtmasanpham.getText().trim();
        String ten = txttensanpham.getText().trim();
        String moTa = txtmota.getText().trim();
        double gia = Double.parseDouble(txtgia.getText().trim());
        String maLoaiSanPham = cbb_mlasp.getSelectedItem().toString();
        int tonKho = Integer.parseInt(txtsoluong.getText().trim());

        if (maSanPham.isEmpty() || ten.isEmpty() || moTa.isEmpty() || gia <= 0 || maLoaiSanPham.isEmpty() || tonKho <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Modelcateg sanPham = new Modelcateg(null, maSanPham, ten, moTa, gia, maLoaiSanPham, tonKho);
        db.addSanPham(sanPham);
        JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadDataFromMongoDB();

        // Clear text fields after adding
        txtmasanpham.setText("");
        txttensanpham.setText("");
        txtmota.setText("");
        txtgia.setText("");
        txtsoluong.setText("");
    }//GEN-LAST:event_btnaddActionPerformed
    private void deleteProduct(String maSanPham) {
        try {
            // Kết nối tới collection sản phẩm
            MongoCollection<Document> productCollection = database.getCollection("sanpham");

            // Xóa sản phẩm theo mã sản phẩm
            productCollection.deleteOne(new Document("ma_san_pham", maSanPham));

            // Cập nhật bảng dữ liệu
            loadDataFromMongoDB();

            JOptionPane.showMessageDialog(this, "Sản phẩm đã được xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow >= 0) {
            String maSanPham = (String) jTable2.getValueAt(selectedRow, 0); // Lấy mã sản phẩm từ hàng đã chọn

            // Xóa sản phẩm theo mã sản phẩm
            deleteProduct(maSanPham);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
//    int selectedRow = jTable2.getSelectedRow();
//    if (selectedRow >= 0) {
//        txtmasanpham.setText(jTable2.getValueAt(selectedRow, 0).toString());
//        txttensanpham.setText(jTable2.getValueAt(selectedRow, 1).toString());
//        txtmota.setText(jTable2.getValueAt(selectedRow, 2).toString());
//        txtgia.setText(jTable2.getValueAt(selectedRow, 3).toString());
//        cbb_mlasp.setSelectedItem(jTable2.getValueAt(selectedRow, 4).toString());
//        txtsoluong.setText(jTable2.getValueAt(selectedRow, 5).toString());
    }//GEN-LAST:event_jTable2MouseClicked
    private void loadDataFromMongoDB() {
        try {
            List<Document> products = db.getAllProducts();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            for (Document doc : products) {
                String maSP = doc.getString("ma_san_pham");
                String tenSP = doc.getString("ten");
                String moTa = doc.getString("mo_ta");
                double gia = doc.getDouble("gia");
                String maLoaiSP = doc.getString("ma_loai_san_pham");
                int tonKho = doc.getInteger("ton_kho");
                model.addRow(new Object[]{maSP, tenSP, moTa, gia, maLoaiSP, tonKho});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
//        try {
//
//            List<Document> documents = collection.find().into(new ArrayList<>());
//
//            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
//            model.setRowCount(0);
//
//            for (Document doc : documents) {
//                String maLoaiSanPham = doc.getString("ma_loai_san_pham");
//                String tenSanPham = doc.getString("ten");
//                String moTa = doc.getString("mo_ta");
//
//                model.addRow(new Object[]{maLoaiSanPham, tenSanPham, moTa});
//            }
//            MongoCollection<Document> collection2 = database.getCollection("sanpham");
//            List<Document> documents2 = collection2.find().into(new ArrayList<>());
//
//            DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
//            model2.setRowCount(0);
//
//            // Thêm dữ liệu vào jTable2
//            for (Document doc : documents2) {
//
//                String maSanPham = doc.getString("ma_san_pham");
//                String ten = doc.getString("ten");
//                String moTa = doc.getString("mo_ta");
//                double gia = doc.getDouble("gia");
//                String maLoaiSanPham = doc.getString("ma_loai_san_pham");
//                int tonKho = doc.getInteger("ton_kho");
//
//                model2.addRow(new Object[]{maSanPham, ten, moTa, gia, maLoaiSanPham, tonKho});
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public static void main(String[] args) {
        // Sử dụng SwingUtilities để đảm bảo giao diện người dùng được tạo trong Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Tạo khung chính của ứng dụng
                JFrame frame = new JFrame("Quản Lý Sản Phẩm");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Tạo và thêm Form_SanPham vào khung chính
                Form_SanPham formSanPham = new Form_SanPham();
                frame.add(formSanPham);

                // Thiết lập kích thước và hiển thị khung
                frame.pack();
                frame.setLocationRelativeTo(null); // Đặt khung ở giữa màn hình
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JComboBox<String> cbb_mlasp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_maloaisanpham;
    private javax.swing.JTextField txt_mota;
    private javax.swing.JTextField txt_tensp;
    private javax.swing.JTextField txtgia;
    private javax.swing.JTextField txtmasanpham;
    private javax.swing.JTextField txtmota;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensanpham;
    private javax.swing.JButton txtxoa;
    // End of variables declaration//GEN-END:variables
}
