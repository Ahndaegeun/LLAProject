package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionDB;

import vo.InventoryVO;

public class InventoryDAO {
	
	public boolean insertItem(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO INVENTORY VALUES (");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?");
		sql.append(")");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, vo.getItemName());
		ps.setInt(2, vo.getCharIdx());
		ps.setInt(3, vo.getItemCo());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	public List<InventoryVO> showInventory(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM INVENTORY");
		sql.append(" WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		
		ResultSet rs = ps.executeQuery();
		
		List<InventoryVO> list = new ArrayList<>();
		while(rs.next()) {
			String itemName = rs.getString("ITEM_NM");
			int charIdx = rs.getInt("CHAR_IDX");
			int itemCo = rs.getInt("ITEM_CO");
			list.add(new InventoryVO(itemName, charIdx, itemCo));
		}
		
		return list;
	}
	
	public boolean useItemMinus(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE INVENTORY");
		sql.append("   SET ITEM_CO = ITEM_CO - 1");
		sql.append(" WHERE CHAR_IDX = ?");
		sql.append("   AND ITEM_NM = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		ps.setString(2, vo.getItemName());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean useItemDelete(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM INVENTORY");
		sql.append(" WHERE CHAR_IDX = ?");
		sql.append("   AND ITEM_NM = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		ps.setString(2, vo.getItemName());
		
		if(ps.executeUpdate() > 0) {
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
		sql.append("													        I.ITEM_DEF + C.CHAR_DEF,");
		sql.append("													   FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("													  WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("													    AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("													    AND IV.CHAR_IDX = ?)");
		sql.append("	 WHERE CHAR_IDX = ?;");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		ps.setInt(2, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean mountingWeapon(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET (CHAR_WEAPON , CHAR_ATT, CHAR_DEF) = (SELECT I.ITEM_NM,");
		sql.append("												        I.ITEM_ATT + C.CHAR_ATT,");
		sql.append("												        I.ITEM_DEF + C.CHAR_DEF,");
		sql.append("												   FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("												  WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("												    AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("												    AND IV.CHAR_IDX = ?)");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		ps.setInt(2, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean mountingArmor(InventoryVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CHARACTERS");
		sql.append("	   SET (CHAR_ARMOR , CHAR_ATT, CHAR_DEF) = (SELECT I.ITEM_NM,");
		sql.append("												       I.ITEM_ATT + C.CHAR_ATT,");
		sql.append("												       I.ITEM_DEF + C.CHAR_DEF,");
		sql.append("												  FROM ITEMS I, INVENTORY IV, CHARACTERS C");
		sql.append("												 WHERE I.ITEM_NM = IV.ITEM_NM");
		sql.append("												   AND IV.CHAR_IDX = C.CHAR_IDX");
		sql.append("												   AND IV.CHAR_IDX = ?)");
		sql.append("	 WHERE CHAR_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getCharIdx());
		ps.setInt(2, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
}


































