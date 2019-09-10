package site.imcu.gossip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;
import site.imcu.gossip.pojo.Member;
import site.imcu.gossip.pojo.vo.MemberVo;

/**
 * @author ：menghe
 * Created in 2019/9/5 9:05
 */
@Repository
public interface MemberMapper extends BaseMapper<Member> {
    /**
     *列出粉丝
     * @param memberId id
     * @param iPage 分页
     * @return 用户
     */
    IPage<MemberVo> selectFollower(IPage<MemberVo> iPage, Integer memberId);

    /**
     * 列出关注的用户
     * @param memberId id
     * @param iPage 分页
     * @return 用户
     */
    IPage<MemberVo> selectFollowing(IPage<MemberVo> iPage, Integer memberId);
}
