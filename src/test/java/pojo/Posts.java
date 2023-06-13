package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
}
