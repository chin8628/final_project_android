package kmitl.fina.boonyarith58070077.bnk48feed;

import org.junit.Test;

import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MemberUnitTest implements Member.memberListener {

    @Test
    public void clearMemberFilterWhenEmpty() {
        Member.clearFilter();
        assertEquals(0, Member.getMemberFilter().size());
    }

    @Test
    public void clearMemberFilterWhenNotEmpty() {
        Member.addFilter("can");
        Member.addFilter("pun");
        Member.clearFilter();
        assertEquals(0, Member.getMemberFilter().size());
    }

    @Test
    public void addMemberFilter() {
        Member.addFilter("can");
        Member.addFilter("pun");
        Member.addFilter("music");

        assertTrue("Can should be in filter.", Member.getMemberFilter().contains("can"));
        assertTrue("Pun should be in filter.", Member.getMemberFilter().contains("pun"));
        assertTrue("Music should be in filter.", Member.getMemberFilter().contains("music"));
        assertEquals(3, Member.getMemberFilter().size());
    }

    @Test
    public void getAllMember() {
        String[] member = {
                "can", "cherprang", "izutarina", "jaa", "jan", "jane", "jennis", "jib", "kaew", "kaimook",
                "kate", "korn", "maysa", "mind", "miori", "mobile", "music", "namhom", "namneung", "namsai",
                "nink", "noey", "orn", "piam", "pun", "pupe", "satchan", "tarwaan"
        };

        for (String name: member) {
            assertTrue("Member in MemberList is missing.", Member.getMember().contains(name));
        }
    }

    @Test
    public void addMemberToAlreadyGetMemberFromApi() {
        Member member = new Member(this);
        member.addAlreadyCallApiMember("pun");
        member.addAlreadyCallApiMember("can");
        member.addAlreadyCallApiMember("jaa");
        member.addAlreadyCallApiMember("jan");
        member.addAlreadyCallApiMember("orn");

        assertEquals(5, Member.getMemberAlreadyGetData().size());
    }

    @Test
    public void getNameSystemByFacebookId() {
        assertEquals("can", Member.getNameSystem("1101940286600866"));
        assertEquals("tarwaan", Member.getNameSystem("1853455571604493"));
        assertEquals("piam", Member.getNameSystem("602655736590296"));
        assertEquals("kaimook", Member.getNameSystem("1847059015573535"));
        assertEquals("mind", Member.getNameSystem("1485756048150292"));
    }

    @Test
    public void ClearAllMemberWasSearchAfterAddMemberWasSearch() {
        String[] member = {
                "can", "cherprang", "izutarina", "jaa", "jan", "jane", "jennis", "jib", "kaew", "kaimook",
                "kate", "korn", "maysa", "mind", "miori", "mobile", "music", "namhom", "namneung", "namsai",
                "nink", "noey", "orn", "piam", "pun", "pupe", "satchan", "tarwaan"
        };

        Member memberClass = new Member(this);
        for(String name: member) {
            memberClass.addAlreadyCallApiMember(name);
        }

        Member.clearMemberAlreadyGetData();
        assertEquals(0, Member.getMemberAlreadyGetData().size());
    }

    @Test
    public void checkIsMemberFilterIsEmpty() {
        Member.addFilter("can");
        Member.addFilter("pun");
        Member.addFilter("music");

        Member.clearFilter();

        assertTrue("Need True when Member filter is empty", Member.isMemberFilterIsEmpty());
    }

    @Override
    public void onAllMemberWasSearched() {

    }
}
