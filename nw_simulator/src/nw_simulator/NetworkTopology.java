package nw_simulator;

import java.util.HashMap;
import java.util.Map;

/**********************************************
 * ネットワークトポロジー
 **********************************************/

public class NetworkTopology {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	protected Map<Integer, Object> nodes = null;
	protected Map<Integer, Link> links = null;
	private Map<String, String> arpTable = null;		// 改修予定
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	NetworkTopology() {
		this.nodes = new HashMap<Integer, Object>(0);
		this.links = new HashMap<Integer, Link>(0);
		this.arpTable = new HashMap<String, String>(0);	// 改修予定
	}
	

	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// ノードを追加する
	protected void addNode(Object node) {
		int tmpID = -1;
		if(node instanceof Server) {
			tmpID = (Server.class.cast(node)).getNo();
		} else if(node instanceof Hub) {
			tmpID = (Hub.class.cast(node)).getNo();
		}
		
		this.nodes.put(tmpID, node);
	}
	
	// ノードを返す
	protected Object getNode(int nodeNo) {
		return this.nodes.get(nodeNo);
	}
	
	// IPアドレスを設定する
	protected boolean setIpAddr(int nodeNo, String ipAddr) {
		if(this.nodes.get(nodeNo) instanceof Server) {
			(Server.class.cast(this.nodes.get(nodeNo))).setIpAddr(ipAddr);
			updateArpTable(nodeNo, 0, ipAddr);		// 改修予定
			return true;
		} else {
			return false;
		}
	}
	
	// IPアドレスを設定する (ポート番号指定版)
	protected boolean setIpAddr(int nodeNo, int portNo, String ipAddr) {
		if(this.nodes.get(nodeNo) instanceof Server) {
			(Server.class.cast(this.nodes.get(nodeNo))).setIpAddr(portNo, ipAddr);
			updateArpTable(nodeNo, portNo, ipAddr);		// 改修予定
			return true;
		} else {
			return false;
		}
	}
	
	// IPアドレスを返す
	protected String getIpAddr(int nodeNo) {
		if(this.nodes.get(nodeNo) instanceof Server) {
			return (Server.class.cast(this.nodes.get(nodeNo))).getIpAddr();
		} else {
			return "IPaddr none";
		}
	}
	
	// IPアドレスを返す (ポート番号指定版)
	protected String getIpAddr(int nodeNo, int portNo) {
		if(this.nodes.get(nodeNo) instanceof Server) {
			return (Server.class.cast(this.nodes.get(nodeNo))).getIpAddr(portNo);
		} else {
			return "IPaddr none";
		}
	}
	
	// MACアドレスを返す
	protected String getMacAddr(int nodeNo) {
		return (Server.class.cast(this.nodes.get(nodeNo))).getMacAddr();
	}
	
	// MACアドレスを返す (ポート番号指定版)
	protected String getMacAddr(int nodeNo, int portNo) {
		return (Server.class.cast(this.nodes.get(nodeNo))).getMacAddr(portNo);
	}
	
	// リンクを追加する
	protected void addLink(int pairCode, Link link) {
		this.links.put(pairCode, link);
		connectLink(pairCode, this.links.get(pairCode));
	}
	
	// リンクを追加する (ポート番号指定版)
	protected void addLink(int pairCode, int portNo, Link link) {
		this.links.put(pairCode, link);
		connectLink(portNo, pairCode, this.links.get(pairCode));
	}
	
	// IPv4通信を行う
	protected void sendPacket(IPv4Packet packet) {
		// 送信元ノードを探索
		String srcIPaddr = packet.getSrcIPaddr();
		int srcNodeID = -1;
		for(int i = 0; i < this.nodes.size(); i++) {
			Object node = this.nodes.get(i);
			if(node instanceof Server) {
				// Serverの全ポートを対象に探索
				for(int j = 0; j < Server.class.cast(node).getPortNum(); j++) {
					if(Server.class.cast(node).getIpAddr(j).equals(srcIPaddr)) {
						srcNodeID = i;
					}
				}
			}
		}
		
		if(srcNodeID == -1) {		// 該当IPを持ったノードが存在しない場合
			System.out.println(srcIPaddr + "は存在しません。");
		} else {
		}
	}
	

	
	/**********************************************
	 * Privateメソッド
	 **********************************************/
	// 全ServerのArpTableを最新化 <= 改修予定
	private void updateArpTable(int nodeNo, int portNo, String ipAddr) {
		// MAC・IPアドレスを取得
		this.arpTable.put((Server.class.cast(this.nodes.get(nodeNo))).getMacAddr(portNo), (Server.class.cast(this.nodes.get(nodeNo))).getIpAddr(portNo));
		
		// サーバノード全体に反映
		for(int i = 0; i < this.nodes.size(); i++) {
			if(this.nodes.get(i) instanceof Server) {
				(Server.class.cast(this.nodes.get(i))).setArpTable(this.arpTable);
			}
		}
	}
	
	// Serverにリンクを接続する
	private void connectLink(int pairCode, Link link) {
		int srcNodeID = pairCode >> GlobalVariable.SD_DIVIDE_BIT;
		int dstNodeID = pairCode % GlobalVariable.MAX_NODE;
		
		(Server.class.cast(this.nodes.get(srcNodeID))).connectLink(link, dstNodeID);
	}
	
	// Serverにリンクを接続する
	private void connectLink(int portNo, int pairCode, Link link) {
		int srcNodeID = pairCode >> GlobalVariable.SD_DIVIDE_BIT;
		int dstNodeID = pairCode % GlobalVariable.MAX_NODE;
		
		(Server.class.cast(this.nodes.get(srcNodeID))).connectLink(portNo, link, dstNodeID);
	}
}
