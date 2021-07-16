package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import vo.MemberVO;
import util.ConnectionDB;

public class SignupDAO {
	private StringBuilder sql = new StringBuilder();
	
	//�ߺ� üũ
	public boolean idCheck(MemberVO memVo) throws Exception {
		sql.append("SELECT 1");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_ID = ?");
		
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, memVo.getId());
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	//���� ���� üũ
	public boolean signUpCheck(MemberVO memVo) throws Exception {
		sql.append("SELECT 1");
		sql.append("  FROM MEMBER");
		sql.append(" WHERE MEM_NM = ?");
		sql.append("   AND MEM_REGNO = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, memVo.getName());
		ps.setString(2, memVo.getRegno());
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	//15�� Ȯ��
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
	
	//���̵� ��ȿ�� �˻�
	public boolean insertIdCheck(String id) {
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{8,20}$";
		
		if(Pattern.matches(regex, id)) {
			return true;
		}
		return false;
	}
	
	//��й�ȣ ��ȿ�� �˻�
	public boolean insertPwCheck(String pw) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

		if(Pattern.matches(regex, pw)) {
			return true;
		}
		return false;
	}
	
	//��й�ȣ ���Է� ��ġ
	public boolean passWordMatchCheck(String currentPw, String targetPw) {
		if(currentPw.equals(targetPw)) {
			return true;
		}
		return false;
	}
}



































