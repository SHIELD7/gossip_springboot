package site.imcu.gossip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.gossip.mapper.LoveMapper;
import site.imcu.gossip.pojo.Love;
import site.imcu.gossip.service.LoveService;

/**
 * @author ：menghe
 * Created in 2019/9/6 19:48
 */
@Service
public class LoveServiceImpl implements LoveService {
    private LoveMapper loveMapper;

    @Autowired
    public LoveServiceImpl(LoveMapper loveMapper) {
        this.loveMapper = loveMapper;
    }

    @Override
    public int addLove(Love love) {
        if (isLoveExisted(love)){
            return 1;
        }
        return loveMapper.insert(love);
    }

    @Override
    public int deleteLove(Love love) {
        return loveMapper.delete(new QueryWrapper<Love>().eq("gossip_id",love.getGossipId()).eq("member_id",love.getMemberId()));
    }

    @Override
    public int countLoveByGossipId(Integer gossipId) {
        return loveMapper.selectCount(new QueryWrapper<Love>().eq("gossip_id",gossipId));
    }

    @Override
    public boolean inLoveWithGossip(Integer gossipId, Integer memberId) {
        Love love = new Love();
        love.setMemberId(memberId);
        love.setGossipId(gossipId);
        return isLoveExisted(love);
    }

    private boolean isLoveExisted(Love love){
        Love result = loveMapper.selectOne(new QueryWrapper<Love>().eq("gossip_id",love.getGossipId()).eq("member_id",love.getMemberId()));
        return result != null;
    }
}
