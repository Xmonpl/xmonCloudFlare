package cf.xmon.cloudflare.config;

import com.google.gson.annotations.SerializedName;

public class Instance {
    @SerializedName("API_ID")
    private String API_ID;

    @SerializedName("API_AUTH_KEY")
    private String API_AUTH_KEY;

    @SerializedName("ZONE_NAME")
    private String ZONE_NAME;

    @SerializedName("ZONE_ID")
    private String ZONE_ID;

    @SerializedName("DNSREC_ID")
    private String DNSREC_ID;

    @SerializedName("IP")
    private String IP;

    public Instance() {
        this.API_ID = "me@example.com";
        this.API_AUTH_KEY = "foo47eb745079dac9320b638f5e225cf483cc5cfdda41";
        this.ZONE_NAME = "example.com";
        this.ZONE_ID = "bar1105f4ecef8ad9ca31a8372d0c353";
        this.DNSREC_ID = "baz372954025e0ba6aaa6d586b9e0b59";
        this.IP = "127.0.0.1";
    }

    public String getAPI_ID() {
        return API_ID;
    }

    public void setAPI_ID(String API_ID) {
        this.API_ID = API_ID;
    }

    public String getAPI_AUTH_KEY() {
        return API_AUTH_KEY;
    }

    public void setAPI_AUTH_KEY(String API_AUTH_KEY) {
        this.API_AUTH_KEY = API_AUTH_KEY;
    }

    public String getZONE_NAME() {
        return ZONE_NAME;
    }

    public void setZONE_NAME(String ZONE_NAME) {
        this.ZONE_NAME = ZONE_NAME;
    }

    public String getZONE_ID() {
        return ZONE_ID;
    }

    public void setZONE_ID(String ZONE_ID) {
        this.ZONE_ID = ZONE_ID;
    }

    public String getDNSREC_ID() {
        return DNSREC_ID;
    }

    public void setDNSREC_ID(String DNSREC_ID) {
        this.DNSREC_ID = DNSREC_ID;
    }
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
