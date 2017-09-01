package com.xwke.security.support;

import com.xwke.api.userauth.UserAuthDTO;

public interface UserAuthConnector {
    UserAuthDTO findByUsername(String username);

    UserAuthDTO findByRef(String ref);

    UserAuthDTO findById(String id);
}
