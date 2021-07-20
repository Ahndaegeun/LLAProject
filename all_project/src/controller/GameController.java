package controller;

import java.util.List;
import java.util.Random;

import dao.CharacterDAO;
import dao.InventoryDAO;
import dao.ItemDAO;
import vo.CharacterVO;
import vo.InventoryVO;
import vo.ItemVO;
import vo.MonstersVO;

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
		List<InventoryVO> list = invenDao.showInventory(new InventoryVO(null, vo.getCharIdx(), 0, null));
		return list;
	}
	
	public boolean useItem(InventoryVO vo) throws Exception {
		InventoryDAO invenDao = InventoryDAO.getInvetoryDAO();
		boolean result = false;
		
		if(vo.getDitin().equals("3")) {
			result = invenDao.mountingWeapon(vo);
		} else if(vo.getDitin().equals("4")) {
			result = invenDao.mountingArmor(vo);
		}
		
		if(result) {
			result = invenDao.useItemDelete(vo);
		}
		
		
		if(vo.getDitin().equals("1")){
			result = invenDao.useItemChangeUser(vo);
			if(vo.getItemCo() == 1) {
				result = invenDao.useItemDelete(vo);
			} else {
				result = invenDao.useItemMinus(vo);
			}
		} else if(vo.getDitin().equals("2")) {
			result = false;
		}
		
		
		return result;
	}
	
	public boolean unEquippingWeapon(CharacterVO vo) throws Exception {
		InventoryDAO inveDao = InventoryDAO.getInvetoryDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();

		if(vo.getCharWeapon().equals("없음")) {
			return false;
		}
		boolean insertItem = inveDao.insertItem(new InventoryVO(vo.getCharWeapon(), vo.getCharIdx(), 1, "3"));
		boolean unEquip = charDao.unEquippingWeapon(vo);

		if(insertItem && unEquip) {
			return true;
		}
		return false;
	}
	
	public boolean unEquippingArmor(CharacterVO vo) throws Exception {
		InventoryDAO inveDao = InventoryDAO.getInvetoryDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		if(vo.getCharArmor().equals("없음")) {
			return false;
		}
		boolean insertItem = inveDao.insertItem(new InventoryVO(vo.getCharArmor(), vo.getCharIdx(), 1, "4"));
		boolean unEquip = charDao.unEquippingArmor(vo);
		
		if(insertItem && unEquip) {
			return true;
		}
		return false;
	}
	
	public int chooseItemOrGold() {
		Random rnd = new Random();
		return rnd.nextInt(2);
	}
	
	public boolean userGetItem(CharacterVO charVo, MonstersVO monVo) throws Exception {
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		InventoryDAO invenDao = InventoryDAO.getInvetoryDAO();
		ItemDAO itemDao = ItemDAO.geItemDAO();
		
		List<InventoryVO> inven = invenDao.showInventory(new InventoryVO(null, charVo.getCharIdx(), 0, null));
		String ditin = itemDao.getItemDitin(new ItemVO(monVo.getItemNm()));
		
		List<InventoryVO> hasItem = invenDao.showInventory(new InventoryVO(monVo.getItemNm(), charVo.getCharIdx(), 0, ditin));
		if(hasItem.size() > 0) {
			return true;
		}
		
		for(InventoryVO entry : inven) {
			if(entry.getItemName().equals(monVo.getItemNm())) {
				charDao.increaseItem(charVo);
			}
		}
		
		
		
		return charDao.addItem(charVo, new ItemVO(monVo.getItemNm(), ditin));
	}
	
	public boolean userGetGold(CharacterVO charVo, MonstersVO monVo) throws Exception {
		CharacterDAO dao = CharacterDAO.getCharacterDAO();
		return dao.addGold(charVo, monVo.getMomGold());
	}
}
