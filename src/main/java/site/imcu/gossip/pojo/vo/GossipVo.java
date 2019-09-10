package site.imcu.gossip.pojo.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/6 15:34
 */
@Data
public class GossipVo {
    private Integer gossipId;
    private Integer memberId;
    private Date postTime;
    private String content;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;
    private String pic6;
    private String pic7;
    private String pic8;
    private String pic9;
    private Integer original;
    private Integer repostId;

    private Integer repostCount;
    private Integer loveCount;
    private Integer commentCount;

    private String memberName;
    private String avatar;
    private Integer isFollowing;
    private Integer picCount;

    private boolean loved;

}
