package site.imcu.gossip.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.imcu.gossip.jwt.JWTUtil;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.Relation;
import site.imcu.gossip.pojo.bo.GossipBo;
import site.imcu.gossip.pojo.vo.GossipVo;
import site.imcu.gossip.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/5 20:09
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/gossip")
@RequiresAuthentication
public class GossipController {
    private GossipService gossipService;
    private MemberService memberService;
    private CommentService commentService;
    private LoveService loveService;
    private RelationService relationService;

    @Autowired
    public GossipController(GossipService gossipService, MemberService memberService, CommentService commentService, LoveService loveService, RelationService relationService) {
        this.gossipService = gossipService;
        this.memberService = memberService;
        this.commentService = commentService;
        this.loveService = loveService;
        this.relationService = relationService;
    }

    @PostMapping("/post")
    public GossipResult postGossip(GossipBo gossipBo, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        Gossip gossip = new Gossip();
        BeanUtils.copyProperties(gossipBo,gossip);
        gossip.setMemberId(member.getMemberId());
        gossip.setOriginal(1);
        gossip.setPostTime(new Date());
        int result = gossipService.addGossip(gossip);
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    @GetMapping("/timeline")
    public GossipResult getTimeline(Integer pageCurrent, Integer pageSize,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        IPage<GossipVo> iPage = new Page<>();
        iPage.setCurrent(pageCurrent);
        iPage.setSize(pageSize);

        IPage<GossipVo> result = gossipService.findTimeLineByPage(iPage,member.getMemberId());

        return GossipResult.success(fillUpGossipVo(result,member.getMemberId()));
    }

    @GetMapping("/square")
    public GossipResult getSquare(Integer pageCurrent, Integer pageSize,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        IPage<GossipVo> iPage = new Page<>();
        iPage.setCurrent(pageCurrent);
        iPage.setSize(pageSize);
        IPage<GossipVo> result = gossipService.findAllGossip(iPage);
        return GossipResult.success(fillUpGossipVo(result,member.getMemberId()));
    }

    @GetMapping("/member_id")
    public GossipResult findById(Integer pageCurrent,Integer pageSize,Integer memberId,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        IPage<GossipVo> iPage = new Page<>();
        iPage.setCurrent(pageCurrent);
        iPage.setSize(pageSize);
        log.info("参数{}{}{}",memberId,pageCurrent,pageSize);
        IPage<GossipVo> result = gossipService.findByMemberId(iPage,memberId);
        return GossipResult.success(fillUpGossipVo(result,member.getMemberId()));

    }

    private IPage<GossipVo> fillUpGossipVo(IPage<GossipVo> page,Integer memberId){
        for (GossipVo gossipVo : page.getRecords()) {
            gossipVo.setLoveCount(loveService.countLoveByGossipId(gossipVo.getGossipId()));
            gossipVo.setCommentCount(commentService.countCommentByGossipId(gossipVo.getGossipId()));
            gossipVo.setPicCount(getPicCount(gossipVo));
            Relation relation = new Relation();
            relation.setFollowerId(memberId);
            relation.setFollowingId(gossipVo.getMemberId());
            gossipVo.setIsFollowing(relationService.isRelationExisted(relation)?1:0);
            if (gossipVo.getMemberId().equals(memberId)){
                gossipVo.setIsFollowing(-1);
            }
            gossipVo.setLoved(loveService.inLoveWithGossip(gossipVo.getGossipId(),memberId));

        }
        return page;
    }

    private static int getPicCount(GossipVo gossip){
        if (StrUtil.isEmpty(gossip.getPic1())) {
            return 0;
        }
        if (StrUtil.isEmpty(gossip.getPic2())) {
            return 1;
        }
        if (StrUtil.isEmpty(gossip.getPic3())) {
            return 2;
        }
        if (StrUtil.isEmpty(gossip.getPic4())) {
            return 3;
        }
        if (StrUtil.isEmpty(gossip.getPic5())) {
            return 4;
        }
        if (StrUtil.isEmpty(gossip.getPic6())) {
            return 5;
        }
        if (StrUtil.isEmpty(gossip.getPic7())) {
            return 6;
        }
        if (StrUtil.isEmpty(gossip.getPic8())) {
            return 7;
        }
        if (StrUtil.isEmpty(gossip.getPic9())) {
            return 8;
        }
        return 9;
    }
}
