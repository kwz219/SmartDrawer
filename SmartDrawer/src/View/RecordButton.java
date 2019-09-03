package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.baidu.aip.util.Util;

import Logic.SoundRecorder;
import Logic.VoiceRecognizer;

public class RecordButton extends JButton{
             public static RecordButton RB=new RecordButton();
             private int rbstate=0;
             private String voiceContent="";
             private RecordButton() {
            	         voiceContent="";
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
										RB.setIcon(rbstate);
										voiceContent=SoundRecorder.getRS().startRecognize();
										System.out.println(voiceContent);
										CommandField.getField().setText(voiceContent);
										rbstate=0;
										RB.setIcon(rbstate);
										}
										//System.out.println(voiceContent);							
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
             public String getContent() {
            	    return RB.voiceContent;
             }
             
}
