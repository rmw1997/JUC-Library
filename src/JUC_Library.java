/*

A java program to keep a record of books in JUC library. 
The user can either enter the information of n books using the scanner,
or read books information from any binary file.
whether a user writes or reads books,
the books will be inserted in JUC Library ("BookDB.dat" file).

Each book is stored as an object of "Book" class using ObjectStream,
the first book is added using ObjectOutputStream 
while the others are added using AppendableObjectOutputStream
to avoid Data Corruption.

*/

import java.io.*;
import java.util.*;

public class JUC_Library {
		private static String BookLibrary = "BookDB.dat";
		private static String filename;
		private static int n;

		public static void main(String[] args) {
			
			
			Scanner info = new Scanner(System.in);
		    System.out.println("Welcome to JUC Library");
		    System.out.println("------------------------------");
		    System.out.println("Please enter the number of books you would like to register, or 0 to register them from a file: ");
		try{
			n=info.nextInt();
			info.nextLine();
			
				if(n>0){
				Book b;
					
				for(int i=0 ; i<n ; i++){
							
							System.out.println("\nPlease enter the information of book "+(i+1));
							System.out.println("Book's Title: ");
							String title=info.nextLine();
							System.out.println("Book's Author: ");
							String author=info.nextLine();
							System.out.println("Book's Publisher: ");
							String publisher=info.nextLine();
							System.out.println("Book's ISBN: ");
							int ISBN=info.nextInt();
							System.out.println("Book's Price: ");
							double price=info.nextDouble();
							System.out.println("Book's Number: ");
							int number=info.nextInt();
							info.nextLine();

							b = new Book(title,author,publisher,ISBN,price,number);
							writeToBinary(BookLibrary,b,true);
							System.out.println(b.title.toLowerCase()+" has been added succefully");

						}
						
						}
						else {
						System.out.println("\nPlease enter the file name you would like to read from, OR enter \"BookDB\" to print all the books in the JUC Library: ");
							String FN = info.nextLine().trim();
							String extension = ".dat";
							filename = FN + extension;
							readFromBinaryFile(filename);
						}
						}catch(InputMismatchException ex){
							System.out.println("Invalid Input");
						}
		}

		public static void writeToBinary (String filename, Book obj, boolean append){
				File file = new File (filename);
				ObjectOutputStream out = null;

				try{
						if (!file.exists () || !append) out = new ObjectOutputStream (new FileOutputStream (filename));
						else out = new AppendableObjectOutputStream (new FileOutputStream (filename, append));
						out.writeObject(obj);
						out.flush();
				}catch (FileNotFoundException e){
					System.out.println("File Not Found Exception");
					e.printStackTrace();
				}catch(IOException e){
					System.out.println("I/O Exception");
					e.printStackTrace();
				}finally{
						try{
								if (out != null) out.close();
						}catch (Exception e){
								e.printStackTrace ();
						}
				}
		}

		public static void readFromBinaryFile (String filename){
				File file = new File (filename);

				if (file.exists()){
						ObjectInputStream ois = null;
						try{
								ois = new ObjectInputStream (new FileInputStream (filename));
								while (true){
										Book b1 = (Book) ois.readObject();
										
										if(filename.equals("BookDB.dat")){
										System.out.println("\nBook Title: "+b1.title);
										System.out.println("Book's Author: "+b1.author);
										System.out.println("Book's Publisher: "+b1.publisher);
										System.out.println("Book's ISBN: "+b1.ISBN);
										System.out.println("Book's Price: "+b1.price);
										System.out.println("Book's Number: "+b1.number);}
										else{
										writeToBinary(BookLibrary,b1,true);	
										System.out.println(b1.title.toLowerCase()+" has been added succefully");}
								}
								
						}catch (EOFException e){
						}catch (FileNotFoundException e){
							System.out.println("File Not Found Exception");
							e.printStackTrace();
						}catch(IOException e){
							System.out.println("I/O Exception");
							e.printStackTrace();
						}catch(ClassNotFoundException e){
							System.out.println("Class Not Found Exception");
							e.printStackTrace();
						}
						finally{
							try {
								if (ois != null) ois.close();
							} catch (IOException e) {
								System.out.println("I/O Exception");
								e.printStackTrace();
							}	
							}
				}else
					System.out.println("File doesn't exist");
				
		}

		private static class AppendableObjectOutputStream extends ObjectOutputStream {
					public AppendableObjectOutputStream(OutputStream out) throws IOException {
						super(out);
					}

					@Override
					protected void writeStreamHeader() throws IOException {}
		}
}