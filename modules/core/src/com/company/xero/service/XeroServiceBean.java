package com.company.xero.service;

import com.company.xero.configuration.XeroOAuthConfiguration;
import com.connectifier.xeroclient.XeroClient;
import com.connectifier.xeroclient.models.Invoice;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Resources;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service(XeroService.NAME)
public class XeroServiceBean implements XeroService {

    @Inject
    XeroOAuthConfiguration xeroOAuthConfiguration;

    @Inject
    Resources resources;

    @Inject
    Metadata metadata;

    @Override
    public Collection<com.company.xero.entity.Invoice> getInvoicesFromXero() {

        String consumerKey = xeroOAuthConfiguration.getConsumerKey();
        String consumerSecret = xeroOAuthConfiguration.getConsumerSecret();
        String privateKeyPemFilename = xeroOAuthConfiguration.getPrivateKeyPemFilename();

        Collection<com.company.xero.entity.Invoice> invoiceList = new ArrayList<com.company.xero.entity.Invoice>();


        if (consumerKey != null && consumerSecret != null && privateKeyPemFilename != null) {

            try {
                XeroClient client = initXeroClient(privateKeyPemFilename, consumerKey, consumerSecret);

                List<Invoice> xeroInvoiceList = client.getInvoices();

                return createInvoicesFromXeroInvoices(invoiceList, xeroInvoiceList);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return invoiceList;
    }

    private XeroClient initXeroClient(String pemFileUrl, String consumerKey, String consumerSecret) throws IOException {
        //File pemFile = resources.getResource("com/company/xero/service/privatekey.pem").getFile();
        File pemFile = resources.getResource(pemFileUrl).getFile();
        Reader pemReader = new FileReader(pemFile);
        return new XeroClient(pemReader, consumerKey, consumerSecret);
    }

    private Collection<com.company.xero.entity.Invoice> createInvoicesFromXeroInvoices(Collection<com.company.xero.entity.Invoice> invoiceList, List<Invoice> xeroInvoiceList) {
        for (Invoice xeroInvoice : xeroInvoiceList) {
            com.company.xero.entity.Invoice invoice = metadata.create(com.company.xero.entity.Invoice.class);

            invoice.setInvoiceDate(xeroInvoice.getDate());
            invoiceList.add(invoice);
        }
        return invoiceList;
    }
}