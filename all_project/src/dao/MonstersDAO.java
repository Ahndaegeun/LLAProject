package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.JDBCUtil;
import vo.MonstersVO;

public class MonstersDAO {
	private MonstersDAO() {}
	private static MonstersDAO instance;
	public static MonstersDAO getMonsterDAO() {
		if(instance == null) {
			instance = new MonstersDAO();
		}
		return instance;
	}
	
	public List<MonstersVO> showMonsterList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM MONSTERS");
		
		List<Map<String, Object>> map = JDBCUtil.getInstance().selectList(sql.toString());
		List<MonstersVO> list = new ArrayList<>();
		
		for(int i = 0; i < map.size(); i++) {
			MonstersVO monster = new MonstersVO((map.get(i).get("MON_NM") + ""), 
					Integer.parseInt(map.get(i).get("MON_HP") + ""), 
					Integer.parseInt(map.get(i).get("MON_ATT") + ""), 
					Integer.parseInt(map.get(i).get("MON_DEF") + ""),
					Integer.parseInt(map.get(i).get("MON_GOLD") + ""), 
					Integer.parseInt(map.get(i).get("MON_LEV") + ""), 
					(String)map.get(i).get("ITEM_NM"));
			list.add(monster);
		}
		
		return list;
	}
	
	public double attMonster(MonstersVO vo) {
		double damage = vo.getMomAtt() * 1.2;
		return damage;
	}
	
	public MonstersVO defMonster(MonstersVO monVo, double userAtt) {
		double def = monVo.getMomDef() * monVo.getMomLev() / 10;
		
		double damage = monVo.getMomHp() - userAtt;
		
		if(damage - def >= monVo.getMomHp()) {
			monVo.setMomHp(0);
			return monVo;
		}
		monVo.setMomHp((int)damage);
		return monVo;
	}
	
	public int sendExe(MonstersVO vo) {
		return vo.getMomLev() * 10;
	}
	
	public String sendItem(MonstersVO vo) {
		String item = vo.getItemNm();
		return item;
	}
	
	
	public int sendGold(MonstersVO vo) {
		int gold = vo.getMomGold();
		return gold;
	}
}
