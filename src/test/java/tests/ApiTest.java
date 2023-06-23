package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Posts;

import static db.DataBaseManager.createPostInDB;
import static db.DataBaseManager.getPost;
import static db.DataBaseManager.deleteAllPosts;

import static props.ConfigurationManager.config;
import static wp.WordpressApiManager.*;

/**
 * The type Api test.
 */
public class ApiTest {

    /**
     * Field - postID.
     */
    private int postID;

    /**
     * Create basic post.
     */
    @BeforeEach
    @Step("create basic post")
    public void createBasicPost() {
        postID = createPostInDB(config().postTitle(), config().postContent());
    }

    /**
     * Change wp post title.
     */
    @Story("Update wp post")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void changeWpPostTitle() {
        updatePostTitle(postID, config().newPostTitle())
                .statusCode(200)
                .assertThat()
                .body("title.rendered", Matchers.equalTo(config().newPostTitle()));
        Posts posts = getPost(postID);
        Assertions.assertEquals(posts.getPostTitle(), config().newPostTitle(),
                "last post in database doesn't contain title " + config().newPostTitle());
        Assertions.assertEquals(posts.getPostContent(), config().postContent(),
                "last post in database doesn't contain content " + config().postContent());
    }

    /**
     * Delete wp post content.
     */
    @Story("Delete wp post")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void deleteWpPost() {
        deletePosts(postID)
                .statusCode(200)
                .assertThat()
                .body("status", Matchers.equalTo("trash"));
        Posts posts = getPost(postID);
        Assertions.assertEquals(posts.getPostTitle(), config().postTitle(),
                "last post in database doesn't contain title " + config().postTitle());
        Assertions.assertEquals(posts.getStatus(), "trash",
                "last post in database doesn't contain status 'trash'");
    }

    /**
     * Read wp post.
     */
    @Story("Get wp post")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void getWpPost() {
        Posts posts = readPost(postID);
        Assertions.assertEquals(config().postTitle(), posts.getTitle().getRendered(),
                "new post in WP doesn't contain title " + config().postTitle());
        Assertions.assertEquals("<p>" + config().postContent() + "</p>\n", posts.getContent().getRendered(),
                "new post in WP doesn't contain content " + config().postContent());
        Assertions.assertEquals("publish", posts.getStatus(),
                "new post in WP doesn't contain status 'publish'");
    }

    /**
     * Delete created posts.
     */
    @AfterAll
    public static void deleteCreatedPosts() {
        deleteAllPosts();
    }
}
