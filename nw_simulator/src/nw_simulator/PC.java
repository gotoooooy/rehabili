package nw_simulator;
import java.util.*;

//ぴーしー
public class PC extends Node {
	
	/**********************************************
	 * メンバ変数
	 **********************************************/
	private Map<String, String> arpTable = null;
	
	
	
	/**********************************************
	 * コンストラクタ
	 **********************************************/
	PC() {
		super();
		this.arpTable = new HashMap<String, String>(0);
	}
	
	PC(int no) {
		super(no);
		this.arpTable = new HashMap<String, String>(0);
	}
	
	PC(int no, int portNum) {
		super(no, portNum);
		this.arpTable = new HashMap<String, String>(0);
	}
	

	
	/**********************************************
	 * Protectedメソッド
	 **********************************************/
	// ARPテーブルをがっさり受け取って、がっさり設定する　超ズル技！！
	protected void setArpTable(Map<String, String> arpTable) {
		this.arpTable = arpTable;
		System.out.println("PC:\t" + arpTable);
	}
	
	// IPアドレスを設定する
	protected void setIpAddr(String ipAddr) {
		this.port.get(0).setIpAddr(ipAddr);
	}
	
	// IPアドレスを設定する (ポート番号指定版)
	protected void setIpAddr(int portNo, String ipAddr) {
		this.port.get(portNo).setIpAddr(ipAddr);
	}
	
	// IPアドレスを返す
	protected String getIpAddr() {
		return this.port.get(0).getIpAddr();
	}
		
	// IPアドレスを返す (ポート番号指定版)
	protected String getIpAddr(int portNo) {
		return this.port.get(portNo).getIpAddr();
	}
	
}
