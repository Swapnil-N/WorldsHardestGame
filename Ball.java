import java.awt.*;
public class Ball{

	private int x,y,size,dir;
	private Polygon poly;

	public Ball(int a, int b, int c, int d, Polygon f){
		x = a;
		y = b;
		size = c;
		dir = d;
		poly=f;
	}

	public void moveX(){

		if (dir==0){
			if(poly.contains(new Rectangle(x+2,y,15,15)))
					x+=2;
			else dir=1;
		}

		else{
			if(poly.contains(new Rectangle(x-2,y,15,15)))
				x-=2;
			else dir=0;
		}
	}

	public void moveY(){

		if (dir==0){
			if(poly.contains(new Rectangle(x,y+2,15,15)))
					y+=2;
			else dir=1;
		}

		else{
			if(poly.contains(new Rectangle(x,y-2,15,15)))
				y-=2;
			else dir=0;
		}
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getSize(){
		return size;
	}

	public int getDir(){
		return dir;
	}

	public Polygon getPolygon(){
		return poly;
	}
	//accessor methods for variables



}