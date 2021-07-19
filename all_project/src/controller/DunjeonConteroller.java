package controller;

import dao.DunjeonDAO;
import vo.CharacterVO;
import vo.DunjeonVO;
import vo.MonstersVO;

public class DunjeonConteroller {
	private DunjeonConteroller () {}
	private static DunjeonConteroller instance;
	public static DunjeonConteroller geDunjeonConteroller() {
		if (instance == null) {
			instance = new DunjeonConteroller();
		}
		return instance;
	}
	private DunjeonDAO dao = DunjeonDAO.geDunjeonDAO();
	
	public DunjeonVO showUserDunjeon(CharacterVO vo) {
		DunjeonVO dunjoen = dao.showDunjeonInfo(vo);
		return dunjoen;
	}
	
	public MonstersVO getDunjeonMonster(DunjeonVO vo) {
		return dao.getMonster(vo);
	}
}
