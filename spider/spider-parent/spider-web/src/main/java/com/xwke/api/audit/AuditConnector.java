package com.xwke.api.audit;

public interface AuditConnector {
    void log(AuditDTO auditDto);
}
