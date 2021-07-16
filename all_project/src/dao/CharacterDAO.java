package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.ConnectionDB;

import vo.CharacterVO;

import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {
	
	public boolean createCharacter(CharacterVO charVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CHARACTERS (");
		sql.append("		CHAR_IDX,");
		sql.append("		CHAR_NM,");
		sql.append("		MEM_ID,");
		sql.append("		JOB,");
		sql.append("		FLOOR");
		sql.append("	) VALUES (");
		sql.append("		SEQ_CHARACTER_IDX.NEXTVAL,");
		sql.append("		?,");
		sql.append("		?,");
		sql.append("		?,");
		sql.append("		?");
		sql.append("	)");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, charVo.getCharName());
		ps.setString(2, charVo.getMemId());
		ps.setString(3, charVo.getJob());
		ps.setInt(4, charVo.getFloor());
		
		int result = ps.executeUpdate();
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteCharacter(CharacterVO charVo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CHARACTERS");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, charVo.getMemId());
		ps.setInt(2, charVo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public List<CharacterVO> showCharacter() throws Exception {
		List<CharacterVO> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CHAR_NM,");
		sql.append("	   CHAR_LEV,");
		sql.append("	   JOB,");
		sql.append("	   CHAR_IDX,");
		sql.append("	   MEM_ID");
		sql.append("  FROM CHARACTERS");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int idx = rs.getInt("CHAR_IDX");
			String name = rs.getString("CHAR_NM");
			int lev = rs.getInt("CHAR_LEV");
			String job = rs.getString("JOB");
			String memId = rs.getString("MEM_ID");
			list.add(new CharacterVO(idx, name, lev, job, memId));
		}
		
		
		return list;
	}
	
	public List<CharacterVO> showCharacter(String userId) throws Exception {
		List<CharacterVO> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM CHARACTERS");
		sql.append(" WHERE MEM_ID = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, userId);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int charIdx = rs.getInt("CHAR_IDX");
			String charName = rs.getString("CHAR_NM");
			int charHp = rs.getInt("CHAR_HP");
			int charMaxHp = rs.getInt("CHAR_MAX_HP");
			int charMp = rs.getInt("CHAR_MP");
			int charMaxMp = rs.getInt("CHAR_MAX_MP");
			int charLevel = rs.getInt("CHAR_LEV");
			int charExe = rs.getInt("CHAR_EXE");
			int charMaxExe = rs.getInt("CHAR_MAX_EXE");
			int charAtt = rs.getInt("CHAR_ATT");
			int charDef = rs.getInt("CHAR_DEF");
			String charWeapon = rs.getString("CHAR_WEAPON");
			String charArmor = rs.getString("CHAR_ARMOR");
			int charGold = rs.getInt("CHAR_GOLD");
			String memId = rs.getString("MEM_ID");
			String job = rs.getString("JOB");
			int floor = rs.getInt("FLOOR");
			list.add(new CharacterVO(charIdx, charName, charHp, charMaxHp, charMp, charMaxMp, charLevel, charExe, charMaxExe, charAtt, charDef, charWeapon, charArmor, charGold, memId, job, floor));
		}
		
		return list;
	}
	
	public CharacterVO showCharacterInfo(int idx) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM CHARACTERS");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, idx);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int charIdx = rs.getInt("CHAR_IDX");
			String charName = rs.getString("CHAR_NM");
			int charHp = rs.getInt("CHAR_HP");
			int charMaxHp = rs.getInt("CHAR_MAX_HP");
			int charMp = rs.getInt("CHAR_MP");
			int charMaxMp = rs.getInt("CHAR_MAX_MP");
			int charLevel = rs.getInt("CHAR_LEV");
			int charExe = rs.getInt("CHAR_EXE");
			int charMaxExe = rs.getInt("CHAR_MAX_EXE");
			int charAtt = rs.getInt("CHAR_ATT");
			int charDef = rs.getInt("CHAR_DEF");
			String charWeapon = rs.getString("CHAR_WEAPON");
			String charArmor = rs.getString("CHAR_ARMOR");
			int charGold = rs.getInt("CHAR_GOLD");
			String memId = rs.getString("MEM_ID");
			String job = rs.getString("JOB");
			int floor = rs.getInt("FLOOR");
			
			return new CharacterVO(charIdx, charName, charHp, charMaxHp, charMp, charMaxMp, charLevel, charExe, charMaxExe, charAtt, charDef, charWeapon, charArmor, charGold, memId, job, floor);
		}
		return null;
	}
	
	public boolean unEquippingWeapon(CharacterVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET CHAR_WEAPON = NULL");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean unEquippingArmor(CharacterVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET CHAR_ARMOR = NULL");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	// ���� ����
	public boolean beingAtt(CharacterVO vo, double damage) throws Exception {
		int newHp = (int)(vo.getCharHp() - (damage - vo.getCharDef()));
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_HP = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, newHp);
		ps.setInt(2, vo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	// ��� ȹ��
	public boolean addGold(CharacterVO vo, int gold) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_GOLD = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharGold() + gold);
		ps.setInt(2, vo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	// ������ ȹ��
	public boolean addItem(CharacterVO vo, String item) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO INVENTORY VALUES (");
		sql.append(" 	?,");
		sql.append(" 	?,");
		sql.append(" 	1)");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, item);
		ps.setInt(2, vo.getCharIdx());
		
		int result = ps.executeUpdate();
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	// �⺻����
	public double basicAtt(CharacterVO vo) {
		double damage = vo.getCharAtt() * 1.2;
		return damage;
	}
	
	// ��ų ��������
	public List<Integer> skillAtt(CharacterVO vo, String skillName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT S.SKILL_ATT,");
		sql.append("	    S.SKILL_MP,");
		sql.append("	    S.SKILL_LEV");
		sql.append("   FROM SKILLS S, CHARACTERS C");
		sql.append("  WHERE S.JOB = C.JOB");
		sql.append("    AND S.SKILL_NM = ?");
		sql.append("    AND C.CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, skillName);
		ps.setInt(2, vo.getCharIdx());
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		int att = rs.getInt("S.SKILL_ATT");
		int mp = rs.getInt("S.SKILL_MP");
		int lev = rs.getInt("S.SKILL_LEV");
		List<Integer> list = new ArrayList<>();
		list.add((int)(att * 1.2));
		list.add(mp);
		list.add(lev);
		return list;
	}
	
	//��ų ��� �� ĳ���� ���� ����
	public boolean beforSkillUse(CharacterVO vo, List<Integer> list) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("       SET CHAR_MP = ?");
		sql.append("     WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, list.get(1));
		ps.setInt(2, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	//����ġ ȹ��
	public boolean getExe(CharacterVO vo, int exe) throws Exception {
		int increaseExe = vo.getCharExe() + exe;
		if(increaseExe >= vo.getCharMaxExe()) {
			int newExe = increaseExe - vo.getCharMaxExe();
			return levelUp(vo, newExe);
		}
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_EXE = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharExe() + exe);
		ps.setInt(2, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	//������
	public boolean levelUp(CharacterVO vo, int leftExe) throws Exception {
		int newHp = (int)(vo.getCharMaxHp() * 1.3);
		int newMp = (int)(vo.getCharMaxMp() * 1.2);
		int newExe = (int)(vo.getCharMaxExe() * 1.3);
		int newAtt = (int)(vo.getCharAtt() * 1.1);
		int newDef = (int)(vo.getCharDef() * 1.1);
		int nextLev = vo.getCharLevel() + 1;
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET (CHAR_HP, ");
		sql.append("        CHAR_MAX_HP, ");
		sql.append("        CHAR_MP, ");
		sql.append("        CHAR_MAX_MP, ");
		sql.append("        CHAR_ATT, ");
		sql.append("        CHAR_DEF, ");
		sql.append("        CHAR_EXE, ");
		sql.append("        CHAR_MAX_EXE, ");
		sql.append("        CHAR_LEV) = (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, newHp);
		ps.setInt(2, newHp);
		ps.setInt(3, newMp);
		ps.setInt(4, newMp);
		ps.setInt(5, newAtt);
		ps.setInt(6, newDef);
		ps.setInt(7, leftExe);
		ps.setInt(8, newExe);
		ps.setInt(9, nextLev);
		ps.setInt(10, vo.getCharIdx());

		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
}

























