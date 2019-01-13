package View;

import java.awt.BasicStroke;


import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Logic.TimerHelper;
import Model.Circle;
import Model.Line;
import Model.Point;
import Model.PointIndex;
import Model.Triangle;
import Logic.ListTraverseHelper;
import Logic.PointRecorder;
import Logic.PointsFittingHelper;
import Logic.PointsFittingHelper.Graphicstype;
import Logic.PointsFittingHelper.Pointtype;

public class DrawerPanel extends JPanel implements MouseMotionListener,MouseListener{
	    
	    private enum Drawerstate{
	    		Blank,Drawing,EndDrawing,Ajusting
	    }
	    private Drawerstate Drawerstatus;
	    //singleton of DrawerPanel
	    private static DrawerPanel Mydrawer=new DrawerPanel();
	    //Drawer's memeber value
        private Color Brushcolor;
        private Color Backgroundcolor;
        private int Brushsize;
        private int Isdrawing;//judge if a drawing is over
	    private BufferedImage img;
	    private Long Releasetime;
	    private ArrayList<Dimension> pointList=new ArrayList<Dimension>();//when a drawing begins,use this to record all points
	    private ArrayList<Dimension> nofitpointList=new ArrayList<Dimension>();//record all points which is not amended
	    private int pointLptr;
	    private PointRecorder pointRecorder=new PointRecorder();//when a drawing begins,use this to record several points of a drawing
	    
	    private HashMap<Point,PointIndex> PointMap=new HashMap<Point,PointIndex>();
	    private PointIndex currentPi;
	    private ArrayList<Line> lineList=new ArrayList<Line>();//maintain a list of lines
	    private ArrayList<Point> mpointList=new ArrayList<Point>();//maintain a list of  datastrcuture Model.Points
	    private ArrayList<Triangle> triangleList=new ArrayList<Triangle>();//maintain a list of Triangles
	    private ArrayList<Circle> circleList=new ArrayList<Circle>();//maintain a list of Circles
	    
	    int x=0;
	
