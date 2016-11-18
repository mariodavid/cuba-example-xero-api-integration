# CUBA Platform example to interact with Xero API

This is an CUBA example application that shows how to integrate with external APIs.
In this case the external API is [Xero](https://github.com/connectifier/xero-java-client/).

The first part to integrate with an external API is to decide either to consume
their APIs through there network protocol (e.g. RESTful HTTP JSON endpoints) or through some kind of client API wrapper library.

In this case we will use the Java wrapper library for Xero from here: https://github.com/connectifier/xero-java-client/


### Adding the required dependency to your project
In the java world, maven is the de-facto standard for package managing and depency resolution (like NuGet / npm).

CUBA uses gradle as a build tool, which is compatible with maven distribution management, so we can just use the existing package
You can find instructions about how the name of the dependency is in the project. THe actual binary normally sits in [maven central](http://search.maven.org/#artifactdetails%7Ccom.connectifier.xero%7Cclient%7C0.13%7Cjar).

So, in this case, just open the build.gradle in your IDE or use CUBA studio to add the following dependency:
    
    compile 'com.connectifier.xero:client:0.13'
    
The result can be seen in the [build.gradle](https://github.com/mariodavid/cuba-example-xero-api-integration/blob/master/build.gradle#L96) of the example.

### Using the API classes

To use the API, i created the [XeroService](https://github.com/mariodavid/cuba-example-xero-api-integration/blob/master/modules/core/src/com/company/xero/service/XeroServiceBean.groovy), that is called from the UI button to import invoices from the API.
The service uses the configuration facility ([XeroOAuthConfiguration](https://github.com/mariodavid/cuba-example-xero-api-integration/blob/master/modules/global/src/com/company/xero/configuration/XeroOAuthConfiguration.java)) to externalize settings on consumerKey, consumerSecret and private key filename 
More information about Configuration interfaces can be found in the [CUBA docs](https://doc.cuba-platform.com/manual-6.3/config_interfaces.html).

The actual API call happens through the XeroClient like this:

    List<Invoice> xeroInvoiceList = client.getInvoices()
    
The invoices are instances of the DTO objects of the library. I used it to transform it into instances of the actual entites of the application in the method [persistXeroInvoices](https://github.com/mariodavid/cuba-example-xero-api-integration/blob/master/modules/core/src/com/company/xero/service/XeroServiceBean.groovy#L66)

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
    
After a successful imoport of the data, the UI table gets updated like [this](https://github.com/mariodavid/cuba-example-xero-api-integration/blob/master/modules/web/src/com/company/xero/web/invoice/InvoiceBrowse.java#L23):

    boolean downloadSuccessful = xeroService.downloadInvoicesFromXero();
    
    if (downloadSuccessful) {
      invoicesDs.refresh();
    }
    


    

