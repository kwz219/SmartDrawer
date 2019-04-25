package View;

import java.awt.BasicStroke;






import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Logic.TimerHelper;
import Model.Circle;
import Model.Line;
import Model.ListIndex;
import Model.Point;
import Model.PointIndex;
import Model.Triangle;
import commandAnalyse.CommandAnalyst;
import commandAnalyse.CommandExucuteInterface;
import Logic.CommandExecuteInterfaceImplement;
import Logic.FontLoader;
import Logic.HandWrittingRecognizer;
import Logic.ListTraverseHelper;
import Logic.PointRecorder;
import Logic.PointsFittingHelper;
import Logic.PointsFittingHelper.Graphicstype;
import Logic.PointsFittingHelper.Pointtype;

public class DrawerPanel extends JPanel implements MouseMotionListener,MouseListener{
	    
	    private enum Drawerstate{
	    		Blank,Drawing,EndDrawing,Ajusting,Commanding,EndCommanding
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
	    private PointRecorder commandRecorder=new PointRecorder();
	    private HashMap<Point,PointIndex> PointMap=new HashMap<Point,PointIndex>();
	    private ArrayList<ListIndex> Lilist=new ArrayList<ListIndex>();
	    private ArrayList<PointIndex> currentPIs;
	    private Dimension AjustingDimension;
	    private Dimension BeforeDimension;
	    private ArrayList<Line> lineList=new ArrayList<Line>();//maintain a list of lines
	    private ArrayList<Point> mpointList=new ArrayList<Point>();//maintain a list of  datastrcuture Model.Points
	    private ArrayList<Triangle> triangleList=new ArrayList<Triangle>();//maintain a list of Triangles
	    private ArrayList<Circle> circleList=new ArrayList<Circle>();//maintain a list of Circles
	    
	    int x=0;
	    private CommandExucuteInterface cei= new CommandExecuteInterfaceImplement();
	    private HandWrittingRecognizer Recognizer=HandWrittingRecognizer.getRecognizer();
	//singleton schema ,so private,init member value
	private DrawerPanel(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get the size of the screen
		screenSize.setSize(screenSize.getWidth(),screenSize.getHeight()-15);
		addMouseMotionListener(this);
		addMouseListener(this);
		Brushcolor=new Color(255,255,255,220);
		Backgroundcolor=new Color(29,39,35);
		setSize(screenSize.width,screenSize.height);
		this.setVisible(true);
		this.setBackground(Backgroundcolor);
		pointLptr=0;
		Brushsize=6;
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
		currentPIs=null;
		commandRecorder=new PointRecorder();
		
	}
	//rewrite the paint method
	public void paint(Graphics graphics) {
		super.paint(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        String root=System.getProperty("user.dir");
       
        //ImageIcon ii=new ImageIcon(root+"/Resource/ChalkBoard.jpg");
        
        //g2d.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
        g2d.setBackground(Color.white);
        g2d.setFont(FontLoader.getFont("chalk"));
        Color FontColor=new Color(190,120,180,230);
        g2d.setColor(Brushcolor);
        g2d.setStroke(new BasicStroke(Brushsize));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
       
        //draw command if exists on the screen
        for(int i=0;i<commandRecorder.Plist.size();i++) {
        	  g2d.fillOval(commandRecorder.Plist.get(i).width, commandRecorder.Plist.get(i).height, Brushsize, Brushsize);
        }
        
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
        	   Line l=lineList.get(i);
        	   g2d.drawLine(l.getStartpoint().getCoordinate().width,l.getStartpoint().getCoordinate().height,l.getEndpoint().getCoordinate().width,l.getEndpoint().getCoordinate().height);
        	   /*if(l.getStartpoint().getName().equals("noname")) {
        		   
        	   }else {
        		   g2d.setColor(FontColor);
        		   g2d.drawString(l.getStartpoint().getName(), l.getStartpoint().getX(), l.getStartpoint().getY());
        		   g2d.drawString(l.getEndpoint().getName(), l.getEndpoint().getX(), l.getEndpoint().getY());
        		   g2d.setColor(Brushcolor);
        	   }*/
        }
        
        //draw all single points
        for(int i=0;i<mpointList.size();i++) {
        	   Point p=mpointList.get(i);
        	   g2d.fillOval(p.getCoordinate().width,p.getCoordinate().height,Brushsize,Brushsize);
        	   /*if(!p.getName().equals("noname")) {
        		   g2d.setColor(FontColor);
        	       g2d.drawString(p.getName(),p.getX(),p.getY());
        	       g2d.setColor(Brushcolor);
        	   }*/
        }
        
        //draw all triangles
        for(int i=0;i<triangleList.size();i++) {
        	   Triangle tri=triangleList.get(i);
        	   g2d.drawPolygon(triangleList.get(i).getXpoints(),triangleList.get(i).getYpoints(), 3);
        	   /*g2d.setColor(FontColor);
        	   g2d.drawString(tri.getVertex1().getName(),tri.getVertex1().getX(),tri.getVertex1().getY());
        	   g2d.drawString(tri.getVertex2().getName(),tri.getVertex2().getX(),tri.getVertex2().getY());
        	   
        	   g2d.drawString(tri.getVertex3().getName(),tri.getVertex3().getX(),tri.getVertex3().getY());
        	   g2d.setColor(Brushcolor);*/
        }        
        
        //draw all cricles
        for(int i=0;i<circleList.size();i++) {
           g2d.fillOval(circleList.get(i).getCenter().getX()-Brushsize/2,circleList.get(i).getCenter().getY()-Brushsize/2,Brushsize, Brushsize);
        	   g2d.drawOval(circleList.get(i).getUpperleft().width, circleList.get(i).getUpperleft().height,circleList.get(i).getRadius()*2,circleList.get(i).getRadius()*2);
        	   /*g2d.setColor(FontColor);
        	   g2d.drawString(circleList.get(i).getCenter().getName(),circleList.get(i).getCenter().getX(),circleList.get(i).getCenter().getY());
        	   g2d.setColor(Brushcolor);*/
        }
        /*int []x= {100,100,200};
        int []y= {100,200,100};
        g2d.drawPolygon(x, y, 3);*/
        
        //draw name of points
        g2d.setColor(FontColor);
        for(Point p:PointMap.keySet()) {
        	   if(!p.getName().equals("noname")) {
        		   g2d.drawString(p.getName(),p.getX()-Brushsize/2, p.getY()-Brushsize/2);
        	   }
        }
        g2d.dispose();
        
        
	}
	
