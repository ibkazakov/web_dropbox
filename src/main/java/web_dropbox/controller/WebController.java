package web_dropbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web_dropbox.store.File;
import web_dropbox.store.FileStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private FileStore store;

    @GetMapping("/greeting")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<File> fileList = store.getFileList();
        model.addAttribute("files", fileList);
        return "filelist";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String fileName = file.getOriginalFilename();
                String fileType = file.getContentType();
                store.createFile(fileName, fileType, fileContent);
                // System.out.println("file_loaded " + fileName);
                return "redirect:/";
            } catch (Exception e) {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/download/{id}")
    public void downloadFile(HttpServletRequest request,
                             HttpServletResponse response,
                             @PathVariable Long id) {
        if (store.exists(id)) {
            File file = store.getFile(id);
            response.setContentType(file.getType());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
            try {
                response.getOutputStream().write(file.getContent());
                response.getOutputStream().flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File with id " + id + " do not exist");
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteFile(HttpServletRequest request,
                           HttpServletResponse response
                            ,@PathVariable Long id) {
        if (store.exists(id)) {
            store.deleteFile(id);
        } else {
            System.out.println("File with id " + id + " do not exist");
        }
        return "redirect:/";
    }
}
