package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The type Content.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {

    /**
     * Field - rendered.
     */
    private String rendered;
}
