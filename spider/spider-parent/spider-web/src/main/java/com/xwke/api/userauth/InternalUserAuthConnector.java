package com.xwke.api.userauth;
import javax.servlet.http.HttpSession;



public interface InternalUserAuthConnector {
    UserAuthDTO findFromSession(HttpSession session);
}
