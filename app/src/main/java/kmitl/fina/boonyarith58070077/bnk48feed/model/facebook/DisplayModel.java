package kmitl.fina.boonyarith58070077.bnk48feed.model.facebook;

import java.util.List;

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed.FacebookFeedData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.profile.FacebookProfileModel;

public class DisplayModel {

    private List<FacebookFeedData> facebookFeedData;
    private FacebookProfileModel facebookProfileModel;
    private DisplayModel.displayModelListener displayModelListener;

    public interface displayModelListener {
        void onReady();
    }

    public DisplayModel(DisplayModel.displayModelListener displayModelListener) {
        this.displayModelListener = displayModelListener;
    }

    public List<FacebookFeedData> getFacebookFeedData() {
        return facebookFeedData;
    }

    public void setFacebookFeedData(List<FacebookFeedData> facebookFeedData) {
        this.facebookFeedData = facebookFeedData;
        this.checkDataIsReady();
    }

    public void addFacebookFeedData(List<FacebookFeedData> facebookFeedData) {
        this.facebookFeedData.addAll(facebookFeedData);
        this.checkDataIsReady();
    }

    public FacebookProfileModel getFacebookProfileModel() {
        return facebookProfileModel;
    }

    public void setFacebookProfileModel(FacebookProfileModel facebookProfileModel) {
        this.facebookProfileModel = facebookProfileModel;
        this.checkDataIsReady();
    }

    private void checkDataIsReady() {
        if (this.facebookFeedData != null && this.facebookProfileModel != null) {
            this.displayModelListener.onReady();
        }
    }
}
