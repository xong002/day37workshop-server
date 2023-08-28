package practice.day37workshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import practice.day37workshop.model.Post;
import practice.day37workshop.service.PostService;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService svc;

    // add get mapping to homepage, add to comments & file attributes to model
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("post", new Post());
        return "index";
    }

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createPost(@RequestPart String comments, @RequestPart MultipartFile file,
            Model model) {
        try {
            // System.out.println(file.getContentType());
            // if (file.getContentType().equals("image/jpeg")) {
            // svc.createPost(comments, file.getBytes());
            // } else
            // throw new Exception("file type is not image/jpeg");

            Optional<String> opt = svc.createPost(comments, file.getBytes());

            model.addAttribute("post", new Post());
            return ResponseEntity.ok().body(Json.createObjectBuilder().add("generatedId", opt.get()).build().toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Json.createObjectBuilder().add("message", e.getMessage()).build().toString());
        }

    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Optional<Post> result = svc.getPostById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(result.get());
    }

    @GetMapping("/posts/{id}/file")
    @ResponseBody
    public ResponseEntity<String> getPostBlobById(@PathVariable String id) {
        Optional<byte[]> result = svc.getPostBlobById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(result.get().toString());
    }
}
