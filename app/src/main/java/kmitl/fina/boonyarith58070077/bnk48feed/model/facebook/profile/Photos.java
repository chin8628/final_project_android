
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("data")
    @Expose
    private List<FacebookProfileData> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<FacebookProfileData> getData() {
        return data;
    }

    public void setData(List<FacebookProfileData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
