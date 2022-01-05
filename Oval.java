package 기말과제;

import java.awt.Graphics;
import java.util.ArrayList;

public class Oval extends Shape{
	int width;
	int height;
	
	public Oval(int x, int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) { //타원 그리기
		g.drawOval(x, y, width, height); 
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //타원 복사 메소드
		g.drawOval(x + 10, y+ 10, width, height); //가로 10, 세로 10 이동
		shapes.add(new Oval(x+10, y+10, width, height));
	}
	public boolean clickedIn(int x3 ,int y3) { //타원 내부에 있는지 검사
		if((x3 > x) && (x3 < x+width) && (y3 > y) && (y3 < y + height))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //Control_Point 생성하는 메소드
		g.drawRect(x-2, y-2, 4, 4);
		g.drawRect(x + width -2, y + height -2, 4, 4);
		rect[0] = new Rectangle(x-2, y-2, 4, 4); //rect[0]에 저장
		rect[1] = new Rectangle(x + width -2, y + height -2, 4, 4); //rect[1]에 저장
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //사각형 이동
		x = endx - offx;  //a,b는 도형의 내부를 클릭했을때 어디에 클릭했는지 판단, 반영해서 이동한다.
		y = endy - offy;
		
		g.drawOval(x, y, width, height);
		return (new Oval(x, y, width, height)); //사각형 반환
	}
	public Shape SizeLeft(Graphics g,int endx, int endy) { //왼쪽 박스를 드래그했을 때
		if(((x+width) <= endx) && ((y+height) <= endy)) { //아래쪽 박스보다 밑으로 드래그했을 때
			width = endx - x - width;
			height = endy - y - height;
			x = endx;
			y = endy;
			g.drawOval(x, y, width, height);
			return (new Oval(x, y, width, height));
		}else {
			width = width + x - endx;  
			height = height + y - endy;  
			x = endx;      
			y = endy;
			g.drawOval(x, y, width, height); 
			return (new Oval(x, y, width, height));
		}
	}

	public Shape SizeRight(Graphics g,int endx, int endy) { //아래쪽 박스를 드래그했을 때
		if((endx <= x) && (endy<= y)) { //위쪽 박스보다 위로 드래그했을 때
			width = x - endx;
			height = y - endy;
			x = endx;
			y = endy;
			g.drawOval(x, y, width, height);
			return (new Oval(x, y, width, height));
		}else {
			width = endx - x;   
			height = endy - y;  
			g.drawOval(x, y, width, height);
			return (new Oval(x, y, width, height));
		}
	}
}
