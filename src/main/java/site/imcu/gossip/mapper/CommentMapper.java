package site.imcu.gossip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;
import site.imcu.gossip.pojo.Comment;
import site.imcu.gossip.pojo.vo.CommentVo;

/**
 * @author ：menghe
 * Created in 2019/9/6 17:21
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查找评论
     * @param page 分页
     * @param gossipId id
     * @return 评论
     */
    IPage<CommentVo> selectCommentWithMember(IPage<CommentVo> page,Integer gossipId);
}
