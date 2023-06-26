package db;

import pojo.Posts;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.DataBaseConnection.createConnection;

/**
 * The type Data base manager.
 */
public final class DataBaseManager {

    /**
     * Gets post data from db.
     * @param id the id
     * @return the post data from db
     */
    public static List<Posts> getPostDataFromDB(final int id) {
        ResultSet resultSet;
        List<Posts> list = new ArrayList<Posts>();
        String query = "SELECT * FROM wp_posts WHERE ID = ?";
        Posts posts;
        PreparedStatement statement;
        try (Connection connection = createConnection()) {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts = new Posts();
                posts.setId(resultSet.getString("id"));
                posts.setStatus(resultSet.getString("post_status"));
                posts.setPostContent(resultSet.getString("post_content"));
                posts.setPostTitle(resultSet.getString("post_title"));
                list.add(posts);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * Gets post.
     * @param id the id
     * @return the post
     */
    public static Posts getPost(final int id) {
        return getPostDataFromDB(id).get(0);
    }

    /**
     * Create post in db int.
     * @param title   the title
     * @param content the content
     * @return the int
     */
    public static int createPostInDB(final String title, final String content) {
        int insertPostID = 0;
        String query = "INSERT wordpress.wp_posts (post_author, post_date, " +
                "post_date_gmt, post_content," +
                "post_title, post_excerpt, " +
                "post_status, to_ping, pinged, " +
                "post_modified, post_modified_gmt, " +
                "post_content_filtered) " +
                "VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), ?, ?, '', 'publish', '', '', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '')";
        ResultSet generatedKeyResult;
        PreparedStatement statement;
        try (Connection connection = createConnection()) {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, content);
            statement.setString(2, title);
            statement.executeUpdate();
            generatedKeyResult = statement.getGeneratedKeys();
            if (generatedKeyResult.next()) {
                insertPostID = generatedKeyResult.getInt(1);
                generatedKeyResult.close();
            }
            statement.close();
            generatedKeyResult.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insertPostID;
    }


    /**
     * Delete all created post in db.
     */
    public static void deleteAllPosts() {
        String query = "TRUNCATE wp_posts";
        PreparedStatement statement;
        try (Connection connection = createConnection()) {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DataBaseManager() {
        throw new IllegalStateException("Utility class");
    }
}
