package com.userauth.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.userauth.dao.UserDao;
import com.userauth.domain.User;

public class UserXmlDao implements UserDao
{

    private static final Logger logger = Logger.getLogger(UserXmlDao.class.getName());

    private static String STORAGE_FILE_NAME = "users.xml";
    private static String XML_ELEMENT_LOGIN = "login";
    private static String XML_ELEMENT_PASSWORD = "password";

    private static String ERROR_PARSING_DOM = "Error parsing storage.";

    @Override
    public User getUser(String login)
    {
        User user = null;

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(STORAGE_FILE_NAME))
        {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName(XML_ELEMENT_LOGIN);

            Node foundLogin = IntStream.range(0, users.getLength()).mapToObj(users::item)
                .filter(node -> login.equals(node.getTextContent())).findAny().orElse(null);

            if (foundLogin != null)
            {
                user = new User();
                user.setLogin(login);
                user.setPassword(getPassword(foundLogin.getParentNode()));
            }

        }
        catch (DOMException | IOException | SAXException | ParserConfigurationException e)
        {
            logger.error(e);
            throw new RuntimeException(ERROR_PARSING_DOM, e);
        }

        return user;
    }

    protected String getPassword(Node user)
    {

        NodeList childs = user.getChildNodes();
        Node foundLogin = IntStream.range(0, childs.getLength()).mapToObj(childs::item)
            .filter(node -> XML_ELEMENT_PASSWORD.equals(node.getNodeName())).findAny().orElse(null);

        String password = foundLogin != null ? foundLogin.getTextContent() : null;
        return password;
    }

}
