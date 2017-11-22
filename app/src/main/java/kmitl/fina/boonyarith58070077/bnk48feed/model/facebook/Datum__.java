
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum__ {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("media")
    @Expose
    private Media_ media;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("target")
    @Expose
    private Target_ target;
    @SerializedName("description")
    @Expose
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Media_ getMedia() {
        return media;
    }

    public void setMedia(Media_ media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Target_ getTarget() {
        return target;
    }

    public void setTarget(Target_ target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
