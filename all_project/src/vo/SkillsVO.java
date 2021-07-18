package vo;

public class SkillsVO {
	private String skillNm;
	private int skillAtt;
	private int skillMp;
	private int skillLev;
	private String job;

	public SkillsVO(String skillNm, int skillAtt, int skillMp, int skillLev, String job) {
		this.skillNm = skillNm;
		this.skillAtt = skillAtt;
		this.skillMp = skillMp;
		this.skillLev = skillLev;
		this.job = job;
	}
	
	public SkillsVO () {}

	public String getSkillNm() {
		return skillNm;
	}

	public void setSkillNm(String skillNm) {
		this.skillNm = skillNm;
	}

	public int getSkillAtt() {
		return skillAtt;
	}

	public void setSkillAtt(int skillAtt) {
		this.skillAtt = skillAtt;
	}

	public int getSkillMp() {
		return skillMp;
	}

	public void setSkillMp(int skillMp) {
		this.skillMp = skillMp;
	}

	public int getSkillLev() {
		return skillLev;
	}

	public void setSkillLev(int skillLev) {
		this.skillLev = skillLev;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "SkillsVO [skillNm=" + skillNm + ", skillAtt=" + skillAtt + ", skillMp=" + skillMp + ", skillLev="
				+ skillLev + ", job=" + job + "]";
	}
	
	
}