package main;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import com.userauth.ws.UserAuthorizationWS;
import com.userauth.ws.bind.XmlUser;
import com.userauth.ws.client.UserAuthServiceClient;
import com.userauth.ws.publisher.AuthWebServicePublisher;

public class UserAuthWebService
{
    private static final String COMMAND_STOP = "stop";
    private static final String COMMAND_AUTHORIZE_PATTERN = "^authorize [^ ]* [^ ]*$";

    private static final Logger logger = Logger.getLogger(AuthWebServicePublisher.class.getName());
    private static Endpoint endpoint;

    public static void main(String[] args)
    {
        if (endpoint == null || !endpoint.isPublished())
        {
            endpoint = AuthWebServicePublisher.publish(true);
        }

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String command = scanner.nextLine();
            if (COMMAND_STOP.equals(command))
            {
                AuthWebServicePublisher.unpublish(endpoint);
                scanner.close();
                break;
            }
            else if (command.matches(COMMAND_AUTHORIZE_PATTERN) && endpoint.isPublished())
            {
                String[] requestCommand = command.split(" ");
                XmlUser user = new XmlUser(requestCommand[1], requestCommand[2]);
                logger.info(new UserAuthServiceClient().getService().authorize(user));
            }
            else
            {
                logger.info("Wrong command");
            }
        }
    }
}
