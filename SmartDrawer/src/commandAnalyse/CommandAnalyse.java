package commandAnalyse;
//�����Ӧ��ֻ��Ψһʵ��
public class CommandAnalyse {
	//private Command com;
	private String commandtype;
	private String nextexpect="geo";
	private CommandList list=new CommandList();
	public void commandAnalyse(String command) {
		Command com=new Command();
		com.setTpye("�½�");
		int length=command.length();
		nextexpect="geo";
		//ÿ�����ʼ������һ��ͼ�δʣ�����������ֻ��ͼ�δ���Ϊ�½����
		//ÿ��ͼ�δʺ��������һ��λ�ôʻ�����������ͽ�����
		for(int i=0;i<length;i++) {
			String word=command.substring(i, i+1);
			//����������ڱ�ʶ����Ӧ����ʲô��������ȡ�����Ϊgeo�����ȥ������ȡ������ͼ�δʣ������type�����ȥ������ȡ���������ʹ�
			switch (nextexpect){
			case"geo": 
				switch (word) {
				case"��":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+1;
					geo.setType("��");
					geo.setName(command.substring(i, i+1));
					nextexpect="type";
					break;}
				case"ֱ":{
					if(command.substring(i+1,i+2).equals("��")) {
						CommandGeo geo=new CommandGeo ();
						com.addGeo(geo);
						i=i+2;
						geo.setType("ֱ��");
						geo.setName(command.substring(i, i+2));
						i=i+1;
						nextexpect="type";
					}
					break;}
				case"��":{
					if(command.charAt(i+1) >= 'A' && command.charAt(i+1) <= 'Z') {
						CommandGeo geo=new CommandGeo ();
						com.addGeo(geo);
						i=i+1;
						geo.setType("��");
						geo.setName(command.substring(i, i+3));
						i=i+2;
						nextexpect="type";
					}	
					break;}
				case"Բ":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+1;
					geo.setType("Բ");
					geo.setName(command.substring(i, i+1));
					nextexpect="type";
					break;}
				case"��":{
					if(command.length()>i+3) {
						if(command.charAt(i+3) >= 'A' && command.charAt(i+3) <= 'Z') {
							CommandGeo geo=new CommandGeo ();
							com.addGeo(geo);
							i=i+3;
							geo.setType("������");
							geo.setName(command.substring(i, i+3));
							i=i+2;
							nextexpect="type";
						}
					}
					break;}
				case"ƽ":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+5;
					geo.setType("ƽ���ı���");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"��":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+3;
					geo.setType("������");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"��":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+2;
					geo.setType("����");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"��":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+2;
					geo.setType("����");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				}
				break;
			case"type":
				nextexpect="geo";
				if(com.getTpye().equals("�½�")) {
					switch(word) {
					case"��":com.setTpye("����");
					break;
					case"ƽ":
						if(command.contains("ƽ��")) com.setTpye("ƽ��");
						if(command.contains("ƽ��")) com.setTpye("ƽ��");
						break;
					case"��":com.setTpye("λ��");
						break;
					case"��":com.setTpye("����");
						break;
					case"��":com.setTpye("��ֱ");
						break;
					case"��":com.setTpye("����");
						break;
					case"��":
						if(command.contains("��")) {
							com.setTpye("��");
						}
						else if(command.contains("����")) {
							com.setTpye("����");
						}
						else if(command.contains("����")) {
							com.setTpye("����");
						}
						else if(command.contains("ƽ����")) {
							com.setTpye("ƽ����");
						}
						else if(command.contains("��")) {
							com.setTpye("�ض��Ƕ�");
							int num;
							String s;
							s=command.substring(i+1,command.indexOf("��"));
							num=Integer.valueOf(s);						
							com.setNumber(num);
						}
						else if(command.contains("ֱ��������")) {
							com.setTpye("ֱ��������");
							if(command.contains("����ֱ��������")) {
									com.setTpye("����ֱ��������");
								}
						}
						else if(command.contains("�ȱ�������")) {
							com.setTpye("�ȱ�������");
						}
						else if(command.contains("����������")) {
							com.setTpye("����������");
						}
						else if(command.contains("����")) {
							com.setTpye("����");
						}
						else if(command.contains("������")) {
							com.setTpye("������");
						}
						else if(command.contains("����")) {
							com.setTpye("����");
						}
						else if(command.contains("ƽ���ı���")) {
							com.setTpye("ƽ���ı���");
						}
						break;
					}
				}
				else {
				}
			}
		}
		com.print();
		this.list.add(com);
	}
	
	
}
