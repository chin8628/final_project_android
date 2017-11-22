
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookModel {

    @SerializedName("data")
    @Expose
    private List<FacebookFeedData> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<FacebookFeedData> getData() {
        return data;
    }

    public void setData(List<FacebookFeedData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return "data: " + data.toString() + " paging: " + paging.toString();
    }

}
