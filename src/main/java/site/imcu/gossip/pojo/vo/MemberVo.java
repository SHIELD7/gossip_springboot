package site.imcu.gossip.pojo.vo;

import lombok.Data;

/**
 * @author ：menghe
 * Created in 2019/9/5 11:01
 */
@Data
public class MemberVo {
    private Integer memberId;
    private String memberName;
    private String avatar;
    private String token;

    private int gossipCount;
    private int followerCount;
    private int followingCount;
}
