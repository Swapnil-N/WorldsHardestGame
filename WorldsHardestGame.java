import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
public class WorldsHardestGame extends JPanel implements KeyListener,Runnable
{
	private float angle;
	private int x;
	private int y;
	private JFrame frame;
	private Thread t;
	private boolean gameOn;
	private Font f;
	private Polygon poly;
	private GradientPaint gp;
	private Color color;
	private int deaths;
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private ArrayList<Ball> ballList;
	private int level = 1;
	private boolean show = true;
	private Ball thing;
	private boolean hit = true;

	public WorldsHardestGame()
	{
		frame=new JFrame();
		x=100;
		y=300;
		deaths = 0;
		gameOn=true;
		color=new Color(250,100,100);
		level1();
		f=new Font("ARIAL",Font.BOLD,22);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(806,529);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
	}
	public void level1()
	{
 		poly= new Polygon();
		poly.addPoint(75,180);
		poly.addPoint(185,180);
		poly.addPoint(185,360);
		poly.addPoint(220,360);
		poly.addPoint(220,220);
		poly.addPoint(545,220);
		poly.addPoint(545,185);
		poly.addPoint(725,185);
		poly.addPoint(725,400);
		poly.addPoint(615,400);
		poly.addPoint(615,220);
		poly.addPoint(580,220);
		poly.addPoint(580,365);
		poly.addPoint(255,365);
		poly.addPoint(255,400);
		poly.addPoint(75,400);

		ballList=new ArrayList<Ball>();

		ballList.add(new Ball(225,300,15,0,poly));
		ballList.add(new Ball(225,230,15,0,poly));
		ballList.add(new Ball(550,265,15,1,poly));
		ballList.add(new Ball(550,335,15,1,poly));



	}
	public void level2()
	{
		poly= new Polygon();

		poly.addPoint(75,255);
		poly.addPoint(184,255);
		poly.addPoint(184,184);
		poly.addPoint(615,184);
		poly.addPoint(615,255);
		poly.addPoint(720,255);

		poly.addPoint(720,330);
		poly.addPoint(615,330);
		poly.addPoint(615,400);
		poly.addPoint(184,400);
		poly.addPoint(184,330);
		poly.addPoint(75,330);

		x = 100;
		y = 280;

		ballList=new ArrayList<Ball>();

		ballList.add(new Ball(198,204,15,0,poly));
		ballList.add(new Ball(270,204,15,0,poly));
		ballList.add(new Ball(342,204,15,0,poly));
		ballList.add(new Ball(414,204,15,0,poly));
		ballList.add(new Ball(486,204,15,0,poly));
		ballList.add(new Ball(558,204,15,0,poly));

		ballList.add(new Ball(234,380,15,1,poly));
		ballList.add(new Ball(306,380,15,1,poly));
		ballList.add(new Ball(378,380,15,1,poly));
		ballList.add(new Ball(450,380,15,1,poly));
		ballList.add(new Ball(522,380,15,1,poly));
		ballList.add(new Ball(592,380,15,1,poly));

		thing = new Ball(400,295,12,0,poly);


	}



	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting (AND ONLY PAINTING) happens here!
		//Don't use this method to deal with mathematics
		//The painting imps aren't fond of math.

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,0,800,500);
		g2d.setColor(new Color(180,181,254));
		g2d.fillRect(5,45,790,450);

		g2d.setFont(f);
		g2d.setColor(new Color(248,248,248));
		g2d.drawString("MENU",12,30);
		g2d.drawString(level+"/30",375,30);
		g2d.drawString("DEATHS: "+ deaths,670,30);


		g2d.setPaint(Color.WHITE);
		g2d.fill(poly);
		if(level==1)
		{
			g2d.setPaint(new Color(181,254,180));
			g2d.fillRect(75,180,108,220);
			g2d.fillRect(615,185,108,215);

			int box1 = 222;
			g2d.setPaint(new Color(230,230,255));

			for(int i=0;i<5;i++){
				g2d.fillRect(box1,220,36,36);
				g2d.fillRect(box1,290,36,36);
				box1+=72;
			}

			int box2 = 257;
			for(int i=0;i<5;i++){
				g2d.fillRect(box2,257,36,36);
				g2d.fillRect(box2,327,36,36);
				box2+=72;
			}

			g2d.fillRect(545,185,36,36);
			g2d.fillRect(221,362,36,36);

		}
		else{
			int box1 = 185;
			g2d.setPaint(new Color(230,230,255));

			for(int i=0;i<6;i++){
				g2d.fillRect(box1,185,36,36);
				g2d.fillRect(box1,257,36,36);
				g2d.fillRect(box1,329,36,36);
				box1+=72;
			}

			int box2 = 221;
			for(int i=0;i<6;i++){
				g2d.fillRect(box2,221,36,36);
				g2d.fillRect(box2,293,36,36);
				g2d.fillRect(box2,365,36,36);
				box2+=72;
			}

			g2d.setPaint(new Color(181,254,180));
			g2d.fillRect(75,255,112,75);
			g2d.fillRect(614,255,105,75);


			if (show){
				g2d.setStroke(new BasicStroke(4));
				g2d.setPaint(Color.YELLOW);
				g2d.fillOval(396,288,12,12);
				g2d.setPaint(Color.BLACK);
				g2d.drawOval(396,288,12,12);
			}

		}


		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(4));
		g2d.draw(poly);

		g2d.setPaint(Color.RED);
		g2d.fillRect(x,y,20,20);
		g2d.setPaint(Color.BLACK);
		g2d.drawRect(x,y,20,20);

		g2d.setStroke(new BasicStroke(3));


		for(Ball b:ballList)
		{

			g2d.setPaint(Color.BLUE);
			g2d.fillOval(b.getX(),b.getY(),b.getSize(),b.getSize());
			g2d.setColor(Color.BLACK);
			g2d.drawOval(b.getX(),b.getY(),b.getSize(),b.getSize());

		}

		if (!gameOn){
			g2d.setColor(Color.WHITE);
			g2d.fillRect(5,45,790,450);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("ARIAL",Font.BOLD,50));
			g2d.drawString("Game Over",255,255);
		}

	}
	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				//MATH HAPPENS HERE!!!!
				if (right && poly.contains(new Rectangle(x+7,y,15,15)))
					x++;
				if (left && poly.contains(new Rectangle(x-5,y,15,15)))
					x--;
				if (up && poly.contains(new Rectangle(x,y-5,15,15)))
					y--;
				if (down && poly.contains(new Rectangle(x,y+7,15,15)))
					y++;

				for(Ball b:ballList)
				{
					if (level == 1)
						b.moveX();
					else
						b.moveY();

					Ellipse2D.Double circle=new Ellipse2D.Double(b.getX(),b.getY(),b.getSize(),b.getSize());
					if(circle.intersects(new Rectangle(x,y,22,20)) && hit)
					{
						deaths++;
						x=100;
						y=300;
						show = true;
					}
				}

				if (level==2){
					Ellipse2D.Double thing2=new Ellipse2D.Double(thing.getX(),thing.getY(),thing.getSize(),thing.getSize());
					if(thing2.intersects(new Rectangle(x,y,20,10)))
						show = false;
				}



				if(level==1)
				{
					Polygon p = new Polygon();
					p.addPoint(615,185);
					p.addPoint(723,185);
					p.addPoint(732,400);
					p.addPoint(615,400);
					if (p.contains(new Rectangle(x,y,15,15))){
						level2();
						level++;
					}
				}
				else
				{
					Polygon p = new Polygon();
					p.addPoint(615,255);
					p.addPoint(720,255);
					p.addPoint(720,330);
					p.addPoint(615,330);
					if (!show && p.contains(new Rectangle(x,y,15,15)))
						gameOn=false;

				}

				try
				{
					t.sleep(5);
				}
				catch(InterruptedException e)
				{

				}
				repaint();
			}
			else
			{


			}
		}
	}

	public void keyPressed(KeyEvent ke)
	{
		System.out.println(ke.getKeyCode());
		if(ke.getKeyCode()==39) // right
			right = true;
		if(ke.getKeyCode()==37) // left
			left = true;
		if(ke.getKeyCode()==38) // up
			up = true;
		if(ke.getKeyCode()==40) //down
			down = true;
		if(ke.getKeyCode()==50){
			level2();
			level = 2;
		}
		if (ke.getKeyCode()==72)
			hit = false;
		if (ke.getKeyCode()==68)
			hit = true;


	}
	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode()==39) // right
			right = false;
		if(ke.getKeyCode()==37) // left
			left = false;
		if(ke.getKeyCode()==38) // up
			up = false;
		if(ke.getKeyCode()==40) //down
			down = false;
	}
	public void keyTyped(KeyEvent ke)
	{
	}
	public static void main(String args[])
	{
		WorldsHardestGame app=new WorldsHardestGame();
	}
}