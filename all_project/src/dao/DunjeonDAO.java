package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.CharacterVO;
import vo.DunjeonVO;
import vo.MonstersVO;

public class DunjeonDAO {
	private DunjeonDAO() {}
	private static DunjeonDAO instance;
	public static DunjeonDAO geDunjeonDAO() {
		if(instance == null) {
			instance = new DunjeonDAO();
		}
		return instance;
	}
	
	public DunjeonVO showDunjeonInfo(CharacterVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FLOOR,");
		sql.append(" 	    ADMFEE,");
		sql.append(" 	    MON_NM");
		sql.append("   FROM DUNJEON");
		sql.append("  WHERE FLOOR = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getFloor());
		
		Map<String, Object> map = JDBCUtil.getInstance().selectOne(sql.toString(), list);
		int floor = (Integer)map.get("FLOOR");
		int admfee = (Integer)map.get("ADMFEE");
		String monNm = (String)map.get("MON_NM");
		
		return new DunjeonVO(floor, admfee, monNm);
	}
	
	public MonstersVO getMonster(DunjeonVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT M.MON_NM,");
		sql.append(" 	    M.MON_HP,");
		sql.append(" 	    M.MON_ATT,");
		sql.append(" 	    M.MON_DEF,");
		sql.append(" 	    M.MON_GOLD");
		sql.append(" 	    M.MON_LEV,");
		sql.append(" 	    M.ITEM_NM");
		sql.append("   FROM MONSTERS M, DUNJEON D");
		sql.append("  WHERE M.MON_NM = D.MON_NM");
		sql.append("    AND D.FLOOR = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getFloor());
		
		Map<String, Object> map = JDBCUtil.getInstance().selectOne(sql.toString(), list);
		String name = (String)map.get("MON_NM");
		int hp = (Integer)map.get("MON_HP");
		int att = (Integer)map.get("MON_ATT");
		int def = (Integer)map.get("MON_DEF");
		int gold = (Integer)map.get("MON_GOLD");
		int lev = (Integer)map.get("MON_LEV");
		String item = (String)map.get("MON_NM");
		
		MonstersVO monster = new MonstersVO(name, hp, att, def, gold, lev, item);
		return monster;
	}
	
}
