import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFrame;


public class Count  extends JFrame{
	public Map<String,Integer> map;
	public  String[] wordCount ;
	private static final long serialVersionUID = 1L;
	public String []  oneword(Map<String,Integer> map,String s){		
		long a =System.currentTimeMillis();
		String[] s1=s.split(";"); 
		for(int i=0;i<s1.length;i++){   
			for(Entry<String,Integer> entry:map.entrySet()){
				if(s1[i].equals(entry.getKey())){
					map.put(entry.getKey(),entry.getValue());
					System.out.println("单词"+entry.getKey()+"出现了"+entry.getValue()+"次");
                    break;
				}
			}	
		}
		System.out.println("\r执行耗时:"+(System.currentTimeMillis()-a+"ms"));//输出耗时时间
		return s1;	
	}
		
    //柱状图
	public Count(Map<String,Integer> map,String[] wordCount){
		super();
		
		this.wordCount = wordCount;
		this.map = map;
		setTitle("绘制柱形图");
		setBounds(wordCount.length, 200, 450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Count(){
		super();
	}
	@Override
	public void paint(Graphics g){
		int Width = getWidth();
		int Height = getHeight();
		int leftMargin = 50;
		int topMargin = 50;
		Graphics2D g2 = (Graphics2D) g;
		int ruler = Height-topMargin;
		int rulerStep = ruler/20;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, Width, Height);
		g2.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<rulerStep;i++){
			g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
		}
		g2.setColor(Color.BLUE);
		int m=0;
		for(int i = 0;i<wordCount.length;i++){
			int value = map.get(wordCount[i]);
			int step = (m+1)*40;
			g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);
			g2.drawString(wordCount[i], leftMargin+step*2, Height-value-5);			
			m++;
		}
	}
	
	//高频词统计
	public void gaopin(Map<String,Integer> map,int k){
		long a =System.currentTimeMillis();
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue() - o1.getValue(); 
            }  
        }); 
     //输出前k个数
      for(int i = 0; i<k; i++){  
            System.out.println("单词"+list.get(i).getKey()+ "出现了 " +list.get(i).getValue()+"次");  
        }    
      System.out.println("\r执行耗时:"+(System.currentTimeMillis()-a+"ms"));//输出耗时时间
    }

	//存入文件
	public void out(Map<String,Integer> map ){
		File f=new File("result.txt");			
		try{
			if(!f.exists()){
				f.createNewFile();	
			}
			FileWriter ff=new FileWriter(f.getCanonicalFile());
			for(Entry<String,Integer> entry:map.entrySet()){
				ff.write(entry.getKey()+"/"+entry.getValue()+"    "+"\n");
			}
			ff.close();						
			System.out.println("统计输出完成！");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}	

	//统计该文本行数及字符数
	public int[] allCount(String fileName) throws IOException {
		long a =System.currentTimeMillis();
		String line = fileName;
		File file = new File(line);
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
		BufferedReader br = new BufferedReader(isr);
		int charNum = 0;
		int wordsNum = 0;
		int lineNum = 0;
		while (br.read() != -1) {
			String s = br.readLine();
			charNum += s.length();
			wordsNum += s.split(" ").length;
			lineNum++;
		}
		isr.close();
		int[] linenum = { charNum, wordsNum, lineNum, };
		for (int i = 0; i < linenum.length; i++) {
			System.out.println(linenum[i]);
		}
		System.out.println("字符数:"+charNum+"\n单词数:"+wordsNum+"\n行数:"+lineNum);
		System.out.println("\r执行耗时:"+(System.currentTimeMillis()-a+"ms"));
		return linenum;
	}	
}
