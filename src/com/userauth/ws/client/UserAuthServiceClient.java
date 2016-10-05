package com.userauth.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import com.userauth.ws.UserAuthorizationWS;
import com.userauth.ws.bind.XmlUser;
import com.userauth.ws.publisher.AuthWebServicePublisher;

public class UserAuthServiceClient
{

    private static final Logger logger = Logger.getLogger(UserAuthServiceClient.class.getName());

    private UserAuthorizationWS service = null;

    public UserAuthServiceClient()
    {
        init();
    }

    private void init()
    {
        URL wsdlUrl;
        try
        {
            wsdlUrl = new URL(AuthWebServicePublisher.WSDL_ADDRESS);

            QName qname = new QName("http://impl.ws.userauth.com/", "UserAuthorizationWSImplService");

            Service webService = Service.create(wsdlUrl, qname);
            service = webService.getPort(UserAuthorizationWS.class);
        }
        catch (MalformedURLException e)
        {
            logger.error(e);
        }
    }

    public static void main(String[] args) throws Exception
    {

        XmlUser user = new XmlUser();
        user.setLogin(null);
        user.setPassword(null);
        logger.info(new UserAuthServiceClient().getService().authorize(user));

    }

    public UserAuthorizationWS getService()
    {
        return service;
    }

}
