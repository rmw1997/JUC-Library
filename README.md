# JUC Library

## Project Overview

A java program to keep a record of books in **Jubail University College Library** or **JUC library**. 
The user can either enter the information of **n** books using the scanner or read books information from any binary file.
Whether a user writes books manually or from a file, the books will be inserted at JUC Library (`BookDB.dat`).


Each book is stored as an object of "Book" class using ObjectStream,
the first book is added using ObjectOutputStream 
while the others are added using AppendableObjectOutputStream
to avoid Data Corruption.

```java

		if (!file.exists () || !append) 
                	out = new ObjectOutputStream (new FileOutputStream (filename));
		else 
                        out = new AppendableObjectOutputStream (new FileOutputStream (filename, append));
		out.writeObject(obj);

```

## Resources
 - (https://stackoverflow.com/questions/4646272/appending-objects-to-a-binary-file)
