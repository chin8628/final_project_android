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
        return this.memberAlreadyGetData.size() == Member.member.length;
    }

}
