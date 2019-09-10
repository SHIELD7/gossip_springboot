package site.imcu.gossip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import site.imcu.gossip.pojo.Comment;
import site.imcu.gossip.pojo.vo.CommentVo;

import java.util.List;

/**
 * @author ：menghe
 * Created in 2019/9/6 17:22
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment 评论
     * @return 1 成功，0失败
     */
    int addComment(Comment comment);

    /**
     * 删除评论
     * @param commentId 评论id
     * @param memberId id
     * @return 1成功，0失败
     */
    int deleteCommentById(Integer commentId,Integer memberId);

    /**
     * 按闲话id查询喜欢数
     * @param gossipId 闲话id
     * @return 评论数
     */
    int countCommentByGossipId(Integer gossipId);

    /**
     * 按闲话id查找评论
     * @param page 分页
     * @param gossipId id
     * @return 评论
     */
    IPage<CommentVo> findCommentByGossipId(IPage<CommentVo> page,Integer gossipId);
}
