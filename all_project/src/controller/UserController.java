package controller;

import dao.LoginDAO;
import dao.MemberDAO;
import util.LoginSessionUtil;
import vo.MemberVO;

public class UserController {
	private UserController() {}
	private static UserController intance ;
	
	public static UserController getLoginController() {
		if(intance == null) {
			intance = new UserController();
		}
		return intance;
	}
	
	public void login(MemberVO user) throws Exception {
		LoginDAO dao = LoginDAO.getLoginDAO();
		if(dao.userCheck(user)) {
			LoginSessionUtil.getInstance().setUser(user);
		}
	}
	
	public boolean SignUp(MemberVO vo, String pwCk) throws Exception {
		MemberDAO dao = MemberDAO.getMemberDAO();
		if(dao.signUpCheck(vo)) return false;
		if(dao.ageCheck(vo.getRegno())) return false;
		//if(!dao.insertIdCheck(vo.getId())) return false;
		if(dao.idCheck(vo)) return false;
		//if(!dao.passWordMatchCheck(vo.getPw(), pwCk)) return false;
		if(dao.insertMember(vo)) return true;
		return false;
	}
	
	public String searchId(MemberVO vo) {
		MemberDAO dao = MemberDAO.getMemberDAO();
		if(dao.returId(vo) != null) {
			return dao.returId(vo).getId();
		}
		return null;
	}
	
	public boolean resetPw(MemberVO vo) {
		MemberDAO dao = MemberDAO.getMemberDAO();
		if(dao.resetPassword(vo)) {
			return true;
		}
		return false;
	}
}
