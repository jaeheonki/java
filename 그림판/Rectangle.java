package 기말과제;

import java.awt.Graphics;
import java.util.ArrayList;

public class Rectangle extends Shape{
	int width;
	int height;
	
	public Rectangle(int x, int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) { //사각형 그리기
		g.drawRect(x, y, width, height); 
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //사각형 복사
		g.drawRect(x + 10, y+ 10, width, height); //가로10, 세로 10 만큼 이동
		shapes.add(new Rectangle(x+10, y+10, width, height)); //ArrayList에 새로운 사각형 추가
	}
	public boolean clickedIn(int x3 ,int y3) { //도형 내부인지 판단
		if((x3 > x) && (x3 < x+width) && (y3 > y) && (y3 < y + height))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //Control_Point를 그린다.
		g.drawRect(x-2, y-2, 4, 4); //왼쪽 위
		g.drawRect(x + width -2, y + height -2, 4, 4); //오른쪽 아래
		rect[0] = new Rectangle(x-2, y-2, 4, 4); //배열에 0번째에 왼쪽 위 사각형 저장
		rect[1] = new Rectangle(x + width -2, y + height -2, 4, 4); //배열의 1번째에 오른쪽 아래쪽 사각형 저장
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //사각형 이동
		x = endx - offx;  //a,b는 도형의 내부를 클릭했을때 어디에 클릭했는지 판단, 반영해서 이동한다.
		y = endy - offy;
		
		g.drawRect(x, y, width, height);
		return (new Rectangle(x, y, width, height)); //사각형 반환
	}
	public Shape SizeLeft(Graphics g,int endx, int endy) { //왼쪽위 사각형을 드래그했을 때 도형 크기 조절
		if(((x+width) <= endx) && ((y+height) <= endy)) { //사각형이 반전될때
			width = endx - x - width; 
			height = endy - y - height;
			x = endx;
			y = endy;
			g.drawRect(x, y, width, height);
			return (new Rectangle(x, y, width, height));
		}else {
			width = width + x - endx;  
			height = height + y - endy;  
			x = endx;      
			y = endy;
			g.drawRect(x, y, width, height); 
			return (new Rectangle(x, y, width, height));
		}
	}

	public Shape SizeRight(Graphics g,int endx, int endy) { 
		if((endx <= x) && (endy<= y)) { //사각형이 반전될때
			width = x - endx;
			height = y - endy;
			x = endx;
			y = endy;
			g.drawRect(x, y, width, height);
			return (new Rectangle(x, y, width, height));
		}else {
			width = endx - x;   
			height = endy - y;  
			g.drawRect(x, y, width, height);
			return (new Rectangle(x, y, width, height));
		}
	}
}

