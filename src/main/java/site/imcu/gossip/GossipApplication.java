package site.imcu.gossip;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("site.imcu.gossip.mapper")
public class GossipApplication {

    public static void main(String[] args) {
        SpringApplication.run(GossipApplication.class, args);
    }

}
