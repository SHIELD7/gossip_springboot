package site.imcu.gossip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.imcu.gossip.jwt.JWTUtil;
import site.imcu.gossip.pojo.Comment;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.bo.CommentBo;
import site.imcu.gossip.pojo.vo.CommentVo;
import site.imcu.gossip.service.CommentService;
import site.imcu.gossip.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ：menghe
 * Created in 2019/9/6 18:51
 */
@RestController
@CrossOrigin
@Slf4j
@RequiresAuthentication
public class CommentController {
    private CommentService commentService;
    private MemberService memberService;

    @Autowired
    public CommentController(CommentService commentService, MemberService memberService) {
        this.commentService = commentService;
        this.memberService = memberService;
    }

    @PostMapping("/comment")
    public GossipResult addComment(CommentBo commentBo, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        log.info("Token is " + token + commentBo.toString())  ;
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentBo,comment);
        comment.setCommentTime(new Date());
        comment.setMemberId(member.getMemberId());
        int result = commentService.addComment(comment);
        if (result == 1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    @GetMapping("/comment")
    public GossipResult listComment(Integer pageCurrent, Integer pageSize, Integer gossipId){
        IPage<CommentVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageCurrent);
        IPage<CommentVo> commentPage = commentService.findCommentByGossipId(page,gossipId);
        return GossipResult.success(fillData(commentPage));
    }

    @DeleteMapping("/comment")
    public GossipResult deleteComment(Integer commentId,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        int result = commentService.deleteCommentById(commentId,member.getMemberId());
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    private IPage<CommentVo> fillData(IPage<CommentVo> iPage){
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        for (CommentVo record : iPage.getRecords()) {
            record.setCommentTimeStr(bjSdf.format(record.getCommentTime()));
        }
        return iPage;
    }

}
