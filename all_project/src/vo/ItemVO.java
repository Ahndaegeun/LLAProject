package vo;

public class ItemVO {
	private String itemName;
	private int itemHp;
	private int itemMp;
	private int itemAtt;
	private int itemDef;

	public ItemVO(String itemName, int itemHp, int itemMp, int itemAtt, int itemDef) {
		this.itemName = itemName;
		this.itemHp = itemHp;
		this.itemMp = itemMp;
		this.itemAtt = itemAtt;
		this.itemDef = itemDef;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemHp() {
		return itemHp;
	}

	public void setItemHp(int itemHp) {
		this.itemHp = itemHp;
	}

	public int getItemMp() {
		return itemMp;
	}

	public void setItemMp(int itemMp) {
		this.itemMp = itemMp;
	}

	public int getItemAtt() {
		return itemAtt;
	}

	public void setItemAtt(int itemAtt) {
		this.itemAtt = itemAtt;
	}

	public int getItemDef() {
		return itemDef;
	}

	public void setItemDef(int itemDef) {
		this.itemDef = itemDef;
	}

	@Override
	public String toString() {
		return "ItemVO [itemName=" + itemName + ", itemHp=" + itemHp + ", itemMp=" + itemMp + ", itemAtt=" + itemAtt
				+ ", itemDef=" + itemDef + "]";
	}
	
	
}
