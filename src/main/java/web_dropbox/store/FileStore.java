package web_dropbox.store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileStore {

    private Map<Long, File> fileMap = new HashMap<Long, File>();

    private Long lastUsedId = 0L;

    public void createFile(String name, byte[] content) {
        Long id = getNextId();
        File newFile = new File(id, name, content);
        fileMap.put(id, newFile);
    }

    public List<File> getFileList() {
        return new ArrayList<File>(fileMap.values());
    }

    public boolean exists(Long id) {
        return fileMap.containsKey(id);
    }

    public File getFile(Long id) {
        return fileMap.get(id);
    }

    public void deleteFile(Long id) {
        fileMap.remove(id);
    }

    private Long getNextId() {
        lastUsedId++;
        return lastUsedId;
    }
}
