package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import vo.MemberVO;
import util.JDBCUtil;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO instance;
	public static MemberDAO getMemberDAO() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public boolean idCheck(MemberVO memVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT MEM_ID");
		sql.append("    FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(memVo.getId());
		
		Map<String, Object> result = jdbc.selectOne(sql.toString(), list);
		
		if(result != null) {
			return true;
		}
		return false;
	}
	
	public boolean signUpCheck(MemberVO memVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_NM = ?");
		sql.append("   AND MEM_REGNO = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(memVo.getName());
		list.add(memVo.getRegno());
		
		Map<String, Object> result =  jdbc.selectOne(sql.toString(), list);
		
		if(result != null) {
			return true;
		}
		return false;
	}
	
	public boolean ageCheck(String regNo) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int curruntYear = Integer.parseInt(dateFormat.format(date));
		
		int firstNo = Integer.parseInt(regNo) / 100000;
		
		if(firstNo <= 2) {
			int regNoYear = Integer.parseInt(regNo) / 10000;
			regNoYear += 2000;
			if(curruntYear - regNoYear < 15) {
				return true;
			}
		}
		return false;
	}
	
	public boolean insertIdCheck(String id) {
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{8,20}$";
		if(Pattern.matches(regex, id)) {
			return false;
		}
		return true;
	}
	
	public boolean insertPwCheck(String pw) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

		if(Pattern.matches(regex, pw)) {
			return false;
		}
		return true;
	}
	
	public boolean passWordMatchCheck(String currentPw, String targetPw) {
		if(currentPw.equals(targetPw)) {
			return true;
		}
		return false;
	}
	
	public boolean insertMember(MemberVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO MEMBER VALUES(");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?");
		sql.append(")");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getId());
		list.add(vo.getName());
		list.add(vo.getPw());
		list.add(vo.getRegno());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public MemberVO returId(MemberVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT MEM_ID");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_NM = ?");
		sql.append("   AND MEM_REGNO = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getName());
		list.add(vo.getRegno());
		
		Map<String, Object> map= JDBCUtil.getInstance().selectOne(sql.toString(), list);
		
		if(map == null) {
			return null;
		}
		return new MemberVO((String)map.get("MEM_ID"));
	}
	
	public boolean resetPassword(MemberVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MEMBER");
		sql.append("        SET MEM_PW = (SELECT MEM_REGNO ");
		sql.append("				                             FROM MEMBER");
		sql.append("				                          WHERE MEM_ID = ?");
		sql.append("				                                AND MEM_NM = ?");
		sql.append("				                                AND MEM_REGNO = ?)");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getId());
		list.add(vo.getName());
		list.add(vo.getRegno());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		if(result > 0) {
			return true;
		}
		return false;
	}
}