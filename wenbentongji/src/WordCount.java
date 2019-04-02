import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class WordCount  {
		public static Map<String,Integer> map=new TreeMap<String,Integer>();

		
		public static void main(String[] args) throws Exception{		
		
        new File();
		
		BufferedReader bf=new BufferedReader(new FileReader("E:/word.txt"));
		String s;
		String[] a;
		while((s=bf.readLine())!=null){
			String[] words=s.split("[^a-zA-Z]");
			for(int i=0;i<words.length;i++){
				String key=words[i].toLowerCase();
				if(key.length()>0){
					if(!map.containsKey(key)){
						map.put(key,1);
					}else{
						int value=map.get(key);
						value++;
						map.put(key,value);
					}
				}
			}			
		}

		System.out.println("-------词频--------");
		System.out.println("1.单词的查找及柱状图的显示");
		System.out.println("2.高频词的显示");
		System.out.println("3.输出文件完成");
		System.out.println("4.统计文本行数及字符数");
		Scanner input = new Scanner(System.in);
		int i=input.nextInt();
		Count t=new Count();
		switch(i){
		case 1:
			System.out.printf("请输入你要查找的单词，并用';'隔开");
			String str= input.next();
			Count tj=new Count(map,t.oneword(map, str)); 
			tj.setVisible(true);
			break;					
		case 2:		
			Scanner sc=new Scanner(System.in);
		    System.out.println("请输入要查看的高频词频的个数:"); 
			int k = sc.nextInt();  
			t.gaopin(map, k);	
			break;
		case 3:
			t.out(map);	
			break;
		case 4:
			t.allCount("E:/word.txt");
			break;
		}
	}
}
