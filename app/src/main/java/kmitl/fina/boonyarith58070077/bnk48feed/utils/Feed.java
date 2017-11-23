package kmitl.fina.boonyarith58070077.bnk48feed.utils;

import java.util.ArrayList;
import java.util.List;

import kmitl.fina.boonyarith58070077.bnk48feed.database.Bookmark;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

public class Feed implements Api.apiListener, Member.memberListener, DatabasePortal.bookmarkDBListener {

    private Api api = new Api(this);
    private Feed.feedListener feedListener;

    @Override
    public void onGetBookmark(List<Bookmark> bookmarks) {
        for (Bookmark bookmark: bookmarks) {
            this.api.callSinglePostApi(bookmark.getId_post());
        }
    }

    public interface feedListener {
        void feedIsReady();
        void feedIsLoad(FacebookModel facebookModel);
        void feedIsLoad(FacebookSinglePost facebookSinglePost);
    }

    public Feed(Feed.feedListener feedListener) {
        this.feedListener = feedListener;
    }

    public void getFeed(String name) {
        this.api.callFeedApi(name);
    }

    public void getAllFeed() {
        Member.clearMemberAlreadyGetData();
        for (String member: Member.getMemberFilter()) {
            getFeed(member);
        }
    }

    public void getAllBookmark() {
        DatabasePortal databasePortal = new DatabasePortal(this);
        databasePortal.getAllBookmark();
    }

    @Override
    public void onFetchSuccess(String member_name, FacebookModel facebookModel) {
        Member member = new Member(this);
        member.addAlreadyCallApiMember(member_name);

        this.feedListener.feedIsLoad(facebookModel);
    }

    @Override
    public void onFetchSuccess(String member_name, FacebookSinglePost facebookSinglePost) {
        this.feedListener.feedIsLoad(facebookSinglePost);
    }

    @Override
    public void onFetchFailed(String member_name) {

    }

    @Override
    public void onAllMemberWasSearched() {
        this.feedListener.feedIsReady();
    }
}
