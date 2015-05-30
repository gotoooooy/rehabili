package nw_simulator;
import java.util.*;

public class main {
	public static void main (String args[]) {
		
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
