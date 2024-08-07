/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Truc
 */
public class ProductBill {
    private String barcode;
    private String name;
    private double unitPrice;

    public ProductBill(String barcode, String name, int unitPrice) {
        this.barcode = barcode;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
