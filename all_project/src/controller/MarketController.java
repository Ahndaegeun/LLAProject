package controller;

import java.util.List;

import dao.CharacterDAO;
import dao.InventoryDAO;
import dao.MarketDAO;
import vo.CharacterVO;
import vo.InventoryVO;
import vo.MarketVO;

public class MarketController {
	private MarketController() {}
	private static MarketController intance;
	public static MarketController getController() {
		if(intance == null) {
			intance = new MarketController();
		}
		return intance;
	}
	
	public List<MarketVO> showBoardList() throws Exception {
		MarketDAO dao = MarketDAO.getMarketDAO();
		
		List<MarketVO> list = dao.showMarketList();
		return list;
	}
	
	public boolean choiceBoard(MarketVO marketVo, CharacterVO charVo) throws Exception {
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		InventoryDAO invenDao = InventoryDAO.getInvetoryDAO();
		
		//게시물 컨텐츠(아이템 이름) 조회
		String itemName = marketVo.getMarketContents();
		
		
		//캐릭터인벤토리 조회
		List<InventoryVO> charInven = invenDao.showInventory(new InventoryVO(null, charVo.getCharIdx(), 0, null));
		
		boolean hasItemCheck = false;
		
		for(InventoryVO entry : charInven) {
			if(entry.getItemName().equals(itemName)) {
				hasItemCheck = true;
			}
		}
		
		//인벤토리 아이템 삭제 및 전송
		if(hasItemCheck) {
			charDao.addGold(charVo, marketVo.getMarketPrice());
			invenDao.insertItem(new InventoryVO(marketVo.getMarketContents(), marketVo.getCharIdx(), 1, null));
		} else {
			return false;
		}
		
		int getItemCount = 0;
		List<InventoryVO> list = invenDao.showInventory(new InventoryVO(null, charVo.getCharIdx(), 0, null));
		for(InventoryVO entry : list) {
			if(entry.getItemName().equals(marketVo.getMarketContents())) {
				getItemCount = entry.getItemCo();
			}
		}
		
		if(getItemCount == 1) {
			invenDao.useItemDelete(new InventoryVO(null, charVo.getCharIdx(), 0, null));
		} else {
			invenDao.useItemMinus(new InventoryVO(null, charVo.getCharIdx(), 0, null));
		}
		
		return true;
	}
	
	public boolean insertBaord(MarketVO marVo, CharacterVO charVo) throws Exception {
		MarketDAO marDao = MarketDAO.getMarketDAO();
		CharacterDAO charDao = CharacterDAO.getCharacterDAO();
		if(charVo.getCharGold() < marVo.getMarketPrice()) {
			return false;
		}
		if(marDao.insertMarketItem(marVo)) {
			if(charDao.descGold(charVo, marVo.getMarketPrice())) {
				return true;
			}
		}
		return false;
	}
}
