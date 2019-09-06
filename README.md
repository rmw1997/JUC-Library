# JUC Library

## Project Overview

A java program to keep a record of books in **JUC library**. 
The user can either enter the information of **n** books using the scanner or read books information from any binary file.
Whether a user writes or reads books, the books will be inserted at JUC Library (`BookDB.dat`).


Each book is stored as an object of "Book" class using ObjectStream,
the first book is added using ObjectOutputStream 
while the others are added using AppendableObjectOutputStream
to avoid Data Corruption.

```public static void writeToBinary (String filename, Book obj, boolean append){
				File file = new File (filename);
				ObjectOutputStream out = null;

				try{
						if (!file.exists () || !append) 
                            out = new ObjectOutputStream (new FileOutputStream (filename));
						else 
                            out = new AppendableObjectOutputStream (new FileOutputStream (filename, append));
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
		}```

## Resources
 - (https://stackoverflow.com/questions/4646272/appending-objects-to-a-binary-file)
