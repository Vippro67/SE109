package com.techshopbe.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection =  "invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	@Id
	private String invoiceID;
	private String userID;
	private int totalCost;
	private String invoiceDate;
	private String shippingDate;
	private String note;
	private boolean otherShippingAddress;
	private String statusInvoice= "PENDING";
	private String userInvoiceIndex;
	public void setDetailedInvoices(List<Invoice> detailedInvoices) {
	}
    public void setShippingInfo(ShippingInfo shippingInfo) {
    }
    public void setTotalPrice(int totalCost2) {
    }
    public ShippingInfo getShippingInfo() {
        return null;
    }
    public DetailedInvoice[] getDetailedInvoices() {
        return null;
    }

}
