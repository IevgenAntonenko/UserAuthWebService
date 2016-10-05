package com.userauth.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.userauth.domain.User;
import com.userauth.service.UserAuthorizationService;
import com.userauth.service.impl.UserAuthorizationServiceImpl;
import com.userauth.ws.UserAuthorizationWS;
import com.userauth.ws.bind.XmlUser;

/**
 * Web service implementation.
 * @author Ievgen_Antonenko
 *
 */
@WebService(endpointInterface = "com.userauth.ws.UserAuthorizationWS")
public class UserAuthorizationWSImpl implements UserAuthorizationWS
{

    /**
     * Used to handle client request for authorization. Converts an xml objects to the domain object model and calls business
     * service for farther handling.
     * 
     * @param xmlUser user data including login and password.
     * @return authorization result message.
     */
    @WebMethod
    @Override
    public String authorize(XmlUser xmlUser)
    {
        UserAuthorizationService service = new UserAuthorizationServiceImpl();
        User user = new User();
        user.setLogin(xmlUser.getLogin());
        user.setPassword(xmlUser.getPassword());
        return service.authorize(user);
    }

}
