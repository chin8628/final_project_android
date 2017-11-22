package kmitl.fina.boonyarith58070077.bnk48feed.model.member;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private final static String[] member = {
            "cherprang", "namhom", "tarwaan", "jennis", "jan", "pupe", "noey", "kate", "jane",
            "nink", "maysa", "namneung", "miori", "jaa", "kaew", "can", "mind", "orn", "namsai",
            "mobile", "music", "pun", "piam", "satchan", "jib", "korn", "kaimook", "izutarina"
    };
    private static List<String> memberAlreadyGetData = new ArrayList<String>();
    private static List<String> memberFilter = new ArrayList<String>();
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
        Member.memberAlreadyGetData.add(member);

        if (this.isAllMemberWasSearch()) {
            this.memberListener.onAllMemberWasSearched();
        }
    }

    private boolean isAllMemberWasSearch() {
        if (!Member.memberFilter.isEmpty()) {
            return Member.memberFilter.size() == Member.memberAlreadyGetData.size();
        }
        return Member.memberAlreadyGetData.size() == Member.member.length;
    }

    public static void addMemberFilter(String name) {
        if (!Member.getMemberFilter().contains(name)) {
            Member.memberFilter.add(name);
        }
    }

    public boolean isMemberFilterIsEmpty() {
        return Member.memberFilter.isEmpty();
    }

    public static void clearMemberAlreadyGetData() {
        Member.memberAlreadyGetData = new ArrayList<String>();
    }

    public static List<String> getMemberFilter() {
        return memberFilter;
    }

    public static void clearFilter() {
        Member.memberFilter = new ArrayList<String>();
    }
}
