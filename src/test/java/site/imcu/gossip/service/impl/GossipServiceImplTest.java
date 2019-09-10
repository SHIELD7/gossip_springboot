package site.imcu.gossip.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.imcu.gossip.pojo.Gossip;
import site.imcu.gossip.service.GossipService;

import static org.junit.Assert.*;

/**
 * @author ：menghe
 * Created in 2019/9/7 18:31
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class GossipServiceImplTest {
    @Autowired
    GossipService gossipService;

    @Test
    public void addGossip() {
        Gossip gossip = new Gossip();
        gossip.setContent("12300");
        gossip.setMemberId(77);
        log.info(gossip.toString());
        gossipService.addGossip(gossip);
    }

    @Test
    public void deleteGossip() {
    }

    @Test
    public void findTimeLineByPage() {
    }
}