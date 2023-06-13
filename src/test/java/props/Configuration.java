package props;

import org.aeonbits.owner.Config;

/**
 * The interface Configuration.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:database.properties",
        "classpath:wp.properties"
})
public interface Configuration extends Config {

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
}
