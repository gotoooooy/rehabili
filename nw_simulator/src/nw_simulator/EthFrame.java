package nw_simulator;



/**********************************************
 * Ethernet フレーム	
 * ※シミュレーターとして必要十分なもののみ定義
 **********************************************/

public class EthFrame {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private String srcMACaddr = null;		// 送信元MACアドレス
	private String dstMACaddr = null;		// 宛先MACアドレス
	private IPv4Packet IPpacket = null;		// カプセル化したIPパケット
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	EthFrame(String srcMACaddr, IPv4Packet IPpacket) {
		this.srcMACaddr = srcMACaddr;
		this.dstMACaddr = "ffffffffffff";
		this.IPpacket = IPpacket;
	}

	EthFrame(String srcMACaddr, String dstMACaddr, IPv4Packet IPpacket) {
		this.srcMACaddr = srcMACaddr;
		this.dstMACaddr = dstMACaddr;
		this.IPpacket = IPpacket;
	}
	
	
}
