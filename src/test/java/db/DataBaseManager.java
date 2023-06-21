package db;

import pojo.Posts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.DataBaseConnection.createConnection;

/**
 * The type Posts utils.
 */
public final class DataBaseManager {

    /**
     * Gets posts.
     * @param id the id
     * @return Posts
     * @throws SQLException the sql exception
     */
    public static List<Posts> getPostDataFromDB(final int id) throws SQLException {
        ResultSet resultSet = null;
        List<Posts> list = new ArrayList<Posts>();
        String query = "SELECT * FROM wp_posts WHERE ID = ?";
        Posts posts;
        try (PreparedStatement statement = createConnection().prepareStatement(query)) {
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
        } finally {
            assert resultSet != null;
            resultSet.close();
            createConnection().close();
        }
        return list;
    }

    /**
     * Gets posts.
     * @param id the id
     * @return Posts
     * @throws SQLException the sql exception
     */
    public static Posts getPost(final int id) throws SQLException {
        return getPostDataFromDB(id).get(0);
    }

    private DataBaseManager() {
        throw new IllegalStateException("Utility class");
    }
}
