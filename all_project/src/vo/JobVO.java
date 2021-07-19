package vo;

public class JobVO {
	private String job;
	private int jobHp;
	private int jobMp;
	private int jobAtt;
	private int jobDef;

	public JobVO(String job, int jobHp, int jobMp, int jobAtt, int jobDef) {
		this.job = job;
		this.jobHp = jobHp;
		this.jobMp = jobMp;
		this.jobAtt = jobAtt;
		this.jobDef = jobDef;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getJobHp() {
		return jobHp;
	}

	public void setJobHp(int jobHp) {
		this.jobHp = jobHp;
	}

	public int getJobMp() {
		return jobMp;
	}

	public void setJobMp(int jobMp) {
		this.jobMp = jobMp;
	}

	public int getJobAtt() {
		return jobAtt;
	}

	public void setJobAtt(int jobAtt) {
		this.jobAtt = jobAtt;
	}

	public int getJobDef() {
		return jobDef;
	}

	public void setJobDef(int jobDef) {
		this.jobDef = jobDef;
	}

	@Override
	public String toString() {
		return "JobVO [job=" + job + ", jobHp=" + jobHp + ", jobMp=" + jobMp + ", jobAtt=" + jobAtt + ", jobDef="
				+ jobDef + "]";
	}

	
}
