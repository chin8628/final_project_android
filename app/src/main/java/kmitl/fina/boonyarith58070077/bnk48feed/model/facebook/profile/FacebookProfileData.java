
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookProfileData {

    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("id")
    @Expose
    private String id;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
