package Manhattan;
//2015112232�����
public class M_T_P {
		
		static MTP_MAP[][] map = new MTP_MAP[5][5]; // �̸� �� ����
		
		//�� �����
		public static void makemap() {
		map[0][0] = new MTP_MAP(3,1);
		map[0][1] = new MTP_MAP(2,0);
		map[0][2] = new MTP_MAP(4,2);
		map[0][3] = new MTP_MAP(0,4);
		map[0][4] = new MTP_MAP(0,3);
		
		map[1][0] = new MTP_MAP(3,4);
		map[1][1] = new MTP_MAP(2,6);
		map[1][2] = new MTP_MAP(4,5);
		map[1][3] = new MTP_MAP(1,2);
		map[1][4] = new MTP_MAP(0,1);
		
		map[2][0] = new MTP_MAP(0,4);
		map[2][1] = new MTP_MAP(7,4);
		map[2][2] = new MTP_MAP(3,5);
		map[2][3] = new MTP_MAP(4,2);
		map[2][4] = new MTP_MAP(0,1);
		
		map[3][0] = new MTP_MAP(3,5);
		map[3][1] = new MTP_MAP(3,6);
		map[3][2] = new MTP_MAP(0,8);
		map[3][3] = new MTP_MAP(2,5);
		map[3][4] = new MTP_MAP(0,3);
		
		map[4][0] = new MTP_MAP(1,0);
		map[4][1] = new MTP_MAP(3,0);
		map[4][2] = new MTP_MAP(2,0);
		map[4][3] = new MTP_MAP(2,0);
		map[4][4] = new MTP_MAP(0,0);
		}
		
		public static void greedy() {
			//�׸��� �˰���
			int row=0,column=0;
			for(int i=1;i<=8;i++) { // �ᱹ 8���� �̵��ؾ��Ѵ�.
				if(map[row][column].right > map[row][column].bottom) { //ū������ ����.
					System.out.print("map["+row+"]"+"["+column+"]"+",");
					column++;
				}else
				{
					System.out.print("map["+row+"]"+"["+column+"]"+",");
					row++;
				}
				if(row == 4 && column==4) { //������ end ����
					System.out.print("map["+row+"]"+"["+column+"]");
				}
			}
		}
		
		
		
	public static void main(String[] args) {
		// �ʸ���� �׸��� �˰��� ����
		makemap();
		greedy();
	}
}

