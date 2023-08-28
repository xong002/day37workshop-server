package practice.day37workshop.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.day37workshop.model.Post;
import practice.day37workshop.repositories.PostsRepository;

@Service
public class PostService {

    @Autowired
    private PostsRepository repo;

    public Optional<String> createPost(String comments, byte[] picture) {
        String newUUID = UUID.randomUUID().toString().substring(0, 8);
        System.out.println(">>>>>>>>>>>>>>>>" + newUUID);
        Post newPost = new Post(newUUID, comments, picture);
        return repo.createPost(newPost);
    }

    public Optional<Post> getPostById(String id){
        return repo.getPostById(id);
    }

    public Optional<byte[]> getPostBlobById(String id){
        return repo.getPostBlobById(id);
    }
}
