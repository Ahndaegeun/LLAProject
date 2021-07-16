package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionDB;

import vo.MonstersVO;

public class MonstersDAO {
	
	public List<MonstersVO> showMonsterList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("  FROM MONSTERS");
		
		ConnectionDB instance = ConnectionDB.getInstance();
		Connection conn = instance.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		
		List<MonstersVO> list = new ArrayList<>();
		while(rs.next()) {
			String monNm = rs.getString("MON_NM");
			int momHp = rs.getInt("MON_HP");
			int momAtt = rs.getInt("MON_ATT");
			int momDef = rs.getInt("MON_DEF");
			int momGold = rs.getInt("MON_GOLD");
			int momLev = rs.getInt("MON_LEV");
			String itemNm = rs.getString("ITEM_NM");
			list.add(new MonstersVO(monNm, momHp, momAtt, momDef, momGold, momLev, itemNm));
		}
		return list;
	}
	
	//����
	public double attMonster(MonstersVO vo) {
		double damage = vo.getMomAtt() * 1.2;
		return damage;
	}
	
	//������ ����
	public MonstersVO defMonster(MonstersVO monVo, double userAtt) {
		double def = monVo.getMomDef() * monVo.getMomLev() / 10;
		
		double damage = monVo.getMomHp() - userAtt;
		monVo.setMomHp((int)(monVo.getMomHp() - (damage - def)));
		return monVo;
	}
	
	//����ġ ���
	public int sendExe(MonstersVO vo) {
		return vo.getMomLev() * 10;
	}
	
	//������ ���
	public String sendItem(MonstersVO vo) {
		String item = vo.getItemNm();
		return item;
	}
	
	
	//��� ���
	public int sendGold(MonstersVO vo) {
		int gold = vo.getMomGold();
		return gold;
	}
}