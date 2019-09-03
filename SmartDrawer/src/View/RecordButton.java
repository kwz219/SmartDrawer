package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RecordButton extends JButton{
             public static RecordButton RB=new RecordButton();
             private int rbstate=0;
             private RecordButton() {
            	         
            	         this.setPreferredSize(new Dimension(26,26));
            	         this.setBackground(Color.BLACK);
            	         this.setForeground(Color.red);
            	         this.setHorizontalAlignment(0);
            	         String root=System.getProperty("user.dir");
            	         String url=root+"/Resource/RecordButton.png";
            	         this.setIcon(new ImageIcon("/Users/zwk/Downloads/RecordButton.jpg"));
            	         this.addActionListener(new ActionListener() {
            	        	     	@Override
								public void actionPerformed(ActionEvent e) {
									//start sound record
									if(rbstate==0) {
										System.out.println("recording starts...");
										
										rbstate=1;
									}else if(rbstate==1){
										System.out.println("recording ends...");
										rbstate=0;
									}
									RB.setIcon(rbstate);
								}

            	         }
);
             }
             public  void setIcon(int state) {
            	     String root=System.getProperty("user.dir"); 
            	     String rburl=root+"/Resource/RecordButton.jpg";
            	     String ringurl=root+"/Resource/Recording.png";
            	     String[] iconurls= {rburl,ringurl};
            	     RB.setIcon(new ImageIcon(iconurls[state]));
             }
             public static RecordButton getRecordButton() {
            	        return RB;
             }
}
