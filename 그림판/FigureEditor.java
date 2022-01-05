package 기말과제;

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
		setTitle("기말 프로젝트");
		panelA = new PanelA();
		panelC = new PanelC(panelA);
		add(panelA, BorderLayout.CENTER);
		add(panelC, BorderLayout.WEST);
		setVisible(true);
	}
}

class PanelA extends JPanel {
	
	ArrayList<Shape> shapes = new ArrayList<Shape>(); //도형들을 담을 ArrayList shapes
	Rectangle rect[] = new Rectangle[2]; //Control_Point를 담을 Rectangle 배열 rect[]
	
	JLabel label;
	String selectedBtn="";
	Point start=null, end=null;
	int x3, y3; //clickedIn에 넣을 클릭했을 때의 좌표
	char cmd = 'N'; //도형을 그릴때 어떤 도형을 그릴지에 대한 char형 변수
	
	PanelA() {
		setBackground(Color.YELLOW); //배경 yellow
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());  //마우스를 읽는 리스너 부착 
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
            repaint(); // 패널의 그리기 요청 주목
        }
        public void mouseReleased(MouseEvent e) {
        	end = e.getPoint();
        	
        	//마우스로 그린 값을 입력
        	int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            int x2 = end.x;
            int y2 = end.y;
            
            if(cmd == 'R') { //버튼에 '사각'이 입력되었을 때
            	Shape s = new Rectangle(x, y, width, height); // 업캐스팅
				shapes.add(s); // shapes에 s 추가
				cmd = 'N'; //다시 cmd를 'N'으로 바꿔서 도형을 다시 그릴 때 버튼 다시 클릭하도록
            }
            else if(cmd == 'O') { //버튼에 '타원'이 입력되었을 때
            	Shape s = new Oval(x,y,width,height); //업캐스팅
            	shapes.add(s); //shapes에 s 추가
            	cmd = 'N';
            }
            else if(cmd == 'L'){
            	Shape s = new Line(x,y,x2,y2); //업캐스팅
            	shapes.add(s); // shapes에 s추가
            	cmd = 'N';
            }
            repaint(); 
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(start == null) // 타원이 만들어지지 않았음
            return;
        g.setColor(Color.BLUE); // 파란색 선택
        //마우스로 그린 값을 입력
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        int x2 = end.x;
        int y2 = end.y;
        if(cmd == 'R') { //버튼에 '사각'이 입력되었을 때
        	g.drawRect(x, y, width, height);
        }else if(cmd == 'O') { //버튼에 '타원'이 입력되었을 때
        	g.drawOval(x, y, width, height);
        }else if(cmd == 'L') {//버튼에 '직선'이 입력되었을 때
        	g.drawLine(x, y, x2, y2);
        }else if(selectedBtn == "불러오기") { //버튼에 '불러오기'가 입력되었을 때
        	loadShapes(); //그림판에 저장된 도형들을 불러온다.
        }
        for(int i=0; i<shapes.size();i++) {
        	//클릭한 위치가 i번째 도형의 내부이고, 도형 그리기가 선택되어있지 않을 때
        	if(shapes.get(i).clickedIn(x3, y3) && cmd =='N') {
        		if(selectedBtn == "복사") { //버튼에 '복사'가 입력되어있을 때
        			shapes.get(i).copy(g, shapes);
        		}
        		else if(selectedBtn == "삭제") {//버튼에 '삭제'가 입력되어있을 때
        			shapes.remove(i);
        		}
        		else { //버튼에 아무것도 입력되어있지 않을 때
        			shapes.get(i).Control_Point(g, rect);		
        		}
        		break;
		}
    }
        for (int i=0; i<shapes.size(); i++) {
        	shapes.get(i).draw(g); //이 코드가 없으면 그려진 후 도형이 남아있지 안흔ㄴ다.
        }
}
    public void saveShapes() { //그림판에 도형을 저장하는 메소드
    	ObjectOutputStream out = null;
    	try {
			out = new ObjectOutputStream(new FileOutputStream("shapes.dat"));
			out.writeObject(shapes);
			out.close();
			System.out.print("저장되었습니다.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void loadShapes() {  //그림판에 저장된 도형을 불러오는 메소드
    	ObjectInputStream in = null;
    	try {
			in = new ObjectInputStream(new FileInputStream("shapes.dat"));
			shapes = (ArrayList<Shape>)in.readObject();
			in.close();
			System.out.print("로드되었습니다. ");
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
		setBackground(Color.BLUE); //배경색 변경
		setLayout(new GridLayout(5,1,5,5));
		JButton b1 = new JButton("사각");
		JButton b2 = new JButton("타원");
		JButton b3 = new JButton("직선"); // 버튼들 추가
		JButton b4 = new JButton("저장");
		JButton b5 = new JButton("불러오기");
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		ActionListener listner = new MyActionListener();
		b1.addActionListener(listner);
		b2.addActionListener(listner);
		b3.addActionListener(listner); // 버튼에 액션리스너 달기
		b4.addActionListener(listner);
		b5.addActionListener(listner);
	}
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			panelA.selectedBtn = actionCmd;
			if(e.getActionCommand() == "저장") { //버튼에 '저장'이 눌렸을때
				panelA.saveShapes(); //파일에 그림들을 저장한다.
			} //버튼들이 눌렸을 때 '사각'은 'R', '타원'은 'O', '직선'은 L로 cmd를 바꾼다.
			if(e.getActionCommand() == "사각") {
				panelA.cmd = 'R';
			}else if(e.getActionCommand() == "타원") {
				panelA.cmd = 'O';
			}else if(e.getActionCommand() == "직선") {
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
