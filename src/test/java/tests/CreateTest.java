package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.Posts;

import static db.DataBaseManager.getPost;
import static db.DataBaseManager.deleteAllPosts;
import static wp.WordpressApiManager.createPost;

import static props.ConfigurationManager.config;

/**
 * The type Create test.
 */
public class CreateTest {

    /**
     * Create wp post.
     */
    @Story("Create wp post")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createWpPost() {
        int postID;
        postID = createPost(config().postTitle(), config().postContent())
                .statusCode(201)
                .assertThat().body("title.rendered", Matchers.equalTo(config().postTitle()))
                .assertThat().body("content.rendered", Matchers.equalTo("<p>" + config().postContent() + "</p>\n"))
                .extract().path("id");
        Posts posts = getPost(postID);
        Assertions.assertEquals(posts.getPostTitle(), config().postTitle(),
                "last post in database doesn't contain title " + config().postTitle());
        Assertions.assertEquals(posts.getPostContent(), config().postContent(),
                "last post in database doesn't contain content " + config().postContent());
        Assertions.assertEquals(posts.getStatus(), "publish",
                "last post in database doesn't contain status 'publish'");
    }

    /**
     * Delete created posts.
     */
    @AfterAll
    public static void deleteCreatedPosts() {
        deleteAllPosts();
    }
}
