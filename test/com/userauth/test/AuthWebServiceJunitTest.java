package com.userauth.test;

import static org.junit.Assert.*;

import javax.xml.ws.Endpoint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.userauth.service.UserAuthorizationService;
import com.userauth.ws.bind.XmlUser;
import com.userauth.ws.client.UserAuthServiceClient;
import com.userauth.ws.publisher.AuthWebServicePublisher;

public class AuthWebServiceJunitTest
{

    private static Endpoint endpoint;
    private static UserAuthServiceClient client;

    private final XmlUser existingUser = new XmlUser("Ievgen", "123");
    private final XmlUser notExistingUser = new XmlUser("Ivan", "987");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        if (endpoint == null || !endpoint.isPublished())
        {
            endpoint = AuthWebServicePublisher.publish(false);
        }
        client = new UserAuthServiceClient();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        if (endpoint != null && endpoint.isPublished())
        {
            AuthWebServicePublisher.unpublish(endpoint);
        }
    }

    @Test
    public void testExistingUser()
    {
        String response = client.getService().authorize(existingUser);
        assertEquals(UserAuthorizationService.MSG_AUTHORIZATION_SUCCESS, response);
    }

    @Test
    public void testNotExistingUser()
    {
        String response = client.getService().authorize(notExistingUser);
        assertEquals(UserAuthorizationService.MSG_AUTHORIZATION_FAILED, response);
    }

}
