package kmitl.fina.boonyarith58070077.bnk48feed.model.utils;

import android.util.Log;

import kmitl.fina.boonyarith58070077.bnk48feed.model.DisplayModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

public class Feed implements Api.apiListener, Member.memberListener {

    private Api api = new Api(this);
    private Feed.feedListener feedListener;

    public interface feedListener {
        void feedIsReady();
        void feedIsLoad(FacebookModel facebookModel);
    }

    public Feed(Feed.feedListener feedListener) {
        this.feedListener = feedListener;
    }

    public void getFeed(String name) {
        this.api.callApi(name);
    }

    public void getAllFeed() {
        Member.clearMemberAlreadyGetData();
        for (String member: Member.getMemberFilter()) {
            getFeed(member);
        }
    }

    @Override
    public void onFetchSuccess(String member_name, FacebookModel facebookModel) {
        Member member = new Member(this);
        member.addAlreadyGotMember(member_name);

        this.feedListener.feedIsLoad(facebookModel);

        Log.d("www", Member.getMemberFilter().toString());
        Log.d("www", Member.getMemberAlreadyGetData().toString());
    }

    @Override
    public void onFetchFailed(String member_name) {
        Member member = new Member(this);
        member.addAlreadyGotMember(member_name);
        Log.d("www", Member.getMemberFilter().toString());
        Log.d("www", Member.getMemberAlreadyGetData().toString());
    }

    @Override
    public void onAllMemberWasSearched() {
        this.feedListener.feedIsReady();
    }
}
