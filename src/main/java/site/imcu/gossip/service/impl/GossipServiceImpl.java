package site.imcu.gossip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.gossip.mapper.GossipMapper;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.pojo.vo.GossipVo;
import site.imcu.gossip.service.GossipService;

import java.util.List;

/**
 * @author ：menghe
 * Created in 2019/9/5 20:00
 */
@Slf4j
@Service
public class GossipServiceImpl implements GossipService {
    private GossipMapper gossipMapper;

    @Autowired
    public GossipServiceImpl(GossipMapper gossipMapper) {
        this.gossipMapper = gossipMapper;
    }

    @Override
    public int addGossip(Gossip gossip) {
        log.info(gossip.toString());
        return gossipMapper.insert(gossip);
    }

    @Override
    public int deleteGossip(Integer gossipId) {
        Gossip gossip = findById(gossipId);
        if (gossip==null){
            return 0;
        }
        gossip.setIsDel(1);
        int result = gossipMapper.updateById(gossip);
        if (result==1){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public IPage<GossipVo> findTimeLineByPage(IPage<GossipVo> iPage, Integer memberId) {
        return gossipMapper.selectTimeLine(iPage,memberId);
    }

    @Override
    public IPage<GossipVo> findAllGossip(IPage<GossipVo> iPage) {
        return gossipMapper.selectGossipVoPage(iPage);
    }

    @Override
    public IPage<GossipVo> findByMemberId(IPage<GossipVo> iPage, Integer memberId) {
        return gossipMapper.selectGossipVoByMemberId(iPage,memberId);
    }

    @Override
    public int countGossip(Integer memberId) {
        return gossipMapper.selectCount(new QueryWrapper<Gossip>().eq("member_id",memberId));
    }

    private Gossip findById(Integer gossipId){
        return gossipMapper.selectById(gossipId);
    }


}
