package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import util.JDBCUtil;
import vo.MemberVO;

public class LoginDAO {
	private LoginDAO() {}
	private static LoginDAO instance;
	public static LoginDAO getLoginDAO() {
		if(instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}
	
	JDBCUtil util = JDBCUtil.getInstance();
	
	public boolean userCheck(MemberVO memVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND MEM_PW = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(memVo.getId());
		list.add(memVo.getPw());
		
		Map<String, Object> result = util.selectOne(sql.toString(), list);
		
		if(result != null) {
			return true;
		}
		return false;
	}
	
	public boolean changePw(String id, String pw) throws Exception {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

		if(!Pattern.matches(regex, pw)) {
			return false;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBER");
		sql.append("   SET MEM_PW = ?");
		sql.append(" WHERE MEM_ID = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(pw);
		list.add(id);
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		
		return false;
	}
}
