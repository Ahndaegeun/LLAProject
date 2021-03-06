package dao;

import util.JDBCUtil;
import vo.CharacterVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.SkillsVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CharacterDAO {
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	private int result;
	
	private static CharacterDAO instance;
	private CharacterDAO() {}
	public static CharacterDAO getCharacterDAO() {
		if(instance == null) {
			instance = new CharacterDAO();
		}
		return instance;
	}
	
	public boolean createCharacter(CharacterVO charVo) throws Exception {
		List<Object> list = new ArrayList<>();
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
		
		list.add(charVo.getCharName());
		list.add(charVo.getMemId());
		list.add("모험가");
		list.add(1);
		
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteCharacter(CharacterVO charVo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CHARACTERS");
		sql.append(" WHERE MEM_ID = ?");
		sql.append("   AND CHAR_IDX = ?");
		
		list.add(charVo.getMemId());
		list.add(charVo.getCharIdx());
		
		result = jdbc.update(sql.toString(), list);
		
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public List<CharacterVO> showAllCharacter(MemberVO vo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CHAR_NM,");
		sql.append("	            CHAR_LEV,");
		sql.append("	            JOB,");
		sql.append("	            CHAR_IDX,");
		sql.append("	            MEM_ID");
		sql.append("   FROM CHARACTERS");
		sql.append(" WHERE MEM_ID = ?");
		
		list.add(vo.getId());
		
		List<Map<String, Object>> result = jdbc.selectList(sql.toString(), list);
		List<CharacterVO> charList = new ArrayList<>();
		
		if(result.size() == 0) {
			return null;
		}
		
		for(int i = 0; i < result.size(); i++) {
			CharacterVO character = new CharacterVO();
			character.setCharName((String)result.get(i).get("CHAR_NM"));
			character.setCharLevel(Integer.parseInt((result.get(i).get("CHAR_LEV")+"")));
			character.setJob((String)result.get(i).get("JOB"));
			character.setCharIdx(Integer.parseInt(result.get(i).get("CHAR_IDX")+""));
			character.setMemId((String)result.get(i).get("MEM_ID"));
			charList.add(character);
		}
		
		return charList;
	}
	
	public CharacterVO showCharacterInfo(CharacterVO vo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CHAR_IDX,");
		sql.append("	   CHAR_NM,");
		sql.append("	   CHAR_HP,");
		sql.append("	   CHAR_MAX_HP,");
		sql.append("	   CHAR_MP,");
		sql.append("	   CHAR_MAX_MP,");
		sql.append("	   CHAR_LEV,");
		sql.append("	   CHAR_EXE,");
		sql.append("	   CHAR_MAX_EXE,");
		sql.append("	   CHAR_ATT,");
		sql.append("	   CHAR_DEF,");
		sql.append("	   NVL(CHAR_WEAPON, '없음') AS CHAR_WEAPON,");
		sql.append("	   NVL(CHAR_ARMOR, '없음') AS CHAR_ARMOR,");
		sql.append("	   CHAR_GOLD,");
		sql.append("	   MEM_ID,");
		sql.append("	   JOB,");
		sql.append("	   FLOOR");
		sql.append("  FROM CHARACTERS");
		sql.append(" WHERE CHAR_IDX = ?");
	
		list.add(vo.getCharIdx());
		Map<String, Object> map =  jdbc.selectOne(sql.toString(), list);
		List<Object> keys = new ArrayList<>();
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			keys.add(entry.getKey());
		}
		
		CharacterVO character = new CharacterVO(
				Integer.parseInt(map.get("CHAR_IDX") + ""), 
				map.get("CHAR_NM") + "",
				Integer.parseInt(map.get("CHAR_HP") + ""), 
				Integer.parseInt(map.get("CHAR_MAX_HP") + ""), 
				Integer.parseInt(map.get("CHAR_MP") + ""), 
				Integer.parseInt(map.get("CHAR_MAX_MP") + ""), 
				Integer.parseInt(map.get("CHAR_LEV") + ""), 
				Integer.parseInt(map.get("CHAR_EXE") + ""), 
				Integer.parseInt(map.get("CHAR_MAX_EXE") + ""), 
				Integer.parseInt(map.get("CHAR_ATT") + ""), 
				Integer.parseInt(map.get("CHAR_DEF") + ""), 
				(String)map.get("CHAR_WEAPON"), 
				(String)map.get("CHAR_ARMOR"), 
				Integer.parseInt(map.get("CHAR_GOLD") + ""), 
				(String)map.get("MEM_ID"), 
				(String)map.get("JOB"),
				Integer.parseInt(map.get("FLOOR") + ""));
		
		return character;
	}
	
	public boolean unEquippingWeapon(CharacterVO vo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET CHAR_WEAPON = NULL");
		sql.append("	 WHERE CHAR_IDX = ?");

		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean unEquippingArmor(CharacterVO vo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET CHAR_ARMOR = NULL");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean beingAtt(CharacterVO vo, double damage) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_HP = CHAR_HP - ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		list.add((int)(damage / 10));
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}

	public boolean addGold(CharacterVO vo, int gold) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_GOLD = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		list.add(vo.getCharGold() + gold);
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean descGold(CharacterVO vo, int gold) {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_GOLD = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		list.add(vo.getCharGold() - gold);
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean addItem(CharacterVO vo, ItemVO item){
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO INVENTORY VALUES (");
		sql.append(" 	?,");
		sql.append(" 	?,");
		sql.append(" 	1,");
		sql.append(" 	?)");
		
		list.add(item.getItemName());
		list.add(vo.getCharIdx());
		list.add(item.getDitin());
		
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean increaseItem(CharacterVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE INVENTORY ");
		sql.append("   SET ITEM_CO = ITEM_CO + 1");
		sql.append(" WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		if(result > 0) {
			return true;
		}
		
		return false;
	}

	public double basicAtt(CharacterVO vo) {
		double damage = vo.getCharAtt() * 1.2;
		return damage;
	}
	
	public SkillsVO skillAtt(CharacterVO charVo, SkillsVO skillVo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT S.SKILL_ATT,");
		sql.append("	    S.SKILL_MP,");
		sql.append("	    S.SKILL_LEV,");
		sql.append("	    S.SKILL_NM,");
		sql.append("	    S.JOB");
		sql.append("   FROM SKILLS S, CHARACTERS C");
		sql.append("  WHERE S.JOB = C.JOB");
		sql.append("    AND S.SKILL_NM = ?");
		sql.append("    AND C.CHAR_IDX = ?");
		
		list.add(skillVo.getSkillNm());
		list.add(charVo.getCharIdx());
		Map<String, Object> result = jdbc.selectOne(sql.toString(), list);
		
		SkillsVO skill = new SkillsVO((String)result.get("SKILL_NM"), Integer.parseInt(result.get("SKILL_ATT") + ""),
				Integer.parseInt(result.get("SKILL_MP") + ""), 
				Integer.parseInt(result.get("SKILL_LEV") + ""),(String)result.get("JOB"));
		
		return skill;
	}
	
	public boolean beforSkillUse(CharacterVO vo, SkillsVO skillVo) throws Exception {
		List<Object> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("       SET CHAR_MP = ?");
		sql.append("     WHERE CHAR_IDX = ?");
		
		list.add(vo.getCharMp() - skillVo.getSkillMp());
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean getExe(CharacterVO vo, int exe) throws Exception {
		List<Object> list = new ArrayList<>();
		int increaseExe = vo.getCharExe() + exe;
		if(increaseExe >= vo.getCharMaxExe()) {
			int newExe = increaseExe - vo.getCharMaxExe();
			return levelUp(vo, newExe);
		}
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_EXE = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		list.add(vo.getCharExe() + exe);
		list.add(vo.getCharIdx());
		result = jdbc.update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean levelUp(CharacterVO vo, int leftExe) throws Exception {
		List<Object> list = new ArrayList<>();
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
		sql.append("        CHAR_LEV) = (SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? FROM DUAL)");
		sql.append(" WHERE CHAR_IDX = ?");
		
		list.add(newHp);
		list.add(newHp);
		list.add(newMp);
		list.add(newMp);
		list.add(newAtt);
		list.add(newDef);
		list.add(leftExe);
		list.add(newExe);
		list.add(nextLev);
		list.add(vo.getCharIdx());
		
		result = jdbc.update(sql.toString(), list);

		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean changeClass(CharacterVO vo, String job) {
		int hp = vo.getCharMaxHp();
		int mp = vo.getCharMaxMp();
		int att = vo.getCharAtt();
		int def = vo.getCharDef();
		
		if(job.equals("전사")) {
			hp *= 1.3;
			def *= 1.3;
		} else if (job.equals("마법사")) {
			mp *= 1.3;
			att *= 1.3;
		} else if (job.equals("도적")) {
			hp *= 1.3;
			att *= 1.3;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET (CHAR_HP, ");
		sql.append("        CHAR_MAX_HP,");
		sql.append("        CHAR_MP,");
		sql.append("        CHAR_MAX_MP,");
		sql.append("        CHAR_ATT,");
		sql.append("        CHAR_DEF,");
		sql.append("        JOB) = (");
		sql.append("        SELECT");
		sql.append("        ?, ");
		sql.append("        ?,");
		sql.append("        ?,");
		sql.append("        ?,");
		sql.append("        ?,");
		sql.append("        ?,");
		sql.append("        ?");
		sql.append("        FROM DUAL");
		sql.append("        )");
		sql.append(" WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(hp);
		list.add(hp);
		list.add(mp);
		list.add(mp);
		list.add(att);
		list.add(def);
		list.add(job);
		list.add(vo.getCharIdx());
		
		if(JDBCUtil.getInstance().update(sql.toString(), list) > 0) {
			return true;
		}
		return false;
	}
	
	public boolean die(CharacterVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET CHAR_HP = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharMaxHp() / 2);
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean run() {
		Random rnd = new Random();
		int percent = rnd.nextInt(2);
		if(percent == 1) {
			return true;
		}
		return false;
	}
	
	public boolean goUpOneFloor(CharacterVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("   SET FLOOR = FLOOR + 1");
		sql.append(" WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
		
	}
	
}