package skgfinanacial;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/NewUser") //annotation compulsory for tomcat 9
public class NewUser extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
		
		PrintWriter obj=response.getWriter();
		
	String name=request.getParameter("uname");
	String mno=request.getParameter("umob");
	String sdep=request.getParameter("udep");
	int dep=Integer.parseInt(sdep);
	//dep has integer rs
	
	
	int pass=(int) Math.floor((Math.random()*(9000-1000+1))+1000);
	//typecasting like int, since Math. functions give double
	
	//max-min+1
	
	
	
	//START FILE CREATION AND SAVING HERE
	
	File f=new File("C:/JAVA 2 programs/bankproject/bankrecords.txt");
	//GIVE PATH  NOT ONLY NAME, BCOZ
	//if only name is given , it is going to apache's root folder,so give absolute path, where file is to be created
	
	//created a file object
	
	if(!f.exists())
	{//if file not exists starting only
		
		//create new file
		
		f.createNewFile(); //file created only once at starting
		
	}
	
	FileWriter fw=new FileWriter(f,true); //appender so ,true means append on "bankrecords.txt" also works
	//opened file for writing , appending like in c
	//for faster and efficient storing use buffered writer
	// first stores in temp buffered memory again sends to file after overflow
	//safe and modern pass filewriter object to the BufferedWriter
	
	BufferedWriter bw=new BufferedWriter(fw);
	
	//i can now use bw.write
	
	bw.newLine();
	bw.write(name+" "); //string
	bw.write(pass + " "); //int
	bw.write(dep + " "); //int
	bw.write(mno); //string
	 //goes to nextline
	//further continued by next user
	
	//data stored
	bw.close();
	fw.close();
	//close opened writers
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//print for servlet html
	
	obj.print("<html>"
			+ "<body><h2>Registration Successful!!!</h2><br><br><center><h1>"+pass+" " +"is your pin to login\n"+
			"</h1>"
			+ "<br><br><br>"
			+ "<p style='font-family:Times New Roman; color:green; font-size:50px; '>Enjoy Banking with SKG Financial Services pvt. ltd</p>"
			+ "<br><br><br>"
			+ "<a href='index.html' style='color:green; font-size:20px;' >Back to login</a>"
			+ "</center></body></html>");
	
	
	}
}