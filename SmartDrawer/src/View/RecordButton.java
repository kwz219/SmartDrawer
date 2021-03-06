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
/**
 * 
 * @author zwk
 * 录音键，按下开始录音，检测到无声音输入后自动停止并调用语音识别接口，相关类：SoundRecorder，VoiceRecognizer
 */
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
            	         String urlnormal=root+"/Resource/RecordButton.jpg";
            	         String urlpress=root+"/Resource/Recording.png";
            	         this.setIcon(new ImageIcon(urlnormal));
            	         this.addActionListener(new ActionListener() {
            	        	     	@Override
								public void actionPerformed(ActionEvent e) {
									//start sound record
									if(rbstate==0) {
										System.out.println("recording starts...");
										
										RB.setIcon(new ImageIcon(urlpress));
										voiceContent=SoundRecorder.getRS().startRecognize();
										System.out.println(voiceContent);
										CommandField.getField().setText(voiceContent);
										rbstate=0;
										RB.setIcon(new ImageIcon(urlnormal));
										}
										//System.out.println(voiceContent);							
								}

            	         }
);
             }
            
             public static RecordButton getRecordButton() {
            	        return RB;
             }
             public String getContent() {
            	    return RB.voiceContent;
             }
             
}
