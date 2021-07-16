package util;

public class LoginSessionUtil {
	private LoginSessionUtil() {}
	private static LoginSessionUtil instance;
	public static LoginSessionUtil getSession() {
		if(instance == null) {
			instance = new LoginSessionUtil();
		}
		return instance;
	}
	public LoginSessionUtil getInstance() {
		return instance;
	}
	public void setInstance(LoginSessionUtil instance) {
		LoginSessionUtil.instance = instance;
	}
	
	
}
