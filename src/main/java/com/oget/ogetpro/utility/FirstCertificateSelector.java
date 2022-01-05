package com.oget.ogetpro.utility;

import java.security.cert.X509Certificate;
import java.util.List;

import xades4j.providers.impl.KeyStoreKeyingDataProvider.SigningCertSelector;

public class FirstCertificateSelector implements SigningCertSelector
{
    public X509Certificate selectCertificate(
            List<X509Certificate> availableCertificates)
    {
        return availableCertificates.get(0);
    }
}
