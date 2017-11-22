package kmitl.fina.boonyarith58070077.bnk48feed.model.member;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private final static String[] member = {
            "cherprang", "namhom", "tarwaan", "jennis", "jan", "pupe", "noey", "kate", "jane",
            "nink", "maysa", "namneung", "miori", "jaa", "kaew", "can", "mind", "orn", "namsai",
            "mobile", "music", "pun", "piam", "satchan", "jib", "korn", "kaimook", "izutarina"
    };
    private List<String> memberAlreadyGetData = new ArrayList<String>();
    private List<String> memberFilter = new ArrayList<String>();
    private Member.memberListener memberListener;

    public interface memberListener {
        void onAllMemberWasSearched();
    }

    public Member(Member.memberListener memberListener) {
        this.memberListener = memberListener;
    }

    public static String[] getMember() {
        return member;
    }

    public void addAlreadyGotMember(String member) {
        this.memberAlreadyGetData.add(member);

        if (this.isAllMemberWasSearch()) {
            this.memberListener.onAllMemberWasSearched();
        }
    }

    private boolean isAllMemberWasSearch() {
        if (!this.memberFilter.isEmpty()) {
            return this.memberFilter.size() == this.memberAlreadyGetData.size();
        }
        return this.memberAlreadyGetData.size() == Member.member.length;
    }

    public void addMemberFilter(String name) {
        this.memberFilter.add(name);
    }

    public boolean isMemberFilterIsEmpty() {
        return this.memberFilter.isEmpty();
    }

    public void clearMemberAlreadyGetData() {
        this.memberAlreadyGetData = new ArrayList<String>();
    }

}
