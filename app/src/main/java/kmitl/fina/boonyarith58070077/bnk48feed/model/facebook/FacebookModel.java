package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookModel {

    @SerializedName("feed")
    @Expose
    private FacebookFeed facebookFeed;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;

    public FacebookFeed getFacebookFeed() {
        return facebookFeed;
    }

    public void setFacebookFeed(FacebookFeed facebookFeed) {
        this.facebookFeed = facebookFeed;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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
