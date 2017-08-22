package com.xwke.security.support;

import com.xwke.api.userauth.UserAuthDTO;

public interface UserAuthConnector {
    UserAuthDTO findByUsername(String username, String tenantId);

    UserAuthDTO findByRef(String ref, String tenantId);

    UserAuthDTO findById(String id, String tenantId);
}
