package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.Posts;

import static db.DataBaseManager.getPost;

import static wp.WordpressApiManager.createPost;

/**
 * The type Create test.
 */
public class CreateTest {

    /**
     * Create wp post.
     * @throws Exception the exception
     */
    @Story("Create wp post")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createWpPost() throws Exception {
        String postTitle = "createPostTitle";
        String postContent = "createPostContent";
        int id;
        id = createPost(postTitle, postContent)
                .statusCode(201)
                .assertThat().body("title.rendered", Matchers.equalTo(postTitle))
                .assertThat().body("content.rendered", Matchers.equalTo("<p>" + postContent + "</p>\n"))
                .extract().path("id");
        Posts posts = getPost(id);
        Assertions.assertEquals(posts.getPostTitle(), "createPostTitle",
                "last post in database doesn't contain title 'createPostTitle'");
        Assertions.assertEquals(posts.getPostContent(), "createPostContent",
                "last post in database doesn't contain content 'createPostContent'");
        Assertions.assertEquals(posts.getStatus(), "publish",
                "last post in database doesn't contain status 'publish'");
    }
}
