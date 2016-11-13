package com.company.xero.web.invoice;

import com.company.xero.entity.Invoice;
import com.company.xero.service.XeroService;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;

public class InvoiceBrowse extends AbstractLookup {

    @Inject
    XeroService xeroService;

    @Inject
    CollectionDatasource<Invoice, UUID> invoicesDs;


    public void xeroActionHandler() {

        boolean downloadSuccessful = xeroService.downloadInvoicesFromXero();

        if (downloadSuccessful) {
            invoicesDs.refresh();
        }

    }
}