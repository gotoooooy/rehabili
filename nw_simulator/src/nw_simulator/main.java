package nw_simulator;

public class main {
	public static void main (String args[]) {
		System.out.println("リバビリマシーン");
		
		Link l0 = new Link(true, 100);
		Link l1 = new Link(false, 100);
		Link l2 = new Link(true, 1000);
		Server p = new Server(0, 3);

		p.connectLink(0, l0);
		p.connectLink(1, l1);
		p.connectLink(2, l2);
		System.out.println(p.getDuplex(0) + " - " + p.getBandwidth(0));
		System.out.println(p.getDuplex(1) + " - " + p.getBandwidth(1));
		System.out.println(p.getDuplex(2) + " - " + p.getBandwidth(2));
	}
}
