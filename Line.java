package �⸻����;

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
	public void draw(Graphics g) { //���� �׸���
		g.drawLine(x, y, x2, y2);
	}
	public void copy(Graphics g, ArrayList<Shape> shapes) { //���� ���� �޼ҵ�
		g.drawLine(x+10,y,x2 + 10, y2); //���ηθ� 10 �̵�
		shapes.add(new Line(x+10, y, x2+10, y2));
	}
	public boolean clickedIn(int x3 ,int y3) { //������ Ŭ���ߴ��� �Ǵ�
		if((x3 > x) && (x3 < x2) && (y3 > y) && (y3 < y2))
			return true;
		else
			return false;
	}
	public void Control_Point(Graphics g, Rectangle rect[]) { //������ Control_Point�� �׸���.
		g.drawRect(x-2, y-2, 4, 4);
		g.drawRect(x2 - 2, y2 -2, 4, 4);
		rect[0] = new Rectangle(x-2, y-2, 4, 4);
		rect[1] = new Rectangle(x2 - 2, y2 - 2, 4, 4);
	}
	public Shape move(Graphics g, int offx, int offy, int endx, int endy) { //�簢�� �̵�
		x = endx - offx;  //a,b�� ������ ���θ� Ŭ�������� ��� Ŭ���ߴ��� �Ǵ�, �ݿ��ؼ� �̵��Ѵ�.
		y = endy - offy;
		x2 = endx + offx - x;
		y2 = endy + endy - y;
		g.drawLine(x, y, x2, y2);
		return (new Line(x, y, x2, y2)); //�簢�� ��ȯ
	}
	public Shape SizeLeft(Graphics g, int endx, int endy) { //���� �� �ڽ��� �巡�������� ȣ��Ǵ� �޼ҵ� - ũ������
		if((x2 <= endx) && (y2 <= endy)) { //�巡�װ� ���� ���� x2, y2 ���� �ؿ� ���� ��
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
	public Shape SizeRight(Graphics g, int endx, int endy) { //������ �Ʒ��� �ڽ��� �巡������ �� ȣ��Ǵ� �޼ҵ� - ũ������
		if((endx <= x) && (endy <= y)) { //�巡�װ� ���� ���� x, y ���� ���� ���� ��
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
