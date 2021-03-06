package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.InventoryVO;

public class InventoryDAO {
	private InventoryDAO() {}
	private static InventoryDAO intance;
	public static InventoryDAO getInvetoryDAO() {
		if(intance == null) {
			intance = new InventoryDAO();
		}
		return intance;
	}
	
	public boolean insertItem(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO INVENTORY VALUES (");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?");
		sql.append(")");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getItemName());
		list.add(vo.getCharIdx());
		list.add(vo.getItemCo());
		list.add(vo.getDitin());

		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		

		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public List<InventoryVO> showInventory(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ITEM_NM, CHAR_IDX, ITEM_CO, DITIN");
		sql.append("  FROM INVENTORY");
		sql.append(" WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		List<Map<String, Object>> map = JDBCUtil.getInstance().selectList(sql.toString(), list);
		List<InventoryVO> result = new ArrayList<>();
		
		for(int i = 0; i < map.size(); i++) {
			InventoryVO inven = new InventoryVO(
					(String)map.get(i).get("ITEM_NM"), 
					Integer.parseInt(map.get(i).get("CHAR_IDX") + ""),
					Integer.parseInt(map.get(i).get("ITEM_CO") + ""),
					(String)map.get(i).get("DITIN"));
			result.add(inven);
		}
		
		return result;
	}
	
	public boolean useItemMinus(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE INVENTORY");
		sql.append("   SET ITEM_CO = ?");
		sql.append(" WHERE CHAR_IDX = ?");
		sql.append("   AND ITEM_NM = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getItemCo() - 1);
		list.add(vo.getCharIdx());
		list.add(vo.getItemName());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean useItemDelete(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM INVENTORY");
		sql.append(" WHERE CHAR_IDX = ?");
		sql.append("   AND ITEM_NM = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		list.add(vo.getItemName());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean useItemChangeUser(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET (CHAR_HP, CHAR_MP, CHAR_ATT, CHAR_DEF) = (SELECT I.ITEM_HP + C.CHAR_HP,");
		sql.append("													        I.ITEM_MP + C.CHAR_MP,");
		sql.append("													        I.ITEM_ATT + C.CHAR_ATT,");
		sql.append("													        I.ITEM_DEF + C.CHAR_DEF");
		sql.append("													   FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("													  WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("													    AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("													    AND IV.CHAR_IDX = ?");
		sql.append("													    AND IV.ITEM_NM = ?)");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		list.add(vo.getItemName());
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean mountingWeapon(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET (CHAR_WEAPON , CHAR_ATT, CHAR_DEF) = (SELECT I.ITEM_NM,");
		sql.append("												        I.ITEM_ATT + C.CHAR_ATT,");
		sql.append("												        I.ITEM_DEF + C.CHAR_DEF");
		sql.append("												   FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("												  WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("												    AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("												    AND IV.CHAR_IDX = ?");
		sql.append("												    AND I.ITEM_NM = ?)");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		list.add(vo.getItemName());
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean mountingArmor(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET (CHAR_ARMOR, CHAR_ATT, CHAR_DEF) = (SELECT I.ITEM_NM,");
		sql.append("												       I.ITEM_ATT + C.CHAR_ATT,");
		sql.append("												       I.ITEM_DEF + C.CHAR_DEF");
		sql.append("												  FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("												 WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("												   AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("												   AND IV.CHAR_IDX = ?");
		sql.append("												   AND I.ITEM_NM = ?)");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getCharIdx());
		list.add(vo.getItemName());
		list.add(vo.getCharIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
}