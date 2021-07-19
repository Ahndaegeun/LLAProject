package util;

import vo.MemberVO;

public class LoginSessionUtil {
	private LoginSessionUtil() {}
	private static LoginSessionUtil instance;
	private static MemberVO user;
	public static LoginSessionUtil getInstance() {
		if(instance == null) {
			instance = new LoginSessionUtil();
		}
		return instance;
	}
	public LoginSessionUtil getSession() {
		return instance;
	}
	public void setInstance(LoginSessionUtil instance) {
		LoginSessionUtil.instance = instance;
	}
	public void setUser(MemberVO user) {
		LoginSessionUtil.user = user;
	}
	public MemberVO getUser() {
		return user;
	}
	
}
