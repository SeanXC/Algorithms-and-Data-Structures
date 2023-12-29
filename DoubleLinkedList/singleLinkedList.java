package csu22011_a2;

public class singleLinkedList {

	public static void main(String[] args) {
		singleLinkedListDemo dm = new singleLinkedListDemo();
		HeroNode node1 = new HeroNode(1, "liu");
		HeroNode node2 = new HeroNode(2, "fei");
		HeroNode node3 = new HeroNode(3, "yu");
		HeroNode node4 = new HeroNode(4, "bu");
		HeroNode node5 = new HeroNode(5, "diao");

		dm.addHeroNode(node1);
		dm.addHeroNode(node2);
		dm.addHeroNode(node3);
		dm.addHeroNode(node4);
		dm.addHeroNode(node5);

		dm.showLinkedList(dm.getHead());

		System.out.println("--------------------------------------------");
		dm.updateLinkedList(new HeroNode (5, "xishi"));
		dm.showLinkedList(dm.getHead());

	}
}

class singleLinkedListDemo {
	private HeroNode head = new HeroNode(0, "");

	public HeroNode getHead() {
		return head;
	}

	public void addHeroNode(HeroNode heroNode) {
		HeroNode temp = head;
		while (true) {
			if (temp.next == null) {
				// Found the end, add the new node.
				temp.next = heroNode;
				break;
			}
			temp = temp.next;
		}
	}

	public void showLinkedList(HeroNode start) {
		HeroNode temp = start.next;  // start from the first node
		while (temp != null) {
			System.out.println(temp);
			temp = temp.next;
		}
	}

	public void updateLinkedList(HeroNode heroNode) {
		if (head.next == null) {
			System.out.print("the list is empty");
			return;
		}
		HeroNode temp = head.next;
		boolean flag = false;
		while (true) {
			if (temp.id == heroNode.id) {
				flag = true;
				break;
			}
			if (temp.next == null) {  // added this to ensure we don't miss out on the last node
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.name = heroNode.name;
		} else {
			System.out.println("no id is " + heroNode.id);
		}
	}

	public void deleteLinkedList(int id) {
		HeroNode temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null) { // end of the list
				break;
			}
			if (temp.next.id == id) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.next = temp.next.next;
		} else {
			System.out.print("no id is " + id);
		}
	}
}

class HeroNode {
	public int id;
	public String name;
	public HeroNode next;  //point to the next node

	public HeroNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + "]";
	}
}
