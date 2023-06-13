package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The type Title.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {

    /**
     * Field - rendered.
     */
    private String rendered;
}
