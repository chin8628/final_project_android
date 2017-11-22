
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookProfileModel {

    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
