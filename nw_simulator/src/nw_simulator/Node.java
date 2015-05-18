package nw_simulator;
import java.util.*;


/**********************************************
 * いわゆるノードを抽象的に定義したもの
 **********************************************/
public abstract class Node {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private int no;
	private ArrayList<Port> port;
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	// この世に存在する…ただひとつのノード…用
	Node() {
		this.no = 0;
		this.port = new ArrayList<Port>(1);
		this.port.add(new Port());
	}
	
	// 単一NICしか持たないノード用
	Node(int no) {
		this.no = no;
		this.port = new ArrayList<Port>(1);
		this.port.add(new Port());
	}
	
	// 複数NICを持つノード用
	Node(int no, int portNum) {
		this.no = no;
		this.port = new ArrayList<Port>(portNum);
		
		for(int i = 0; i < portNum; i++) {
			this.port.add(i, new Port());
		}
	}
	

	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// 管理Noを返す
	protected int getNo() {
		return this.no;
	}
	
	// ポート数を返す
	protected int getPortNum() {
		return this.port.size();
	}
	
	// ケーブルを接続する
	protected void connectLink(Link link) {
		this.port.get(0).connectLink(link);
		
	}
	
	// ケーブルを接続する (ポート番号指定版)
	protected void connectLink(int portNo, Link link) {
		this.port.get(portNo).connectLink(link);
	}
	
	// ポートに接続されたケーブルの通信方式を返す
	protected boolean getDuplex() {
		return getPortDuplex(this.port.get(0));
	}
	
	// ポートに接続されたケーブルの通信方式を返す (ポート番号指定版)
	protected boolean getDuplex(int portNo) {
		return getPortDuplex(this.port.get(portNo));
	}
	
	// ポートに接続されたケーブルの帯域幅を返す
	protected int getBandwidth() {
		return getPortBandwidth(this.port.get(0));
	}
	
	// ポートに接続されたケーブルの帯域幅を返す (ポート番号指定版)
	protected int getBandwidth(int portNo) {
		return getPortBandwidth(this.port.get(portNo));
	}
	

	
	/**********************************************
	 * Privateメソッド
	 **********************************************/
	// ポートの利用状況は判断し、接続ケーブルの通信方式を返す
	private boolean getPortDuplex(Port port) {
		if(port.getUsage()) {
			return port.getDuplex();
		} else {
			return false;
		}
	}

	// ポートの利用状況は判断し、接続ケーブルの帯域幅を返す
	private int getPortBandwidth(Port port) {
		if(port.getUsage()) {
			return port.getBandwidth();
		} else {
			return 0;
		}
	}
}
