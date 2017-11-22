
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subattachments {

    @SerializedName("data")
    @Expose
    private List<SubattachmentData> data = null;

    public List<SubattachmentData> getData() {
        return data;
    }

    public void setData(List<SubattachmentData> data) {
        this.data = data;
    }

}
