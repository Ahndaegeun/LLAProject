package controller;

import java.util.List;

import dao.CharacterDAO;
import dao.InventoryDAO;
import vo.CharacterVO;
import vo.InventoryVO;

public class GameController {
	private GameController () {}
	private static GameController instance;
	public static GameController getController() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public CharacterVO getCharacterInfo(CharacterVO vo) throws Exception {
		CharacterDAO dao = CharacterDAO.getCharacterDAO();
		return dao.showCharacterInfo(vo);
	}
	
	public List<InventoryVO> showInventory(CharacterVO vo) throws Exception {
		InventoryDAO invenDao = InventoryDAO.getInvetoryDAO();
		List<InventoryVO> list = invenDao.showInventory(new InventoryVO(null, vo.getCharIdx(), 0));
		return list;
	}
	
	public boolean useItem(InventoryVO vo) throws Exception {
		InventoryDAO invenDao = InventoryDAO.getInvetoryDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		boolean result = false;
		
		//!!!!!!!!!!!!!!!!! Modify DB !!!!!!!!!!!!!!!!!!!!
		if(vo.getCharIdx() == 1) {
			//weapon class1
			invenDao.mountingWeapon(vo);
			invenDao.useItemDelete(vo);
		} else if(vo.getCharIdx() == 2) {
			//armor class2
			invenDao.mountingArmor(vo);
			invenDao.useItemDelete(vo);
		} else {
			//item class 3
			result = invenDao.useItemChangeUser(vo);
			if(vo.getItemCo() == 1) {
				result = invenDao.useItemDelete(vo);
			} else {
				result = invenDao.useItemMinus(vo);
			}
		}
		
		return result;
	}
	
	public boolean unEquippingWeapon(CharacterVO vo) throws Exception {
		InventoryDAO inveDao = InventoryDAO.getInvetoryDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		boolean insertItem = inveDao.insertItem(new InventoryVO(vo.getCharWeapon(), vo.getCharIdx(), 1));
		boolean unEquip = charDao.unEquippingWeapon(vo);

		if(insertItem && unEquip) {
			return true;
		}
		return false;
	}
	
	public boolean unEquippingArmor(CharacterVO vo) throws Exception {
		InventoryDAO inveDao = InventoryDAO.getInvetoryDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		boolean insertItem = inveDao.insertItem(new InventoryVO(vo.getCharArmor(), vo.getCharIdx(), 1));
		boolean unEquip = charDao.unEquippingArmor(vo);
		
		if(insertItem && unEquip) {
			return true;
		}
		return false;
	}
	
	
}