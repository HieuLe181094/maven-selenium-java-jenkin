package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:envConfig/${environment}.properties"})
public interface EnvironmentConfig extends Config {
    String url();

    @Key("App.Url")
    String getAppUrl();

    @Key("App.User")
    String getAppUserName();

    @Key("App.Pass")
    String getAppPassword();


}