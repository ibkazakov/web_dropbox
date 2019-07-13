package web_dropbox.store;

import java.util.Date;

public class File {
    public Long id;
    public String name;
    public Date created;
    private String type;
    public byte[] content;

    public File(Long id, String name, String type, byte[] content) {
        this.id = id;
        this.setName(name);
        this.setContent(content);
        this.created = new Date();
        this.setType(type);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
