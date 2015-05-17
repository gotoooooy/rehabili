package nw_simulator;



/**********************************************
 * NICを定義したもの
 **********************************************/
public class Port {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	int no;				// 管理No
	boolean usage;		// リンク使用状況 (true:リンクアップ, false:リンクダウン)
	Link link;			// 接続されているケーブル
	
	
	
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
	 * Publicメソッド
	 **********************************************/
	// 管理Noを返す
	int getNo() {
		return this.no;
	}
	
	// リンク使用状況を返す
	boolean getUsage() {
		return this.usage;
	}
	
	// ケーブルを接続する
	void connectLink(Link link) {
		usage = true;
		this.link = new Link(link.getDuplex(), link.getBandwidth());
	}
	
	// 接続ケーブル情報を返す
	Link getLink() {
		return this.link;
	}
	
	// 接続ケーブルの通信方式を返す
	boolean getDuplex() {
		return this.link.getDuplex();
	}
	
	// 接続ケーブルの帯域幅を返す
	int getBandwidth() {
		return this.link.getBandwidth();
	}
}
