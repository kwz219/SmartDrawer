
package View.DrawerPanel;

import java.awt.BasicStroke;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import Model.Stroke;
import Model.Triangle;
import View.CommandField;
import View.StateLabel;
import commandAnalyse.CommandAnalyst;
import commandAnalyse.CommandExucuteInterface;
import Logic.CommandExecuteInterfaceImplement;

import Logic.HandWrittingRecognizer;
import Logic.ListTraverseHelper;
import Logic.PointRecorder;
import Logic.PointsFittingHelper;
import Logic.PointsFittingHelper.Graphicstype;
import Logic.PointsFittingHelper.Pointtype;
/**
 * 
 * @author zwk
 * 底层核心画布类，继承了JPanel进行图形绘制并实现了鼠标监听
 */
public class DrawerPanel extends JPanel implements MouseMotionListener,MouseListener{
	    
	    private enum Drawerstate{
	    		Blank,Drawing,EndDrawing,Ajusting,Commanding,EndCommanding
	    }
	    private Drawerstate Drawerstatus;
	    //singleton of DrawerPanel
	    private static DrawerPanel Mydrawer=new DrawerPanel();
	    //Drawer's memeber value
        private Color Brushcolor;//笔刷颜色
        public Color Backgroundcolor;//背景色
        public int Brushsize;//笔刷大小
        
        private int Commandsize;
        private int Isdrawing;//judge if a drawing is over
	    public BufferedImage img;//整个画面的img，用于ClickGraph方法
	    public BufferedImage img2;//整个画面的img，用于getIMG方法
	    
	    private ArrayList<Dimension> pointList=new ArrayList<Dimension>();//when a drawing begins,use this to record all points
	    public ArrayList<Stroke> nofitpointList=new ArrayList<Stroke>();//record all points which is not amended
	    public int pointLptr;
	    private PointRecorder pointRecorder=new PointRecorder();//when a drawing begins,use this to record several points of a drawing
	    public PointRecorder pointRecorderBuff=new PointRecorder();
	    public PointRecorder commandRecorder=new PointRecorder();
	    public HashMap<Point,PointIndex> PointMap=new HashMap<Point,PointIndex>();
	    private ArrayList<ListIndex> Lilist=new ArrayList<ListIndex>();
	    private ArrayList<PointIndex> currentPIs;
	    private Dimension AjustingDimension;
	    private Dimension BeforeDimension;
	    public ArrayList<Line> lineList=new ArrayList<Line>();//maintain a list of lines
	    public ArrayList<Point> mpointList=new ArrayList<Point>();//maintain a list of  datastrcuture Model.Points
	    public ArrayList<Triangle> triangleList=new ArrayList<Triangle>();//maintain a list of Triangles
	    public ArrayList<Circle> circleList=new ArrayList<Circle>();//maintain a list of Circles
	    public ArrayList<String> orderList=new ArrayList<String>();//存储至今为止输入过的命令
	    
