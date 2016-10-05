package com.userauth.service;

import com.userauth.domain.User;

/**
 * Used for user authorization. Also contains authorization status messages.
 * @author Ievgen_Antonenko
 *
 */
public interface UserAuthorizationService
{

    static String MSG_AUTHORIZATION_SUCCESS = "Authorization success.";
    static String MSG_AUTHORIZATION_FAILED = "Authorization failed.";

    /**
     * Used to perform authorization logic. Checks existence of passed user in the storage.
     * @param user to be authorized.
     * @return authorization status.
     */
    String authorize(User user);
}
