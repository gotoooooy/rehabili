package nw_simulator;



/**********************************************
 * いわゆるケーブルを定義したもの
 **********************************************/
public class Link {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private boolean duplex;		// 通信方式 (true:全二重, false:半二重) <=今はなんの意味もない。。。
	private int bandwidth;		// 帯域幅 (Mbps単位)
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	// 楽したいとき用のやつ。Cat5e相当
	Link() {
		this.duplex = true;
		this.bandwidth = 100;
	}
	
	// 細かく定義できるやつ
	Link(boolean duplex, int bandwidth) {
		this.duplex = duplex;
		this.bandwidth = bandwidth;
	}
	

	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// 通信方式を返す
	protected boolean getDuplex() {
		return this.duplex;		
	}
	
	// 帯域幅を返す
	protected int getBandwidth() {
		return this.bandwidth;
	}
}
