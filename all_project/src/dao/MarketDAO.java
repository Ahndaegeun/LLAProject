package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.JDBCUtil;
import vo.MarketVO;

public class MarketDAO {
	private MarketDAO() {}
	private static MarketDAO intance;
	public static MarketDAO getMarketDAO() {
		if(intance == null) {
			intance = new MarketDAO();
		}
		return intance;
	}
	
	public List<MarketVO> showMarketList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM MARKET");
		sql.append(" WHERE MARKET_STATE = 1");
		
		List<Map<String, Object>> map = JDBCUtil.getInstance().selectList(sql.toString());
		List<MarketVO> list = new ArrayList<>();
		
		for(int i = 0; i < map.size(); i++) {
			MarketVO market = new MarketVO(Integer.parseInt(map.get(i).get("MARKET_IDX") + ""), (String)map.get(i).get("MARKET_TITLE"), 
					(String)map.get(i).get("MARKET_CONTENTS"), Integer.parseInt(map.get(i).get("MARKET_PRICE") + ""), 
					(String)map.get(i).get("MARKET_STATE"), Integer.parseInt(map.get(i).get("CHAR_IDX") + ""),
					(String)map.get(i).get("ITEM_NM"), Integer.parseInt(map.get(i).get("ITEM_CO") + ""));
			list.add(market);
		}
		
		return list;
	}
	
	public boolean updateMarketList(MarketVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE MARKET");
		sql.append("    SET MARKET_STATE = 0");
		sql.append("  WHERE MARKET_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getMarketIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean insertMarketItem(MarketVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO MARKET VALUES(");
		sql.append("	SEQ_MARKET_IDX.NEXTVAL,");
		sql.append("	?,");// TITLE
		sql.append("	?,");// CONTENTS
		sql.append("	?,");// PRICE
		sql.append("	'1',");// STATE
		sql.append("	?,");// CHARIDX
		sql.append("	?,");// ITEM NAME
		sql.append("	?");// ITEM COUNT
		sql.append(")");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getMarketTitle());
		list.add(vo.getMarketContents());
		list.add(vo.getMarketPrice());
		list.add(vo.getCharIdx());
		list.add(vo.getItemNm());
		list.add(vo.getItemCo());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
	
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateMarketItem(MarketVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MARKET");
		sql.append("   SET MARKET_STATE = 0");
		sql.append(" WHERE MARKET_IDX = ?");
		
		List<Object> list = new ArrayList<>();
		list.add(vo.getMarketIdx());
		
		int result = JDBCUtil.getInstance().update(sql.toString(), list);
		
		if(result > 0) {
			return true;
		}
		return false;
	}
}
