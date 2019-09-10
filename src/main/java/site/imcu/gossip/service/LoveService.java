package site.imcu.gossip.service;

import site.imcu.gossip.pojo.Love;

/**
 * @author ：menghe
 * Created in 2019/9/6 17:22
 */
public interface LoveService {
    /**
     * 添加喜欢
     * @param love 喜欢
     * @return 1 成功，0失败
     */
    int addLove(Love love);

    /**
     * 删除喜欢
     * @param love 喜欢
     * @return 1成功，0失败
     */
    int deleteLove(Love love);

    /**
     * 按闲话id查询喜欢数
     * @param gossipId
     * @return
     */
    int countLoveByGossipId(Integer gossipId);

    /**
     * 某人是否点赞
     * @param gossipId 闲话id
     * @param memberId 用户id
     * @return 是否
     */
    boolean inLoveWithGossip(Integer gossipId,Integer memberId);
}
