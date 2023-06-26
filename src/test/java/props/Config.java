package props;

/**
 * The interface Configuration.
 */
@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources({
        "system:properties",
        "classpath:database.properties",
        "classpath:wp.properties",
        "classpath:testData.properties",
})
public interface Config extends org.aeonbits.owner.Config {

    /**
     * Wp username string.
     * @return the string
     */
    @Key("wpUsername")
    String wpUsername();

    /**
     * Wp password string.
     * @return the string
     */
    @Key("wpPassword")
    String wpPassword();

    /**
     * Wp url string.
     * @return the string
     */
    @Key("wpUrl")
    String wpUrl();

    /**
     * Url string.
     * @return the string
     */
    @Key("url")
    String url();

    /**
     * User string.
     * @return the string
     */
    @Key("user")
    String user();

    /**
     * Password string.
     * @return the string
     */
    @Key("password")
    String password();

    /**
     * Post title string.
     * @return the string
     */
    @Key("postTitle")
    String postTitle();

    /**
     * Post content string.
     * @return the string
     */
    @Key("postContent")
    String postContent();

    /**
     * New post title string.
     * @return the string
     */
    @Key("newPostTitle")
    String newPostTitle();

}
