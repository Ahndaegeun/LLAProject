package vo;

public class CharacterVO {
	private int charIdx;
	private String charName;
	private int charHp;
	private int charMaxHp;
	private int charMp;
	private int charMaxMp;
	private int charLevel;
	private int charExe;
	private int charMaxExe;
	private int charAtt;
	private int charDef;
	private String charWeapon;
	private String charArmor;
	private int charGold;
	private String memId;
	private String job;
	private int floor;
	
	
	public CharacterVO() {}
	public CharacterVO(String charName, String memId) {
		this.charName = charName;
		this.memId = memId;
	}
	

	public CharacterVO(int charIdx, String charName, int charLevel, String memId, String job) {
		super();
		this.charIdx = charIdx;
		this.charName = charName;
		this.charLevel = charLevel;
		this.memId = memId;
		this.job = job;
	}

	public CharacterVO(int charIdx, String charName, int charHp, int charMaxHp, int charMp, int charMaxMp,
			int charLevel, int charExe, int charMaxExe, int charAtt, int charDef, String charWeapon, String charArmor,
			int charGold, String memId, String job, int floor) {
		this.charIdx = charIdx;
		this.charName = charName;
		this.charHp = charHp;
		this.charMaxHp = charMaxHp;
		this.charMp = charMp;
		this.charMaxMp = charMaxMp;
		this.charLevel = charLevel;
		this.charExe = charExe;
		this.charMaxExe = charMaxExe;
		this.charAtt = charAtt;
		this.charDef = charDef;
		this.charWeapon = charWeapon;
		this.charArmor = charArmor;
		this.charGold = charGold;
		this.memId = memId;
		this.job = job;
		this.floor = floor;
	}

	public int getCharIdx() {
		return charIdx;
	}

	public void setCharIdx(int charIdx) {
		this.charIdx = charIdx;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public int getCharHp() {
		return charHp;
	}

	public void setCharHp(int charHp) {
		this.charHp = charHp;
	}

	public int getCharMaxHp() {
		return charMaxHp;
	}

	public void setCharMaxHp(int charMaxHp) {
		this.charMaxHp = charMaxHp;
	}

	public int getCharMp() {
		return charMp;
	}

	public void setCharMp(int charMp) {
		this.charMp = charMp;
	}

	public int getCharMaxMp() {
		return charMaxMp;
	}

	public void setCharMaxMp(int charMaxMp) {
		this.charMaxMp = charMaxMp;
	}

	public int getCharLevel() {
		return charLevel;
	}

	public void setCharLevel(int charLevel) {
		this.charLevel = charLevel;
	}

	public int getCharExe() {
		return charExe;
	}

	public void setCharExe(int charExe) {
		this.charExe = charExe;
	}

	public int getCharMaxExe() {
		return charMaxExe;
	}

	public void setCharMaxExe(int charMaxExe) {
		this.charMaxExe = charMaxExe;
	}

	public int getCharAtt() {
		return charAtt;
	}

	public void setCharAtt(int charAtt) {
		this.charAtt = charAtt;
	}

	public int getCharDef() {
		return charDef;
	}

	public void setCharDef(int charDef) {
		this.charDef = charDef;
	}

	public String getCharWeapon() {
		return charWeapon;
	}

	public void setCharWeapon(String charWeapon) {
		this.charWeapon = charWeapon;
	}

	public String getCharArmor() {
		return charArmor;
	}

	public void setCharArmor(String charArmor) {
		this.charArmor = charArmor;
	}

	public int getCharGold() {
		return charGold;
	}

	public void setCharGold(int charGold) {
		this.charGold = charGold;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "CharacterVO [charIdx=" + charIdx + ", charName=" + charName + ", charHp=" + charHp + ", charMaxHp="
				+ charMaxHp + ", charMp=" + charMp + ", charMaxMp=" + charMaxMp + ", charLevel=" + charLevel
				+ ", charExe=" + charExe + ", charMaxExe=" + charMaxExe + ", charAtt=" + charAtt + ", charDef="
				+ charDef + ", charWeapon=" + charWeapon + ", charArmor=" + charArmor + ", charGold=" + charGold
				+ ", memId=" + memId + ", job=" + job + ", floor=" + floor + "]";
	}
	

	
	
}
