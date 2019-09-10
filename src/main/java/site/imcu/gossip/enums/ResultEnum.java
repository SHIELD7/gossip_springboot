package site.imcu.gossip.enums;

/**
 * 
 * @author Administrator
 * @Description: 返回状态码的枚举
 */
public enum ResultEnum {

	/**
	 * 操作成功
	 */
	SUCCESS(200, "成功"),

	/**
	 *必要参数为空
	 */
	ERROR_EMPTY(101,"必要参数为空"),

	/**
	 * 用户名存在
	 */
	ERROR_USERNAME_EXISTED(102,"用户名存在"),
	/**
	 * 用户不存在
	 */
	ERROR_USER_NOT_EXISTED(103,"用户不存在"),

	/**
	 * 密码错误
	 */
	ERROR_PASSWORD(104,"密码错误"),

	/**
	 * 数据添加错误
	 */
	ERROR_INSERT(110,"数据添加错误"),


	/**
	 * 数据查询错误
	 */
	ERROR_SELECT(111,"数据查询错误"),

	/**
	 * 数据更新错误
	 */
	ERROR_UPDATE(112,"数据更新错误");


	public final Integer status;
	public final String content;

	ResultEnum(Integer status, String content){
		this.status = status;
		this.content = content;
	}

}
