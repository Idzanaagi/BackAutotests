package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Posts;

import static db.DataBaseManager.getPost;

import static wp.WordpressApiManager.createPost;
import static wp.WordpressApiManager.deletePosts;
import static wp.WordpressApiManager.updatePostTitle;

/**
 * The type Api test.
 */
public class ApiTest {

    /**
     * Field - id.
     */
    private int id;

    /**
     * Field - postTitle.
     */
    private final String postTitle = "createPostTitle";

    /**
     * Field - postContent.
     */
    private final String postContent = "createPostContent";

    /**
     * Create basic post.
     */
    @BeforeEach
    public void createBasicPost()  {
        id = createPost(postTitle, postContent)
                .extract().path("id");
    }

    /**
     * Change wp post title.
     * @throws Exception the exception
     */
    @Story("Update wp post")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void changeWpPostTitle() throws Exception {
        String newTitle = "New Title";
        updatePostTitle(id, newTitle)
                .statusCode(200)
                .assertThat()
                .body("title.rendered", Matchers.equalTo(newTitle));
        Posts posts = getPost(id);
        Assertions.assertEquals(posts.getPostTitle(), newTitle,
                "last post in database doesn't contain title 'New Title'");
        Assertions.assertEquals(posts.getPostContent(), postContent,
                "last post in database doesn't contain content 'createPostContent'");
    }

    /**
     * Delete wp post content.
     * @throws Exception the exception
     */
    @Story("Delete wp post")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void deleteWpPost() throws Exception {
        deletePosts(id)
                .statusCode(200)
                .assertThat()
                .body("status", Matchers.equalTo("trash"));
        Posts posts = getPost(id);
        Assertions.assertEquals(posts.getPostTitle(), postTitle,
                "last post in database doesn't contain title 'createPostTitle'");
        Assertions.assertEquals(posts.getStatus(), "trash",
                "last post in database doesn't contain status 'trash'");
    }
}
