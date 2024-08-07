/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.main;

import Core.ModelBase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author nguye
 */
public class Form_TaoHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form Form_HoaDon
     */
    private ModelBase modelBase;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public Form_TaoHoaDon() {
        initComponents();
        modelBase = new ModelBase();
        initTable();
        setupBarcodeScanner();
        initTable2();
        setTitle("Quản Lý Hóa Đơn");
        loadPendingInvoices();
        pack(); // Đặt kích thước theo nội dung
        setMinimumSize(getSize()); // Đặt kích thước tối thiểu
        setResizable(false); // Không cho phép thay đổi kích thước
        txtSumTL.setEnabled(false);
        txtSumTL.setFont(new Font("Arial", Font.BOLD, 15));
        txtRank.setEnabled(false);
        txtRank.setFont(new Font("Arial", Font.BOLD, 15));
        txtGiamGia.setEnabled(false);
        txtGiamGia.setFont(new Font("Arial", Font.BOLD, 15));
        txtTongTra.setEnabled(false);
        txtTongTra.setFont(new Font("Arial", Font.BOLD, 15));
        txtMa.setEnabled(false);
        txtMa.setFont(new Font("Arial", Font.BOLD, 15));
        txtTen.setEnabled(false);
        txtTen.setFont(new Font("Arial", Font.BOLD, 15));
        txtTen1.setEnabled(false);
        txtTen1.setFont(new Font("Arial", Font.BOLD, 15));
        txtTongTien.setEnabled(false);
        txtTongTien.setFont(new Font("Arial", Font.BOLD, 15));
        
    }

    private void setupBarcodeScanner() {
        txtMaVach.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String barcodeText = txtMaVach.getText().trim();
                    if (!barcodeText.isEmpty()) {
                        handleBarcodeScan(barcodeText);
                        txtMaVach.setText(""); // Xóa trường sau khi xử lý
                    }
                }
            }
        });
    }

    private void initTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnIdentifiers(new Object[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"});
        model.setRowCount(0); // Đảm bảo không có hàng trống mặc định
    }

    private void initTable2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setColumnIdentifiers(new Object[]{"Số Hóa Đơn", "Ngày", "Số Điện Thoại", "Tổng Tiền", "Trạng Thái"});
        model.setRowCount(0); // Đảm bảo không có hàng trống mặc định
    }

    private void handleBarcodeScan(String barcodeText) {
        String productId = barcodeText.trim();

        // Tìm sản phẩm trong cơ sở dữ liệu
        MongoCollection<Document> productCollection = modelBase.GetCollection("sanpham");
        Document query = new Document("ma_san_pham", productId);
        Document productDoc = productCollection.find(query).first();

        if (productDoc != null) {
            String name = productDoc.getString("ten");
            double unitPrice = productDoc.getDouble("gia");

            // Kiểm tra sản phẩm có trong bảng không
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int rowCount = model.getRowCount();
            boolean found = false;

            for (int i = 0; i < rowCount; i++) {
                Object existingProductIdObj = model.getValueAt(i, 0);
                if (existingProductIdObj != null && existingProductIdObj.equals(productId)) {
                    // Cập nhật số lượng và thành tiền
                    int currentQuantity = (int) model.getValueAt(i, 2);
                    int newQuantity = currentQuantity + 1; // Tăng số lượng
                    double totalPrice = newQuantity * unitPrice; // Cập nhật thành tiền
                    model.setValueAt(newQuantity, i, 2);
                    model.setValueAt(totalPrice, i, 4); // Đảm bảo cột thành tiền là cột số 4
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Nếu không tìm thấy sản phẩm trong bảng, thêm mới với số lượng 1
                double totalPrice = unitPrice; // Thành tiền ban đầu bằng đơn giá vì số lượng = 1
                model.addRow(new Object[]{productId, name, 1, unitPrice, totalPrice});
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã vạch: " + productId);
        }
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
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtSumTL = new javax.swing.JTextField();
        txtRank = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaVach = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnTao = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTen1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTongTra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnTim1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo Hóa Đơn"));

        jLabel3.setText("Số Điện Thoại");

        btnTim.setText("Tìm");
        btnTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimMouseClicked(evt);
            }
        });
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên Khách Hàng");

        jLabel4.setText("Tổng Điểm");

        jLabel5.setText("Rank");

        jLabel6.setText("Mã Sản Phẩm");

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

        btnTao.setText("Tạo");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel7.setText("Mã");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSumTL)
                                    .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRank, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTao)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSumTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTao)
                    .addComponent(btnXoa)
                    .addComponent(jLabel6)
                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh Toán"));

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

        jLabel2.setText("Tổng Tiền");

        jLabel8.setText("Khách Hàng");

        jLabel9.setText("Số Điện Thoại");

        jLabel10.setText("Giảm Giá");

        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });

        jLabel11.setText("Tổng Trả");

        jButton1.setText("Thanh Toán");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Khởi Tạo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnTim1.setText("Tìm");
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongTien))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtTongTra))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTongTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();

        // Check if a row is selected
        if (selectedRow >= 0) {
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sản phẩm này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Remove the selected row
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(selectedRow);
                updateTotalAmount(); // Update the total amount after removal
            }
        } else {
            // No row is selected
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        String invoiceNumber = generateInvoiceNumber();

        // Thu thập thông tin từ JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();

        // Kiểm tra xem JTable có dữ liệu không
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu trong bảng. Không thể tạo hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lấy mã khách hàng từ txtMa
        String customerPhone = txtSDT.getText().trim();

        // Kiểm tra sự tồn tại của khách hàng
        MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
        Document customerQuery = new Document("dien_thoai", customerPhone);
        Document customerDoc = customerCollection.find(customerQuery).first();

        if (customerDoc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng tìm khách hàng cần tạo hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Document> products = new ArrayList<>();
        double points = 0;

        for (int i = 0; i < rowCount; i++) {
            // Lấy giá trị từ JTable
            Object productIdObj = model.getValueAt(i, 0);
            Object quantityObj = model.getValueAt(i, 2);
            Object unitPriceObj = model.getValueAt(i, 3);
            Object totalPriceObj = model.getValueAt(i, 4);

            // Kiểm tra và xử lý giá trị null
            if (productIdObj == null || quantityObj == null || unitPriceObj == null || totalPriceObj == null) {
                JOptionPane.showMessageDialog(this, "Dữ liệu trong bảng không hợp lệ tại hàng " + i, "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String productId = (String) productIdObj;
                int quantity = (Integer) quantityObj;
                double unitPrice = (Double) unitPriceObj;
                double totalPrice = (Double) totalPriceObj;

                products.add(new Document("ma_san_pham", productId)
                        .append("so_luong", quantity)
                        .append("gia", unitPrice)
                        .append("ThanhTien", totalPrice));
                points += totalPrice;
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ tại hàng " + i, "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Lưu thông tin hóa đơn vào MongoDB
        MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");

        Document invoiceDoc = new Document("so_hoa_don", invoiceNumber)
                .append("ngay", new Date()) // Ngày hiện tại
                .append("sodienthoai", customerPhone) // Mã khách hàng từ txtMa
                .append("tong_tien", points)
                .append("san_pham", products)
                .append("trang_thai", "Chưa thanh toán");  // Trạng thái mới tạo

        try {
            invoiceCollection.insertOne(invoiceDoc);
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được tạo với số: " + invoiceNumber);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu hóa đơn vào cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        // Xóa form sau khi lưu hóa đơn
        clearForm();
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimMouseClicked
        // TODO add your handling code here:
        String soDienThoai = txtSDT.getText().trim();
        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
            return;
        }

        MongoCollection<Document> collection = modelBase.GetCollection("khachhang");
        Document query = new Document("dien_thoai", soDienThoai);
        MongoCursor<Document> cursor = collection.find(query).iterator();

        if (cursor.hasNext()) {
            Document doc = cursor.next();
            String ten = doc.getString("ten");
            String ma = doc.getString("ma_khach_hang");
            Document tichLuy = doc.get("tichluy", Document.class);
            int tongDiemTichLuy = tichLuy.getInteger("TongDiemtichluy", 0);
            String rank = tichLuy.getString("Rank");

            txtTen.setText(ten);
            txtMa.setText(ma);
            txtSumTL.setText(String.valueOf(tongDiemTichLuy));
            txtRank.setText(rank);

            JOptionPane.showMessageDialog(this, "Tìm thấy khách hàng: " + ten);
            txtMaVach.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại: " + soDienThoai);
            txtTen.setText("");
            txtMa.setText("");
            txtSumTL.setText("");
            txtRank.setText("");
        }

        cursor.close();

    }//GEN-LAST:event_btnTimMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        int selectedRow = jTable2.getSelectedRow();
//        if (selectedRow >= 0) {
//            // Lấy số hóa đơn từ hàng đã chọn
//            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);
//
//            // Lấy thông tin hóa đơn từ MongoDB
//            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
//            Document query = new Document("so_hoa_don", invoiceNumber);
//            Document invoiceDoc = invoiceCollection.find(query).first();
//
//            if (invoiceDoc != null) {
//                String customerId = invoiceDoc.getString("ma_khach_hang");
//                double points = invoiceDoc.getDouble("tong_tien");
//
//                // Lấy dữ liệu từ ô văn bản
//                double discount = Double.parseDouble(txtGiamGia.getText().replace("%", "")) / 100.0; // Chuyển đổi phần trăm thành thập phân
//                double amountAfterDiscount = Double.parseDouble(txtTongTra.getText());
//
//                // Tính điểm tích lũy mới
//                int loyaltyPoints = (int) Math.floor(points / 10000);
//
//                // Cập nhật thông tin khách hàng
//                MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
//                Document customerQuery = new Document("ma_khach_hang", customerId);
//                Document customerDoc = customerCollection.find(customerQuery).first();
//
//                if (customerDoc != null) {
//                    // Cập nhật tổng tích lũy và mức của khách hàng
//                    Document tichLuy = customerDoc.get("tichluy", Document.class);
//                    int currentLoyaltyPoints = tichLuy.getInteger("TongDiemtichluy", 0);
//
//                    // Cập nhật điểm tích lũy mới
//                    int updatedLoyaltyPoints = currentLoyaltyPoints + loyaltyPoints;
//
//                    // Xác định mức mới dựa trên điểm tích lũy
//                    String newRank = calculateRank(updatedLoyaltyPoints);
//
//                    // Cập nhật khách hàng trong MongoDB
//                    Document updateFields = new Document("tichluy.TongDiemtichluy", updatedLoyaltyPoints)
//                            .append("tichluy.Rank", newRank);
//                    Document update = new Document("$set", updateFields);
//
//                    try {
//                        customerCollection.updateOne(customerQuery, update);
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    // Cập nhật thông tin hóa đơn
//                    Document updateInvoiceFields = new Document("trang_thai", "Đã thanh toán")
//                            .append("giam_gia", discount * 100) // Lưu giảm giá dưới dạng phần trăm
//                            .append("so_tien_tra", amountAfterDiscount); // Số tiền sau khi giảm
//                    Document updateInvoice = new Document("$set", updateInvoiceFields);
//
//                    try {
//                        invoiceCollection.updateOne(query, updateInvoice);
//                        JOptionPane.showMessageDialog(this, "Thanh toán thành công. Hóa đơn đã được cập nhật.");
//
//                        // Cập nhật lại bảng hóa đơn đang chờ
//                        loadPendingInvoices();
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để thanh toán.");
//        }
///// 
////null
//int selectedRow = jTable2.getSelectedRow();
//if (selectedRow >= 0) {
//    // Lấy số hóa đơn từ hàng đã chọn
//    String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);
//
//    // Lấy thông tin hóa đơn từ MongoDB
//    MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
//    Document query = new Document("so_hoa_don", invoiceNumber);
//    Document invoiceDoc = invoiceCollection.find(query).first();
//
//    if (invoiceDoc != null) {
//        String customerId = invoiceDoc.getString("ma_khach_hang");
//        double points = invoiceDoc.getDouble("tong_tien");
//
//        // Lấy số điện thoại từ hóa đơn
//        String customerPhone = invoiceDoc.getString("dien_thoai");
//
//        // Lấy dữ liệu từ ô văn bản
//        double discount = Double.parseDouble(txtGiamGia.getText().replace("%", "")) / 100.0; // Chuyển đổi phần trăm thành thập phân
//        double amountAfterDiscount = Double.parseDouble(txtTongTra.getText());
//
//        // Tính điểm tích lũy mới
//        int loyaltyPoints = (int) Math.floor(points / 10000);
//
//        // Cập nhật thông tin khách hàng
//        MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
//        Document customerQuery = new Document("ma_khach_hang", customerId);
//        Document customerDoc = customerCollection.find(customerQuery).first();
//
//        if (customerDoc != null) {
//            // Cập nhật tổng tích lũy và mức của khách hàng
//            Document tichLuy = customerDoc.get("tichluy", Document.class);
//            int currentLoyaltyPoints = tichLuy != null ? tichLuy.getInteger("TongDiemtichluy", 0) : 0;
//
//            // Cập nhật điểm tích lũy mới
//            int updatedLoyaltyPoints = currentLoyaltyPoints + loyaltyPoints;
//
//            // Xác định mức mới dựa trên điểm tích lũy
//            String newRank = calculateRank(updatedLoyaltyPoints);
//
//            // Cập nhật khách hàng trong MongoDB
//            Document updateFields = new Document("tichluy.TongDiemtichluy", updatedLoyaltyPoints)
//                    .append("tichluy.Rank", newRank);
//            Document update = new Document("$set", updateFields);
//
//            try {
//                customerCollection.updateOne(customerQuery, update);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Cập nhật thông tin hóa đơn
//            Document updateInvoiceFields = new Document("trang_thai", "Đã thanh toán")
//                    .append("giam_gia", discount * 100) // Lưu giảm giá dưới dạng phần trăm
//                    .append("so_tien_tra", amountAfterDiscount) // Số tiền sau khi giảm
//                    .append("dien_thoai", customerPhone); // Cập nhật số điện thoại vào hóa đơn
//            Document updateInvoice = new Document("$set", updateInvoiceFields);
//
//            try {
//                // Cập nhật hóa đơn trong MongoDB
//                invoiceCollection.updateOne(query, updateInvoice);
//                JOptionPane.showMessageDialog(this, "Thanh toán thành công. Hóa đơn đã được cập nhật.");
//
//                // Cập nhật lại bảng hóa đơn đang chờ
//                loadPendingInvoices();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
//        }
//    } else {
//        JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
//    }
//} else {
//    JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để thanh toán.");
//}
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy số hóa đơn từ hàng đã chọn
            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);

            // Lấy thông tin hóa đơn từ MongoDB
            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
            Document query = new Document("so_hoa_don", invoiceNumber);
            Document invoiceDoc = invoiceCollection.find(query).first();

            if (invoiceDoc != null) {
                double points = invoiceDoc.getDouble("tong_tien");

                String customerPhone = txtSDT1.getText().trim();

                // Lấy dữ liệu từ ô văn bản
                double discount = Double.parseDouble(txtGiamGia.getText().replace(',', '.')) / 100.0;
                double amountAfterDiscount = Double.parseDouble(txtTongTra.getText().replace(',', '.'));

                // Tính điểm tích lũy mới
                int loyaltyPoints = (int) Math.floor(points / 10000);

                // Cập nhật thông tin khách hàng
                MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
                Document customerQuery = new Document("dien_thoai", customerPhone);
                Document customerDoc = customerCollection.find(customerQuery).first();
                
                if (customerDoc != null) {
                    // Cập nhật tổng tích lũy và mức của khách hàng
                    Document tichLuy = customerDoc.get("tichluy", Document.class);
                    int currentLoyaltyPoints = tichLuy != null ? tichLuy.getInteger("TongDiemtichluy", 0) : 0;

                    // Cập nhật điểm tích lũy mới
                    int updatedLoyaltyPoints = currentLoyaltyPoints + loyaltyPoints;

                    // Xác định mức mới dựa trên điểm tích lũy
                    String newRank = calculateRank(updatedLoyaltyPoints);

                    // Cập nhật khách hàng trong MongoDB
                    Document updateFields = new Document("tichluy.TongDiemtichluy", updatedLoyaltyPoints)
                            .append("tichluy.Rank", newRank);
                    Document update = new Document("$set", updateFields);

                    try {
                        customerCollection.updateOne(customerQuery, update);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Cập nhật thông tin hóa đơn
                    Document updateInvoiceFields = new Document("trang_thai", "Đã thanh toán")
                            .append("giam_gia", discount * 100) // Lưu giảm giá dưới dạng phần trăm
                            .append("so_tien_tra", amountAfterDiscount) // Số tiền sau khi giảm
                            .append("dien_thoai", customerPhone); // Cập nhật số điện thoại vào hóa đơn
                    Document updateInvoice = new Document("$set", updateInvoiceFields);

                    try {
                        // Cập nhật hóa đơn trong MongoDB
                        invoiceCollection.updateOne(query, updateInvoice);
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công. Hóa đơn đã được cập nhật.");

                        // Cập nhật lại bảng hóa đơn đang chờ
                        loadPendingInvoices();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để thanh toán.");
        }

//        int selectedRow = jTable2.getSelectedRow();
//        if (selectedRow >= 0) {
//            // Lấy số hóa đơn từ hàng đã chọn
//            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);
//
//            // Lấy thông tin hóa đơn từ MongoDB
//            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
//            Document query = new Document("so_hoa_don", invoiceNumber);
//            Document invoiceDoc = invoiceCollection.find(query).first();
//
//            if (invoiceDoc != null) {
//                String customerId = invoiceDoc.getString("ma_khach_hang");
//                double points = invoiceDoc.getDouble("tong_tien");
//
//                // Lấy số điện thoại từ ô văn bản txtSDT1
//                String customerPhone = txtSDT1.getText().trim();
//                if (customerPhone.isEmpty()) {
//                    JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                // Lấy dữ liệu từ ô văn bản
//                double discount = Double.parseDouble(txtGiamGia.getText().replace("%", "")) / 100.0; // Chuyển đổi phần trăm thành thập phân
//                double amountAfterDiscount = Double.parseDouble(txtTongTra.getText());
//
//                // Tính điểm tích lũy mới
//                int loyaltyPoints = (int) Math.floor(points / 10000);
//
//                // Cập nhật thông tin khách hàng
//                MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
//                Document customerQuery = new Document("ma_khach_hang", customerId);
//                Document customerDoc = customerCollection.find(customerQuery).first();
//
//                if (customerDoc != null) {
//                    // Cập nhật tổng tích lũy và mức của khách hàng
//                    Document tichLuy = customerDoc.get("tichluy", Document.class);
//                    int currentLoyaltyPoints = tichLuy != null ? tichLuy.getInteger("TongDiemtichluy", 0) : 0;
//
//                    // Cập nhật điểm tích lũy mới
//                    int updatedLoyaltyPoints = currentLoyaltyPoints + loyaltyPoints;
//
//                    // Xác định mức mới dựa trên điểm tích lũy
//                    String newRank = calculateRank(updatedLoyaltyPoints);
//
//                    // Cập nhật khách hàng trong MongoDB
//                    Document updateFields = new Document("tichluy.TongDiemtichluy", updatedLoyaltyPoints)
//                            .append("tichluy.Rank", newRank);
//                    Document update = new Document("$set", updateFields);
//
//                    try {
//                        customerCollection.updateOne(customerQuery, update);
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
//                    // Cập nhật thông tin hóa đơn
//                    Document updateInvoiceFields = new Document("trang_thai", "Đã thanh toán")
//                            .append("giam_gia", discount * 100) // Lưu giảm giá dưới dạng phần trăm
//                            .append("so_tien_tra", amountAfterDiscount) // Số tiền sau khi giảm
//                            .append("dien_thoai", customerPhone); // Cập nhật số điện thoại vào hóa đơn
//                    Document updateInvoice = new Document("$set", updateInvoiceFields);
//
//                    try {
//                        // Cập nhật hóa đơn trong MongoDB
//                        invoiceCollection.updateOne(query, updateInvoice);
//                        JOptionPane.showMessageDialog(this, "Thanh toán thành công. Hóa đơn đã được cập nhật.");
//
//                        // Cập nhật lại bảng hóa đơn đang chờ
//                        loadPendingInvoices();
//                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để thanh toán.");
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        loadPendingInvoices();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow >= 0) {
            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);

            // Xác nhận xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa hóa đơn số " + invoiceNumber + "?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
                Document query = new Document("so_hoa_don", invoiceNumber);

                try {
                    invoiceCollection.deleteOne(query);
                    JOptionPane.showMessageDialog(this, "Hóa đơn số " + invoiceNumber + " đã được xóa.");

                    // Cập nhật lại bảng
                    loadPendingInvoices();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy số hóa đơn từ hàng đã chọn
            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);

            // Lấy thông tin hóa đơn từ MongoDB
            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
            Document query = new Document("so_hoa_don", invoiceNumber);
            Document invoiceDoc = invoiceCollection.find(query).first();

            if (invoiceDoc != null) {
                String customerPhone = invoiceDoc.getString("sodienthoai");
                double points = invoiceDoc.getDouble("tong_tien");

                // Lấy thông tin khách hàng từ MongoDB
                MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
                Document customerQuery = new Document("dien_thoai", customerPhone);
                Document customerDoc = customerCollection.find(customerQuery).first();

                if (customerDoc != null) {
                    String customerName = customerDoc.getString("ten");
                    // Lấy điểm tích lũy từ trường con
                    Document loyaltyDocument = customerDoc.get("tichluy", Document.class);
                    int loyaltyPoints = loyaltyDocument != null ? loyaltyDocument.getInteger("TongDiemtichluy", 0) : 0;

                    // Tính giảm giá dựa trên điểm tích lũy
                    double discount = calculateDiscount(loyaltyPoints);
                    double discountAmount = points * discount; // Calculate discount amount
                    double amountAfterDiscount = points - discountAmount; // Calculate amount after discount

                    // Cập nhật thông tin khách hàng và hóa đơn trên giao diện
                    txtTen1.setText(customerName);
                    txtSDT1.setText(customerPhone);
                    txtTongTien.setText(String.format("%.2f", points));
                    txtGiamGia.setText(String.format("%.2f", discountAmount)); // Set discount amount
                    txtTongTra.setText(String.format("%.2f", amountAfterDiscount));
                    txtSumTL.setText(String.valueOf(loyaltyPoints));
                    txtRank.setText(calculateRank(loyaltyPoints)); // Cập nhật cấp bậc dựa trên điểm tích lũy
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.");
        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
        String phoneNumber = txtSDT1.getText(); // Lấy số điện thoại từ giao diện người dùng

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
            return;
        }

        // Tìm và hiển thị hóa đơn chưa thanh toán
        getUnpaidInvoicesByPhoneNumber(phoneNumber);
    }//GEN-LAST:event_btnTim1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnTimActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
//        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
//        int selectedRow = jTable2.getSelectedRow();
//        if (selectedRow >= 0) {
//            // Lấy số hóa đơn từ hàng đã chọn
//            String invoiceNumber = (String) jTable2.getValueAt(selectedRow, 0);
//
//            // Lấy số điện thoại từ ô văn bản
//            String customerPhone = txtSDT1.getText();
//
//            // Cập nhật thông tin hóa đơn trong MongoDB
//            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
//            Document query = new Document("so_hoa_don", invoiceNumber);
//
//            // Tạo tài liệu cập nhật với trường dien_thoai
//            Document updateFields = new Document("dien_thoai", customerPhone);
//            Document update = new Document("$set", updateFields);
//
//            try {
//                // Cập nhật hóa đơn
//                UpdateResult result = invoiceCollection.updateOne(query, update);
//                if (result.getMatchedCount() > 0) {
//                    JOptionPane.showMessageDialog(this, "Cập nhật số điện thoại thành công.");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với số hóa đơn đã cho.");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để cập nhật.");
//        }
        //  }
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaActionPerformed
    private void updateTableWithBarcodeData(String maSanPham, String gia) {
        // Tạo mô hình dữ liệu cho JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Xóa các hàng hiện có trong bảng
        model.setRowCount(0);

        // Thêm hàng mới với dữ liệu từ mã vạch
        model.addRow(new Object[]{maSanPham, gia});
    }

    private void updateTotalAmount() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        double points = 0;

        // Iterate over all rows and sum up the total amount
        for (int i = 0; i < rowCount; i++) {
            Object totalPriceObj = model.getValueAt(i, 4); // Assuming column 4 is "Thành Tiền"
            if (totalPriceObj != null) {
                try {
                    double totalPrice = (Double) totalPriceObj;
                    points += totalPrice;
                } catch (ClassCastException e) {
                    JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ tại hàng " + i, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        // Update the txtSumTL field with the total amount
        txtSumTL.setText(String.valueOf(points));
    }

    private void clearForm() {
        // Xóa nội dung của JTable và các trường nhập liệu
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Xóa tất cả hàng trong bảng
        txtSDT.setText("");
        txtTen.setText("");
        txtMa.setText("");
        txtSumTL.setText("");
        txtRank.setText("");
        txtMaVach.setText("");
    }

    private String generateInvoiceNumber() {
        // Lấy ngày và giờ hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        
        String datePart = dateFormat.format(new Date());
        String timePart = timeFormat.format(new Date());

        // Tạo số tự động tăng (4 chữ số)
        int count = counter.incrementAndGet() % 10000; // Giới hạn số tự động tăng trong 4 chữ số

        // Định dạng số tự động tăng thành 4 chữ số
        String countPart = String.format("%04d", count);

        // Kết hợp ngày, giờ và số tự động tăng để tạo số hóa đơn
        return "hd-" + datePart + "-" + timePart + "-" + countPart;
    }

    private void loadPendingInvoices() {
        MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");

        // Tạo truy vấn để lấy hóa đơn có trạng thái "Chưa thanh toán"
        Document query = new Document("trang_thai", "Chưa thanh toán");

        // Lấy danh sách hóa đơn từ MongoDB
        MongoCursor<Document> cursor = invoiceCollection.find(query).iterator();

        // Xóa tất cả các hàng trong JTable2 trước khi cập nhật
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        // Duyệt qua tất cả các hóa đơn và thêm vào JTable2
        while (cursor.hasNext()) {
            Document invoiceDoc = cursor.next();
            String invoiceNumber = invoiceDoc.getString("so_hoa_don");
            Date date = invoiceDoc.getDate("ngay");
            String customerPhpne = invoiceDoc.getString("sodienthoai");
            double points = invoiceDoc.getDouble("tong_tien");
            String status = invoiceDoc.getString("trang_thai");

            // Thêm dòng vào JTable2
            model.addRow(new Object[]{invoiceNumber, date, customerPhpne, points, status});
        }

        cursor.close(); // Đóng con trỏ sau khi hoàn tất
    }

    ////////////
    ///////////
    private void getInvoiceDetails(String invoiceNumber) {
        // Lấy thông tin hóa đơn từ MongoDB
        MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
        Document query = new Document("so_hoa_don", invoiceNumber);
        Document invoiceDoc = invoiceCollection.find(query).first();

        if (invoiceDoc != null) {
            // Lấy mã khách hàng và thông tin hóa đơn
            String customerId = invoiceDoc.getString("ma_khach_hang");
            double points = invoiceDoc.getDouble("tong_tien");
            String status = invoiceDoc.getString("trang_thai");

            // Lấy thông tin khách hàng từ MongoDB
            MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
            Document customerQuery = new Document("ma_khach_hang", customerId);
            Document customerDoc = customerCollection.find(customerQuery).first();

            if (customerDoc != null) {
                // Lấy thông tin khách hàng
                String customerName = customerDoc.getString("ten");
                String customerPhone = customerDoc.getString("dien_thoai");

                // Lấy điểm tích lũy từ trường con
                Document loyaltyDocument = customerDoc.get("tichluy", Document.class);
                int loyaltyPoints = loyaltyDocument != null ? loyaltyDocument.getInteger("TongDiemtichluy", 0) : 0;

                // Tính giảm giá dựa trên điểm tích lũy
                double discount = calculateDiscount(loyaltyPoints);
                double amountAfterDiscount = points * (1 - discount);

                // Tính cấp bậc dựa trên điểm tích lũy
                String rank = calculateRank(loyaltyPoints);

                // Hiển thị thông tin
                txtTen1.setText(customerName);
                txtSDT1.setText(customerPhone);
                txtTongTien.setText(String.valueOf(points));
                txtGiamGia.setText(String.format("%.2f%%", discount * 100)); // Hiển thị phần trăm giảm giá
                txtTongTra.setText(String.format("%.2f", amountAfterDiscount));
                txtSumTL.setText(String.valueOf(loyaltyPoints));
                txtRank.setText(rank);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn.");
        }
    }

    private double calculateDiscount(int loyaltyPoints) {
        if (loyaltyPoints >= 6000) {
            return 0.10;
        } else if (loyaltyPoints >= 5000) {
            return 0.09;
        } else if (loyaltyPoints >= 4000) {
            return 0.07;
        } else if (loyaltyPoints >= 3000) {
            return 0.05;
        } else if (loyaltyPoints >= 1000) {
            return 0.03;
        } else {
            return 0.00;
        }
    }

    private String calculateRank(int loyaltyPoints) {
        if (loyaltyPoints >= 6000) {
            return "Mức 5"; // Từ 6000 điểm trở lên
        } else if (loyaltyPoints >= 5000) {
            return "Mức 4"; // Từ 5000 đến 5999 điểm
        } else if (loyaltyPoints >= 4000) {
            return "Mức 3"; // Từ 4000 đến 4999 điểm
        } else if (loyaltyPoints >= 3000) {
            return "Mức 2"; // Từ 3000 đến 3999 điểm
        } else if (loyaltyPoints >= 1000) {
            return "Mức 1"; // Từ 1000 đến 2999 điểm
        } else {
            return "Mức 0"; // Dưới 1000 điểm (không có cấp bậc)
        }
    }

    /////////////
    /////////
    private void getUnpaidInvoicesByPhoneNumber(String phoneNumber) {
        // Tìm mã khách hàng dựa trên số điện thoại
        MongoCollection<Document> customerCollection = modelBase.GetCollection("khachhang");
        Document customerQuery = new Document("dien_thoai", phoneNumber);
        Document customerDoc = customerCollection.find(customerQuery).first();

        if (customerDoc != null) {
            String customerId = customerDoc.getString("ma_khach_hang");

            // Tìm các hóa đơn chưa thanh toán dựa trên mã khách hàng
            MongoCollection<Document> invoiceCollection = modelBase.GetCollection("hoadon");
            Document invoiceQuery = new Document("ma_khach_hang", customerId).append("trang_thai", "Chưa thanh toán");
            FindIterable<Document> invoices = invoiceCollection.find(invoiceQuery);

            // Kiểm tra nếu không có hóa đơn chưa thanh toán nào
            if (invoices.first() == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn chưa thanh toán nào cho khách hàng này.");
                return;
            }

            // Thiết lập cấu trúc bảng (model) của jTable2
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setColumnIdentifiers(new Object[]{"Số Hóa Đơn", "Ngày", "Mã Khách Hàng", "Tổng Tiền", "Trạng Thái"});
            model.setRowCount(0); // Xóa dữ liệu cũ trên bảng

            // Thêm thông tin hóa đơn vào bảng
            for (Document invoiceDoc : invoices) {
                String invoiceNumber = invoiceDoc.getString("so_hoa_don");
                Date invoiceDate = invoiceDoc.getDate("ngay");
                String invoiceCustomerId = invoiceDoc.getString("ma_khach_hang");
                double totalAmount = invoiceDoc.getDouble("tong_tien");
                String status = invoiceDoc.getString("trang_thai");

                // Thêm hàng vào bảng
                model.addRow(new Object[]{invoiceNumber, invoiceDate, invoiceCustomerId, totalAmount, status});
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại này.");
        }

    }

//    private void handleUpdateButton() {
//        int selectedRow = jTable1.getSelectedRow();
//
//        // Kiểm tra xem có hàng nào được chọn không
//        if (selectedRow >= 0) {
//            try {
//                // Lấy giá đơn vị từ bảng
//                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//                int unitPrice = (int) model.getValueAt(selectedRow, 3); // Cột giá đơn vị là cột 3
//
//                // Lấy số lượng mới từ ô số lượng (cột 2)
//                int newQuantity = (int) model.getValueAt(selectedRow, 2);
//
//                // Kiểm tra số lượng có hợp lệ không (không âm)
//                if (newQuantity < 0) {
//                    JOptionPane.showMessageDialog(null, "Số lượng không thể âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    // Khôi phục giá trị trước khi sửa
//                    model.setValueAt((int) model.getValueAt(selectedRow, 2), selectedRow, 2); // Khôi phục
//                    return;
//                }
//
//                // Tính thành tiền mới
//                int totalPrice = newQuantity * unitPrice; // Thành tiền = đơn giá * số lượng
//
//                // Cập nhật số lượng và thành tiền trong bảng
//                model.setValueAt(newQuantity, selectedRow, 2); // Cập nhật số lượng ở cột 2
//                model.setValueAt(totalPrice, selectedRow, 4); // Cập nhật thành tiền ở cột 4
//
//                JOptionPane.showMessageDialog(null, "Cập nhật số lượng thành công.");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để sửa.");
//        }
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_TaoHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_TaoHoaDon().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTim1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaVach;
    private javax.swing.JTextField txtRank;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtSumTL;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTen1;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTra;
    // End of variables declaration//GEN-END:variables
}
