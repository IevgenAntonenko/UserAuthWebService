package com.userauth.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.userauth.ws.bind.XmlUser;

/**
 * Used to handle client request for authorization. Converts an xml objects to the domain object model and calls business service
 * for farther handling.
 * 
 * @param xmlUser user data including login and password.
 * @return authorization result message.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface UserAuthorizationWS
{
    @WebMethod
    public String authorize(XmlUser xmlUser);
}
