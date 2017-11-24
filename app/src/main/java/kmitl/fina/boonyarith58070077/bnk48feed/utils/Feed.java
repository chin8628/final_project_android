package kmitl.fina.boonyarith58070077.bnk48feed.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kmitl.fina.boonyarith58070077.bnk48feed.database.Bookmark;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

public class Feed implements Api.apiListener, Member.memberListener, DatabasePortal.bookmarkDBListener {

    private int type;
    private Api api = new Api(this);
    private Feed.feedListener feedListener;
    private List<String> bookmarkIdPostList = new ArrayList<>();
    private boolean thisIsBookmarkPage = false;
    private int numberAlreadyFetchBookmark = 0;

    public interface feedListener {
        void feedIsReady();
        void feedIsLoad(FacebookModel facebookModel, List<String> bookmarkIdPostList);
        void feedIsLoad(FacebookSinglePost facebookSinglePost, List<String> bookmarkIdPostList);
    }

    public Feed(Feed.feedListener feedListener, int type) {
        // type 0 = new feed
        // type 1 = bookmark feed

        this.type = type;
        this.feedListener = feedListener;
    }

    public void getFeed(String name) {
        this.api.callFeedApi(name);
    }

    public void getAllFeed() {
        getAllBookmark();
        Member.clearMemberAlreadyGetData();
        for (String member: Member.getMemberFilter()) {
            getFeed(member);
        }
    }

    public void getAllBookmark() {
        new DatabasePortal(this).getAllBookmark();
    }

    @Override
    public void onFetchSuccess(String member_name, FacebookModel facebookModel) {
        Member member = new Member(this);
        member.addAlreadyCallApiMember(member_name);

        this.feedListener.feedIsLoad(facebookModel, bookmarkIdPostList);
    }

    @Override
    public void onFetchSuccess(String member_name, FacebookSinglePost facebookSinglePost) {
        this.feedListener.feedIsLoad(facebookSinglePost, bookmarkIdPostList);
        this.numberAlreadyFetchBookmark += 1;
        if (this.numberAlreadyFetchBookmark == this.bookmarkIdPostList.size()) {
            this.feedListener.feedIsReady();
        }

        Log.d("www", this.numberAlreadyFetchBookmark + " " + this.bookmarkIdPostList.size());
    }

    @Override
    public void onFetchFailed(String member_name) {

    }

    @Override
    public void onGetBookmark(List<Bookmark> bookmarks) {
        this.numberAlreadyFetchBookmark = 0;

        for (Bookmark bookmark: bookmarks) {
            this.bookmarkIdPostList.add(bookmark.getId_post());

            if (thisIsBookmarkPage) {
                this.api.callSinglePostApi(bookmark.getId_post());
            }
        }
    }

    @Override
    public void onAllMemberWasSearched() {
        this.feedListener.feedIsReady();
    }

    public void setThisIsBookmarkPage(boolean thisIsBookmarkPage) {
        this.thisIsBookmarkPage = thisIsBookmarkPage;
    }
}
