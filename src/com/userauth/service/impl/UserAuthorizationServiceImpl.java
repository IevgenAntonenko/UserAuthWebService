package com.userauth.service.impl;

import org.apache.log4j.Logger;

import com.userauth.dao.UserDao;
import com.userauth.dao.impl.UserXmlDao;
import com.userauth.domain.User;
import com.userauth.service.UserAuthorizationService;

public class UserAuthorizationServiceImpl implements UserAuthorizationService
{

    private static final Logger logger = Logger.getLogger(UserAuthorizationServiceImpl.class.getName());

    private UserDao userDao;

    public UserAuthorizationServiceImpl()
    {
        init();
    };

    @Override
    public String authorize(User user)
    {
        String result = MSG_AUTHORIZATION_FAILED;
        if (user != null && user.getLogin() != null)
        {
            User dbUser = userDao.getUser(user.getLogin());
            if (dbUser != null && dbUser.getPassword() != null && dbUser.getPassword().equals(user.getPassword()))
            {

                // do authorization...
                result = MSG_AUTHORIZATION_SUCCESS;
            }
        }
        logger.info(result);
        return result;
    }

    protected void init()
    {
        userDao = new UserXmlDao();
    }

}
