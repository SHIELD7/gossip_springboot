package site.imcu.gossip.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.imcu.gossip.jwt.JWTUtil;
import site.imcu.gossip.pojo.GossipResult;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.Relation;
import site.imcu.gossip.service.MemberService;
import site.imcu.gossip.service.RelationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：menghe
 * Created in 2019/9/8 16:51
 */
@RestController
@RequiresAuthentication
@CrossOrigin
public class RelationController {
    private RelationService relationService;
    private MemberService memberService;

    @Autowired
    public RelationController(RelationService relationService, MemberService memberService) {
        this.relationService = relationService;
        this.memberService = memberService;
    }

    @PostMapping("/relation")
    public GossipResult addRelation(Integer followingId, HttpServletRequest httpServletRequest){
        return handleRelation(followingId, httpServletRequest, 1);
    }
    @DeleteMapping("/relation")
    public GossipResult deleteRelation(Integer followingId,HttpServletRequest httpServletRequest){
        return handleRelation(followingId, httpServletRequest, 0);
    }

    private GossipResult handleRelation(Integer followingId,HttpServletRequest httpServletRequest,Integer action){
        String token = httpServletRequest.getHeader("Token");
        Member member = memberService.findMemberByName(JWTUtil.getUsername(token));
        Relation relation = new Relation();
        relation.setFollowerId(member.getMemberId());
        relation.setFollowingId(followingId);
        int result = 0;
        if (action==1){
            result = relationService.addRelation(relation);
        }
        if(action==0){
            result = relationService.deleteRelation(relation);
        }
        if (result==1){
            return GossipResult.success();
        }else {
            return GossipResult.error();
        }
    }
}
