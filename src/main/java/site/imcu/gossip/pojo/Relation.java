package site.imcu.gossip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ：menghe
 * Created in 2019/9/5 17:40
 */
@Data
public class Relation {
    @TableId(type = IdType.AUTO)
    private Integer relationId;
    private Integer followingId;
    private Integer followerId;
    private Integer isDel;
}
