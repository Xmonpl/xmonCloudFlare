package cf.xmon.cloudflare.config;

import com.google.gson.annotations.SerializedName;

public class Config
{
    @SerializedName("xmonCloudFlare")
    private Instance Instance;

    public Config() {
        this.Instance = new Instance();
    }

    public Instance getInstance() {
        return this.Instance;
    }
}