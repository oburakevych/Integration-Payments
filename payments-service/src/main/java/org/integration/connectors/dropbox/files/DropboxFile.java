package org.integration.connectors.dropbox.files;

public class DropboxFile {
    private String mimeType = null;
    private long fileSize = -1;
    private String charset = null;
    private byte[] contents = null;
    private Entry metadata = null;
    
    public DropboxFile() {}
    
    public DropboxFile(Entry metadata) {
        this.metadata = metadata;
    }
    
    public String getMimeType() {
        return mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public Entry getMetadata() {
        return metadata;
    }
    public void setMetadata(Entry metadata) {
        this.metadata = metadata;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public byte[] getContents() {
        return contents;
    }

}
