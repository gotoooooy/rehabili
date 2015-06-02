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
	protected ArrayList<Port> port;
	
	
	
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
			this.port.add(i, new Port(i));
		}
	}
	
	// コピー
	Node(Node node) {
		this.no = node.getNo();
		this.port = new ArrayList<Port>(node.getPortNum());
		
		for(int i = 0; i < node.getPortNum(); i++) {
			this.port.add(i, new Port(i));
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
	
	// NIC名を返す
	protected String getPortName() {
		return this.port.get(0).getName();
	}
	
	// NIC名を返す (ポート番号指定版)
	protected String getPortName(int portNo) {
		return this.port.get(portNo).getName();
	}
	
	// MACアドレスを返す
	protected String getMacAddr() {
		return this.port.get(0).getMacAddr();
	}
	
	// MACアドレスを返す (ポート番号指定版)
	protected String getMacAddr(int portNo) {
		return this.port.get(portNo).getMacAddr();
	}
	
	// ケーブルを接続する
	protected void connectLink(Link link, int nextNode) {
		this.port.get(0).connectLink(link, nextNode);
		
	}
	
	// ケーブルを接続する (ポート番号指定版)
	protected void connectLink(int portNo, Link link, int nextNode) {
		this.port.get(portNo).connectLink(link, nextNode);
	}
	
	// ポートに接続されたケーブルの帯域幅を返す
	protected int getBandwidth() {
		return getPortBandwidth(this.port.get(0));
	}
	
	// ポートに接続されたケーブルの帯域幅を返す (ポート番号指定版)
	protected int getBandwidth(int portNo) {
		return getPortBandwidth(this.port.get(portNo));
	}
	
	// 隣接ノードの管理No.を返す
	protected int getNextNode() {
		return this.port.get(0).getNextNode();
	}
	
	// 隣接ノードの管理No.を返す (ポート番号指定版)
	protected int getNextNode(int portNo) {
		return this.port.get(portNo).getNextNode();
	}
	

	
	/**********************************************
	 * Privateメソッド
	 **********************************************/
	// ポートの利用状況は判断し、接続ケーブルの帯域幅を返す
	private int getPortBandwidth(Port port) {
		if(port.getUsage()) {
			return port.getBandwidth();
		} else {
			return 0;
		}
	}
}
