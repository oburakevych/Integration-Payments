package org.integration.payments.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils extends org.apache.commons.io.IOUtils {
    public static byte[] getResourceAsByteArray(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        
        return toByteArray(is);
    }
    
    public static InputStream toInputStream(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        return is;
    }
    
    
}
