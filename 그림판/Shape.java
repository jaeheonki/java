package 기말과제;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

abstract public class Shape implements Serializable { //파일 입출력을 위해서 슈퍼클래스를 직렬화
	int x;
	int y;
	Shape(int x, int y){
		this.x = x;
		this.y = y;
	} //슈퍼클래스에 겹치는 메소드가 많아 추상클래스로 선언
	abstract void draw(Graphics g);  //도형을 그리는 메소드
	abstract void copy(Graphics g, ArrayList<Shape> shapes); //도형을 복사하는 메소드
	abstract boolean clickedIn(int x3 ,int y3); //클릭했을 때 도형의 내부인지 판단해주는 메소드
	abstract void Control_Point(Graphics g, Rectangle rect[]); //Control_Point를 그려주는 메소드 
	abstract Shape move(Graphics g, int offx, int offy, int endx, int endy); //도형을 이동시켜주는 메소드
	abstract Shape SizeLeft(Graphics g, int endx, int endy); //왼쪽 위의 ControlPoint를 드래그했을 때, 크기를 바꿔주는 메소드
	abstract Shape SizeRight(Graphics g, int endx, int endy); //오른쪽 아래의 ControlPoint를 드래그 했을 때, 크기를 바꿔주는 메소드
	

}
