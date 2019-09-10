package site.imcu.gossip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.vo.MemberVo;

/**
 * @author ：menghe
 * Created in 2019/9/5 9:06
 */
public interface MemberService {
    /**
     * 按用户名查找
     * @param memberName 用户名
     * @return 用户
     */
    Member findMemberByName(String memberName);

    /**
     * 添加用户
     * @param member 用户
     * @return 1 成功 0失败
     */
    int addMember(Member member);

    /**
     * 查找粉丝
     * @param iPage 分页
     * @param memberId id
     * @return 粉丝
     */
    IPage<MemberVo> findFollower(IPage<MemberVo> iPage,Integer memberId);

    /**
     * 查找关注的用户
     * @param iPage 分页
     * @param memberId id
     * @return 关注
     */
    IPage<MemberVo> findFollowing(IPage<MemberVo> iPage,Integer memberId);

    /**
     * 更新用户信息
     * @param member 用户
     * @return 1成功 2失败
     */
    int updateMember(Member member);
}
