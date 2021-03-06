package vo;

public class MonstersVO {
	private String monNm;
	private int momHp;
	private int momAtt;
	private int momDef;
	private int momGold;
	private int momLev;
	private String itemNm;

	public MonstersVO(String monNm, int momHp, int momAtt, int momDef, int momGold, int momLev, String itemNm) {
		this.monNm = monNm;
		this.momHp = momHp;
		this.momAtt = momAtt;
		this.momDef = momDef;
		this.momGold = momGold;
		this.momLev = momLev;
		this.itemNm = itemNm;
	}

	public String getMonNm() {
		return monNm;
	}

	public void setMonNm(String monNm) {
		this.monNm = monNm;
	}

	public int getMomHp() {
		return momHp;
	}

	public void setMomHp(int momHp) {
		this.momHp = momHp;
	}

	public int getMomAtt() {
		return momAtt;
	}

	public void setMomAtt(int momAtt) {
		this.momAtt = momAtt;
	}

	public int getMomDef() {
		return momDef;
	}

	public void setMomDef(int momDef) {
		this.momDef = momDef;
	}

	public int getMomGold() {
		return momGold;
	}

	public void setMomGold(int momGold) {
		this.momGold = momGold;
	}

	public int getMomLev() {
		return momLev;
	}

	public void setMomLev(int momLev) {
		this.momLev = momLev;
	}

	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	@Override
	public String toString() {
		return "MonstersVO [monNm=" + monNm + ", momHp=" + momHp + ", momAtt=" + momAtt + ", momDef=" + momDef
				+ ", momGold=" + momGold + ", momLev=" + momLev + ", itemNm=" + itemNm + "]";
	}
	
	
}
