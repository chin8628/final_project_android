
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Target_ {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
