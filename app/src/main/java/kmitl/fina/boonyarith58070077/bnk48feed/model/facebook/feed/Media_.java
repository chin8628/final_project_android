
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media_ {

    @SerializedName("image")
    @Expose
    private Image_ image;

    public Image_ getImage() {
        return image;
    }

    public void setImage(Image_ image) {
        this.image = image;
    }

}
