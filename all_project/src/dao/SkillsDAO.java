package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.CharacterVO;
import vo.SkillsVO;

public class SkillsDAO {
	private SkillsDAO() {}
	private static SkillsDAO instance;
	public static SkillsDAO getSkillsDAO() {
		if(instance == null) {
			instance = new SkillsDAO();
		}
		return instance;
	}
	
	public List<SkillsVO> showSkillList(CharacterVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT S.SKILL_NM,");
		sql.append("   	   S.SKILL_ATT,");
		sql.append("   	   S.SKILL_MP,");
		sql.append("   	   S.SKILL_LEV,");
		sql.append("   	   J.JOB ");
		sql.append("  FROM SKILLS S, JOB J, CHARACTERS C");
		sql.append(" WHERE S.JOB = J.JOB");
		sql.append("   AND J.JOB = C.JOB");
		sql.append("   AND SKILL_LEV <= C.CHAR_LEV");
		sql.append("   AND C.CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		
		List<Map<String, Object>> map = JDBCUtil.getInstance().selectList(sql.toString(), list);
		
		List<SkillsVO> skillList = new ArrayList<>();
		for(Map<String, Object> entry : map) {
			SkillsVO skill = new SkillsVO();
			skill.setSkillNm((String)entry.get("SKILL_NM"));
			skill.setSkillAtt(Integer.parseInt(entry.get("SKILL_ATT") + ""));
			skill.setSkillMp(Integer.parseInt(entry.get("SKILL_MP") + ""));
			skill.setSkillLev(Integer.parseInt(entry.get("SKILL_LEV") + ""));
			skill.setJob((String)entry.get("JOB_NM"));
			skillList.add(skill);
		}
		
		return skillList;
	}
}
