import java.io.*;

public class Book implements Serializable{
	public String title;
	public String author;
	public String publisher;
	public int ISBN;
	public double price;
	public int number;
	
	public Book(String title,String author,String publisher,int ISBN,double price,int number){
		this.title=title;
		this.author=author;
		this.publisher=publisher;
		this.ISBN=ISBN;
		this.price=price;
		this.number=number;
	}
}
