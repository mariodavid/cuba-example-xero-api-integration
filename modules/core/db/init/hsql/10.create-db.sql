-- begin XERO_INVOICE
create table XERO_INVOICE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    INVOICE_DATE date,
    INVOICE_NUMBER varchar(255),
    AMOUNT_DUE decimal(19, 2),
    AMOUNT_PAID decimal(19, 2),
    TOTAL decimal(19, 2),
    TOTAL_TAX decimal(19, 2),
    SUB_TOTAL decimal(19, 2),
    --
    primary key (ID)
)^
-- end XERO_INVOICE
