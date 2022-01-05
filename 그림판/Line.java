package 기말과제;

import java.awt.Graphics;
import java.util.ArrayList;

public class Line extends Shape {
	int x2;
	int y2;
	Line(int x1, int y1, int x2, int y2){
		super(x1,y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(Graphics g) { //직선 그리기
		g.drawLine(x, y, x2, y2);
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //직선 복사 메소드
		g.drawLine(x+10,y,x2 + 10, y2); //가로로만 10 이동
		shapes.add(new Line(x+10, y, x2+10, y2));
	}
	public boolean clickedIn(int x3 ,int y3) { //직선을 클릭했는지 판단
		if((x3 > x) && (x3 < x2) && (y3 > y) && (y3 < y2))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //직선에 Control_Point를 그린다.
		g.drawRect(x-2, y-2, 4, 4);
		g.drawRect(x2 - 2, y2 -2, 4, 4);
		rect[0] = new Rectangle(x-2, y-2, 4, 4);
		rect[1] = new Rectangle(x2 - 2, y2 - 2, 4, 4);
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //사각형 이동
		x = endx - offx;  //a,b는 도형의 내부를 클릭했을때 어디에 클릭했는지 판단, 반영해서 이동한다.
		y = endy - offy;
		x2 = endx + offx - x;
		y2 = endy + endy - y;
		g.drawLine(x, y, x2, y2);
		return (new Line(x, y, x2, y2)); //사각형 반환
	}
	public Shape SizeLeft(Graphics g, int endx, int endy) { //왼쪽 위 박스를 드래그했을때 호출되는 메소드 - 크기조절
		if((x2 <= endx) && (y2 <= endy)) { //드래그가 끝난 곳이 x2, y2 보다 밑에 있을 때
			x = x2;
			y = y2;
			x2 = endx;
			y2 = endy;
			g.drawLine(x, y, x2, y2);
			return (new Line(x, y, x2, y2));
		}else {
			x = endx;
			y = endy;
			g.drawLine(x, y, x2, y2);
			return (new Line(x, y, x2, y2));
		}
	}
	public Shape SizeRight(Graphics g, int endx, int endy) { //오른쪽 아래쪽 박스를 드래그했을 때 호출되는 메소드 - 크기조절
		if((endx <= x) && (endy <= y)) { //드래그가 끝난 곳이 x, y 보다 위에 있을 때
			x2 = x;
			y2 = y;
			x = endx;
			y = endy;
			g.drawLine(x, y, x2, y2);
			return (new Line(x, y, x2, y2));
		}else {
			x2 = endx;
			y2 = endy;
			g.drawLine(x, y, x2, y2);
			return (new Line(x, y, x2, y2));
		}
	}
}
