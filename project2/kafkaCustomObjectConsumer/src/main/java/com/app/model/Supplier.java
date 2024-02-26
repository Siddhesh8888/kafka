package com.app.model;

import java.util.Date;

public class Supplier {

    private int suppilerId;
    private String supplierName;
    private Date supplierStartDate;

    public Supplier(int suppilerId, String supplierName, Date supplierStartDate) {
        this.suppilerId = suppilerId;
        this.supplierName = supplierName;
        this.supplierStartDate = supplierStartDate;
    }

    public int getSuppilerId() {
        return suppilerId;
    }

    public void setSuppilerId(int suppilerId) {
        this.suppilerId = suppilerId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getSupplierStartDate() {
        return supplierStartDate;
    }

    public void setSupplierStartDate(Date supplierStartDate) {
        this.supplierStartDate = supplierStartDate;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "suppilerId=" + suppilerId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierStartDate=" + supplierStartDate +
                '}';
    }
}
