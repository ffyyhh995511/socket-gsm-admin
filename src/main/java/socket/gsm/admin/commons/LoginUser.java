package socket.gsm.admin.commons;
/**
 * @author fangyunhe
 * @date 2017年9月27日 下午2:46:43
 * 
 */
public class LoginUser {
	/**
	 * 为了方便，账号密码都一样
	 */
	public static final String LOGIN[] ={"admin","superadmin"};
	
	/**
	 * 属于超级管理员
	 */
	public static final String SUPERLOGIN[] ={"superadmin"};
	
	private LoginUser() {
		
	}
	
	
	public static boolean checkUser(String username,String password){
		for (String item : LOGIN) {
			if(item.equals(username) && item.equals(password)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否已在会话中
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean checkSessionUser(String sessionUser){
		for (String item : LOGIN) {
			if(sessionUser.equals(item+"_"+item)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否超级超级管理员
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean checkAdminUser(String sessionUser){
		for (String item : SUPERLOGIN) {
			if(sessionUser.equals(item+"_"+item)){
				return true;
			}
		}
		return false;
	}
}
