package kmitl.fina.boonyarith58070077.bnk48feed.model.member;

import android.util.ArraySet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Member {

    private final static String[] member = {
            "cherprang", "namhom", "tarwaan", "jennis", "jan", "pupe", "noey", "kate", "jane",
            "nink", "maysa", "namneung", "miori", "jaa", "kaew", "can", "mind", "orn", "namsai",
            "mobile", "music", "pun", "piam", "satchan", "jib", "korn", "kaimook", "izutarina"
    };

    // If I have more time, this properties should be migrate to server instead this
    private final static HashMap<String, String> facebookID;
    static {
        facebookID = new HashMap<String, String>();
        facebookID.put("cherprang", "1244451205642938");
        facebookID.put("namhom", "191331801345573");
        facebookID.put("tarwaan", "1853455571604493");
        facebookID.put("jennis", "377477525964786");
        facebookID.put("jan", "1623170274656810");
        facebookID.put("pupe", "1784166245236547");
        facebookID.put("noey", "302183486851617");
        facebookID.put("kate", "404488706585305");
        facebookID.put("jane", "1836134889992813");
        facebookID.put("nink", "1155611761204422");
        facebookID.put("maysa", "224218921376880");
        facebookID.put("namneung", "626497777542846");
        facebookID.put("miori", "1747261302163006");
        facebookID.put("jaa", "678369835689176");
        facebookID.put("kaew", "244141829364129");
        facebookID.put("can", "1101940286600866");
        facebookID.put("mind", "1485756048150292");
        facebookID.put("orn", "737460709751015");
        facebookID.put("namsai", "1916912851863580");
        facebookID.put("mobile", "412136369140053");
        facebookID.put("music", "719127414916016");
        facebookID.put("pun", "1766861650299003");
        facebookID.put("piam", "602655736590296");
        facebookID.put("satchan", "1687652811533778");
        facebookID.put("jib", "1639661256329148");
        facebookID.put("korn", "1372941429435830");
        facebookID.put("kaimook", "1847059015573535");
        facebookID.put("izutarina", "192097191312105");
    }

    private static List<String> memberAlreadyGetData = new ArrayList<String>();
    private static Set<String> memberFilter = new HashSet<>(Arrays.asList(member));
    private Member.memberListener memberListener;

    public static void setMemberFilter(List<String> memberFilter) {
        Set<String> filter = new HashSet<>();
        filter.addAll(memberFilter);
        Member.memberFilter = filter;
    }

    public interface memberListener {
        void onAllMemberWasSearched();
    }

    public Member(Member.memberListener memberListener) {
        this.memberListener = memberListener;
    }

    public static List<String> getMember() {
        return new ArrayList<>(Arrays.asList(Member.member));
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
        List<String> member_filer = new ArrayList<>();
        member_filer.addAll(Member.memberFilter);
        return member_filer;
    }

    public static void clearFilter() {
        Member.memberFilter = new HashSet<>();
    }

    public static String getNameSystem(String facebookId) {
        for(Map.Entry<String, String> facebookIdItem: Member.facebookID.entrySet()) {
            if (facebookIdItem.getValue().equals(facebookId)) {
                return facebookIdItem.getKey();
            }
        }

        return "not found";
    }
}
