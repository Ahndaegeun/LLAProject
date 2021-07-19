package vo;

public class InventoryVO {
	private String itemName;
	private int charIdx;
	private int itemCo;
	private String ditin;

	public InventoryVO(String itemName, int charIdx, int itemCo, String ditin) {
		this.itemName = itemName;
		this.charIdx = charIdx;
		this.itemCo = itemCo;
		this.ditin = ditin;
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

	public String getDitin() {
		return ditin;
	}

	public void setDitin(String ditin) {
		this.ditin = ditin;
	}

	@Override
	public String toString() {
		return "InventoryVO [itemName=" + itemName + ", charIdx=" + charIdx + ", itemCo=" + itemCo + ", ditin=" + ditin
				+ "]";
	}

	
	
}
