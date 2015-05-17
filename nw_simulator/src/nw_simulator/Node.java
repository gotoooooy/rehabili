package nw_simulator;
import java.util.*;


/**********************************************
 * いわゆるノードを抽象的に定義したもの
 **********************************************/
public abstract class Node {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	int no;
	Vector port;
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	// この世に存在する…ただひとつのノード…用
	Node() {
		this.no = 0;
		this.port = new Vector(1);
		this.port.insertElementAt(new Port(),0);
	}
	
	// 単一NICしか持たないノード用
	Node(int no) {
		this.no = no;
		this.port = new Vector(1);
		this.port.insertElementAt(new Port(),0);
	}
	
	// 複数NICを持つノード用
	Node(int no, int portNum) {
		this.no = no;
		this.port = new Vector(portNum);
		
		for(int i = 0; i < portNum; i++) {
			this.port.insertElementAt(new Port(),i);
		}
	}
	

	
	/**********************************************
	 * Publicメソッド
	 **********************************************/
	// 管理Noを返す
	int getNo() {
		return this.no;
	}
	
	// ポート数を返す
	int getPortNum() {
		return this.port.size();
	}
	
	// ケーブルを接続する
	void connectLink(Link link) {
		Port port = (Port)this.port.get(0);
		port.connectLink(link);
	}
	
	// ケーブルを接続する (ポート番号指定版)
	void connectLink(int portNo, Link link) {
		Port port = (Port)this.port.get(portNo);
		port.connectLink(link);
	}
	
	// ポートに接続されたケーブルの通信方式を返す
	boolean getDuplex() {
		Port port = (Port)this.port.get(0);
		return getPortDuplex(port);
	}
	
	// ポートに接続されたケーブルの通信方式を返す (ポート番号指定版)
	boolean getDuplex(int portNo) {
		Port port = (Port)this.port.get(portNo);
		return getPortDuplex(port);
	}
	
	// ポートに接続されたケーブルの帯域幅を返す
	int getBandwidth() {
		Port port = (Port)this.port.get(0);
		return getPortBandwidth(port);
	}
	
	// ポートに接続されたケーブルの帯域幅を返す (ポート番号指定版)
	int getBandwidth(int portNo) {
		Port port = (Port)this.port.get(portNo);
		return getPortBandwidth(port);
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
