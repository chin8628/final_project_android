
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_ {

    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("target")
    @Expose
    private Target target;
    @SerializedName("subattachments")
    @Expose
    private Subattachments subattachments;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

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

}
