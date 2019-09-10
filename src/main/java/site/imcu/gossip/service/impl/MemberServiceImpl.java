package site.imcu.gossip.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.gossip.mapper.MemberMapper;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.vo.MemberVo;
import site.imcu.gossip.service.MemberService;

/**
 * @author ：menghe
 * Created in 2019/9/5 9:10
 */
@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member findMemberByName(String memberName) {
        return memberMapper.selectOne(new QueryWrapper<Member>().eq("member_name",memberName));
    }

    @Override
    public int addMember(Member member) {
        member.setAvatar("images/default.png");
        member.setSalt(UUID.randomUUID().toString());
        SimpleHash hash = new SimpleHash("md5",
                member.getPassword(), member.getSalt(), 2);
        member.setPassword(hash.toString());
        return memberMapper.insert(member);
    }

    @Override
    public IPage<MemberVo> findFollower(IPage<MemberVo> iPage, Integer memberId) {
        return memberMapper.selectFollower(iPage, memberId);
    }

    @Override
    public IPage<MemberVo> findFollowing(IPage<MemberVo> iPage, Integer memberId) {
        return memberMapper.selectFollowing(iPage, memberId);
    }

    @Override
    public int updateMember(Member member) {
        return memberMapper.updateById(member);
    }
}
