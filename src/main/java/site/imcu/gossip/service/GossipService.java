package site.imcu.gossip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.pojo.vo.GossipVo;

/**
 * @author ：menghe
 * Created in 2019/9/5 17:53
 */
public interface GossipService {
    /**
     * 添加闲话
     * @param gossip 闲话
     * @return 1成功0失败
     */
    int addGossip(Gossip gossip);

    /**
     * 按id删除
     * @param gossipId 闲话id
     * @return 1成功 0失败
     */
    int deleteGossip(Integer gossipId);

    /**
     * 按时间线查询
     * @param iPage 分页参数
     * @param memberId 用户id
     * @return
     */
    IPage<GossipVo> findTimeLineByPage(IPage<GossipVo> iPage,Integer memberId);

    /**
     * 所有
     * @param iPage 分页
     * @return 闲话
     */
    IPage<GossipVo> findAllGossip(IPage<GossipVo> iPage);

    /**
     * 按id
     * @param iPage 分页参数
     * @param memberId 用户id
     * @return 闲话
     */
    IPage<GossipVo> findByMemberId(IPage<GossipVo> iPage,Integer memberId);

    /**
     * 计算闲话数
     * @param memberId id
     * @return
     */
    int countGossip(Integer memberId);




}
