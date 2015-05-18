package nw_simulator;



/**********************************************
 * NICを定義したもの
 **********************************************/
public class Port {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private int no;				// 管理No
	private boolean usage;		// リンク使用状況 (true:リンクアップ, false:リンクダウン)
	private Link link;			// 接続されているケーブル
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	// 単一NICしかないノード用
	Port() {
		this.no = 0;
		this.usage = false;
		this.link = null;
	}
	
	// 複数NICあるノード用
	Port(int no) {
		this.no = no;
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
