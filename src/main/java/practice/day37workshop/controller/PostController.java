package practice.day37workshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import practice.day37workshop.service.PostService;

@Controller
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService svc;

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPost(@RequestPart String comments, @RequestPart MultipartFile file) {
        try{
            svc.createPost(comments, file.getBytes());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
