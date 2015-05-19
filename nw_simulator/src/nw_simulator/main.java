package nw_simulator;
import java.util.*;

public class main {
	public static void main (String args[]) {
		
		// L2通信実装に向けた準備（そのうちARP/RARPのリアルな処理を追加したい)
		Map<String, String> arpTable = new HashMap<String, String>(0);
		
		// PC設定テスト
		PC pc = new PC(0);
		pc.setIpAddr("192.168.1.10");
		arpTable.put(pc.getMacAddr(), pc.getIpAddr());
		
		// Server設定テスト
		Server srv = new Server(1, 3);
		srv.setIpAddr(0, "192.168.1.20");
		arpTable.put(srv.getMacAddr(0), srv.getIpAddr(0));
		srv.setIpAddr(1, "192.168.1.21");
		arpTable.put(srv.getMacAddr(1), srv.getIpAddr(1));
		srv.setIpAddr(2, "192.168.1.22");
		arpTable.put(srv.getMacAddr(2), srv.getIpAddr(2));
		
		// NIC設定状況
		System.out.println("[NIC設定完了]");
		System.out.println("PC:\t" + pc.getPortName() + "\t" + pc.getMacAddr() + " / " + pc.getIpAddr());;
		for(int i = 0; i < srv.getPortNum(); i++) {
			System.out.println("Srv:\t" + srv.getPortName(i) + "\t" + srv.getMacAddr(i) + " / " + srv.getIpAddr(i));
		}
		
		System.out.println();
		System.out.println("[ARPテーブル設定完了]");
		// 魔法のARPテーブルぶち込み！！！
		pc.setArpTable(arpTable);
		srv.setArpTable(arpTable);
	}
}
