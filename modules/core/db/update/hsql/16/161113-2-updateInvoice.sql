alter table XERO_INVOICE add column INVOICE_NUMBER varchar(255) default '' not null ;
alter table XERO_INVOICE add column AMOUNT_DUE decimal(19, 2) ;
alter table XERO_INVOICE add column AMOUNT_PAID decimal(19, 2) ;
alter table XERO_INVOICE add column TOTAL decimal(19, 2) ;
alter table XERO_INVOICE add column TOTAL_TAX decimal(19, 2) ;
alter table XERO_INVOICE add column SUB_TOTAL decimal(19, 2) ;
