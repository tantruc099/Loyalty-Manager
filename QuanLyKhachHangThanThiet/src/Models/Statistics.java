/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author XuanTruong
 */
public class Statistics {

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getTotalLoyaltyCustomers() {
        return totalLoyaltyCustomers;
    }

    public void setTotalLoyaltyCustomers(int totalLoyaltyCustomers) {
        this.totalLoyaltyCustomers = totalLoyaltyCustomers;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    private int maxPoints;
    private int totalLoyaltyCustomers;
    private double totalRevenue;
}
