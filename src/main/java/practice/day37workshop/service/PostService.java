package practice.day37workshop.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.day37workshop.model.Post;
import practice.day37workshop.repositories.PostsRepository;

@Service
public class PostService {
    
    @Autowired
    private PostsRepository repo;

    public void createPost(String comments, byte[] picture){
        Post newPost = new Post(UUID.randomUUID().toString(), comments, picture);
        repo.createPost(newPost);
    }
}
