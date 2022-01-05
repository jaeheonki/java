package �⸻����;

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
	
	public void draw(Graphics g) { //�簢�� �׸���
		g.drawRect(x, y, width, height); 
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //�簢�� ����
		g.drawRect(x + 10, y+ 10, width, height); //����10, ���� 10 ��ŭ �̵�
		shapes.add(new Rectangle(x+10, y+10, width, height)); //ArrayList�� ���ο� �簢�� �߰�
	}
	public boolean clickedIn(int x3 ,int y3) { //���� �������� �Ǵ�
		if((x3 > x) && (x3 < x+width) && (y3 > y) && (y3 < y + height))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //Control_Point�� �׸���.
		g.drawRect(x-2, y-2, 4, 4); //���� ��
		g.drawRect(x + width -2, y + height -2, 4, 4); //������ �Ʒ�
		rect[0] = new Rectangle(x-2, y-2, 4, 4); //�迭�� 0��°�� ���� �� �簢�� ����
		rect[1] = new Rectangle(x + width -2, y + height -2, 4, 4); //�迭�� 1��°�� ������ �Ʒ��� �簢�� ����
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //�簢�� �̵�
		x = endx - offx;  //a,b�� ������ ���θ� Ŭ�������� ��� Ŭ���ߴ��� �Ǵ�, �ݿ��ؼ� �̵��Ѵ�.
		y = endy - offy;
		
		g.drawRect(x, y, width, height);
		return (new Rectangle(x, y, width, height)); //�簢�� ��ȯ
	}
	public Shape SizeLeft(Graphics g,int endx, int endy) { //������ �簢���� �巡������ �� ���� ũ�� ����
		if(((x+width) <= endx) && ((y+height) <= endy)) { //�簢���� �����ɶ�
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
		if((endx <= x) && (endy<= y)) { //�簢���� �����ɶ�
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

