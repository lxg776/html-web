package com.xwke.security.client;


import com.xwke.api.userauth.CurrentUserHolder;
import com.xwke.security.util.SpringSecurityUtils;


public class SpringSecurityCurrentUserHolder implements CurrentUserHolder {
    public String getUserId() {
        return SpringSecurityUtils.getCurrentUserId();
    }

    public String getUsername() {
        return SpringSecurityUtils.getCurrentUsername();
    }
}
