package skgfinanacial;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.util.Scanner;
@WebServlet("/Banker")
public class Banker extends HttpServlet{
	
	public void doGet(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter obj=response.getWriter();
		
		File ff=new File("C:/JAVA 2 programs/bankproject/bankrecords.txt");
		//file exists already bcoz first we register
		
		String pass=request.getParameter("pin");
		
		int pin=Integer.parseInt(pass);
		//in file also it is stored as int only
		
		//here we create scanner object and read and later close it, no need to use filereader
		
		Scanner sc=new Scanner(ff);
		int flag=0;
		
		String n=null,m=null;
		int p=0,d=0;
		
		while(sc.hasNextLine())//goes until end of file, is scanner has somethoing to scan still, or we can use sc.hasNext(), to check for next word exists or not
		{n=sc.next(); //name
		 p=sc.nextInt(); //pin
		 d=sc.nextInt(); //deposit money
		 m=sc.next(); //mobile no.
			/*
			 sc.next() reads the next word (skipping spaces and newlines).

So even if the file has multiple lines, Scanner will continue reading word-by-word — ignoring the \n (newline) unless you use nextLine().


> So yes, every 4 words will be scanned correctly, even if they are split across lines.
You don’t need to worry about newline characters with sc.next().
			 */
		if(pin==p)//user matched, login
		{
			flag=1;
			break;
		}
		
		}//scanning finished
		
		if(flag==1)
		{
			//open a page which says login successful, and do deposit withdraw in a web page
			
			//one input tag readonly bcoz it shud not be altered , that is our base to find the customer in next servlet
			obj.print("<html>"
					+ "<body><br><br>"
					+ "<center><h2 style='font-family:lucida console ; color:red ;font-size:32px ;letter-spacing:0px;'>Welcome back "+n+"</h2>"
							+ "<form action='Service' method='GET' >"
							+ "<br><br><p style='font-size:25px color:green'> Your current balance is "+""
									+ "<input style='color:green height:20px width:100px font-size:18px ;' type='text' name='dep' value='"+d+"' readonly>"+"</p>"
							+ "<br><br>"
							+ "Enter amount:<input type='text' name='am' maxlength='7'><br>"
							+ "<br>"
							+"Choose Deposit or Withdrawal: <select name='service'>"
							+ "<option value='dep'>Deposit</option>"
							+ "<option value='with' >Withdrawal</option>"
							+ "</select>"
							+ "<br><br>"
							+ "<input type='submit' value='OK'>"
							+ "<center>"
							+ "</form>"
							+ "</body>"
							+ "</html>");
		}
		
		else {
			//says user not found, invalid pin
			
		}
		
		
	}
	
	
	
	
}