	    int x=0;
	    private CommandExucuteInterface cei= new CommandExecuteInterfaceImplement();
	    private HandWrittingRecognizer Recognizer=HandWrittingRecognizer.getRecognizer();
	    private Stroke currentStroke=new Stroke();
	//singleton schema ,so private,init member value
	private DrawerPanel(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get the size of the screen
		screenSize.setSize(screenSize.getWidth(),screenSize.getHeight()-15);
		addMouseMotionListener(this);
		addMouseListener(this);
		Brushcolor=new Color(255,255,255,255);
		Backgroundcolor=new Color(23,49,45);
		setSize(screenSize.width,screenSize.height);
		this.setVisible(true);
		this.setBackground(Backgroundcolor);
		pointLptr=0;
		Brushsize=5;
		Commandsize=3;
		Isdrawing=0;
		img =new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_INT_ARGB);
		img2 =new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_BYTE_GRAY);
		Drawerstatus=Drawerstate.Blank;
		currentStroke=new Stroke();
    }
	
	//get singleton
	public static DrawerPanel getDrawer() {
		return Mydrawer;
	}
	//clear the screen,reset attrs
	public  void Clear() {
		Drawerstatus=Drawerstate.Blank;
		pointList=new ArrayList<Dimension>();
		nofitpointList=new ArrayList<Stroke>();
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
		orderList=new ArrayList<String>();
	}
	//rewrite the paint method
	public void paint(Graphics graphics) {
		super.paint(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        String root=System.getProperty("user.dir");
   
        g2d.setBackground(Color.white);
       
        Color FontColor=new Color(230,120,180,240);
        g2d.setColor(Brushcolor);
        
        g2d.setFont(new Font("PingFang SC",Font.BOLD,24));
       
        g2d.setStroke(new BasicStroke(Brushsize));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
       
        //draw current stroke
        if(currentStroke.getPlist().size()>=2) {
        for(int i=0;i<currentStroke.getPlist().size()-1;i++) {
        	   g2d.drawLine(currentStroke.getPlist().get(i).width, currentStroke.getPlist().get(i).height, currentStroke.getPlist().get(i+1).width, currentStroke.getPlist().get(i+1).height);
        }
        }
        //draw pr strokes
        ArrayList<Stroke> slist=pointRecorder.getStrokelist();
        for(int i=0;i<slist.size();i++) {
        	   for(int j=0;j<slist.get(i).getPlist().size()-1;j++) {
        		   g2d.drawLine(slist.get(i).getPlist().get(j).width,slist.get(i).getPlist().get(j).height, slist.get(i).getPlist().get(j+1).width, slist.get(i).getPlist().get(j+1).height);
        	   }
        }
        
        //draw commands
        for(int i=0;i<orderList.size();i++) {
        	    g2d.drawString(orderList.get(i), 10, (i+1)*33);
        }
       
      
        //draw command if exists on the screen
        for(int i=0;i<commandRecorder.getStrokelist().size();i++) {
        	   Stroke stroke=commandRecorder.getStrokelist().get(i);
        	  for(int j=0;j<stroke.getPlist().size()-1;j++) {
        		  g2d.drawLine(stroke.getPlist().get(j).width, stroke.getPlist().get(j).height, stroke.getPlist().get(j+1).width, stroke.getPlist().get(j+1).height);
        	  }
        }
        
        
        //draw all nofitpoints
        for(int i=0;i<nofitpointList.size();i++) {
        	//g2d.fillOval(nofitpointList.get(i).width, nofitpointList.get(i).height, Brushsize, Brushsize);
            Stroke sk=nofitpointList.get(i);
            for(int j=0;j<sk.getPlist().size()-1;j++) {
            	   g2d.drawLine(sk.getPlist().get(j).width,sk.getPlist().get(j).height, sk.getPlist().get(j+1).width, sk.getPlist().get(j+1).height);
            }
        }
      
        //draw all lines
        for(int i=0;i<lineList.size();i++) {
        	   Line l=lineList.get(i);
        	   g2d.drawLine(l.getStartpoint().getCoordinate().width,l.getStartpoint().getCoordinate().height,l.getEndpoint().getCoordinate().width,l.getEndpoint().getCoordinate().height);
        	  
        }
        
        //draw all single points
        for(int i=0;i<mpointList.size();i++) {
        	   Point p=mpointList.get(i);
        	   g2d.fillOval(p.getCoordinate().width,p.getCoordinate().height,Brushsize,Brushsize);
        	  
        }
        
        //draw all triangles
        for(int i=0;i<triangleList.size();i++) {
        	   Triangle tri=triangleList.get(i);
        	   g2d.drawPolygon(triangleList.get(i).getXpoints(),triangleList.get(i).getYpoints(), 3);
        	  
        }        
        
        //draw all cricles
        for(int i=0;i<circleList.size();i++) {
           g2d.fillOval(circleList.get(i).getCenter().getX()-Brushsize/2,circleList.get(i).getCenter().getY()-Brushsize/2,Brushsize, Brushsize);
        	   g2d.drawOval(circleList.get(i).getUpperleft().width, circleList.get(i).getUpperleft().height,circleList.get(i).getRadius()*2,circleList.get(i).getRadius()*2);
        	  
        }
       
        //draw name of points
        g2d.setColor(FontColor);
        g2d.setFont(new Font("Arial",Font.BOLD,24));
        ArrayList<String> namelist=new ArrayList<String>();
        for(Point p:PointMap.keySet()) {
        	   if(!p.getName().equals("noname")&&(!namelist.contains(p.getName()))) {
        		   g2d.drawString(p.getName(),p.getX()-Brushsize/2, p.getY()-Brushsize/2);
        		   namelist.add(p.getName());
        	   }
        }
        StateLabel.getLabel().changetext(getState().toString());
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
			for(int i=0;i<pointRecorder.getStrokelist().size();i++) {
				nofitpointList.add(pointRecorder.getStrokelist().get(i));
			}
		}
		
		}
		//reset pointRecorder and pointList
		pointRecorderBuff=pointRecorder;
		pointRecorder=new PointRecorder();
		pointList.clear();

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
			currentStroke.addPoint(e.getX(), e.getY());
		    pointRecorder.Add(e.getX(), e.getY());
		}else if(Drawerstatus==Drawerstate.Commanding) {
			currentStroke.addPoint(e.getX(), e.getY());
			commandRecorder.Add(e.getX(), e.getY());
		}
		Isdrawing=0;
		
        this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Drawerstatus==Drawerstate.Drawing) {
			endDrawing();
			this.repaint();
			Drawerstatus=Drawerstate.EndDrawing;
			printstate();
		}else if(Drawerstatus==Drawerstate.EndDrawing) {
			if(ImageDealer.Clickgraph(e.getX(), e.getY())) {
				ArrayList<PointIndex> pilist=ListTraverseHelper.getALLPI(new Dimension(e.getX(),e.getY()), PointMap);
				if(pilist.size()==0) {
					System.out.println("not a point");
					Drawerstatus=Drawerstate.Commanding;
					this.repaint();
					printstate();
				}else {
					BeforeDimension=new Dimension(e.getX(),e.getY());
					Drawerstatus=Drawerstate.Ajusting;
					this.repaint();
					printstate();
					currentPIs=pilist;
				    System.out.println("Point is selected");
				}
			}else {
				Drawerstatus=Drawerstate.Commanding;
				this.repaint();
				printstate();
			}
			
		}else if(Drawerstatus==Drawerstate.Ajusting) {
			if(ImageDealer.Clickgraph(e.getX(), e.getY())) {
				ArrayList<PointIndex> pilist=ListTraverseHelper.getALLPI(new Dimension(e.getX(),e.getY()), PointMap);
				if(pilist.size()==0) {
					Drawerstatus=Drawerstate.Blank;
					printstate();
					System.out.println("end adjusting");
				}else {
					Drawerstatus=Drawerstate.Ajusting;
					printstate();
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
			this.repaint();
			printstate();
		}else if(Drawerstatus==Drawerstate.EndCommanding) {
			if(commandRecorder.getPlist().size()!=0) {
			try {
				byte[] image=ImageDealer.getIMG();
				String recresult=Recognizer.getRecognizeResult(image);
				System.out.println(recresult);
				CommandField.getField().setText(recresult);
				
			} catch (IOException e1) {
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
		System.out.println("MousePressed");
		if(Drawerstatus==Drawerstate.Blank) {
			Drawerstatus=Drawerstate.Drawing;
			printstate();
		}else if(Drawerstatus==Drawerstate.Drawing||Drawerstatus==Drawerstate.Commanding) {
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Drawerstatus==Drawerstate.Drawing) {
			pointRecorder.addStroke(currentStroke);
			currentStroke=new Stroke();
			System.out.println("MouseReleased"+" "+pointRecorder.getStrokelist().size());
			
		}else if(Drawerstatus==Drawerstate.Commanding) {
			commandRecorder.addStroke(currentStroke);
			currentStroke=new Stroke();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(CursorSetter.getCursor("chalk"));
	}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	//获得输入栏中的指令，同时包含一些底层可以进行快捷测试的指令
	public void getCommand(String command) {
		if(command.equals("cl")) {
		cei.changeLine(new Line(new Point(new Dimension(100,100),"A"),new Point(new Dimension(200,200),"B")));
		}else if(command.equals("ct")) {
			//changetriangle测试
			cei.changeTriangle(new Triangle(new Point(new Dimension(250,250),"Q"),new Point(new Dimension(250,350),"W"),new Point(new Dimension(350,250),"E")));
		}else if(command.equals("cc")) {
			cei.changeCircle(new Circle(new Point(new Dimension(100,100),"O"),50));
		}else if(command.equals("清空")) {
			this.Clear();
		}else if(command.equals("print")) {
			//打印目前所有端点
		    ListTraverseHelper.printall(PointMap);
		    this.repaint();
		}else if(command.equals("printl")) {
			for(int i=0;i<lineList.size();i++) {
				System.out.println("Line"+i+" "+lineList.get(i).getStartpoint().getName()+lineList.get(i).getStartpoint().getCoordinate()+lineList.get(i).getEndpoint().getName()+lineList.get(i).getEndpoint().getCoordinate());
			}
		}else if(command.contains("Brushsize")) {
			this.Brushsize=Integer.valueOf(command.substring(9));
		}else if(command.contains("blank")) {
			this.Drawerstatus=Drawerstate.Blank;
		}else if(command.equals("img")) {
			try {
				ImageDealer.getIMG();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("撤销")) {
			if(Drawerstatus==Drawerstate.Drawing) {
				pointList=new ArrayList<Dimension>();
				pointRecorder=new PointRecorder();
			}else if(Drawerstatus==Drawerstate.Commanding) {
				commandRecorder=new PointRecorder();
			}
		}else if(command.equals("killnofit")) {
			nofitpointList=new ArrayList<Stroke>();
			currentStroke=new Stroke();
		}
		this.repaint();
	}
   
    public void printstate() {
    	       System.out.println("Drawerstate: "+Drawerstatus);
    }

    
    public boolean PointExists(String name) {
    	      return ListTraverseHelper.Pointexist(name, PointMap);
    }
    
    
    public Point find_aPointbyname(String name) {
    	      Point p=ListTraverseHelper.Findapoint(name, PointMap);
    	      return p;
    }
    

    public Line findLineends_byname(String sname,String ename) {
    	      return ListTraverseHelper.FindLine_byname(sname, ename, PointMap);
    }
    //将状态调整至blank
    public void ToBlank() {
    	    Drawerstatus=Drawerstate.Blank;
    }
    public Drawerstate getState() {
    	       return Drawerstatus;
    }
    
    //在命令列表中添加该命令
    public void addCommand(String command) {
    	     orderList.add(command);
    }
    
}
