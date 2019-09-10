package site.imcu.gossip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/6 17:20
 */
@Data
public class Love {
    @TableId(type = IdType.AUTO)
    private Integer loveId;
    private Integer memberId;
    private Integer gossipId;
    private Date loveTime;
}
