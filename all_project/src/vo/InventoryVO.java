package vo;

public class InventoryVO {
	private String itemName;
	private int charIdx;
	private int itemCo;

	public InventoryVO(String itemName, int charIdx, int itemCo) {
		this.itemName = itemName;
		this.charIdx = charIdx;
		this.itemCo = itemCo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getCharIdx() {
		return charIdx;
	}

	public void setCharIdx(int charIdx) {
		this.charIdx = charIdx;
	}

	public int getItemCo() {
		return itemCo;
	}

	public void setItemCo(int itemCo) {
		this.itemCo = itemCo;
	}

	@Override
	public String toString() {
		return "InventoryVO [itemName=" + itemName + ", charIdx=" + charIdx + ", itemCo=" + itemCo + "]";
	}
	
	
}
