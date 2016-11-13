package com.company.xero.service

import com.company.xero.configuration.XeroConfigurationException
import com.company.xero.configuration.XeroOAuthConfiguration
import com.connectifier.xeroclient.XeroClient
import com.connectifier.xeroclient.models.Invoice
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.Resources
import org.springframework.stereotype.Service

import javax.inject.Inject
import java.io.*

@Service(XeroService.NAME)
public class XeroServiceBean implements XeroService {

    @Inject
    XeroOAuthConfiguration xeroOAuthConfiguration

    @Inject
    Resources resources

    @Inject
    Metadata metadata

    @Inject
    DataManager dataManager

    @Override
    public boolean downloadInvoicesFromXero() throws XeroConfigurationException {

        String consumerKey = xeroOAuthConfiguration.getConsumerKey()
        String consumerSecret = xeroOAuthConfiguration.getConsumerSecret()
        String privateKeyPemFilename = xeroOAuthConfiguration.getPrivateKeyPemFilename()

        if (!consumerKey || !consumerSecret || !privateKeyPemFilename) {
            throw new XeroConfigurationException("Xero Configuration not properly configured. Check Admnistration > Application Settings > Xero")
        }


        try {
            XeroClient client = initXeroClient(privateKeyPemFilename, consumerKey, consumerSecret)

            List<Invoice> xeroInvoiceList = client.getInvoices()

            persistXeroInvoices(xeroInvoiceList)

            return true

        } catch (FileNotFoundException e) {
            throw new XeroConfigurationException("Private Key PEM file not found. Either filename not configured right or file was not placed in the CUBA conf directory (see: https://doc.cuba-platform.com/manual-6.3/conf_dir.html)", e)


        } catch (IOException e) {
            throw new XeroConfigurationException("Private Key PEM file not found. Either filename not configured right or file was not placed in the CUBA conf directory (see: https://doc.cuba-platform.com/manual-6.3/conf_dir.html)", e)
        }
    }

    private XeroClient initXeroClient(String pemFileUrl, String consumerKey, String consumerSecret) throws IOException {
        File pemFile = resources.getResource(pemFileUrl).getFile()
        Reader pemReader = new FileReader(pemFile)
        return new XeroClient(pemReader, consumerKey, consumerSecret)
    }

    private void persistXeroInvoices(List<Invoice> xeroInvoiceList) {

        xeroInvoiceList.each { Invoice xeroInvoice ->

            com.company.xero.entity.Invoice invoiceEntity = metadata.create(com.company.xero.entity.Invoice.class)
            invoiceEntity.invoiceNumber = xeroInvoice.invoiceNumber
            invoiceEntity.invoiceDate = xeroInvoice.date
            invoiceEntity.total = xeroInvoice.total
            invoiceEntity.amountDue = xeroInvoice.amountDue
            invoiceEntity.amountPaid = xeroInvoice.amountPaid
            invoiceEntity.subTotal = xeroInvoice.subTotal
            invoiceEntity.totalTax = xeroInvoice.totalTax

            dataManager.commit(invoiceEntity)
        }
    }
}