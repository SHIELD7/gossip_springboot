package site.imcu.gossip.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.imcu.gossip.jwt.JWTUtil;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.pojo.Love;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.service.LoveService;
import site.imcu.gossip.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ：menghe
 * Created in 2019/9/6 18:51
 */
@RestController
@CrossOrigin
@RequiresAuthentication
public class LoveController {
    private LoveService loveService;
    private MemberService memberService;

    @Autowired
    public LoveController(LoveService loveService, MemberService memberService) {
        this.loveService = loveService;
        this.memberService = memberService;
    }

    @PostMapping("/love")
    public GossipResult addLove(Integer gossipId, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        Love love = new Love();
        love.setGossipId(gossipId);
        love.setMemberId(member.getMemberId());
        love.setLoveTime(new Date());
        int result = loveService.addLove(love);
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }

    @DeleteMapping("/love")
    public GossipResult deleteLove(Integer gossipId, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        Love love = new Love();
        love.setGossipId(gossipId);
        love.setMemberId(member.getMemberId());
        int result = loveService.deleteLove(love);
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }
}
