package vo;

public class DunjeonVO {
	private int floor;
	private int admfee;
	private String monNm;
	
	public DunjeonVO(int floor, int admfee, String monNm) {
		super();
		this.floor = floor;
		this.admfee = admfee;
		this.monNm = monNm;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getAdmfee() {
		return admfee;
	}

	public void setAdmfee(int admfee) {
		this.admfee = admfee;
	}

	public String getMonNm() {
		return monNm;
	}

	public void setMonNm(String monNm) {
		this.monNm = monNm;
	}

	@Override
	public String toString() {
		return "DunjeonVO [floor=" + floor + ", admfee=" + admfee + ", monNm=" + monNm + "]";
	}
	
	
}
