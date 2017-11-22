
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacebookFeed {

    @SerializedName("paging")
    @Expose
    private Paging paging;
    @SerializedName("data")
    @Expose
    private List<FacebookData> data = null;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<FacebookData> getData() {
        return data;
    }

    public void setData(List<FacebookData> data) {
        this.data = data;
    }

}
