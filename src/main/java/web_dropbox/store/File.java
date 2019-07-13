package web_dropbox.store;

import java.util.Date;

public class File {
    public Long id;
    public String name;
    public Date created;
    public byte[] content;

    public File(Long id, String name, byte[] content) {
        this.id = id;
        this.setName(name);
        this.setContent(content);
        this.created = new Date();
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
}
