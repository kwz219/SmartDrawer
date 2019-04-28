package commandAnalyse;
//这个类应该只有唯一实例,需要补充一个单例模式
public class CommandAnalyse {
	//private Command com;
	private String commandtype;
	private String nextexpect="geo";
	CommandList list=CommandList.getInstance();
	public void commandAnalyse(String command) {
		Command com=new Command();
		com.settype("新建");
		int length=command.length();
		nextexpect="geo";
		//每个命令开始必须是一个图形词，如果这个命令只有图形词则为新建命令。
		//每个图形词后面必须是一个位置词或者这条命令就结束了
		for(int i=0;i<length;i++) {
			String word=command.substring(i, i+1);
			//这个变量用于标识下面应该用什么方法来读取，如果为geo则接下去期望读取到的是图形词，如果是type则接下去期望读取到的是类型词
			switch (nextexpect){
			case"geo": 
				switch (word) {
				case"删":{
					com.settype("删除");
					i=i+1;
					nextexpect="geo";
					break;
				}
				case"点":{
					CommandPoint geo=new CommandPoint (command.substring(i+1, i+2));
					com.addGeo(geo);
					i=i+1;
					geo.setType("点");
					nextexpect="type";
					break;}
				case"直":{
					if(command.substring(i+1,i+2).equals("线")) {
						CommandLine geo=new CommandLine (command.substring(i+2, i+4));
						com.addGeo(geo);
						geo.setType("直线");
						i=i+3;
						nextexpect="type";
					}
					break;}
				case"角":{
					if(command.charAt(i+1) >= 'A' && command.charAt(i+1) <= 'Z') {
						CommandGeo geo=new CommandGeo ();
						com.addGeo(geo);
						i=i+1;
						geo.setType("角");
						geo.setName(command.substring(i, i+3));
						i=i+2;
						nextexpect="type";
					}	
					break;}
				case"圆":{
					CommandCircle geo=new CommandCircle(command.substring(i+1, i+2));
					com.addGeo(geo);
					i=i+1;
					geo.setType("圆");
					nextexpect="type";
					break;}
				case"三":{
					if(command.length()>i+3) {
						if(command.charAt(i+3) >= 'A' && command.charAt(i+3) <= 'Z') {
							CommandTriangle geo=new CommandTriangle (command.substring(i+3, i+6));
							com.addGeo(geo);
							geo.setType("三角形");
							i=i+5;
							nextexpect="type";
						}
					}
					break;}
				case"平":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+5;
					geo.setType("平行四边形");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"正":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+3;
					geo.setType("正方形");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"矩":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+2;
					geo.setType("矩形");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				case"菱":{
					CommandGeo geo=new CommandGeo ();
					com.addGeo(geo);
					i=i+2;
					geo.setType("菱形");
					geo.setName(command.substring(i, i+4));
					i=i+3;
					nextexpect="type";
					break;}
				}
				break;
			case"type":
				nextexpect="geo";
				if(com.gettype().equals("新建")) {
					switch(word) {
					case"交":com.settype("交于");
					break;
					case"平":
						if(command.contains("平行")) com.settype("平行");
						if(command.contains("平分")) com.settype("平分");
						break;
					case"在":com.settype("位于");
						break;
					case"等":com.settype("等于");
						break;
					case"垂":com.settype("垂直");
						break;
					case"切":com.settype("切于");
						break;
					case"是":
						if(command.contains("高")) {
							com.settype("高");
						}
						else if(command.contains("中线")) {
							com.settype("中线");
						}
						else if(command.contains("中线")) {
							com.settype("中线");
						}
						else if(command.contains("平分线")) {
							com.settype("平分线");
						}
						else if(command.contains("度")) {
							com.settype("特定角度");
							int num;
							String s;
							s=command.substring(i+1,command.indexOf("度"));
							num=Integer.valueOf(s);						
							com.setNumber(num);
						}
						else if(command.contains("直角三角形")) {
							com.settype("直角三角形");
							if(command.contains("等腰直角三角形")) {
									com.settype("等腰直角三角形");
								}
						}
						else if(command.contains("等边三角形")) {
							com.settype("等边三角形");
						}
						else if(command.contains("等腰三角形")) {
							com.settype("等腰三角形");
						}
						else if(command.contains("菱形")) {
							com.settype("菱形");
						}
						else if(command.contains("正方形")) {
							com.settype("正方形");
						}
						else if(command.contains("矩形")) {
							com.settype("矩形");
						}
						else if(command.contains("平行四边形")) {
							com.settype("平行四边形");
						}
						break;
					}
				}
				else {
				}
			}
		}
		this.list.add(com);
		com.execute();
	}
}