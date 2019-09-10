package site.imcu.gossip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.gossip.mapper.RelationMapper;
import site.imcu.gossip.pojo.Relation;
import site.imcu.gossip.service.RelationService;

/**
 * @author ：menghe
 * Created in 2019/9/6 15:27
 */
@Service
public class RelationServiceImpl implements RelationService {
    private RelationMapper relationMapper;

    @Autowired
    public RelationServiceImpl(RelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    @Override
    public int addRelation(Relation relation) {
        Relation result = relationMapper.selectOne(new QueryWrapper<Relation>().eq("following_id", relation.getFollowingId()).eq("follower_id", relation.getFollowerId()));
        if (result==null){
            relation.setIsDel(0);
            return relationMapper.insert(relation);
        }else {
            result.setIsDel(0);
            return relationMapper.updateById(result);
        }
    }

    @Override
    public int deleteRelation(Relation relation) {
        Relation result = relationMapper.selectOne(new QueryWrapper<Relation>().eq("following_id", relation.getFollowingId()).eq("follower_id", relation.getFollowerId()).eq("is_del", 0));
        if (result!=null){
            result.setIsDel(1);
            return relationMapper.updateById(result);
        }else {
            return 1;
        }
    }

    @Override
    public boolean isRelationExisted(Relation relation) {
        Relation result = relationMapper.selectOne(new QueryWrapper<Relation>().eq("following_id", relation.getFollowingId()).eq("follower_id", relation.getFollowerId()).eq("is_del", 0));
        return result!=null;
    }

    @Override
    public int countFollower(Integer memberId) {
        return relationMapper.selectCount(new QueryWrapper<Relation>().eq("following_id",memberId));
    }

    @Override
    public int countFollowing(Integer memberId) {
        return relationMapper.selectCount(new QueryWrapper<Relation>().eq("follower_id",memberId));
    }
}
