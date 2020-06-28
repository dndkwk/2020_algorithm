package RBTree;
//2015112232�����

class Node {
	public int key;
	public Node left;
	public Node right;
	public Node parent;
	public char color = 'r'; // r=red/b=black
}

public class RBT {
	public Node root;
	public Node nil;// leaf ���

	RBT() {
		// ������
		// leaf��� ���� �� �ʱ�ȭ
		nil = new Node();
		nil.right = null;
		nil.left = null;
		nil.color = 'b';
		root = nil;
	}

	public void show() {
		// ���
		// ����Լ��� �̿��� key�� ������ ���
		System.out.println("");
		if (root == nil) {
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
		if (p != nil) {
			showSub(p.right, level + 1);
			for (j = 0; j < level; j++) {
				System.out.print("\t");
			}
			System.out.print(p.key + "" + p.color);// Ű�� ���� ���
			if ((p.right != nil) && (p.left != nil)) {
				System.out.print("<");
			} else if (p.right != nil) {
				System.out.print("/");
			} else if (p.left != nil) {
				System.out.print("\\");
			}
			System.out.println("");
			showSub(p.left, level + 1);
		}
	}

	public void insert(int element) {
		// root�� ����
		Node newNode = new Node();
		newNode.parent = null;
		newNode.key = element;
		newNode.left = nil;
		newNode.right = nil;
		newNode.color = 'r'; // ���ο� ��� �ʱ�ȭ

		Node y = null;
		Node x = this.root;

		while (x != nil) { // parent���ϱ�
			y = x;
			if (newNode.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		newNode.parent = y;// parent ����
		if (y == null) {// ����ִ� Ʈ�� �϶�
			root = newNode;
		} else if (newNode.key < y.key) {// ���� ������ �����ڽ�
			y.left = newNode;
		} else {// ���� ũ�� ������ �ڽ�
			y.right = newNode;
		}

		if (newNode.parent == null) { // ����ִ� Ʈ������ ó�� ��� �����϶�
			newNode.color = 'b';// ���
			return;
		}

		if (newNode.parent.parent == null) {// ���θ� ���� ��
			return;
		}

		fixinsert(newNode); // rotate �� �� �ٲٱ�
	}

	public void fixinsert(Node k) {
		// �� �ٲٱ� �� rotate�ϱ�
		Node u;// ���� ��� ����
		while (k.parent.color == 'r') {// �θ� ����
			if (k.parent == k.parent.parent.right) {// �θ� ���θ��� ������ �ڽ�
				u = k.parent.parent.left;// ����
				if (u.color == 'r') {// ������ ����
					u.color = 'b';// ������� �ٲ�
					k.parent.color = 'b';// �θ� ���� �������
					k.parent.parent.color = 'r'; // ���θ� ���� ��������
					k = k.parent.parent; // ���θ� �������� �� �ݺ���
				} else {// ���� ���
					if (k == k.parent.left) {// �����ڽ�
						k = k.parent;
						rightRotate(k);// ������ ȸ��
					}
					k.parent.color = 'b';
					k.parent.parent.color = 'r';
					leftRotate(k.parent.parent);// ���� ȸ��
				}
			} else {// �θ� ���θ��� ���� �ڽ��̿��� ���� ���� ������� �Ѵ�
				u = k.parent.parent.right;// ����
				if (u.color == 'r') {
					u.color = 'b';
					k.parent.color = 'b';
					k.parent.parent.color = 'r';
					k = k.parent.parent;
				} else {
					if (k == k.parent.right) {
						k = k.parent;
						leftRotate(k);
					}
					k.parent.color = 'b';
					k.parent.parent.color = 'r';
					rightRotate(k.parent.parent);
				}
			}
			if (k == root) {// root�� ����
				break;
			}
		}
		root.color = 'b';// root�� ���� �׻� ���
	}

	public void leftRotate(Node x) {
		// �������� ������
		Node y = x.right;
		x.right = y.left;// y�� ���� ����Ʈ���� x�� �����ʿ� ���δ�

		// �θ���� �ٽ� �����Ѵ�
		if (y.left != nil) { // leaf ��尡 �ƴϸ�
			y.left.parent = x;
		}
		y.parent = x.parent;

		if (x.parent == null) {// �θ� ������
			this.root = y;
		} else if (x == x.parent.left) {// x�� �����ڽ�
			x.parent.left = y;
		} else { // x�� �������ڽ�
			x.parent.right = y;
		}

		// x�� y �����ϱ�
		y.left = x;
		x.parent = y;
	}

	public void rightRotate(Node x) {
		// ���������� ������
		Node y = x.left;
		x.left = y.right;

		// �θ���� �ٽ� �����Ѵ�
		if (y.right != nil) {// leaf ��尡 �ƴϸ�
			y.right.parent = x;
		}
		y.parent = x.parent;

		if (x.parent == null) {// �θ� ������
			this.root = y;
		} else if (x == x.parent.right) {// x�� ������ �ڽ�
			x.parent.right = y;
		} else {// x�� ���� �ڽ�
			x.parent.left = y;
		}

		// x�� y�����ϱ�
		y.right = x;
		x.parent = y;
	}
}
