package com.company.xero.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "XERO_INVOICE")
@Entity(name = "xero$Invoice")
public class Invoice extends StandardEntity {
    private static final long serialVersionUID = 1216854099536114274L;

    @Temporal(TemporalType.DATE)
    @Column(name = "INVOICE_DATE")
    protected Date invoiceDate;

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }


}