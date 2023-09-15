package check;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Reader {
	public static double calculateCosSimilarity(Map<String, Integer> vec1, Map<String, Integer> vec2) {
	    double dotProduct = 0.0;
	    double norm1 = 0.0;
	    double norm2 = 0.0;

	    for (Map.Entry<String, Integer> entry : vec1.entrySet()) {
	    	String word = entry.getKey();
	        int count = entry.getValue();
	        dotProduct += count * vec2.getOrDefault(word, 0);
	        norm1 += Math.pow(count, 2);
	    }

	    for (Map.Entry<String, Integer> entry : vec2.entrySet()) {
	        int count = entry.getValue();
	        norm2 += Math.pow(count, 2);
	    }
	    
	    System.out.println(dotProduct);
	    return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
	}

	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		String filePath1=sc.next();
		String filePath2=sc.next();
		String filePath3=sc.next();
//		String filePath1 = "D:/1.txt";
//		String filePath2 = "D:/2.txt";
        StringBuilder content1 = new StringBuilder();
        StringBuilder content2 = new StringBuilder();
        String line;
        BufferedReader reader = null;
		
		reader = new BufferedReader(new FileReader(filePath1));
        while ((line = reader.readLine()) != null) {
            content1.append(line);
            content1.append(System.lineSeparator());
        }
        reader.close();
        reader = new BufferedReader(new FileReader(filePath2));
        while ((line = reader.readLine()) != null) {
            content2.append(line);
            content2.append(System.lineSeparator());
        }
        reader.close();
        String paper1= new String(content1);
        String paper2= new String(content2);
        System.out.println(paper1+"xx");
		System.out.println(paper2+"xx");
		List<Term> segList1 = HanLP.segment(paper1);
		List<Term> segList2 = HanLP.segment(paper2);
		System.out.println("1"+segList1+"xx");
		System.out.println("2"+segList2+"xx");
		//String[] words1 = paper1.split("，");
		ArrayList<String> list1=new ArrayList<>();
		Map<String, Integer> wordCountMap1 = new HashMap<>();
		for(int i=0;i<segList1.size();i++) {
			list1.add(segList1.get(i).word);
		}
		for (String word : list1) {
		    wordCountMap1.put(word, wordCountMap1.getOrDefault(word, 0) + 1);
		}

		ArrayList<String> list2=new ArrayList<>();
		Map<String, Integer> wordCountMap2 = new HashMap<>();
		for(int i=0;i<segList2.size();i++) {
			list2.add(segList2.get(i).word);
		}
		for (String word : list2) {
		    wordCountMap2.put(word, wordCountMap2.getOrDefault(word, 0) + 1);
		}
		
		
		double rate=calculateCosSimilarity(wordCountMap1, wordCountMap2);
		System.out.println(new DecimalFormat("0.00").format(rate));
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath3));
		out.write(new DecimalFormat("0.00").format(rate));
		out.close();
	}

}
