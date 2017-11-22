
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("target")
    @Expose
    private Target target;
    @SerializedName("subattachments")
    @Expose
    private Subattachments subattachments;
    @SerializedName("media")
    @Expose
    private Media_ media;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Subattachments getSubattachments() {
        return subattachments;
    }

    public void setSubattachments(Subattachments subattachments) {
        this.subattachments = subattachments;
    }

    public Media_ getMedia() {
        return media;
    }

    public void setMedia(Media_ media) {
        this.media = media;
    }

}
