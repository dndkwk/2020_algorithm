package BSTree;
//2015112232�����
class Node {
	public int key;
	public Node left;
	public Node right;

	public Node(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
}

class BS {
	public Node root;

	public BS() {
		this.root = null;
	}

	public void show() {
		// ���
		System.out.println("");
		if (root == null) {
			System.out.println("Empty tree");
		} else {
			System.out.println("");
			showSub(root, 1);
			System.out.println("");
		}
		System.out.println("");
	}

	public void showSub(Node p, int level) {
		// ��� ����
		int j;
		if (p != null) {
			showSub(p.right, level + 1);
			for (j = 0; j < level; j++) {
				System.out.print("\t");
			}
			System.out.print(p.key);
			if ((p.right != null) && (p.left != null)) {
				System.out.print("<");
			} else if (p.right != null) {
				System.out.print("/");
			} else if (p.left != null) {
				System.out.print("\\");
			}
			System.out.println("");
			showSub(p.left, level + 1);
		}
	}

	public void insert(int element) {
		// ���� ����
		Node newNode = new Node(element);
		if (root == null) { // empty��� ���ο� node�� ����Ų��.
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) { // key���� ���Ͽ� null�� �ڸ��� insert�Ѵ�.
			parent = current;
			if (element < current.key) { // ���� key���� element���� Ŭ��
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {// ���� key���� element���� ���� ��
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public void retrieve(int element) {
		// Ž�� ����
		Node current = root;
		while (current != null) {
			if (current.key == element) { // ���� key���� element�� ���ٸ�
				System.out.print("left child is ");
				if (current.left == null) {
					System.out.println("none");
				} else {
					System.out.println(current.left.key);
				}
				System.out.print("right child is ");
				if (current.right == null) {
					System.out.println("none");
				} else {
					System.out.println(current.right.key);
				}
				return;
			} else if (current.key > element) {// ���� key���� element���� ũ�ٸ� left��
				current = current.left;
			} else {// ���� key���� element���� �۴ٸ� right��
				current = current.right;
			}
		}
	}

	public void remove(int element) {
		// ���� ����
		if(root==null) {
			return;
		}
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false; // ���� �ڽ����� ������ �ڽ����� �������ִ� ����
		while (current.key != element) { // �����Ϸ��� ���� ���� �� ����� �θ��带 �˷��ִ� �ݺ���
			parent = current;
			if (current.key > element) { // ���� �ڽ����� �̵�
				isLeftChild = true;
				current = current.left;
			} else { // ������ �ڽ����� �̵�
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) { // ���ٸ�
				System.out.println("�ش��ϴ� ���� �����ϴ�.");
				return;
			}
		}
		// ��� 1 : �ڽ� ��尡 ���� ���
		if ((current.left == null) && (current.right == null)) {
			if (current == root) { // root�� key�� ���� ���϶�
				root = null;
			}
			if (isLeftChild == true) { // �θ��� ���� �ڽ��̶��
				parent.left = null;
			} else { // �θ��� ������ �ڽ��̶��
				parent.right = null;
			}
		} else if (current.right == null) {// ��� 2 : �ڽ��� �Ѱ��� ���
			// ������ �ڽ��� ���� ��
			if (current == root) {// root�� key�� ���� ���϶�
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.left == null) {
			// ���� �ڽ��� ���� ��
			if (current == root) {// root�� key�� ���� ���϶�
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if ((current.left != null) && (current.right != null)) {
			// ��� 3 : �ڽ��� �� ���� ���
			// ������ ���� Ʈ������ �ּҰ��� ã�´�.
			Node min = findmin(current);
			if (current == root) {
				root = min;
			} else if (isLeftChild) {
				parent.left = min;
			} else {
				parent.right = min;
			}
			min.left = current.left;
		}
	}

	public Node findmin(Node p) {
		// �ּҰ� ã��
		Node min = null;
		Node minparent = null;
		Node curernt = p.right;

		while (curernt != null) {//������ ���� �ڽı��� ����
			minparent = min;
			min = curernt;
			curernt = curernt.left;
		}
		if (min != p.right) { // �ּҰ��� �ٷ� ������ �ڽ��� �ƴҶ�
			minparent.left = min.right; // �ּҰ��� �ڽĳ��� ����
			min.right = p.right; // ���� ���� ��ġ �ٲٱ�
		}

		return min;
	}

	public void quit() {
		// ���� ����
		System.exit(0);
	}
}
