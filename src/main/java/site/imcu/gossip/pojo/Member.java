package site.imcu.gossip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ：menghe
 * Created in 2019/9/5 8:48
 */
@Data
public class Member {
    @TableId(type = IdType.AUTO)
    private Integer memberId;
    private String memberName;
    private String password;
    private String salt;
    private String avatar;
}
