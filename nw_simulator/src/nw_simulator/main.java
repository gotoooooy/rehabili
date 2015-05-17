package nw_simulator;

public class main {
	public static void main (String args[]) {
		System.out.println("リバビリマシーン");
		
		Link l1 = new Link();
		Link l2 = new Link();
		Link l3 = new Link();
		PC p = new PC(0, 3);
		
		p.connectLink(0, l1);
		p.connectLink(1, l2);
		p.connectLink(2, l3);
		System.out.println(p.getDuplex(1));
		System.out.println(p.getBandwidth(1));
	}
}
