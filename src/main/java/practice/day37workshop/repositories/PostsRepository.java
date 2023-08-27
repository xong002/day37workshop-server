package practice.day37workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import practice.day37workshop.model.Post;

@Repository
public class PostsRepository {

    private String ADD_POST_SQL = "insert into posts values (?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createPost(Post post) {
        int result = jdbcTemplate.update(ADD_POST_SQL, post.getPostId(), post.getComments(), post.getPicture());
        System.out.println(result);
    }
}
