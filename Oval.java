package �⸻����;

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
	
	public void draw(Graphics g) { //Ÿ�� �׸���
		g.drawOval(x, y, width, height); 
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //Ÿ�� ���� �޼ҵ�
		g.drawOval(x + 10, y+ 10, width, height); //���� 10, ���� 10 �̵�
		shapes.add(new Oval(x+10, y+10, width, height));
	}
	public boolean clickedIn(int x3 ,int y3) { //Ÿ�� ���ο� �ִ��� �˻�
		if((x3 > x) && (x3 < x+width) && (y3 > y) && (y3 < y + height))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //Control_Point �����ϴ� �޼ҵ�
		g.drawRect(x-2, y-2, 4, 4);
		g.drawRect(x + width -2, y + height -2, 4, 4);
		rect[0] = new Rectangle(x-2, y-2, 4, 4); //rect[0]�� ����
		rect[1] = new Rectangle(x + width -2, y + height -2, 4, 4); //rect[1]�� ����
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //�簢�� �̵�
		x = endx - offx;  //a,b�� ������ ���θ� Ŭ�������� ��� Ŭ���ߴ��� �Ǵ�, �ݿ��ؼ� �̵��Ѵ�.
		y = endy - offy;
		
		g.drawOval(x, y, width, height);
		return (new Oval(x, y, width, height)); //�簢�� ��ȯ
	}
	public Shape SizeLeft(Graphics g,int endx, int endy) { //���� �ڽ��� �巡������ ��
		if(((x+width) <= endx) && ((y+height) <= endy)) { //�Ʒ��� �ڽ����� ������ �巡������ ��
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

	public Shape SizeRight(Graphics g,int endx, int endy) { //�Ʒ��� �ڽ��� �巡������ ��
		if((endx <= x) && (endy<= y)) { //���� �ڽ����� ���� �巡������ ��
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
