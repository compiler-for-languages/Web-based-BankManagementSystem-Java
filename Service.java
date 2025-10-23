package skgfinanacial;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.util.Scanner;
import java.util.*; //for collections





@WebServlet("/Service")
public class Service extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response )throws IOException,ServletException
	{
		response.setContentType("text/html");
	
	PrintWriter obj=response.getWriter();
	
	String depo=request.getParameter("dep");
	
	int depu=Integer.parseInt(depo);
		String choice=request.getParameter("service");
		String amm=request.getParameter("am");
		
		int amount=Integer.parseInt(amm);
		File f=new File("C:/JAVA 2 programs/bankproject/bankrecords.txt");
		Scanner sc=new Scanner(f);
		
	ArrayList<Customers> arr=new ArrayList<>();
	//dynamic array for objects
	//to store all details
		while(sc.hasNextLine())
		{
			//sc.next(); //name
			// sc.nextInt(); //pin
			 //sc.nextInt(); //deposit money
			// sc.next(); //mobile no.
			 arr.add(new Customers(sc.next(),sc.nextInt(),sc.nextInt(),sc.next()));
                      
			 
		}
		
		
		int i;
		int last=0;
		for(i=0;i<arr.size();i++)
		{
			if(depu==arr.get(i).dep)
			{   if(choice.equals("dep"))
				{arr.get(i).dep+=amount;
				last=arr.get(i).dep;
				break;
				}
			else if(choice.equals("with"))
			{
				arr.get(i).dep-=amount;    
				last=arr.get(i).dep;
				break;
			}
			}
		}
		int x=i;
		
		FileWriter fw=new FileWriter(f); //or file name , file will be reset and all existing content deleted
		//opens for writing fresh
		//for faster and efficient storing use buffered writer
		// first stores in temp buffered memory again sends to file after overflow
		//safe and modern pass filewriter object to the BufferedWriter
		
		BufferedWriter bw=new BufferedWriter(fw);
		
		//i can now use bw.write
		
		//again rewrite all old records with new modified
		for(i=0;i<arr.size();i++)
		{bw.newLine();
			bw.write(arr.get(i).name+" "); //string
		bw.write(arr.get(i).pin + " "); //int
		bw.write(arr.get(i).dep+ " "); //int
		bw.write(arr.get(i).mob); //string
		 //goes to nextline
		//further continued by next user
		}
		//data stored
		bw.close();
		fw.close();
		
		String naam=null;
		String maab=null;
		//to print in receipt
		if(x<arr.size())
		{naam=arr.get(x).name;
		 maab=arr.get(x).mob;
		}
		//last var has the balance
		
		obj.print("<html><body>"
				+ "<center><p style='color:green; font-size:40px; '>Transaction Successful<br><br> Your current balance is " +last + " </p></center>"
						+ ""
						+ "<br><br><a href='index.html' > Session expired , Go back </a> &nbsp; &nbsp; &nbsp;"
						+ ""
						+ ""
						+ "<br><br>"
						+ "<center><div style='border:2px solid blue; margin:200px'><h3 id='put'> </h3></div></center>"
						+ "<script>"
						+ "let parr=['SKG financial services pvt ltd.','Name:"+naam+"','Current Balance:"+last+" Rs','Mobile no.:"+maab+"'];"
						+ "let z=0;"
						
						+ "function rprinter()"
						+ "{if(z<parr.length)"
						+ "{"
						+ "document.getElementById('put').innerHTML+=parr[z]+'<br>';"
						+ "z++;"
						+ "setTimeout(rprinter,1500);"
						+ "}"
						+ ""
						+ "else{"
					
						+ "}"
						+ "}"
						+ "rprinter(); // call function once then setTimeout starts recursion"
						+ ""
						
						+ "</script>"
						+ "</body></html>"
						);
		// \" means using escape character equal to " only to specify which quote ends what
	}/*
	🧩 So what happens?
Every time rprinter() runs:

It prints a line.

It schedules the next call after 1.5 seconds.

Then it exits.

When z reaches parr.length, it executes the else block, and that is the final point where you should safely do something like:
	
	This is not true recursion, but rather a delayed loop using callbacks.

Each call doesn't wait for the next one to finish — it just says "do this again later" and leaves.
parallel running occurs so dont put </div> after call put it in else block
*/
	

	
}



class Customers{
	String name;
	int pin;
	int dep;
	String mob;
	
	Customers(String a, int b, int c,String d)
	{
		this.name=a;
		this.pin=b;
		this.dep=c;
		this.mob=d;
	}
}