	//when finish a drawing,call this method
	public void endDrawing() {
		int iffit=0;
		ArrayList<Dimension> tmplist=pointRecorder.getPlist();
		
		if(tmplist.size()>0) {
		if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Line) {
            System.out.println("a line ");
            Point lineend1=new Point(tmplist.get(0),Pointtype.Lineend);
            Point lineend2=new Point(tmplist.get(tmplist.size()-1),Pointtype.Lineend);
            Line newLine=new Line(lineend1,lineend2);
		   lineList.add(newLine);
		   //newLine.print();
		   PointIndex pi1=new PointIndex(1,Pointtype.Lineend,"Unnamedline");
		   pi1.setListIndex(lineList.size()-1);
		   PointIndex pi2=new PointIndex(2,Pointtype.Lineend,"Unnamedline");
		   pi2.setListIndex(lineList.size()-1);
		   PointMap.put(lineend1,pi1);
		   PointMap.put(lineend2,pi2);
		   iffit=1;
		}
		else if(PointsFittingHelper.PointsFit(tmplist)==Graphicstype.Point) {
			System.out.println("a point ");
			Point p=new Point(tmplist.get(tmplist.size()/2));
			p.setType(Pointtype.Singlepoint);
			mpointList.add(p);
			PointIndex poi=new PointIndex(0,Pointtype.Singlepoint,"Unnamedpoint");
			poi.setListIndex(mpointList.size()-1);
			PointMap.put(p, poi);
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
	public byte[] getIMG() throws IOException {
		Graphics2D img2d=img2.createGraphics();
		this.paint(img2d);
		
		img2d.dispose();
	    Long time=System.currentTimeMillis();
	    String timename=String.valueOf(time);
		
		int x=commandRecorder.getLeftmost_point().width-Brushsize*10;
		int y=commandRecorder.getLowest_point().height-Brushsize*10;
		int w=commandRecorder.getRightmost_point().width-x+Brushsize*20;
		int h=commandRecorder.getHighest_point().height-y+Brushsize*20;
		System.out.println("x:"+x+" y:"+y+" w:"+w+" h"+h+" leftp:"+commandRecorder.getLeftmost_point()+" rightp"+commandRecorder.getRightmost_point()+" lowp"+commandRecorder.getLowest_point()+" highp"+commandRecorder.getHighest_point());
	    BufferedImage partimg=img2.getSubimage(x, y, w, h);
	    int width=partimg.getWidth();
	    int height=partimg.getHeight();
	    BufferedImage biimg=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
	    for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = partimg.getRGB(i, j);
			biimg.setRGB(i, j, rgb);
		    }
		}
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(biimg, "jpg",out);
		
		return out.toByteArray();


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
			for(int i=0;i<currentPIs.size();i++) {
				PointIndex currentPi=currentPIs.get(i);
				if(currentPi.Type==Pointtype.Lineend) { 
				lineList.get(currentPi.getListIndex()).getPointbyindex(currentPi.Innerindex).setCoordinate(new Dimension(e.getX(),e.getY()));
			     }else if(currentPi.Type==Pointtype.Triangleend) {
				triangleList.get(currentPi.getListIndex()).getPoint_byindex(currentPi.Innerindex).setCoordinate(new Dimension(e.getX(),e.getY()));
			    }else if(currentPi.Type==Pointtype.Circlecenter) {
				circleList.get(currentPi.getListIndex()).moveCenter(new Dimension(e.getX(),e.getY()));
			    }
			}
			AjustingDimension=new Dimension(e.getX(),e.getY());
			
		}else if(Drawerstatus==Drawerstate.Drawing) {
			pointList.add(new Dimension(e.getX(),e.getY()));
			
		    pointRecorder.Add(e.getX(), e.getY());
		}else if(Drawerstatus==Drawerstate.Commanding) {
			commandRecorder.Add(e.getX(), e.getY());
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
				
				ArrayList<PointIndex> pilist=ListTraverseHelper.getALLPI(new Dimension(e.getX(),e.getY()), PointMap);
				
				if(pilist.size()==0) {
					
					System.out.println("not a point");
					Drawerstatus=Drawerstate.Commanding;
					printstate();
				}else {
					BeforeDimension=new Dimension(e.getX(),e.getY());
					Drawerstatus=Drawerstate.Ajusting;
					printstate();
					//System.out.println(pi.Listindex);
					
					currentPIs=pilist;
					
				    System.out.println("Point is selected");
				}
			}else {
				Drawerstatus=Drawerstate.Commanding;
				printstate();
			}
			
		}else if(Drawerstatus==Drawerstate.Ajusting) {
			if(Clickgraph(e.getX(), e.getY())) {
				ArrayList<PointIndex> pilist=ListTraverseHelper.getALLPI(new Dimension(e.getX(),e.getY()), PointMap);
				if(pilist.size()==0) {
					Drawerstatus=Drawerstate.Blank;
					printstate();
					System.out.println("end adjusting");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					printstate();
					//System.out.println(pi.Listindex);
					BeforeDimension=new Dimension(e.getX(),e.getY());
					currentPIs=pilist;
				}
			}else {
				ListTraverseHelper.UpdateCoordinate(BeforeDimension, AjustingDimension, PointMap);
				BeforeDimension=new Dimension(-1,-1);
				AjustingDimension=new Dimension(-1,-1);
				pointRecorderBuff=new PointRecorder();
				Drawerstatus=Drawerstate.Blank;
				printstate();
			}
		}else if(Drawerstatus==Drawerstate.Commanding) {
			Drawerstatus=Drawerstate.EndCommanding;
			printstate();
		}else if(Drawerstatus==Drawerstate.EndCommanding) {
			if(commandRecorder.getPlist().size()!=0) {
			try {
				byte[] image=this.getIMG();
				String recresult=Recognizer.getRecognizeResult(image);
				System.out.println(recresult);
				CommandField.getField().setText(recresult);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			commandRecorder=new PointRecorder();
			this.repaint();
			Drawerstatus=Drawerstate.Blank;
			printstate();
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
		}else if(command.equals("print")) {
		    ListTraverseHelper.printall(PointMap);
		    this.repaint();
		}else if(command.equals("printl")) {
			for(int i=0;i<lineList.size();i++) {
				System.out.println("Line"+i+" "+lineList.get(i).getStartpoint().getName()+lineList.get(i).getStartpoint().getCoordinate()+lineList.get(i).getEndpoint().getName()+lineList.get(i).getEndpoint().getCoordinate());
			}
		}else if(command.contains("Brushsize")) {
			this.Brushsize=Integer.valueOf(command.substring(9));
		}else if(command.equals("img")) {
			try {
				this.getIMG();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.repaint();
	}
	
	
	public void addTriangle(Triangle triangle) {
		triangle.printPoints();
		Point v1=this.fixPoints_byname(triangle.getVertex1().getName());
		Point v2=this.fixPoints_byname(triangle.getVertex2().getName());
		Point v3=this.fixPoints_byname(triangle.getVertex3().getName());
		if(v1!=null) {
			
			triangle.getVertex1().setCoordinate(v1.getCoordinate());
			triangle.getVertex1().setType(Pointtype.Triangleend);
		}
		if(v2!=null) {
			
			triangle.getVertex2().setCoordinate(v2.getCoordinate());
			triangle.getVertex2().setType(Pointtype.Triangleend);
		}
		if(v3!=null) {
			
			triangle.getVertex3().setCoordinate(v3.getCoordinate());
			triangle.getVertex3().setType(Pointtype.Triangleend);
		}
		triangleList.add(triangle);
		PointIndex pi1=new PointIndex(1,Pointtype.Triangleend,triangle.getTriname());
		pi1.setListIndex(triangleList.size()-1);
		PointIndex pi2=new PointIndex(2,Pointtype.Triangleend,triangle.getTriname());
		pi2.setListIndex(triangleList.size()-1);
		PointIndex pi3=new PointIndex(3,Pointtype.Triangleend,triangle.getTriname());
		pi3.setListIndex(triangleList.size()-1);
		PointMap.put(triangle.getVertex1(),pi1 );
		PointMap.put(triangle.getVertex2(),pi2 );
		PointMap.put(triangle.getVertex3(),pi3 );
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		this.repaint();
	}
	
	public void addCircle(Circle circle) {
		Point center=this.fixPoints_byname(circle.getCenter().getName());
		if(center!=null) {
			center.setType(Pointtype.Circlecenter);
			circle.setCenter(center);
		}
		
		circleList.add(circle);
		PointIndex pio=new PointIndex(0,Pointtype.Circlecenter,circle.getCenter().getName());
		pio.setListIndex(circleList.size()-1);
		PointMap.put(circle.getCenter(),pio);
		
		nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		this.repaint();
	}

	public void addPoint(Point A) {
		int index=mpointList.indexOf(A);
		if(index==-1) {
		     mpointList.add(A);
		     A.setType(Pointtype.Singlepoint);
		     ListTraverseHelper.delete_byCorType(PointMap,Pointtype.Singlepoint,A.getCoordinate());
		     PointIndex pi=new PointIndex(0,Pointtype.Singlepoint,A.getName());
		     pi.setListIndex(mpointList.size()-1);
		     PointMap.put(A, pi);
		     nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		}else {
			mpointList.get(index).setName(A.getName());
			PointIndex pi= new PointIndex(0,Pointtype.Singlepoint,A.getName());
			pi.setListIndex(index);
			PointMap.put(A, pi);
		}
		this.repaint();
	}
	
	public void addLine(Line l) {
		Point ps=new Point(l.getStartpoint().getCoordinate(),Pointtype.Lineend);
		Point pe=new Point(l.getEndpoint().getCoordinate(),Pointtype.Lineend);
		
		
		Line prel=new Line(ps,pe);
		int index=lineList.indexOf(prel);
		Point snps=this.fixPoints_byname(l.getStartpoint().getName());
		Point snpe=this.fixPoints_byname(l.getEndpoint().getName());
		ps.setName(l.getStartpoint().getName());
		pe.setName(l.getEndpoint().getName());
		if(index!=-1) {
			ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Lineend,ps.getCoordinate());
			ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Lineend,pe.getCoordinate());
			
			lineList.get(index).getStartpoint().setName(l.getStartpoint().getName());
			lineList.get(index).getEndpoint().setName(l.getEndpoint().getName());
			if(snps!=null) {
				lineList.get(index).getStartpoint().setCoordinate(snps.getCoordinate());
				ps.setCoordinate(snps.getCoordinate());
				
			}
			if(snpe!=null) {
				lineList.get(index).getEndpoint().setCoordinate(snpe.getCoordinate());
				pe.setCoordinate(snpe.getCoordinate());
			}
			    PointIndex pi1=new PointIndex(1,Pointtype.Lineend,l.getLinename());
			    pi1.setListIndex(index);
			    PointIndex pi2=new PointIndex(2,Pointtype.Lineend,l.getLinename());
			    pi2.setListIndex(index);
				PointMap.put(pe, pi2);
				PointMap.put(ps, pi1);
				
			
			
			
		}else {
		    if(snps!=null) {
		    	   
		    		   snps.setType(Pointtype.Lineend);
			    	   l.setPointbyindex(1, snps);
		    	  
		    	  
		    }
		    if(snpe!=null) {
		    	   snpe.setType(Pointtype.Lineend);
		    	   l.setPointbyindex(2, snpe);
		    }
		    lineList.add(l);
		    
		    nofitpointList.removeAll(nofitpointList.subList(pointLptr, nofitpointList.size()));
		    PointIndex pi1=new PointIndex(1,Pointtype.Lineend,l.getLinename());
		    pi1.setListIndex(lineList.size()-1);
		    PointIndex pi2=new PointIndex(1,Pointtype.Lineend,l.getLinename());
		    pi2.setListIndex(lineList.size()-1);
		    PointMap.put(ps, pi1);
		    PointMap.put(pe, pi2);
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
    	   ListTraverseHelper.delete_byCorType(PointMap,Pointtype.Singlepoint,mpointList.get(index).getCoordinate());
    	   mpointList.remove(index);
    }
    
    public void delline_byindex(int index) {
    	  ListTraverseHelper.delete_byCorType(PointMap,Pointtype.Lineend,lineList.get(index).getStartpoint().getCoordinate());
    	  ListTraverseHelper.delete_byCorType(PointMap,Pointtype.Lineend,lineList.get(index).getEndpoint().getCoordinate());
    	  lineList.remove(index);
    }
    
    public void deltriangle_byindex(int index) {
    	  
    	  ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Triangleend, triangleList.get(index).getVertex1().getCoordinate());
    	  ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Triangleend, triangleList.get(index).getVertex2().getCoordinate());
    	  ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Triangleend, triangleList.get(index).getVertex3().getCoordinate());
    	  triangleList.remove(index);
    }
    
    public void delcircle_byindex(int index) {
    	  
    	  ListTraverseHelper.delete_byCorType(PointMap, Pointtype.Circlecenter, circleList.get(index).getCenter().getCoordinate());
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
    
    public ArrayList<Line> getLines_containspname(String pname){
    	      ArrayList<Line> linel=new ArrayList<Line>();
    	      for(int i=0;i<lineList.size();i++) {
    	    	      if(lineList.get(i).getStartpoint().getName().equals(pname)||lineList.get(i).getEndpoint().getName().equals(pname)) {
    	    	    	     linel.add(lineList.get(i));
    	    	    	     System.out.println("line "+lineList.get(i).getStartpoint().getName()+lineList.get(i).getEndpoint().getName()+" added");
    	    	      }
    	      }
    	      return linel;
    }
    public boolean PointExists(String name) {
    	      return ListTraverseHelper.Pointexist(name, PointMap);
    }
    
    public void changeCircleRadius(int index,int radius) {
    	      circleList.get(index).setRadius(radius);
    }
    
    public Point find_aPointbyname(String name) {
    	      Point p=ListTraverseHelper.Findapoint(name, PointMap);
    	      return p;
    }
    
    public Point fixPoints_byname(String name) {
    	 Point p=ListTraverseHelper.Findapoint(name, PointMap);
    	 if(p!=null) {
    		 return p;
    	 }
    	   return null;
    }
    
    public void changeAllpointsofline_byname(String name,Point p) {
    	     for(int i=0;i<lineList.size();i++) {
    	    	     Line l=lineList.get(i);
    	    	     Point ps=l.getStartpoint();
    	    	     Point pe=l.getEndpoint();
    	    	     if(ps.getName().equals(name)) {
    	    	    	    lineList.get(i).getStartpoint().setCoordinate(p.getCoordinate());
    	    	     }else if(pe.getName().equals(name)) {
    	    	    	    lineList.get(i).getEndpoint().setCoordinate(p.getCoordinate());
    	    	     }
    	     }
    }
    public void changeAllpointsoftriangle_byname(String name,Point p) {
	     for(int i=0;i<triangleList.size();i++) {
	    	     Triangle tri=triangleList.get(i);
	    	     Point v1=tri.getVertex1();
	    	     Point v2=tri.getVertex2();
	    	     Point v3=tri.getVertex3();
	    	     if(v1.getName().equals(name)) {
	    	    	    triangleList.get(i).getVertex1().setCoordinate(p.getCoordinate());
	    	     }else if(v2.getName().equals(name)) {
	    	    	 triangleList.get(i).getVertex2().setCoordinate(p.getCoordinate());
	    	     }else if(v3.getName().equals(name)) {
	    	    	 triangleList.get(i).getVertex3().setCoordinate(p.getCoordinate());
	    	     }
	     }
    }
    public void changeAllpointsofcircle_byname(String name,Point p) {
    	    for(int i=0;i<circleList.size();i++) {
    	    	   Circle cir=circleList.get(i);
    	    	   Point center=cir.getCenter();
    	    	   if(center.getName().equals(name)) {
    	    		   circleList.get(i).moveCenter(p.getCoordinate());
    	    	   }
    	    }
    }
    
    public void  updatePointmap(String name,Point newp) {
    	     Iterator it=PointMap.keySet().iterator();
    	     ArrayList<Point> plist=new ArrayList<Point>();
    	     ArrayList<PointIndex> pilist=new ArrayList<PointIndex>();
    	     while(it.hasNext()) {
    	    	       Point p=(Point)it.next();
    	    	       if(p.getName().equals(name)) {
    	         		   System.out.println("update p "+p.getName());
    	         		   Point np=p;
    	         		   np.setCoordinate(newp.getCoordinate());
    	         		   PointIndex pi=PointMap.get(p);
    	         		   plist.add(p);
    	         		   pilist.add(pi);
    	         		   it.remove();
    	         	   }
    	     }
    	     for(int i=0;i<plist.size();i++) {
    	    	     PointMap.put(plist.get(i),pilist.get(i));
    	     }
    	    this.repaint();
    	     
    }
    public ArrayList<ListIndex> getallIndexofapoint(Dimension d){
    	       ArrayList<ListIndex> lilist=new ArrayList<ListIndex>();
    	       for(int i=0;i<lineList.size();i++) {
    	    	       if(lineList.get(i).getStartpoint().getCoordinate().equals(d)||lineList.get(i).getEndpoint().getCoordinate().equals(d)) {
    	    	    	      lilist.add(new ListIndex(Pointtype.Lineend,i));
    	    	       }
    	       }
    	       for(int i=0;i<triangleList.size();i++) {
    	    	      if(triangleList.get(i).getVertex1().getCoordinate().equals(d)||triangleList.get(i).getVertex2().getCoordinate().equals(d)||triangleList.get(i).getVertex3().getCoordinate().equals(d)) {
    	    	    	      lilist.add(new ListIndex(Pointtype.Triangleend,i));
    	    	      }
    	       }
    	       for(int i=0;i<circleList.size();i++) {
    	    	      if(circleList.get(i).getCenter().getCoordinate().equals(d)) {
    	    	    	    lilist.add(new ListIndex(Pointtype.Circlecenter,i));
    	    	      }
    	       }
    	       return lilist;
    }
    public Line findLineends_byname(String sname,String ename) {
    	      return ListTraverseHelper.FindLine_byname(sname, ename, PointMap);
    }
    public void ToBlank() {
    	    Drawerstatus=Drawerstate.Blank;
    }
}
