package org.integration.payments.server.util;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    public static byte[] getResourceAsByteArray(InputStream is) throws IOException {
        return org.apache.commons.io.IOUtils.toByteArray(is);
    }
}
