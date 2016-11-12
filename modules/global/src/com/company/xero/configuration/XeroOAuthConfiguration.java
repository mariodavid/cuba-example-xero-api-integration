/*
 * TODO Copyright
 */

package com.company.xero.configuration;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.DATABASE)
public interface XeroOAuthConfiguration extends Config {

    @Property("xero.consumerKey")
    String getConsumerKey();

    @Property("xero.consumeSecret")
    String getConsumerSecret();

    @Property("xero.privateKeyPemFilename")
    String getPrivateKeyPemFilename();
}
