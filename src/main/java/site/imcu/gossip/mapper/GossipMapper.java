package site.imcu.gossip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.pojo.vo.GossipVo;

import java.util.List;

/**
 * @author ：menghe
 * Created in 2019/9/5 17:51
 */
@Repository
public interface GossipMapper extends BaseMapper<Gossip> {
    /**
     * 按时间线查询
     * @param page 分页参数
     * @param memberId 用户id
     * @return 分页闲话
     */
    IPage<GossipVo> selectTimeLine(IPage<GossipVo> page, Integer memberId);


    /**
     * 查询所有
     * @param iPage 分页
     * @return
     */
    IPage<GossipVo> selectGossipVoPage(IPage<GossipVo> iPage);

    /**
     * 按用户id查找
     * @param iPage 分页参数
     * @param memberId 用户id
     * @return 用户闲话
     */
    IPage<GossipVo> selectGossipVoByMemberId(IPage<GossipVo> iPage,Integer memberId);
}
