package �⸻����;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;


public class FigureEditor {
	public static void main(String[] args) {
		new FigureEditorFrame();
	}
}

class FigureEditorFrame extends JFrame {
	PanelA panelA;
	PanelC panelC;
	
	FigureEditorFrame() {
		setSize(500,300);
		setTitle("�⸻ ������Ʈ");
		panelA = new PanelA();
		panelC = new PanelC(panelA);
		add(panelA, BorderLayout.CENTER);
		add(panelC, BorderLayout.WEST);
		setVisible(true);
	}
}

class PanelA extends JPanel {
	
	ArrayList<Shape> shapes = new ArrayList<Shape>(); //�������� ���� ArrayList shapes
	Rectangle rect[] = new Rectangle[2]; //Control_Point�� ���� Rectangle �迭 rect[]
	
	JLabel label;
	String selectedBtn="";
	Point start=null, end=null;
	int x3, y3; //clickedIn�� ���� Ŭ������ ���� ��ǥ
	char cmd = 'N'; //������ �׸��� � ������ �׸����� ���� char�� ����
	
	PanelA() {
		setBackground(Color.YELLOW); //��� yellow
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());  //���콺�� �д� ������ ���� 
	}
	

	
	class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			start = e.getPoint();
			x3 = e.getX();
			y3 = e.getY();
		}
		
        public void mousePressed(MouseEvent e) {
            start = e.getPoint();
            x3 = e.getX();
            y3 = e.getY();
        }
        public void mouseDragged(MouseEvent e) {
            end = e.getPoint();
            repaint(); // �г��� �׸��� ��û �ָ�
        }
        public void mouseReleased(MouseEvent e) {
        	end = e.getPoint();
        	
        	//���콺�� �׸� ���� �Է�
        	int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            int x2 = end.x;
            int y2 = end.y;
            
            if(cmd == 'R') { //��ư�� '�簢'�� �ԷµǾ��� ��
            	Shape s = new Rectangle(x, y, width, height); // ��ĳ����
				shapes.add(s); // shapes�� s �߰�
				cmd = 'N'; //�ٽ� cmd�� 'N'���� �ٲ㼭 ������ �ٽ� �׸� �� ��ư �ٽ� Ŭ���ϵ���
            }
            else if(cmd == 'O') { //��ư�� 'Ÿ��'�� �ԷµǾ��� ��
            	Shape s = new Oval(x,y,width,height); //��ĳ����
            	shapes.add(s); //shapes�� s �߰�
            	cmd = 'N';
            }
            else if(cmd == 'L'){
            	Shape s = new Line(x,y,x2,y2); //��ĳ����
            	shapes.add(s); // shapes�� s�߰�
            	cmd = 'N';
            }
            repaint(); 
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(start == null) // Ÿ���� ��������� �ʾ���
            return;
        g.setColor(Color.BLUE); // �Ķ��� ����
        //���콺�� �׸� ���� �Է�
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        int x2 = end.x;
        int y2 = end.y;
        if(cmd == 'R') { //��ư�� '�簢'�� �ԷµǾ��� ��
        	g.drawRect(x, y, width, height);
        }else if(cmd == 'O') { //��ư�� 'Ÿ��'�� �ԷµǾ��� ��
        	g.drawOval(x, y, width, height);
        }else if(cmd == 'L') {//��ư�� '����'�� �ԷµǾ��� ��
        	g.drawLine(x, y, x2, y2);
        }else if(selectedBtn == "�ҷ�����") { //��ư�� '�ҷ�����'�� �ԷµǾ��� ��
        	loadShapes(); //�׸��ǿ� ����� �������� �ҷ��´�.
        }
        for(int i=0; i<shapes.size();i++) {
        	//Ŭ���� ��ġ�� i��° ������ �����̰�, ���� �׸��Ⱑ ���õǾ����� ���� ��
        	if(shapes.get(i).clickedIn(x3, y3) && cmd =='N') {
        		if(selectedBtn == "����") { //��ư�� '����'�� �ԷµǾ����� ��
        			shapes.get(i).copy(g, shapes);
        		}
        		else if(selectedBtn == "����") {//��ư�� '����'�� �ԷµǾ����� ��
        			shapes.remove(i);
        		}
        		else { //��ư�� �ƹ��͵� �ԷµǾ����� ���� ��
        			shapes.get(i).Control_Point(g, rect);		
        		}
        		break;
		}
    }
        for (int i=0; i<shapes.size(); i++) {
        	shapes.get(i).draw(g); //�� �ڵ尡 ������ �׷��� �� ������ �������� ���礤��.
        }
}
    public void saveShapes() { //�׸��ǿ� ������ �����ϴ� �޼ҵ�
    	ObjectOutputStream out = null;
    	try {
			out = new ObjectOutputStream(new FileOutputStream("shapes.dat"));
			out.writeObject(shapes);
			out.close();
			System.out.print("����Ǿ����ϴ�.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void loadShapes() {  //�׸��ǿ� ����� ������ �ҷ����� �޼ҵ�
    	ObjectInputStream in = null;
    	try {
			in = new ObjectInputStream(new FileInputStream("shapes.dat"));
			shapes = (ArrayList<Shape>)in.readObject();
			in.close();
			System.out.print("�ε�Ǿ����ϴ�. ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

class PanelB extends JPanel {
	PanelA panelA;
	PanelB(PanelA panelA) {
		this.panelA = panelA;
		setBackground(Color.BLUE); //���� ����
		setLayout(new GridLayout(5,1,5,5));
		JButton b1 = new JButton("�簢");
		JButton b2 = new JButton("Ÿ��");
		JButton b3 = new JButton("����"); // ��ư�� �߰�
		JButton b4 = new JButton("����");
		JButton b5 = new JButton("�ҷ�����");
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		ActionListener listner = new MyActionListener();
		b1.addActionListener(listner);
		b2.addActionListener(listner);
		b3.addActionListener(listner); // ��ư�� �׼Ǹ����� �ޱ�
		b4.addActionListener(listner);
		b5.addActionListener(listner);
	}
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			panelA.selectedBtn = actionCmd;
			if(e.getActionCommand() == "����") { //��ư�� '����'�� ��������
				panelA.saveShapes(); //���Ͽ� �׸����� �����Ѵ�.
			} //��ư���� ������ �� '�簢'�� 'R', 'Ÿ��'�� 'O', '����'�� L�� cmd�� �ٲ۴�.
			if(e.getActionCommand() == "�簢") {
				panelA.cmd = 'R';
			}else if(e.getActionCommand() == "Ÿ��") {
				panelA.cmd = 'O';
			}else if(e.getActionCommand() == "����") {
				panelA.cmd = 'L';
			}else {
				panelA.cmd = 'N';
			}
			
		}
		
	}
}

class PanelC extends JPanel {
	PanelC(PanelA panelA) {
		add(new PanelB(panelA));
	}
}
