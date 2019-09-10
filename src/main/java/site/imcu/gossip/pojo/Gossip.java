package site.imcu.gossip.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/5 17:40
 */
@Data
public class Gossip {
    @TableId(type = IdType.AUTO)
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
    private int isDel;
}
