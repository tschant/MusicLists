import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintStream;


public class MusicList {
	//used for user to input location that text file will be created and folders that will be listed
	private static String fileName;
	//used to create a listing of that specific folder
	private static File dir;
	private static File[] dirSub;
	//will be used to transfer the folder names over to text file
	private static File[] music;
	private static File[][] musicSub;
	//will be used to write the folder names to text file
	private static String[] musicList;
	private static String[][] musicListSub;
	private static File file;
	public static Scanner kb = new Scanner(System.in);
	//END DECLARTION OF VARIABLES
	
	
	public static void main(String[] args) {
		System.out.println("Enter the directory of your music folders:");
		System.out.println();
		//note the forward slashes
		System.out.println("For Mac Itunes: /Users/'username'/Music/iTunes/");
		System.out.println("For Windows XP: C:/Documents and Settings/'username'/My Documents/My Music/iTunes/");
		System.out.println("For Windows Vista & 7 Itunes: C:/Users/'username'/Music/iTunes");
		//user types in the directory of music
		fileName = kb.next();
		//get the names of the folders and set the arrays
		getFiles(fileName);
		//turn the file name into a string that can be printed to text file
		createStringName();
		removeMP3();
		//confirm folders with the console
		printToConsole();
		//create a text file for the user
		printToTextFile();
		printMainTextFile();
	}
	
	
	
	public static void removeMP3(){
		//goes through every folder and removes the files
		for(int i=0;i<music.length;i++){
			for(int t=0;t<musicList[i].length();t++){
				if(musicList[i].charAt(t)=='.'){
					musicList[i]="";
				}
			}
		}

		//goes through the files and finds any file with a '.' in it and removes it
		for(int i=0;i<musicSub.length;i++){
			if(musicSub[i]!=null){
				for(int x=0;x<musicSub[i].length;x++)
					for(int t=0;t<musicListSub[i][x].length();t++){
						if(musicListSub[i][x].charAt(t)=='.'){
							musicListSub[i][x]="";
						}
					}
				}
		}
	}
	
	public static void getFiles(String fileName){
		
		dir = new File(fileName);
		//creates an array of the folders within the user defined folder
		System.out.println(dir.listFiles());
		music = dir.listFiles();

		//allocates an array with length similar to File (will be used for text file output)
		musicList = new String[music.length];
		
		dirSub = new File[music.length];
		for(int i=0; i<music.length; i++){
			dirSub[i] = music[i];
		}
		musicSub = new File[dirSub.length][];
		for(int i=0;i<music.length;i++){
			musicSub[i] = dirSub[i].listFiles();
		}
		//creates the string for the sub files
			musicListSub = new String[musicSub.length][];
		for(int i=0;i<musicSub.length;i++){
			if( musicSub[i]!=null){
				musicListSub[i] = new String[musicSub[i].length];
			}
		}
	}
	
	public static void printToConsole(){
		//outputs to console the folders
		for(int i=0;i<music.length;i++){
			System.out.println(music[i]);
			if( musicSub[i]!=null){ //(musicSub[i].length>=1) &&
				for(int t=0;t<musicSub[i].length;t++){
					System.out.println(musicSub[i][t]);
				}
			}
		}
	}
	
	public static void createStringName(){
		for(int i=0;i<music.length;i++){
			//int length = fileName.length();
			musicList[i] = music[i].getName();
			//musicList[i] = musicList[i].substring(0,length-1);
		}
		for(int i=0;i<musicSub.length;i++){
			if(musicSub[i]!=null){
				for(int t=0;t<musicSub[i].length;t++){
					musicListSub[i][t] = musicSub[i][t].getName();
				}
			}
		}
	}

	public static void printToTextFile(){
		//LEARN WHAT THIS DOES!!!!!!!!
		try {
			file = new File(fileName, "MusicList.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			//write to text file how many folders there are
			out.write("Number of Folders: " +music.length+"\r\n\r\n");
			for (int i = 0; i < music.length; i++){
				//write the name of the folders (not the path)
				if(!musicList[i].equals("")){
					out.write(musicList[i]+"\r\n");
				}
				//saftey measure to make sure a null file/folder did not get through
				if(musicSub[i]!=null){
					for(int t=0;t<musicSub[i].length;t++){
						//since the files were replace with "" they must be removed from final print
						if(!musicListSub[i][t].equals(""))
					//write the name of the sub folders (not the path)
							out.write("	"+musicListSub[i][t]+"\r\n");
					}
					out.write("\r\n");
				}
			}
			out.close();
			System.out.println();
			//output to console that program finished
			System.out.println("File created successfully.");
			//output the location of text file
			System.out.println("File created in: "+fileName);
			//if there is an error creating a file this will show up
			} catch (FileNotFoundException e) {
				 System.out.println("Error #1: "+e.getMessage());
				 try {
						BufferedWriter out = new BufferedWriter(new FileWriter("MusicList.txt"));
						//write to text file how many folders there are
						out.write(music.length+"\r\n\r\n");
						for (int i = 0; i < music.length; i++){
							//write the name of the folders (not the path)
							out.write(musicList[i]+"\r\n");
								if(musicSub[i]!=null){
									for(int t=0;t<musicSub[i].length;t++){
										if(!musicListSub[i][t].equals(""))
									//write the name of the folders (not the path)
											out.write("	"+musicListSub[i][t]);
									}
									out.write("\r\n");
								}
							}
						out.close();
						System.out.println();
						//output to console that program finished
						System.out.println("File created successfully in default location.");
						//if there is an error creating a file this will show up
						} catch (FileNotFoundException f) {
							 System.out.println("Error #1: "+f.getMessage());
						} catch (IOException f) {
							System.out.println("Error #2: "+f.getMessage());
						}
			} catch (IOException e) {
				System.out.println("Error #2: "+e.getMessage());
			}
	}

	public static void printMainTextFile(){
		try {
			file = new File(fileName, "MusicListMain.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			//write to text file how many folders there are
			out.write("Number of Folders: " +music.length+"\r\n\r\n");
			for (int i = 0; i < music.length; i++){
				//write the name of the folders (not the path)
				if(!musicList[i].equals("")){
					out.write(musicList[i]+"\r\n");
				}
			}
			out.close();
			System.out.println();
			//output to console that program finished
			System.out.println("File created successfully.");
			//output the location of text file
			System.out.println("File created in: "+fileName);
			//if there is an error creating a file this will show up
			} catch (FileNotFoundException e){
				 System.out.println("Error #1: "+e.getMessage());
				 try {
						BufferedWriter out = new BufferedWriter(new FileWriter("MusicListMain.txt"));
						//write to text file how many folders there are
						out.write(music.length+"\r\n\r\n");
						for (int i = 0; i < music.length; i++){
							if(!musicList[i].equals("")){
								//write the name of the folders (not the path)
								out.write(musicList[i]+"\r\n");
							}
						}
						out.close();
						System.out.println();
						//output to console that program finished
						System.out.println("File created successfully in default location.");
						//if there is an error creating a file this will show up
						} catch (FileNotFoundException f) {
							 System.out.println("Error #1: "+f.getMessage());
						} catch (IOException f) {
							System.out.println("Error #2: "+f.getMessage());
						}
			} catch (IOException e) {
				System.out.println("Error #2: "+e.getMessage());
			}
	}
	
}

