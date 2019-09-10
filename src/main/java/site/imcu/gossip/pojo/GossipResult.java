package site.imcu.gossip.pojo;

import lombok.Data;

/**
 * @author menghe
 * @Description: 自定义响应数据结构
 * 				其他自行处理
 * 				200：表示成功
 * 			    403: 没有权限
 * 			    405: 没有登录
 * 			    406：密码错误
 * 			    407：用户名不存在
 * 			    408：token错误
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				555：异常抛出信息
 */
@Data
public class GossipResult {

    private Integer status;
    private String msg;
    private Object data;


    public static GossipResult build(Integer status, String msg, Object data) {
        return new GossipResult(status, msg, data);
    }
    public static GossipResult build(Integer status, String msg) {
        return new GossipResult(status, msg,null);
    }

    public static GossipResult success(Object data) {
        return new GossipResult(data);
    }

    public static GossipResult success() {
        return new GossipResult(null);
    }

    public static GossipResult error(){
        return new GossipResult(null,"",500);
    }


    public GossipResult() {
    }


    public GossipResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private GossipResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

}
