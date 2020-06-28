package RBTree;
//2015112232백근주

class Node {
	public int key;
	public Node left;
	public Node right;
	public Node parent;
	public char color = 'r'; // r=red/b=black
}

public class RBT {
	public Node root;
	public Node nil;// leaf 노드

	RBT() {
		// 생성자
		// leaf노드 생성 및 초기화
		nil = new Node();
		nil.right = null;
		nil.left = null;
		nil.color = 'b';
		root = nil;
	}

	public void show() {
		// 출력
		// 재귀함수를 이용해 key와 색깔을 출력
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
		// 출력 연산
		int j;
		if (p != nil) {
			showSub(p.right, level + 1);
			for (j = 0; j < level; j++) {
				System.out.print("\t");
			}
			System.out.print(p.key + "" + p.color);// 키와 색을 출력
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
		// root로 삽입
		Node newNode = new Node();
		newNode.parent = null;
		newNode.key = element;
		newNode.left = nil;
		newNode.right = nil;
		newNode.color = 'r'; // 새로운 노드 초기화

		Node y = null;
		Node x = this.root;

		while (x != nil) { // parent구하기
			y = x;
			if (newNode.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		newNode.parent = y;// parent 저장
		if (y == null) {// 비어있는 트리 일때
			root = newNode;
		} else if (newNode.key < y.key) {// 값이 작으면 왼쪽자식
			y.left = newNode;
		} else {// 값이 크면 오른쪽 자식
			y.right = newNode;
		}

		if (newNode.parent == null) { // 비어있는 트리에서 처음 노드 삽입일때
			newNode.color = 'b';// 흑색
			return;
		}

		if (newNode.parent.parent == null) {// 조부모가 없을 때
			return;
		}

		fixinsert(newNode); // rotate 및 색 바꾸기
	}

	public void fixinsert(Node k) {
		// 색 바꾸기 및 rotate하기
		Node u;// 삼촌 노드 저장
		while (k.parent.color == 'r') {// 부모가 적색
			if (k.parent == k.parent.parent.right) {// 부모가 조부모의 오른쪽 자식
				u = k.parent.parent.left;// 삼촌
				if (u.color == 'r') {// 삼촌이 적색
					u.color = 'b';// 흑색으로 바꿈
					k.parent.color = 'b';// 부모 색도 흑색으로
					k.parent.parent.color = 'r'; // 조부모 색을 적색으로
					k = k.parent.parent; // 조부모를 기준으로 또 반복함
				} else {// 삼촌 흑색
					if (k == k.parent.left) {// 왼쪽자식
						k = k.parent;
						rightRotate(k);// 오른쪽 회전
					}
					k.parent.color = 'b';
					k.parent.parent.color = 'r';
					leftRotate(k.parent.parent);// 왼쪽 회전
				}
			} else {// 부모가 조부모의 왼쪽 자식이여도 위와 같은 방식으로 한다
				u = k.parent.parent.right;// 삼촌
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
			if (k == root) {// root면 끝냄
				break;
			}
		}
		root.color = 'b';// root의 색은 항상 흑색
	}

	public void leftRotate(Node x) {
		// 왼쪽으로 돌리기
		Node y = x.right;
		x.right = y.left;// y의 왼쪽 서브트리를 x의 오른쪽에 붙인다

		// 부모들을 다시 저장한다
		if (y.left != nil) { // leaf 노드가 아니면
			y.left.parent = x;
		}
		y.parent = x.parent;

		if (x.parent == null) {// 부모가 없으면
			this.root = y;
		} else if (x == x.parent.left) {// x가 왼쪽자식
			x.parent.left = y;
		} else { // x가 오른쪽자식
			x.parent.right = y;
		}

		// x와 y 연결하기
		y.left = x;
		x.parent = y;
	}

	public void rightRotate(Node x) {
		// 오른쪽으로 돌리기
		Node y = x.left;
		x.left = y.right;

		// 부모들을 다시 저장한다
		if (y.right != nil) {// leaf 노드가 아니면
			y.right.parent = x;
		}
		y.parent = x.parent;

		if (x.parent == null) {// 부모가 없으면
			this.root = y;
		} else if (x == x.parent.right) {// x가 오른쪽 자식
			x.parent.right = y;
		} else {// x가 왼쪽 자식
			x.parent.left = y;
		}

		// x와 y연결하기
		y.right = x;
		x.parent = y;
	}
}
