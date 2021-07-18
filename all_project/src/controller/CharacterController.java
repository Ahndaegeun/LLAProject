package controller;

import java.util.List;
import dao.CharacterDAO;
import vo.CharacterVO;
import vo.MemberVO;

public class CharacterController {
	private CharacterController() {}
	private static CharacterController instance;
	public static CharacterController getCharacterController() {
		if(instance == null) {
			instance = new CharacterController();
		}
		return instance;
	}
	
	public boolean createCharacter(CharacterVO vo) throws Exception {
		CharacterDAO dao = CharacterDAO.getCharacterDAO();
		if(dao.createCharacter(vo)) {
			return true;
		}
		return false;
	}
	
	public boolean deleteCharacter(CharacterVO vo) throws Exception {
		CharacterDAO dao = CharacterDAO.getCharacterDAO();
		if(dao.deleteCharacter(vo)) {
			return true;
		}
		return false;
	}
	
	public List<CharacterVO> showAllCharacters(MemberVO vo) throws Exception {
		CharacterDAO dao = CharacterDAO.getCharacterDAO();
		List<CharacterVO> list = dao.showAllCharacter(vo);
		
		return list;
	}
}