import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class ListCompare {
	private static String filename;
	private static String fileName1;
	private static String fileName2;
	private static Scanner kb = new Scanner(System.in);
	private static String[] list1;
	private static String[] list2;
	private static String[] ComparedList;
	
	
	public static void main(String[] args) {
		System.out.println("Enter the directory of the lists:");
		fileName1 = kb.next();
		fileName2 = kb.next();
		createArrays(fileName1, fileName2);
		System.out.println("Where do you want to create the text file?");
		filename = kb.next();
		compareLists();
		Arrays.sort(ComparedList);
		
		for(int x=0;x<ComparedList.length;x++){
			if(ComparedList[x]!=" " && ComparedList[x]!=null)
				System.out.println(ComparedList[x]);
		}
		createTxtFile();
			
	}
		
	public static void createTxtFile(){
		/*
		try {
			File file = new File(filename, "Compared.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			//write to text file how many folders there are
			out.write("Number of different files: "+ ComparedList.length +"\r\n\r\n");
			for (int i = 0; i < ComparedList.length; i++){
				//write the name of the folders (not the path)
				out.write(ComparedList[i]+"\r\n");
			}
			
			out.close();
			System.out.println();
			//output to console that program finished
			System.out.println("File created successfully.");
			//output the location of text file
			System.out.println("File created in: "+filename);
			//if there is an error creating a file this will show up
		} catch (Exception e){
			System.out.println("Program encountered a problem" + e);
		}
		*/
		try {
			File file = new File(filename, "ComparedSplit.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			//write to text file how many folders there are
			//out.write("Number of different files: "+ ComparedList.length);
			
			out.write("PART 1: \r\n");
			for (int i = 0; i < list1.length; i++){
				//write the name of the folders (not the path)
				if(list1[i]!=" " && list1!=null)
					out.write(list1[i]+"\r\n");
			}
			out.write("\r\n\r\n PART 2: \r\n");
			for (int i = 0; i < list2.length; i++){
				//write the name of the folders (not the path)
				if(list2[i]!=" " && list2!=null)
					out.write(list2[i]+"\r\n");
			}
			out.close();
			System.out.println();
			//output to console that program finished
			System.out.println("File created successfully.");
			//output the location of text file
			System.out.println("File created in: "+filename);
			//if there is an error creating a file this will show up
		} catch (Exception e){
			System.out.println("Program encountered a problem" + e);
		}
	}
	
	public static void compareLists(){
		String[] tempComparedList = new String[list1.length+list2.length];
		int i=0;
		list1[0]=" ";
		list2[0]=" ";
		
		
		for(int x=0; x<list1.length;x++){
			for(int y=0;y<list2.length;y++){
				if(list1[x].equalsIgnoreCase(list2[y])){
					list1[x]=" ";
					list2[y]=" ";
				}
			}
		}
		for(int x=0; x<list1.length;x++){
				if(list1[x]==" ")
					continue;
				else{
					tempComparedList[i]=list1[x];
					i++;
				}
		}
		for(int y=0;y<list2.length;y++){
			if(list2[y]==" ")
				continue;
			else{
				tempComparedList[i]=list2[y];
				i++;
			}
		}
		
		int Counter=0;
		for(int x=0;x<tempComparedList.length;x++)
			if(tempComparedList[x]==null || tempComparedList[x]==" ")
				Counter++;
		
		ComparedList = new String[list1.length+list2.length-Counter];
		i=0;
		for(int x=0;x<tempComparedList.length;x++){
			if(tempComparedList[x]!=" " && tempComparedList[x]!=null){
				ComparedList[i]=tempComparedList[x];
				i++;
			}
		}
	}
	
	public static void createArrays(String file1, String file2){
		try{
			Scanner inputStream1 = new Scanner(new File(file1));
			String line = inputStream1.nextLine();
			while(inputStream1.hasNextLine()){
				line += "/" + inputStream1.nextLine();
			}
			list1 = line.split("/");
		}
		catch(Exception e){
			System.out.println("Program encountered a problem: "+e);
		}
		try{
			Scanner inputStream2 = new Scanner(new File(file2));
			String line = inputStream2.nextLine();
			while(inputStream2.hasNextLine()){
				line += "/" + inputStream2.nextLine();
			}
			list2 = line.split("/");
		}
		catch(Exception e){
			System.out.println("Program encountered a problem: "+e);
		}
	}
	
	
	
}
