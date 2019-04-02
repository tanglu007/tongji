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

		System.out.println("-------��Ƶ--------");
		System.out.println("1.���ʵĲ��Ҽ���״ͼ����ʾ");
		System.out.println("2.��Ƶ�ʵ���ʾ");
		System.out.println("3.����ļ����");
		System.out.println("4.ͳ���ı��������ַ���");
		Scanner input = new Scanner(System.in);
		int i=input.nextInt();
		Count t=new Count();
		switch(i){
		case 1:
			System.out.printf("��������Ҫ���ҵĵ��ʣ�����';'����");
			String str= input.next();
			Count tj=new Count(map,t.oneword(map, str)); 
			tj.setVisible(true);
			break;					
		case 2:		
			Scanner sc=new Scanner(System.in);
		    System.out.println("������Ҫ�鿴�ĸ�Ƶ��Ƶ�ĸ���:"); 
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
