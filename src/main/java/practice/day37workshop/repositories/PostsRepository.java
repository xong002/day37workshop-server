package practice.day37workshop.repositories;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import practice.day37workshop.model.Post;

@Repository
public class PostsRepository {

    private String ADD_POST_SQL = "insert into posts values (?,?,?)";
    private String GET_POST_BY_ID_SQL = "select * from posts where post_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<String> createPost(Post post) {
        int rowsUpdated = jdbcTemplate.update(ADD_POST_SQL, post.getPostId(), post.getComments(), post.getPicture());
        if (rowsUpdated > 0) {
            return Optional.of(post.getPostId());
        }
        return Optional.empty();
    }

    public Optional<Post> getPostById(String id) {
        List<Post> resultList = jdbcTemplate.query(GET_POST_BY_ID_SQL, (ResultSet rs) -> {
            List<Post> results = new LinkedList<>();
            while (rs.next()) {
                Post post = new Post(
                        rs.getString("post_id"),
                        rs.getString("comments"),
                        rs.getBytes("picture"));
                results.add(post);
            }
            return results;
        }, id);
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }

    public Optional<byte[]> getPostBlobById(String id) {
        List<byte[]> resultList = jdbcTemplate.query(GET_POST_BY_ID_SQL, (ResultSet rs) -> {
            List<byte[]> results = new LinkedList<>();
            while (rs.next()) {
                results.add(rs.getBytes("picture"));
            }
            return results;
        }, id);
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(resultList.get(0));
    }
}
