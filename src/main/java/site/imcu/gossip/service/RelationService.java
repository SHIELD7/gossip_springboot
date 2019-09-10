package site.imcu.gossip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import site.imcu.gossip.pojo.Relation;

/**
 * @author ：menghe
 * Created in 2019/9/6 15:24
 */
public interface RelationService {
    /**
     * 添加关注
     * @param relation 关注
     * @return 1成功2失败
     */
    int addRelation(Relation relation);

    /**
     * 取消关注
     * @param relation 关注
     * @return 1成功2失败
     */
    int deleteRelation(Relation relation);

    /**
     * 关系是否存在
     * @param relation 关系
     * @return 1存在0不存在
     */
    boolean isRelationExisted(Relation relation);

    /**
     * 查询粉丝数
     * @param memberId id
     * @return 粉丝数
     */
    int countFollower(Integer memberId);

    /**
     * 查询关注数
     * @param memberId id
     * @return 关注数
     */
    int countFollowing(Integer memberId);

}
