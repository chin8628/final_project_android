package kmitl.fina.boonyarith58070077.bnk48feed.model;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookProfile;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

public class DisplayModel {

    private List<FacebookData> facebookDataList = new ArrayList<>();
    private List<FacebookSinglePost> facebookSinglePosts = new ArrayList<>();
    private List<FacebookData> tempFacebookDataList = new ArrayList<>();

    public DisplayModel() { }

    public DisplayModel(FacebookModel facebookModel) {
        this.addFacebookDataList(facebookModel);
    }

    public List<FacebookData> getFacebookDataList() {
        return facebookDataList;
    }

    public List<FacebookData> getFilteredFacebookDataList() {
        List<FacebookData> newFacebookDataList = new ArrayList<>();

        for(FacebookData facebookData: this.facebookDataList) {
            String name_system = facebookData.getFacebookProfile().getNameSystem();
            if (Member.getMemberFilter().contains(name_system)) {
                newFacebookDataList.add(facebookData);
            }
        }

        return newFacebookDataList;
    }

    public void clearData() {
        this.facebookDataList = new ArrayList<>();
    }

    public void addFacebookDataList(FacebookModel insertFacebookModel) {
        FacebookProfile facebookProfile = new FacebookProfile();
        facebookProfile.setId(insertFacebookModel.getId());
        facebookProfile.setAbout(insertFacebookModel.getAbout());
        facebookProfile.setName(insertFacebookModel.getName());
        facebookProfile.setPhotos(insertFacebookModel.getPhotos());
        facebookProfile.setNameSystem(Member.getNameSystem(insertFacebookModel.getId()));

        List<FacebookData> data = insertFacebookModel.getFacebookFeed().getData();
        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getAttachments() == null) {
                data.remove(data.get(i));
            }
        }

        for (int i=0; i<data.size(); i++) {
            data.get(i).setFacebookProfile(facebookProfile);
        }

        this.tempFacebookDataList.addAll(insertFacebookModel.getFacebookFeed().getData());
    }

    public void addFacebookDataList(FacebookSinglePost insertFacebookSinglePost) {

        if (this.facebookSinglePosts == null) {
            this.facebookSinglePosts = new ArrayList<>();
        }

        boolean itsDuplicate = false;
        for (FacebookSinglePost facebookSinglePost: this.facebookSinglePosts) {
            if (facebookSinglePost.getId().equals(insertFacebookSinglePost.getId())) {
                itsDuplicate = true;
                break;
            }
        }

        if (!itsDuplicate) {
            this.facebookSinglePosts.add(insertFacebookSinglePost);
        }

        this.facebookSinglePosts = sortByTime(this.facebookSinglePosts, 1);
    }

    private List<FacebookData> sortByTime(List<FacebookData> facebookDataList) {
        Collections.sort(facebookDataList, new Comparator<FacebookData>() {
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

        return facebookDataList;
    }

    private List<FacebookSinglePost> sortByTime(List<FacebookSinglePost> facebookSinglePostList, int flag) {
        Collections.sort(facebookSinglePostList, new Comparator<FacebookSinglePost>() {
            @Override
            public int compare(FacebookSinglePost facebookData1, FacebookSinglePost facebookData2) {

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

        return facebookSinglePostList;
    }

    public List<FacebookSinglePost> getFacebookSinglePosts() {
        List<FacebookSinglePost> facebookSinglePostList = new ArrayList<>();
        facebookSinglePostList.addAll(this.facebookSinglePosts);
        return facebookSinglePostList;
    }

    public boolean isFacebookDataListEmpty() {
        return this.facebookDataList.isEmpty();
    }

    public void addFacebookDataListIsDone() {
        this.tempFacebookDataList = sortByTime(this.tempFacebookDataList);

        if (this.facebookDataList == null) {
            this.facebookDataList = new ArrayList<>();
        }

        for (FacebookData facebookData1: this.tempFacebookDataList) {
            boolean itsDuplicate = false;

            for (FacebookData facebookData2: this.facebookDataList) {
                if (facebookData1.getId().equals(facebookData2.getId())) {
                    itsDuplicate = true;
                    break;
                }
            }

            if (!itsDuplicate) {
                this.facebookDataList.add(facebookData1);
            }
        }
    }
}
