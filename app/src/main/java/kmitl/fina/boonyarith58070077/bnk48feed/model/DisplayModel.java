package kmitl.fina.boonyarith58070077.bnk48feed.model;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookProfile;

public class DisplayModel {

    private List<FacebookData> facebookDataList;

    public DisplayModel() { }

    public List<FacebookData> getFacebookDataList() {
        return facebookDataList;
    }

    public void setFacebookDataList(FacebookModel facebookModel) {
        FacebookProfile facebookProfile = new FacebookProfile();
        facebookProfile.setId(facebookModel.getId());
        facebookProfile.setAbout(facebookModel.getAbout());
        facebookProfile.setName(facebookModel.getName());
        facebookProfile.setPhotos(facebookModel.getPhotos());

        Log.d("setFacebookDataList", facebookModel.getName());
        Log.d("setFacebookDataList", facebookModel.getFacebookFeed().getPaging().getNext());
        for(FacebookData facebookData : facebookModel.getFacebookFeed().getData()) {
            facebookData.setFacebookProfile(facebookProfile);
        }

        if (this.facebookDataList == null) {
            this.facebookDataList = facebookModel.getFacebookFeed().getData();
        } else {
            this.facebookDataList.addAll(facebookModel.getFacebookFeed().getData());
        }
    }

    public void sortByTime() {
        Collections.sort(this.facebookDataList, new Comparator<FacebookData>() {
            @Override
            public int compare(FacebookData facebookData1, FacebookData facebookData2) {

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                sdf.setTimeZone(TimeZone.getDefault());

                Date time1 = new Date();
                Date time2 = new Date();
                try {
                    time1 = sdf.parse(facebookData1.getCreatedTime());
                    time2 = sdf.parse(facebookData2.getCreatedTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long diff = time1.getTime() - time2.getTime();

                if (diff > 0)
                    return -1;
                if (diff < 0)
                    return 1;
                return 0;
            }
        });
    }
}