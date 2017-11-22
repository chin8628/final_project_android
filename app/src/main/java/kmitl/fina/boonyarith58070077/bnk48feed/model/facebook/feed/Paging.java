
package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging {

    @SerializedName("cursors")
    @Expose
    private Cursors cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public Cursors getCursors() {
        return cursors;
    }

    public void setCursors(Cursors cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