	//singleton schema ,so private,init member value
	private DrawerPanel(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get the size of the screen
		addMouseMotionListener(this);
		addMouseListener(this);
		Brushcolor=Color.BLACK;
		Backgroundcolor=Color.WHITE;
		setSize(screenSize.width,screenSize.height);
		this.setVisible(true);
		this.setBackground(Backgroundcolor);
		pointLptr=0;
		Brushsize=10;
		Isdrawing=0;
		img =new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_INT_ARGB);
		Drawerstatus=Drawerstate.Blank;
    }
	
	//get singleton
	public static DrawerPanel getDrawer() {
		return Mydrawer;
	}
	
	//rewrite the paint method
	public void paint(Graphics graphics) {
		super.paint(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setBackground(Color.WHITE);
        g2d.setFont(new Font("Arial",Font.BOLD,32));
        g2d.setColor(Brushcolor);
        g2d.setStroke(new BasicStroke(Brushsize));
       
        //draw all nofitpoints
        for(int i=0;i<nofitpointList.size();i++) {
        	g2d.fillOval(nofitpointList.get(i).width, nofitpointList.get(i).height, Brushsize, Brushsize);
        }
        
        //draw all temporary points
        for(int i=0;i<pointList.size();i++){
            g2d.fillOval(pointList.get(i).width, pointList.get(i).height, Brushsize, Brushsize);
        }
        
        //draw all lines
        for(int i=0;i<lineList.size();i++) {
        	   g2d.drawLine(lineList.get(i).getStartpoint().getCoordinate().width,lineList.get(i).getStartpoint().getCoordinate().height,lineList.get(i).getEndpoint().getCoordinate().width,lineList.get(i).getEndpoint().getCoordinate().height);
        }
        
        //draw all single points
        for(int i=0;i<mpointList.size();i++) {
        	   g2d.fillOval(mpointList.get(i).getCoordinate().width, mpointList.get(i).getCoordinate().height,Brushsize,Brushsize);
        }
        
        
       
        g2d.dispose();
        
        
	}
	
	//when finish a drawing,call this method
	public void endDrawing() {
		int iffit=0;
		ArrayList<Dimension> tmplist=pointRecorder.getPlist();
		System.out.println(tmplist.size());
		if(tmplist.size()>0) {
		if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Line) {
            System.out.println("a line");
            Point lineend1=new Point(tmplist.get(0),Pointtype.Lineend);
            Point lineend2=new Point(tmplist.get(tmplist.size()-1),Pointtype.Lineend);
            Line newLine=new Line(lineend1,lineend2);
		   lineList.add(newLine);
		   System.out.println("lineend1"+lineend1.getCoordinate());
		   PointMap.put(lineend1,new PointIndex(lineList.indexOf(newLine),1,Pointtype.Lineend));
		   PointMap.put(lineend2,new PointIndex(lineList.indexOf(newLine),2,Pointtype.Lineend));
		   iffit=1;
		}
		else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Point) {
			System.out.println("a point");
			mpointList.add(new Point(tmplist.get(0)));
			iffit=1;
		}else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Triangle) {
			iffit=1;
		}else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Circle) {
			iffit=1;
		}
		if(iffit==0) {
			nofitpointList.addAll(pointList);
		}
		pointLptr=pointList.size();
		}
		//reset pointRecorder and pointList
		pointRecorder=new PointRecorder();
		pointList.clear();
		
		System.out.println("endDrawing");
	}
	
	//get a image of the Jpanel,not yet used
	public void getIMG() throws IOException {
		Graphics2D img2d=img.createGraphics();
		this.paint(img2d);
		
		img2d.dispose();
	   
		File f=new File("/Users/zwk/Documents/pics/1.jpg");
		if( !f.exists() )
		{
			f.createNewFile();
			System.out.println(123);
		}
	    
		ImageIO.write(img, "jpg",f);


	}
	//judge if click the graphs
	public boolean Clickgraph(int x,int y) {
		boolean result=false;
		Graphics2D img2d=img.createGraphics();
		int BackgroundRGB=Backgroundcolor.getRGB();
		this.paint(img2d);
		if(img.getRGB(x, y)!=BackgroundRGB) {
			result=true;
		}
		img2d.dispose();
		return result;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// draw pixel while dragging the mouse
		if(Drawerstatus==Drawerstate.Ajusting) {
			if(currentPi.Type==Pointtype.Lineend) {
				lineList.get(currentPi.Listindex).getPointbyindex(currentPi.Innerindex).setCoordinate(new Dimension(e.getX(),e.getY()));
			}else if(currentPi.Type==Pointtype.Triangleend) {
				
			}else if(currentPi.Type==Pointtype.Circlecenter) {
				
			}
		}else if(Drawerstatus==Drawerstate.Drawing) {
			pointList.add(new Dimension(e.getX(),e.getY()));
			
		    pointRecorder.Add(e.getX(), e.getY());
		}
		Isdrawing=0;
		
        this.repaint();

		
		
		
		//System.out.println(e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
        
		System.out.println("co:"+e.getX()+" "+e.getY());
		
		if(Drawerstatus==Drawerstate.Drawing) {
			endDrawing();
			this.repaint();
			Drawerstatus=Drawerstate.EndDrawing;
			//pointRecorder.Empty();
			//Isdrawing=1;
		}else if(Drawerstatus==Drawerstate.EndDrawing) {
			if(Clickgraph(e.getX(), e.getY())) {
				PointIndex pi=ListTraverseHelper.FindnearestPoint(new Dimension(e.getX(),e.getY()),PointMap);
				if(pi==null) {
					
					System.out.println("not a point");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					System.out.println(pi.Listindex);
					currentPi=pi;
				}
			}else {
				Drawerstatus=Drawerstate.Blank;
			}
			
		}else if(Drawerstatus==Drawerstate.Ajusting) {
			if(Clickgraph(e.getX(), e.getY())) {
				PointIndex pi=ListTraverseHelper.FindnearestPoint(new Dimension(e.getX(),e.getY()),PointMap);
				if(pi==null) {
					Drawerstatus=Drawerstate.Blank;
					System.out.println("end adjusting");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					System.out.println(pi.Listindex);
					currentPi=pi;
				}
			}else {
				Drawerstatus=Drawerstate.Blank;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Drawerstatus==Drawerstate.Blank) {
			Drawerstatus=Drawerstate.Drawing;
		}
		
		//System.out.println(e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Releasetime=System.currentTimeMillis();
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
