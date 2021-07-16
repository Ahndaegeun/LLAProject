package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionDB;

import vo.MarketVO;

public class MarketDAO {
	public List<MarketVO> showMarketList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM MARKET");
		sql.append(" WHERE MARKET_STAET = 1");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		
		List<MarketVO> list = new ArrayList<>();
		while(rs.next()) {
			int marketIdx = rs.getInt("MARKET_IDX");
			String marketTitle = rs.getString("MARKET_TITLE");
			String marketContents = rs.getString("MARKET_CONTENTS");
			int marketPrice = rs.getInt("MARKET_PRICE");
			String marketState = rs.getString("MARKET_STATE");
			int charIdx = rs.getInt("CHAR_IDX");
			list.add(new MarketVO(marketIdx, marketTitle, marketContents, marketPrice, marketState, charIdx));
		}
		
		return list;
	}
	
	public boolean insertMarketItem(MarketVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO MARKET VALUES(");
		sql.append("	SEQ_MARKET_IDX,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	?,");
		sql.append("	1,");
		sql.append("	?");
		sql.append(")");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, vo.getMarketTitle());
		ps.setString(2, vo.getMarketTitle());
		ps.setInt(3, vo.getMarketPrice());
		ps.setInt(4, vo.getCharIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateMarketItem(MarketVO vo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE MARKET");
		sql.append("   SET MARKET_STATE = 0");
		sql.append(" WHERE MARKET_IDX = ?");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setInt(1, vo.getMarketIdx());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
}
