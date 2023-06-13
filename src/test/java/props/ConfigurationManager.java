package props;

import org.aeonbits.owner.ConfigCache;

/** The type Configuration manager. */
public final class ConfigurationManager {

    private ConfigurationManager() {
    }

    /**
     * Configuration configuration.
     * @return the configuration
     */
    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}

