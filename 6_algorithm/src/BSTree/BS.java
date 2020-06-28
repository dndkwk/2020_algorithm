package BSTree;
//2015112232백근주
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
		// 출력
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
		// 출력 연산
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
		// 삽입 연산
		Node newNode = new Node(element);
		if (root == null) { // empty라면 새로운 node를 가리킨다.
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) { // key값을 비교하여 null인 자리에 insert한다.
			parent = current;
			if (element < current.key) { // 현재 key값이 element보다 클때
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {// 현재 key값이 element보다 작을 때
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public void retrieve(int element) {
		// 탐색 연산
		Node current = root;
		while (current != null) {
			if (current.key == element) { // 현재 key값이 element와 같다면
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
			} else if (current.key > element) {// 현재 key값이 element보다 크다면 left로
				current = current.left;
			} else {// 현재 key값이 element보다 작다면 right로
				current = current.right;
			}
		}
	}

	public void remove(int element) {
		// 삭제 연산
		if(root==null) {
			return;
		}
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false; // 왼쪽 자식인지 오른쪽 자식인지 구별해주는 변수
		while (current.key != element) { // 삭제하려는 값의 노드와 그 노드의 부모노드를 알려주는 반복문
			parent = current;
			if (current.key > element) { // 왼쪽 자식으로 이동
				isLeftChild = true;
				current = current.left;
			} else { // 오른쪽 자식으로 이동
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) { // 없다면
				System.out.println("해당하는 값이 없습니다.");
				return;
			}
		}
		// 경우 1 : 자식 노드가 없는 경우
		if ((current.left == null) && (current.right == null)) {
			if (current == root) { // root의 key가 삭제 값일때
				root = null;
			}
			if (isLeftChild == true) { // 부모의 왼쪽 자식이라면
				parent.left = null;
			} else { // 부모의 오른쪽 자식이라면
				parent.right = null;
			}
		} else if (current.right == null) {// 경우 2 : 자식이 한개일 경우
			// 오른쪽 자식이 없을 때
			if (current == root) {// root의 key가 삭제 값일때
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.left == null) {
			// 왼쪽 자식이 없을 때
			if (current == root) {// root의 key가 삭제 값일때
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if ((current.left != null) && (current.right != null)) {
			// 경우 3 : 자식이 두 개일 경우
			// 오른쪽 서브 트리에서 최소값을 찾는다.
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
		// 최소값 찾기
		Node min = null;
		Node minparent = null;
		Node curernt = p.right;

		while (curernt != null) {//마지막 왼쪽 자식까지 도달
			minparent = min;
			min = curernt;
			curernt = curernt.left;
		}
		if (min != p.right) { // 최소값이 바로 오른쪽 자식이 아닐때
			minparent.left = min.right; // 최소값의 자식노드와 연결
			min.right = p.right; // 삭제 노드와 위치 바꾸기
		}

		return min;
	}

	public void quit() {
		// 종료 연산
		System.exit(0);
	}
}
