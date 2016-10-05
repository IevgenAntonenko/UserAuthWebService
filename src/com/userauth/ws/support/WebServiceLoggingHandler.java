package com.userauth.ws.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class WebServiceLoggingHandler implements SOAPHandler<SOAPMessageContext>
{

    private static final Logger logger = Logger.getLogger(WebServiceLoggingHandler.class.getName());

    @Override
    public void close(MessageContext arg0)
    {

    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0)
    {
        SOAPMessage message = arg0.getMessage();
        try
        {
            OutputStream os = new ByteArrayOutputStream();
            message.writeTo(os);
            logger.info(os.toString());
        }
        catch (SOAPException | IOException e)
        {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0)
    {
        SOAPMessage message = arg0.getMessage();
        boolean isOutboundMessage = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isOutboundMessage)
        {
            logger.info("OUTBOUND MESSAGE");

        }
        else
        {
            logger.info("INBOUND MESSAGE");
        }

        try (OutputStream os = new ByteArrayOutputStream())
        {
            message.writeTo(os);
            logger.info(os.toString() + "\n");
        }
        catch (SOAPException | IOException e)
        {
            logger.error(e);
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders()
    {
        return null;
    }

}
