package com.xwke.security.support;
public interface AccountAliasConnector {
    String findUsernameByAlias(String username);

    void updateAlias(String username, String type, String alias);
}
