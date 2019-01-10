package View;

import java.awt.BasicStroke;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
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

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Logic.TimerHelper;
import Model.Line;
import Logic.PointRecorder;

public class DrawerPanel extends JPanel implements MouseMotionListener,MouseListener{
	    
	    //singleton of DrawerPanel
	    private static DrawerPanel Mydrawer=new DrawerPanel();
	    //Drawer's memeber value
        private Color Brushcolor;
        private int Brushsize;
        private int Isdrawing;//judge if a drawing is over
	    private BufferedImage img;
	    private Long Releasetime;
	    private ArrayList<Dimension> pointList=new ArrayList<Dimension>();//when a drawing begins,use this to record all points
	    private int pointLptr;
	    private PointRecorder pointRecorder=new PointRecorder();//when a drawing begins,use this to record several points of a drawing
	    
	    private ArrayList<Line> lineList=new ArrayList<Line>();//maintain a list of lines
	    
	    int x=0;
	
	//singleton schema ,so private,init member value
	private DrawerPanel(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//get the size of the screen
		addMouseMotionListener(this);
		addMouseListener(this);
		setSize(screenSize.width,screenSize.height);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		pointLptr=0;
		Brushcolor=Color.BLACK;
		Brushsize=10;
		Isdrawing=0;
		img =new BufferedImage(screenSize.width,screenSize.height,BufferedImage.TYPE_INT_BGR);
		
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
 
        g2d.setColor(Brushcolor);
        g2d.setStroke(new BasicStroke(Brushsize));
        /*if((System.currentTimeMillis()-Releasetime)>1000) {
        	   endonce();
        }*/
        
        //draw all points without correction
        for(int i=0;i<pointList.size();i++){
            g2d.fillOval(pointList.get(i).width, pointList.get(i).height, Brushsize, Brushsize);
        }
        
        //draw all lines
        for(int i=0;i<lineList.size();i++) {
        	   g2d.drawLine(lineList.get(i).getStartpoint().width,lineList.get(i).getStartpoint().height,lineList.get(i).getEndpoint().width,lineList.get(i).getEndpoint().height);
        }
        
        g2d.dispose();
        
        
	}
	
	//when finish a drawing,call this method
	public void endDrawing() {
		
		//check if the line is vertical or not
		if(Line.Isvertical(pointRecorder.getHighest_point(),pointRecorder.getLowest_point())) {
			lineList.add(new Line(pointRecorder.getHighest_point(),pointRecorder.getLowest_point()));
		}else {
			lineList.add(new Line(pointRecorder.getLeftmost_point(),pointRecorder.getRightmost_point()));
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
	@Override
	public void mouseDragged(MouseEvent e) {
		// draw pixel while dragging the mouse
		Isdrawing=0;
		pointList.add(new Dimension(e.getX(),e.getY()));
		pointLptr++;
	    pointRecorder.Add(e.getX(), e.getY());
        this.repaint();

		
		
		
		//System.out.println(e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x++;
		if(x%3==0) {
			/*try {
				getIMG();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			System.out.println(pointRecorder.getLeftmost_point().getWidth()+" "+pointRecorder.getRightmost_point().getWidth()+" "+pointRecorder.getHighest_point().getHeight()+" "+pointRecorder.getLowest_point().getHeight());
			System.out.print("size"+pointRecorder.Plist.size());
		}
		System.out.println(Isdrawing+" "+lineList.size()+" "+pointList.size()+" "+pointRecorder.Count);
		if(Isdrawing==0) {
			endDrawing();
			this.repaint();
			//pointRecorder.Empty();
			Isdrawing=1;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
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
