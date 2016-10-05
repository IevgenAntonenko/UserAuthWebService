package com.userauth.dao;

import com.userauth.domain.User;

/**
 * Used to get user from underlying storage.
 * @author Ievgen_Antonenko
 *
 */
public interface UserDao
{

    /**
     * Get user by login
     * @param login
     * @return found user or null
     */
    User getUser(String login);
}
