package com.company.xero.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.math.BigDecimal;

@Table(name = "XERO_INVOICE")
@Entity(name = "xero$Invoice")
public class Invoice extends StandardEntity {
    private static final long serialVersionUID = 1216854099536114274L;

    @Temporal(TemporalType.DATE)
    @Column(name = "INVOICE_DATE")
    protected Date invoiceDate;

    @Column(name = "INVOICE_NUMBER")
    protected String invoiceNumber;

    @Column(name = "AMOUNT_DUE")
    protected BigDecimal amountDue;

    @Column(name = "AMOUNT_PAID")
    protected BigDecimal amountPaid;

    @Column(name = "TOTAL")
    protected BigDecimal total;

    @Column(name = "TOTAL_TAX")
    protected BigDecimal totalTax;

    @Column(name = "SUB_TOTAL")
    protected BigDecimal subTotal;

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }


    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }


}