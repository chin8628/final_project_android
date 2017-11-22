
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("paging")
    @Expose
    private Paging_ paging;
    @SerializedName("data")
    @Expose
    private List<Datum___> data = null;

    public Paging_ getPaging() {
        return paging;
    }

    public void setPaging(Paging_ paging) {
        this.paging = paging;
    }

    public List<Datum___> getData() {
        return data;
    }

    public void setData(List<Datum___> data) {
        this.data = data;
    }

}
