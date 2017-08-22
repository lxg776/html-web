package com.xwke.security.support;
public interface AccountCredentialConnector {
    String findPassword(String username, String tenantId);
}
