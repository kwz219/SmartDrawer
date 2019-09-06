package View.DrawerPanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * @author zwk
 * 对画面缓存img进行的相关处理
 */
public class ImageDealer {
	//对当前所画部分进行截图，并转换为字节流
	public static byte[] getIMG() throws IOException {
		Graphics2D img2d=DrawerPanel.getDrawer().img2.createGraphics();
		DrawerPanel.getDrawer().paint(img2d);
		
		img2d.dispose();
	    Long time=System.currentTimeMillis();
	    String timename=String.valueOf(time);
		
		int x=DrawerPanel.getDrawer().commandRecorder.getLeftmost_point().width-DrawerPanel.getDrawer().Brushsize*10;
		int y=DrawerPanel.getDrawer().commandRecorder.getLowest_point().height-DrawerPanel.getDrawer().Brushsize*10;
		int w=DrawerPanel.getDrawer().commandRecorder.getRightmost_point().width-x+DrawerPanel.getDrawer().Brushsize*20;
		int h=DrawerPanel.getDrawer().commandRecorder.getHighest_point().height-y+DrawerPanel.getDrawer().Brushsize*20;
		System.out.println("x:"+x+" y:"+y+" w:"+w+" h"+h+" leftp:"+DrawerPanel.getDrawer().commandRecorder.getLeftmost_point()+" rightp"+DrawerPanel.getDrawer().commandRecorder.getRightmost_point()+" lowp"+DrawerPanel.getDrawer().commandRecorder.getLowest_point()+" highp"+DrawerPanel.getDrawer().commandRecorder.getHighest_point());
	    if(y<0)y=0;
	    if(x<0)x=0;
		BufferedImage partimg=DrawerPanel.getDrawer().img2.getSubimage(x, y, w, h);
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
	//judge if click the graphs,即判断点击在了图形上还是点击在了背景上
		public static boolean Clickgraph(int x,int y) {
			boolean result=false;
			Graphics2D img2d=DrawerPanel.getDrawer().img.createGraphics();
			int BackgroundRGB=DrawerPanel.getDrawer().Backgroundcolor.getRGB();
			DrawerPanel.getDrawer().paint(img2d);
			if(DrawerPanel.getDrawer().img.getRGB(x, y)!=BackgroundRGB) {
				result=true;
			}
			img2d.dispose();
			return result;
		}
}
