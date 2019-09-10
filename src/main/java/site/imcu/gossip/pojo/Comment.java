package site.imcu.gossip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/6 17:17
 */
@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer memberId;
    private Integer gossipId;
    private Date commentTime;
    private String commentContent;
    private int isDel;
}
