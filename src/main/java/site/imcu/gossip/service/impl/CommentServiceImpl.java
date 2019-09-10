package site.imcu.gossip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.gossip.mapper.CommentMapper;
import site.imcu.gossip.pojo.Comment;
import site.imcu.gossip.pojo.vo.CommentVo;
import site.imcu.gossip.service.CommentService;

/**
 * @author ：menghe
 * Created in 2019/9/6 18:22
 */
@Service
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteCommentById(Integer commentId,Integer memberId) {
        Comment comment = commentMapper.selectById(commentId);
        if (comment==null){
            return 0;
        }
        if (comment.getMemberId().equals(memberId)){
            comment.setIsDel(1);
            return commentMapper.updateById(comment);
        }else {
            return 0;
        }
    }

    @Override
    public int countCommentByGossipId(Integer gossipId) {
        return commentMapper.selectCount(new QueryWrapper<Comment>().eq("gossip_id",gossipId));
    }

    @Override
    public IPage<CommentVo> findCommentByGossipId(IPage<CommentVo> page, Integer gossipId) {
        return commentMapper.selectCommentWithMember(page,gossipId);
    }
}
