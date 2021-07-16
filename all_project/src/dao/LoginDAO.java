package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import vo.MemberVO;
import util.ConnectionDB;

public class LoginDAO {
	
	public boolean userCheck(MemberVO memVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND MEM_PW = ?");
		
		
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, memVo.getId());
		ps.setString(2, memVo.getPw());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
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
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, pw);
		ps.setString(2, id);
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		
		return false;
	}
}