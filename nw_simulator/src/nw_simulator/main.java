package nw_simulator;
import java.util.*;

public class main {
	public static void main(String args[]) {
		Practice p = new Practice();
		p.practice1();
	}
}

class Practice {

	// 問1:PC,Hub,Cableを実装せよ
	void practice1() {
		System.out.println("問1：PC,Hub,Cableを実装せよ\n");
		
		// ネットワークを構成
		NetworkTopology nw = new NetworkTopology();
		nw.addNode(new Server(0));
		nw.setIpAddr(0, "192.168.1.10");
		nw.addNode(new Server(1));
		nw.setIpAddr(1, "192.168.1.11");
		nw.addNode(new Hub(2));
		
		// ネットワーク構成図
		System.out.println(
				"[ネットワーク構成図]\n" + 
				"+------+    +-----+    +------+\n" + 
				"| Srv0 |----| Hub |----| Srv1 |\n" + 
				"+------+    +-----+    +------+\n" + 
				"\n" + 
				"[Srv0]\t" + "MAC:" + nw.getMacAddr(0) + "\t" + "IP:" + nw.getIpAddr(0) + "\n" + 
				"[Srv1]\t" + "MAC:" + nw.getMacAddr(1) + "\t" + "IP:" + nw.getIpAddr(1) + "\n"  
				);
		
		// パケット生成
		IPv4Packet packet1 = new IPv4Packet("192.168.1.10", "192.168.1.11", "Hello");
		
	}
}




// テストコード <= 削除予定
class TestCode {
	void testcode3() {
		// ネットワーク生誕
		NetworkTopology nw = new NetworkTopology();
		
		// ネットワーク成長
		nw.addNode(new Server(0));
		nw.addNode(new Server(1,3));
		nw.addLink((0 << GlobalVariable.SD_DIVIDE_BIT) + 1, new Link());
		nw.setIpAddr(0, "192.168.1.10");
		nw.addLink((1 << GlobalVariable.SD_DIVIDE_BIT) + 0, 1, new Link());
		nw.setIpAddr(1, 1, "192.168.1.11");
	}
	
	void testcode2() {
		// Objectクラスのお勉強コード
		ArrayList<Object> test = new ArrayList<Object>(0);
		test.add(new Server(0));
		
		if(test.get(0) instanceof Server) {
			System.out.println("My classname is Server");
		}
	}
	
	void testcode1() {
		// L2通信実装に向けた準備（そのうちARP/RARPのリアルな処理を追加したい)
		Map<String, String> arpTable = new HashMap<String, String>(0);
		
		// Server0 設定テスト
		Server srv0 = new Server(0);
		srv0.setIpAddr("192.168.1.10");
		arpTable.put(srv0.getMacAddr(), srv0.getIpAddr());

		// Server1 設定テスト
		Server srv1 = new Server(1, 3);
		srv1.setIpAddr(0, "192.168.1.20");
		arpTable.put(srv1.getMacAddr(0), srv1.getIpAddr(0));
		srv1.setIpAddr(1, "192.168.2.21");
		arpTable.put(srv1.getMacAddr(1), srv1.getIpAddr(1));
		srv1.setIpAddr(2, "192.168.3.22");
		arpTable.put(srv1.getMacAddr(2), srv1.getIpAddr(2));
		
		// リンク接続
		Link link0_1 = new Link();
		srv0.connectLink(link0_1, 1);
		Link link1_0 = new Link();
		srv0.connectLink(0, link1_0, 0);
		
		// NIC設定状況
		System.out.println("[NIC設定完了]");
		System.out.println("Srv0:\t" + srv0.getPortName() + "\t" + srv0.getMacAddr() + " / " + srv0.getIpAddr());;
		for(int i = 0; i < srv1.getPortNum(); i++) {
			System.out.println("Srv1:\t" + srv1.getPortName(i) + "\t" + srv1.getMacAddr(i) + " / " + srv1.getIpAddr(i));
		}
		
		System.out.println();
		System.out.println("[ARPテーブル設定完了]");
		// 魔法のARPテーブルぶち込み！！！
		srv0.setArpTable(arpTable);
		srv1.setArpTable(arpTable);
	}
}
