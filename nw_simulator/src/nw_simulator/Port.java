package nw_simulator;
import java.util.*;


/**********************************************
 * NICを定義したもの
 **********************************************/
public class Port {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private int no;				// 管理No
	private String name;		// NIC名
	private String macAddr;		// MACアドレス
	private String ipAddr;		// IPアドレス
	private boolean usage;		// リンク使用状況 (true:リンクアップ, false:リンクダウン)
	private Link link;			// 接続されているケーブル
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	// 単一NICしかないノード用
	Port() {
		this.no = 0;
		this.name = "eth-0";
		this.macAddr = UUID.randomUUID().toString().substring(24, 36); // ユニークなIDってことで代用
		this.ipAddr = null;
		this.usage = false;
		this.link = null;
	}
	
	// 複数NICあるノード用
	Port(int no) {
		this.no = no;
		this.name = "eth-" + Integer.toString(this.no);
		this.macAddr = UUID.randomUUID().toString().substring(24, 36); // ユニークなIDってことで代用
		this.ipAddr = null;
		this.usage = false;
		this.link = null;
	}
	

	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// 管理Noを返す
	protected int getNo() {
		return this.no;
	}
	
	// NIC名を返す
	protected String getName() {
		return this.name;
	}
	
	// MACアドレスを返す
	protected String getMacAddr() {
		return this.macAddr;
	}
	
	// IPアドレスを返す
	protected String getIpAddr() {
		return this.ipAddr;
	}
	
	// IPアドレスを設定する
	protected void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	// リンク使用状況を返す
	protected boolean getUsage() {
		return this.usage;
	}
	
	// ケーブルを接続する
	protected void connectLink(Link link) {
		usage = true;
		this.link = new Link(link.getDuplex(), link.getBandwidth());
	}
	
	// 接続ケーブル情報を返す
	protected Link getLink() {
		return this.link;
	}
	
	// 接続ケーブルの通信方式を返す
	protected boolean getDuplex() {
		return this.link.getDuplex();
	}
	
	// 接続ケーブルの帯域幅を返す
	protected int getBandwidth() {
		return this.link.getBandwidth();
	}
}
