package com.userauth.ws.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlUser")
public class XmlUser
{

    public XmlUser()
    {
    }

    public XmlUser(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    @XmlElement(name = "login")
    private String login;

    @XmlElement(name = "password")
    private String password;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
