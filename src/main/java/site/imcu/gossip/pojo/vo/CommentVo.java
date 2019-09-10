package site.imcu.gossip.pojo.vo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/6 19:37
 */
@Data
public class CommentVo {
    private Integer commentId;
    private Integer memberId;
    private Integer gossipId;
    private Date commentTime;
    private String commentContent;
    private String commentTimeStr;
    private String memberName;
    private String avatar;
}
