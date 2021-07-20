package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.ItemVO;

public class ItemDAO {
	private ItemDAO() {}
	private static ItemDAO instance;
	public static ItemDAO geItemDAO() {
		if(instance == null) {
			instance = new ItemDAO();
		}
		return instance;
	}
	
	public String getItemDitin(ItemVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DITIN");
		sql.append("  FROM ITEMS");
		sql.append(" WHERE ITEM_NM = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getItemName());
		
		Map<String, Object> map = JDBCUtil.getInstance().selectOne(sql.toString(), list);
		
		return (String)map.get("DITIN");
	}
}
