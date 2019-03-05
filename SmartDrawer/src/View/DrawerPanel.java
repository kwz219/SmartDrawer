package View;

import java.awt.BasicStroke;


import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import commandAnalyse.CommandAnalyst;
import commandAnalyse.CommandExucuteInterface;
import Logic.CommandExecuteInterfaceImplement;
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
	    private BufferedImage img2;
	    private Long Releasetime;
	    private ArrayList<Dimension> pointList=new ArrayList<Dimension>();//when a drawing begins,use this to record all points
	    private ArrayList<Dimension> nofitpointList=new ArrayList<Dimension>();//record all points which is not amended
	    private int pointLptr;
	    private PointRecorder pointRecorder=new PointRecorder();//when a drawing begins,use this to record several points of a drawing
	    private PointRecorder pointRecorderBuff=new PointRecorder();
	    private HashMap<Point,PointIndex> PointMap=new HashMap<Point,PointIndex>();
	    private PointIndex currentPi;
	    private ArrayList<Line> lineList=new ArrayList<Line>();//maintain a list of lines
	    private ArrayList<Point> mpointList=new ArrayList<Point>();//maintain a list of  datastrcuture Model.Points
	    private ArrayList<Triangle> triangleList=new ArrayList<Triangle>();//maintain a list of Triangles
	    private ArrayList<Circle> circleList=new ArrayList<Circle>();//maintain a list of Circles
	    
	    int x=0;
	    private CommandExucuteInterface cei= new CommandExecuteInterfaceImplement();
	//singleton schema ,so private,init member value
	private DrawerPanel(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get the size of the screen
		screenSize.setSize(screenSize.getWidth(),screenSize.getHeight()-15);
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
		img2 =new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_BYTE_GRAY);
		Drawerstatus=Drawerstate.Blank;
    }
	
	//get singleton
	public static DrawerPanel getDrawer() {
		return Mydrawer;
	}
	//clear the screen
	public  void Clear() {
		Drawerstatus=Drawerstate.Blank;
		pointList=new ArrayList<Dimension>();
		nofitpointList=new ArrayList<Dimension>();
		pointRecorder=new PointRecorder();
		pointRecorderBuff=new PointRecorder();
		PointMap=new HashMap<Point,PointIndex>();
		lineList=new ArrayList<Line>();
		mpointList=new ArrayList<Point>();
		triangleList=new ArrayList<Triangle>();
		circleList=new ArrayList<Circle>();
		pointLptr=0;
		currentPi=null;
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
        
        //draw all triangles
        for(int i=0;i<triangleList.size();i++) {
        	   g2d.drawPolygon(triangleList.get(i).getXpoints(),triangleList.get(i).getYpoints(), 3);
        }        
        
        //draw all cricles
        for(int i=0;i<circleList.size();i++) {
           g2d.fillOval(circleList.get(i).getCenter().getX()-Brushsize/2,circleList.get(i).getCenter().getY()-Brushsize/2,Brushsize, Brushsize);
        	   g2d.drawOval(circleList.get(i).getUpperleft().width, circleList.get(i).getUpperleft().height,circleList.get(i).getRadius()*2,circleList.get(i).getRadius()*2);
        	   
        }
        /*int []x= {100,100,200};
        int []y= {100,200,100};
        g2d.drawPolygon(x, y, 3);*/
        g2d.dispose();
        
        
	}
	
	//when finish a drawing,call this method
	public void endDrawing() {
		int iffit=0;
		ArrayList<Dimension> tmplist=pointRecorder.getPlist();
		
		if(tmplist.size()>0) {
		if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Line) {
            //System.out.println("a line ");
            Point lineend1=new Point(tmplist.get(0),Pointtype.Lineend);
            Point lineend2=new Point(tmplist.get(tmplist.size()-1),Pointtype.Lineend);
            Line newLine=new Line(lineend1,lineend2);
		   lineList.add(newLine);
		   //newLine.print();
		   PointMap.put(lineend1,new PointIndex(lineList.indexOf(newLine),1,Pointtype.Lineend));
		   PointMap.put(lineend2,new PointIndex(lineList.indexOf(newLine),2,Pointtype.Lineend));
		   iffit=1;
		}
		else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Point) {
			//System.out.println("a point ");
			mpointList.add(new Point(tmplist.get(0)));
			iffit=1;
		}else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Triangle) {
			iffit=1;
		}else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Circle) {
			iffit=1;
		}
		if(iffit==0) {
			pointLptr=nofitpointList.size();
			nofitpointList.addAll(pointList);
		}
		
		}
		//reset pointRecorder and pointList
		pointRecorderBuff=pointRecorder;
		pointRecorder=new PointRecorder();
		pointList.clear();
		
		//System.out.println("endDrawing");
	}
	
	//get a image of the Jpanel,not yet used
	public void getIMG() throws IOException {
		Graphics2D img2d=img2.createGraphics();
		this.paint(img2d);
		
		img2d.dispose();
	   
		File f=new File("/Users/zwk/Documents/pics/1.jpg");
		if( !f.exists() )
		{
			f.createNewFile();
			System.out.println(123);
		}
		int x=pointRecorder.getLeftmost_point().width-2;
		int y=pointRecorder.getLowest_point().height-2;
		int w=pointRecorder.getRightmost_point().width-x+Brushsize;
		int h=pointRecorder.getHighest_point().height-y+Brushsize;
		System.out.println("x:"+x+" y:"+y+" w:"+w+" h"+h+" leftp:"+pointRecorder.getLeftmost_point()+" rightp"+pointRecorder.getRightmost_point()+" lowp"+pointRecorder.getLowest_point()+" highp"+pointRecorder.getHighest_point());
	    BufferedImage partimg=img2.getSubimage(x, y, w, h);
		ImageIO.write(partimg, "jpg",f);
		


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
				triangleList.get(currentPi.Listindex).getPoint_byindex(currentPi.Innerindex).setCoordinate(new Dimension(e.getX(),e.getY()));
			}else if(currentPi.Type==Pointtype.Circlecenter) {
				circleList.get(currentPi.Listindex).moveCenter(new Dimension(e.getX(),e.getY()));
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
		
        /*
		System.out.println("co:"+e.getX()+" "+e.getY());
		try {
			this.getIMG();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		if(Drawerstatus==Drawerstate.Drawing) {
			endDrawing();
			this.repaint();
			Drawerstatus=Drawerstate.EndDrawing;
			printstate();
			//pointRecorder.Empty();
			//Isdrawing=1;
		}else if(Drawerstatus==Drawerstate.EndDrawing) {
			if(Clickgraph(e.getX(), e.getY())) {
				
				PointIndex pi=ListTraverseHelper.FindnearestPoint(new Dimension(e.getX(),e.getY()),PointMap);
				if(pi==null) {
					
					System.out.println("not a point");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					printstate();
					//System.out.println(pi.Listindex);
					currentPi=pi;
				    System.out.println("A "+pi.Type.toString()+" is selected");
				}
			}else {
				Drawerstatus=Drawerstate.Blank;
				printstate();
			}
			
		}else if(Drawerstatus==Drawerstate.Ajusting) {
			if(Clickgraph(e.getX(), e.getY())) {
				PointIndex pi=ListTraverseHelper.FindnearestPoint(new Dimension(e.getX(),e.getY()),PointMap);
				if(pi==null) {
					Drawerstatus=Drawerstate.Blank;
					printstate();
					System.out.println("end adjusting");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					printstate();
					//System.out.println(pi.Listindex);
					currentPi=pi;
				}
			}else {
				pointRecorderBuff=new PointRecorder();
				Drawerstatus=Drawerstate.Blank;
				printstate();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Drawerstatus==Drawerstate.Blank) {
			Drawerstatus=Drawerstate.Drawing;
			printstate();
		}
		
		//System.out.println(e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	public void getCommand(String command) {
		//System.out.println(command);
		if(command.equals("cl")) {
		cei.changeLine(new Line(new Point(new Dimension(100,100),"A"),new Point(new Dimension(200,200),"B")));
		}else if(command.equals("ct")) {
			cei.changeTriangle(new Triangle(new Point(new Dimension(250,250),"Q"),new Point(new Dimension(250,350),"W"),new Point(new Dimension(350,250),"E")));
		}else if(command.equals("cc")) {
			cei.changeCircle(new Circle(new Point(new Dimension(100,100),"O"),50));
		}else if(command.equals("clear")) {
			this.Clear();
		}
		this.repaint();
	}
	
	
	public void addTriangle(Triangle triangle) {
		triangleList.add(triangle);
		PointMap.put(triangle.getVertex1(),new PointIndex(triangleList.indexOf(triangle),1,Pointtype.Triangleend) );
		PointMap.put(triangle.getVertex2(),new PointIndex(triangleList.indexOf(triangle),2,Pointtype.Triangleend) );
		PointMap.put(triangle.getVertex3(),new PointIndex(triangleList.indexOf(triangle),3,Pointtype.Triangleend) );
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		this.repaint();
	}
	
	public void addCircle(Circle circle) {
		circleList.add(circle);
		PointMap.put(circle.getCenter(),new PointIndex(circleList.indexOf(circle),0,Pointtype.Circlecenter));
		
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		this.repaint();
	}

	public void addPoint(Point A) {
		mpointList.add(A);
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		this.repaint();
	}
	
	public void addLine(Line l) {
		Point ps=new Point(l.getStartpoint().getCoordinate(),Pointtype.Lineend);
		Point pe=new Point(l.getEndpoint().getCoordinate(),Pointtype.Lineend);
		
		
		Line prel=new Line(ps,pe);
		int index=lineList.indexOf(prel);
		if(index!=-1) {
			lineList.get(index).getStartpoint().setName(l.getStartpoint().getName());
			lineList.get(index).getEndpoint().setName(l.getEndpoint().getName());
			PointMap.remove(ps);
			PointMap.remove(pe);
			PointMap.put(ps, new PointIndex(index,1,Pointtype.Lineend));
			PointMap.put(pe, new PointIndex(index,2,Pointtype.Lineend));
		}else {
		
		lineList.add(l);
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		}
		this.repaint();
	}
    public int findPointIndex_byname(String name) {
    	     int index=-1;
    	     for(int i=0;i<mpointList.size();i++) {
    	    	     
    	    	     if(name.equals(mpointList.get(i).getName())) {
    	    	    	    return i;
    	    	     }
    	     }
    	     return index;
    }

    public int findCircleIndex_byname(String name) {
    	     int index=-1;
	     for(int i=0;i<circleList.size();i++) {
	    	     
	    	     if(name.equals(circleList.get(i).getCenter().getName())) {
	    	    	    return i;
	    	     }
	     }
	     return index;
    }
    
    public int findLineIndex_byname(String name) {
    	     int index=-1;
	     for(int i=0;i<lineList.size();i++) {
	    	     Point startpoint=lineList.get(i).getStartpoint();
	    	     Point endpoint=lineList.get(i).getEndpoint();
	    	     String name1=startpoint.getName()+endpoint.getName();
	    	     String name2=endpoint.getName()+startpoint.getName();
	    	     //System.out.println("name "+name+" name1 "+name1+" name2 "+name2);
	    	     if(name.equals(name1)||name.equals(name2)) {
	    	    	 //System.out.println("equals");
	    	    	 return i;
	    	     }
	    	     
	     }
	     return index;
    }
    
    public int findTriangleIndex_byname(String name) {
    	      int index=-1;
    	      for(int i=0;i<triangleList.size();i++) {
    	    	    Point v1=triangleList.get(i).getVertex1();
    	    	    Point v2=triangleList.get(i).getVertex2();
    	    	    Point v3=triangleList.get(i).getVertex3();
    	    	    String name123=v1.getName()+v2.getName()+v3.getName();
    	    	    String name132=v1.getName()+v3.getName()+v2.getName();
    	    	    String name213=v2.getName()+v1.getName()+v3.getName();
    	    	    String name231=v2.getName()+v3.getName()+v1.getName();
    	    	    String name312=v3.getName()+v1.getName()+v3.getName();
    	    	    String name321=v3.getName()+v2.getName()+v1.getName();
    	    	    if(name.equals(name123)||name.equals(name132)||name.equals(name213)||name.equals(name231)||name.equals(name312)||name.equals(name321)) {
    	    	    	   return i;
    	    	    }
    	      }
    	      return index;
    }
    
    public void delpoint_byindex(int index) {
    	   PointMap.remove(mpointList.get(index));
    	   mpointList.remove(index);
    }
    
    public void delline_byindex(int index) {
    	  PointMap.remove(lineList.get(index));
    	  lineList.remove(index);
    }
    
    public void deltriangle_byindex(int index) {
    	  PointMap.remove(triangleList.get(index));
    	  triangleList.remove(index);
    }
    
    public void delcircle_byindex(int index) {
    	  PointMap.remove(circleList.get(index));
    	  circleList.remove(index);
    }
    
    public Point getpoint_byindex(int index) {
    	      return mpointList.get(index);
    }
    
    public Line getline_byindex(int index) {
    	      return lineList.get(index);
    }
    
    public Triangle gettriangle_byindex(int index) {
    	     return triangleList.get(index);
    }
    
    public Circle getcircle_byindex(int index) {
    	     return circleList.get(index);
    }
    
    public ArrayList<Point> getTriPoints_byDrawing(){
    	       return pointRecorderBuff.getTriangleVertexes();
    }
    
    public Circle getCircle_byDrawing() {
    	       return pointRecorderBuff.getSimilarCircle();
    }
    
    public Line getLine_byDrawing() {
    	       return pointRecorderBuff.getSimilarLine();
    }
    
    public Point getPoint_byDrawing() {
    	       return pointRecorderBuff.getSimilarPoint();
    }
    
    public void printstate() {
    	       System.out.println("Drawerstate: "+Drawerstatus);
    }
    
    public ArrayList<Point> getAllpoint_byname(String name){
    	       return ListTraverseHelper.Findallpoints(name, PointMap);
    }
    
    public void changeLineend(Point preend,Point point){
    	      PointIndex Pi=PointMap.get(preend);
    	      lineList.get(Pi.Listindex).setPointbyindex(Pi.Innerindex, point);
    }
    
    public void changeTriangleend(Point preend,Point point) {
    	      PointIndex Pi=PointMap.get(preend);
    	      triangleList.get(Pi.Listindex).setPoint_byindex(Pi.Innerindex, point);
    }
    
    public void changeCirclecenter(Point preend,Point point) {
    	      PointIndex Pi=PointMap.get(preend);
    	      circleList.get(Pi.Listindex).setCenter(point);
    }
    
    public void replaceTriangleVertex(Point p) {
    	      Point preP=ListTraverseHelper.Findapoint_bynametype(p.getName(),PointMap,Pointtype.Triangleend);
    	      PointIndex Pi=PointMap.get(preP);
    	      p.setType(Pointtype.Triangleend);
    	      triangleList.get(Pi.Listindex).setPoint_byindex(Pi.Innerindex,p);
    	      PointMap.remove(preP);
    	      PointMap.put(p, Pi);
    }
    
}
