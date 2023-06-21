package wp;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;
import static props.ConfigurationManager.configuration;

/**
 * The type Word press.
 */
public final class WordpressApiManager {

    /**
     * The constant URL.
     */
    private static final String URL = configuration().wpUrl();

    /**
     * The REQUEST_SPEC.
     */
    private static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setAuth(preemptive().basic(configuration().wpUsername(), configuration().wpPassword()))
            .setBaseUri(URL)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();

    /**
     * Delete posts validatable response.
     * @param postID the post id
     * @return the validatable response
     */
    @Step("delete post from WP")
    public static ValidatableResponse deletePosts(final int postID) {
        return given()
                .spec(REQUEST_SPEC)
                .queryParam("rest_route", "/wp/v2/posts/" + postID)
                .when()
                .delete()
                .then();
    }

    /**
     * Update post title validatable response.
     * @param postID  the post id
     * @param content the content
     * @return the validatable response
     */
    @Step("update post in WP")
    public static ValidatableResponse updatePostTitle(final int postID, final String content) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", content);
        return given()
                .spec(REQUEST_SPEC)
                .queryParam("rest_route", "/wp/v2/posts/" + postID)
                .and()
                .body(requestParams.toMap())
                .when()
                .patch()
                .then();
    }

    /**
     * Create post int.
     * @param title   the title
     * @param content the content
     * @return the int
     */
    @Step("create post in WP")
    public static ValidatableResponse createPost(final String title, final String content) {
        return given()
                .spec(REQUEST_SPEC)
                .queryParam("rest_route", "/wp/v2/posts/")
                .queryParam("title", title)
                .queryParam("content", content)
                .queryParam("status", "publish")
                .when()
                .post()
                .then();
    }

    private WordpressApiManager() {
        throw new IllegalStateException("Utility class");
    }
}
