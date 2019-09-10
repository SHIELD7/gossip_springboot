package site.imcu.gossip.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.imcu.gossip.enums.ResultEnum;
import site.imcu.gossip.jwt.JWTUtil;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.bo.MemberBo;
import site.imcu.gossip.pojo.vo.MemberVo;
import site.imcu.gossip.service.GossipService;
import site.imcu.gossip.service.MemberService;
import site.imcu.gossip.service.RelationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：menghe
 * Created in 2019/9/5 10:45
 */
@Slf4j
@RestController
@CrossOrigin
public class MemberController {

    private MemberService memberService;
    private GossipService gossipService;
    private RelationService relationService;

    @Autowired
    public MemberController(MemberService memberService, GossipService gossipService, RelationService relationService) {
        this.memberService = memberService;
        this.gossipService = gossipService;
        this.relationService = relationService;
    }

    @PostMapping("/register")
    public GossipResult register(MemberBo memberBo){
        if (StrUtil.isEmpty(memberBo.getPassword())||StrUtil.isEmpty(memberBo.getMemberName())){
            return GossipResult.build(ResultEnum.ERROR_EMPTY.status,ResultEnum.ERROR_EMPTY.content);
        }

        Member member = memberService.findMemberByName(memberBo.getMemberName());
        if (member!=null){
            return GossipResult.build(ResultEnum.ERROR_USERNAME_EXISTED.status,ResultEnum.ERROR_USERNAME_EXISTED.content);
        }
        member = new Member();
        BeanUtils.copyProperties(memberBo,member);
        int result = memberService.addMember(member);
        if (result == 1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    @PostMapping("/login")
    public GossipResult login(MemberBo memberBo){
        if (StrUtil.isEmpty(memberBo.getPassword())||StrUtil.isEmpty(memberBo.getMemberName())){
            return GossipResult.build(ResultEnum.ERROR_EMPTY.status,ResultEnum.ERROR_EMPTY.content);
        }
        Member member = memberService.findMemberByName(memberBo.getMemberName());
        if (member==null){
            return GossipResult.build(ResultEnum.ERROR_USER_NOT_EXISTED.status,ResultEnum.ERROR_USER_NOT_EXISTED.content);
        }

        SimpleHash hash = new SimpleHash("md5",
                memberBo.getPassword(), member.getSalt(), 2);
        if (hash.toString().equals(member.getPassword())){
            MemberVo memberVo = new MemberVo();
            BeanUtils.copyProperties(member,memberVo);
            memberVo.setToken(JWTUtil.createToken(member.getMemberName()));
            memberVo = fillUpMemberVo(memberVo);
            return GossipResult.success(memberVo);
        }else {
            return GossipResult.build(ResultEnum.ERROR_PASSWORD.status,ResultEnum.ERROR_PASSWORD.content);
        }

    }

    @RequiresAuthentication
    @PutMapping("/avatar")
    public GossipResult changeAvatar(String avatar,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        member.setAvatar(avatar);
        int result = memberService.updateMember(member);
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    @RequiresAuthentication
    @GetMapping("/follower")
    public GossipResult findFollower(Integer pageCurrent, Integer pageSize, HttpServletRequest httpServletRequest){
        return findRelation(pageCurrent,pageSize,httpServletRequest,1);
    }

    @RequiresAuthentication
    @GetMapping("/following")
    public GossipResult findFollowing(Integer pageCurrent, Integer pageSize, HttpServletRequest httpServletRequest){
        return findRelation(pageCurrent,pageSize,httpServletRequest,0);
    }

    @RequiresAuthentication
    @GetMapping("/refresh")
    public GossipResult refresh(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));

        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);
        memberVo.setToken(JWTUtil.createToken(member.getMemberName()));
        fillUpMemberVo(memberVo);
        return GossipResult.success(memberVo);
    }

    private GossipResult findRelation(Integer pageCurrent, Integer pageSize,HttpServletRequest httpServletRequest,Integer action){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        IPage<MemberVo> iPage = new Page<>();
        iPage.setSize(pageSize);
        iPage.setCurrent(pageCurrent);
        IPage<MemberVo> result;
        if (action==1){
            result = memberService.findFollower(iPage, member.getMemberId());
        }else {
            result = memberService.findFollowing(iPage,member.getMemberId());
        }
        return GossipResult.success(result);
    }

    private MemberVo fillUpMemberVo(MemberVo memberVo){
        memberVo.setFollowerCount(relationService.countFollower(memberVo.getMemberId()));
        memberVo.setFollowingCount(relationService.countFollowing(memberVo.getMemberId()));
        memberVo.setGossipCount(gossipService.countGossip(memberVo.getMemberId()));
        return memberVo;
    }
}
