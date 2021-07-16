package vo;

public class MarketVO {
	private int marketIdx;
	private String marketTitle;
	private String marketContents;
	private int marketPrice;
	private String marketState;
	private int charIdx;

	public MarketVO(int marketIdx, String marketTitle, String marketContents, int marketPrice, String marketState,
			int charIdx) {
		this.marketIdx = marketIdx;
		this.marketTitle = marketTitle;
		this.marketContents = marketContents;
		this.marketPrice = marketPrice;
		this.marketState = marketState;
		this.charIdx = charIdx;
	}

	public int getMarketIdx() {
		return marketIdx;
	}

	public void setMarketIdx(int marketIdx) {
		this.marketIdx = marketIdx;
	}

	public String getMarketTitle() {
		return marketTitle;
	}

	public void setMarketTitle(String marketTitle) {
		this.marketTitle = marketTitle;
	}

	public String getMarketContents() {
		return marketContents;
	}

	public void setMarketContents(String marketContents) {
		this.marketContents = marketContents;
	}

	public int getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getMarketState() {
		return marketState;
	}

	public void setMarketState(String marketState) {
		this.marketState = marketState;
	}

	public int getCharIdx() {
		return charIdx;
	}

	public void setCharIdx(int charIdx) {
		this.charIdx = charIdx;
	}

	@Override
	public String toString() {
		return "MarketVO [marketIdx=" + marketIdx + ", marketTitle=" + marketTitle + ", marketContents="
				+ marketContents + ", marketPrice=" + marketPrice + ", marketState=" + marketState + ", charIdx="
				+ charIdx + "]";
	}
	
	
}
