package com.xwke.security.support;

public class MockAccountCredentialConnector implements
        AccountCredentialConnector {
    public String findPassword(String username, String tenantId) {
        return username;
    }
}
