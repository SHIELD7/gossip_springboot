package site.imcu.gossip.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.imcu.gossip.pojo.vo.GossipVo;

import static org.junit.Assert.*;

/**
 * @author ：menghe
 * Created in 2019/9/6 16:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GossipMapperTest {

    @Autowired
    GossipMapper gossipMapper;
    @Test
    public void selectTimeLine() {
        IPage<GossipVo> gossipVoIPage = new Page<>();
        gossipMapper.selectTimeLine(gossipVoIPage,77);
    }
}