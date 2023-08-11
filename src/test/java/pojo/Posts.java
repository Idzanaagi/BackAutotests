package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

/**
 * The type Posts.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {

    /**
     * Field - id.
     */
    private String id;

    /**
     * Field - status.
     */
    private String status;

    /**
     * Field - postTitle.
     */
    @JsonProperty("post_title")
    private String postTitle;

    /**
     * Field - postContent.
     */
    @JsonProperty("post_content")
    private String postContent;

    /**
     * The Content.
     * -- GETTER --
     *  Gets content
     * @return the content

     */
    @Getter
    private Content content;

    /**
     * The Title.
     * -- GETTER --
     *  Gets title.
     * @return the title
     */
    @Getter
    private Title title;
}
