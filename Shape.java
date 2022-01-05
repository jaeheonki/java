package �⸻����;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

abstract public class Shape implements Serializable { //���� ������� ���ؼ� ����Ŭ������ ����ȭ
	int x;
	int y;
	Shape(int x, int y){
		this.x = x;
		this.y = y;
	} //����Ŭ������ ��ġ�� �޼ҵ尡 ���� �߻�Ŭ������ ����
	abstract void draw(Graphics g);  //������ �׸��� �޼ҵ�
	abstract void copy(Graphics g, ArrayList<Shape> shapes); //������ �����ϴ� �޼ҵ�
	abstract boolean clickedIn(int x3 ,int y3); //Ŭ������ �� ������ �������� �Ǵ����ִ� �޼ҵ�
	abstract void Control_Point(Graphics g, Rectangle rect[]); //Control_Point�� �׷��ִ� �޼ҵ� 
	abstract Shape move(Graphics g, int offx, int offy, int endx, int endy); //������ �̵������ִ� �޼ҵ�
	abstract Shape SizeLeft(Graphics g, int endx, int endy); //���� ���� ControlPoint�� �巡������ ��, ũ�⸦ �ٲ��ִ� �޼ҵ�
	abstract Shape SizeRight(Graphics g, int endx, int endy); //������ �Ʒ��� ControlPoint�� �巡�� ���� ��, ũ�⸦ �ٲ��ִ� �޼ҵ�
	

}
