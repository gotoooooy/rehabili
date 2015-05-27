package nw_simulator;



/**********************************************
 * IPv4パケット	
 * ※シミュレーターとして必要十分なもののみ定義
 **********************************************/

public class IPv4Packet {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private String srcIPaddr = null;		// 送信元IPアドレス
	private String dstIPaddr = null;		// 宛先IPアドレス
	private int TTL = 0;					// Time to Live
	private String IPdata = null;			// ペイロード
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	IPv4Packet(String srcIPaddr, String dstIPaddr) {
			this.srcIPaddr = srcIPaddr;
			this.dstIPaddr = dstIPaddr;
			this.TTL = 255;
			this.IPdata = "Hello!" + dstIPaddr;
	}
	
	IPv4Packet(String srcIPaddr, String dstIPaddr, String IPdata) {
		this.srcIPaddr = srcIPaddr;
		this.dstIPaddr = dstIPaddr;
		this.TTL = 255;
		this.IPdata = IPdata;
	}
	
	IPv4Packet(String srcIPaddr, String dstIPaddr, int TTL, String IPdata) {
		this.srcIPaddr = srcIPaddr;
		this.dstIPaddr = dstIPaddr;
		this.TTL = TTL;
		this.IPdata = IPdata;
	}
	
	
	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// 送信元IPアドレスを返す
	protected String getSrcIPaddr() {
		return this.srcIPaddr;
	}
	
	// 宛先IPアドレスを返す
	protected String getDstIPaddr() {
		return this.dstIPaddr;
	}
	
	// Time to Liveを返す
	protected int getTTL() {
		return this.TTL;
	}
	
	// Time to Liveをデクリメント
	protected void decTTL() {
		this.TTL--;
	}
	
	// IPパケットのペイロードを返す
	protected String getIPdata() {
		return this.IPdata;
	}
}
