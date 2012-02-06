package org.integration.connectors.process;

import java.util.UUID;

public interface Executor {
    void run(UUID companyAccountId);
    void run(UUID companyAccountId, String path);
}
