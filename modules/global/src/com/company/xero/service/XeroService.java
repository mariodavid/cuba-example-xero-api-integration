package com.company.xero.service;


import com.company.xero.entity.Invoice;

import java.util.Collection;

public interface XeroService {
    String NAME = "xero_XeroService";

    public Collection<Invoice> getInvoicesFromXero();
}