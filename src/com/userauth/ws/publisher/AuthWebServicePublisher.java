package com.userauth.ws.publisher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;

import com.userauth.service.impl.UserAuthorizationServiceImpl;
import com.userauth.ws.impl.UserAuthorizationWSImpl;
import com.userauth.ws.support.WebServiceLoggingHandler;

/**
 * Used for publishing the web service endpoint
 * @author Ievgen_Antonenko
 *
 */
public class AuthWebServicePublisher
{
    private static final Logger logger = Logger.getLogger(AuthWebServicePublisher.class.getName());

    public static final String ENDPOINT_ADDRESS = "http://localhost:8888/webservice/authorize";
    public static final String WSDL_ADDRESS = ENDPOINT_ADDRESS + "?wsdl";

    private static final String MESSAGE_ENDPOINT_PUBLISHED = "Endpoint published on " + ENDPOINT_ADDRESS + "\n";
    private static final String MESSAGE_ENDPOINT_UNPUBLISHED = "Endpoint unpublished";

    public static Endpoint publish(boolean addLoggingHandler)
    {
        Endpoint endpoint = Endpoint.create(new UserAuthorizationWSImpl());
        ExecutorService es = Executors.newWorkStealingPool();

        if (addLoggingHandler)
        {
            Binding binding = endpoint.getBinding();

            List<Handler> handlerList = binding.getHandlerChain();
            handlerList.add(new WebServiceLoggingHandler());
            binding.setHandlerChain(handlerList);
        }
        endpoint.setExecutor(es);
        endpoint.publish(ENDPOINT_ADDRESS);

        logger.info(MESSAGE_ENDPOINT_PUBLISHED);
        return endpoint;
    }

    public static void unpublish(Endpoint endpoint)
    {
        if (endpoint != null && endpoint.isPublished())
        {
            endpoint.stop();
            logger.info(MESSAGE_ENDPOINT_UNPUBLISHED);
        }
    }
}
