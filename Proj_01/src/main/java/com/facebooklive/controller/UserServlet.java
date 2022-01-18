package com.facebooklive.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebooklive.entity.FacebookUser;
import com.facebooklive.entity.TimeLine;
import com.facebooklive.service.FacebookServiceInterface;
import com.facebooklive.utility.ServiceFactory;

public class UserServlet extends HttpServlet {
	public String m=null;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  m=request.getParameter("method");
		  System.out.println("hii "+m);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center>");
		
		FacebookServiceInterface fs=ServiceFactory.createObject();
		
		if(m.equals("register")) {
			
			String name=request.getParameter("nm");
			String password=request.getParameter("pass");
			String email=request.getParameter("em");
			String gender=request.getParameter("g");
			String age=request.getParameter("age");
			
			FacebookUser fb=new FacebookUser();
			fb.setName(name);
			fb.setAge(age);
			fb.setEmail(email);
			fb.setGender(gender);
			fb.setPassword(password);
			
			int i=fs.createProfileService(fb);
			
			if(i>0) {
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style>");
//				out.println("<br><br><br><br><font size=5 color=blue><b>Profile created successfully</b></font>");
				//out.println("<div style=' background-color:pink; padding-bottom:700px;' ><h1 style='text-align:center;color:blue;'>Profile Created Successfully</h1>");
				out.println("<h1 style='text-align:center;color:blue;'>Profile Created Successfully</h1>");
				out.println("<h2>Your Username : "+email+"</h2>" +"  <h2> password :  "+password+"</h2>");
				out.println("<br><a href=login.html><button style='background-color:blue;color:white;'>Continue</button></a>");
				//out.println("</div>");
				out.println("</html></body>");
			}
			else {
				out.println("could not create profile");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.html");
				rd.include(request, response);
			}
			
		}
		if(m.equals("login")) {
			
			String password=request.getParameter("pass");
			String email=request.getParameter("em");
			
			
			FacebookUser fb=new FacebookUser();
			fb.setPassword(password);
			fb.setEmail(email);
			
			
			int i=fs.loginProfileService(fb);
			
			if(i>0) {
				
				HttpSession hs=request.getSession(true);
				hs.setAttribute("userid", email);
				
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/UserServlet?method=UserHomePage");
				rd.forward(request, response);
			}
			else {
				out.println("Invalid id and password");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
		}
		
		// f(m.equals(
		if(m.equals("UserHomePage")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			out.println("<div style='background-image:url(https://wallup.net/wp-content/uploads/2019/09/29615-pirates-of-the-caribbean-jack-sparrow-pirate-johnny-depp-1.jpg);width:100%;height: 100%;background-position: center;background-repeat: no-repeat;background-position: center center;background-attachment: fixed;-webkit-background-size: cover;-moz-background-size: cover;-o-background-size: cover;background-size:center center;'> ");
			out.println("<html>\r\n" + 
					"<style>\r\n" + 
					".btn-group button {\r\n" + 
					"  background-color:black; /* Green background */\r\n" + 
					"  border: 1px solid green; /*  */\r\n" + 
					"  color: white; /* White text */\r\n" + 
					"  padding: 10px 24px; /* Some padding */\r\n" + 
					"  cursor: pointer; /* Pointer/hand icon */\r\n" + 
					"  width: 25%; /* Set a width if needed */\r\n" + 
					"  display: block; /* Make the buttons appear below each other */\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".btn-group button:not(:last-child) {\r\n" + 
					"  border-bottom: none; /* Prevent double borders */\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"/* Add a background color on hover */\r\n" + 
					".btn-group button:hover {\r\n" + 
					"  background-color: #3e8e41;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"<body>");
			out.println(" <br>");
			out.println("<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"  <meta charset=\"UTF-8\">\r\n" + 
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"  <title>Govardhan@gopi</title>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"\r\n" + 
					 
					"<div class=\"profile-pic-div\">\r\n" + 
					"  <img src=\"image.jpg\" id=\"photo\">\r\n" + 
					"  <input type=\"file\" id=\"file\">\r\n" + 
					"  <label for=\"file\" id=\"uploadBtn\">Choose Photo</label>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"<script src=\"app.js\"></script>\r\n" + 
					
					"\r\n" + 
					"<style>*{\r\n" + 
					"					    margin: 0;\r\n" + 
					"					    padding: 0;\r\n" + 
					"					    box-sizing: border-box;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					body{\r\n" + 
					"					    height: 100vh;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					h1{\r\n" + 
					"					    font-family: sans-serif;\r\n" + 
					"					    text-align: center;\r\n" + 
					"					    font-size: 30px;\r\n" + 
					"					    color: #222;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					.profile-pic-div{\r\n" + 
					"					    height: 200px;\r\n" + 
					"					    width: 200px;\r\n" + 
					"					    position: absolute;\r\n" + 
					"					    top: 70%;\r\n" + 
					"					    left: 10%;\r\n" + 
					"					    transform: translate(-50%,-50%);\r\n" + 
					"					    border-radius: 50%;\r\n" + 
					"					    overflow: hidden;\r\n" + 
					"					    border: 1px solid grey;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#photo{\r\n" + 
					"					    height: 100%;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#file{\r\n" + 
					"					    display: none;\r\n" + 
					"					}\r\n" + 
					"\r\n" + 
					"					#uploadBtn{\r\n" + 
					"					    height: 40px;\r\n" + 
					"					    width: 100%;\r\n" + 
					"					    position: absolute;\r\n" + 
					"					    bottom: 0;\r\n" + 
					"					    left: 50%;\r\n" + 
					"					    transform: translateX(-50%);\r\n" + 
					"					    text-align: center;\r\n" + 
					"					    background: rgba(0, 0, 0, 0.7);\r\n" + 
					"					    color: wheat;\r\n" + 
					"					    line-height: 30px;\r\n" + 
					"					    font-family: sans-serif;\r\n" + 
					"					    font-size: 15px;\r\n" + 
					"					    cursor: pointer;\r\n" + 
					"					    display: none;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</body>\r\n" +
					"<script> //declearing html elements\r\n" + 
					"\r\n" + 
					"const imgDiv = document.querySelector('.profile-pic-div');\r\n" + 
					"const img = document.querySelector('#photo');\r\n" + 
					"const file = document.querySelector('#file');\r\n" + 
					"const uploadBtn = document.querySelector('#uploadBtn');\r\n" + 
					"\r\n" + 
					"//if user hover on img div \r\n" + 
					"\r\n" + 
					"imgDiv.addEventListener('mouseenter', function(){\r\n" + 
					"    uploadBtn.style.display = \"block\";\r\n" + 
					"});\r\n" + 
					"\r\n" + 
					"//if we hover out from img div\r\n" + 
					"\r\n" + 
					"imgDiv.addEventListener('mouseleave', function(){\r\n" + 
					"    uploadBtn.style.display = \"none\";\r\n" + 
					"});\r\n" + 
					"\r\n" + 
					"//lets work for image showing functionality when we choose an image to upload\r\n" + 
					"\r\n" + 
					"//when we choose a foto to upload\r\n" + 
					"\r\n" + 
					"file.addEventListener('change', function(){\r\n" + 
					"    //this refers to file\r\n" + 
					"    const choosedFile = this.files[0];\r\n" + 
					"\r\n" + 
					"    if (choosedFile) {\r\n" + 
					"\r\n" + 
					"        const reader = new FileReader(); //FileReader is a predefined function of JS\r\n" + 
					"\r\n" + 
					"        reader.addEventListener('load', function(){\r\n" + 
					"            img.setAttribute('src', reader.result);\r\n" + 
					"        });\r\n" + 
					"\r\n" + 
					"        reader.readAsDataURL(choosedFile);\r\n" + 
					"\r\n" + 
					"        //Allright is done\r\n" + 
					"\r\n" + 
					 
					"    }\r\n" + 
					"});</script>\r\n" +
 
					"</html>");
			out.println("");
			out.println("");

			
			out.println("<div class=\"btn-group\" align=\"left\">\r\n"  + 
					"  <a href=UserServlet?method=viewProfile> <button>View Profile</button></a> \r\n" + 
					"  <a href=UserServlet?method=editProfile><button>Edit Profile</button></a>\r\n" + 
					" <a href=UserServlet?method=searchProfile> <button>Search Profile</button></a> \r\n" + 
					" <a href=UserServlet?method=uploadphoto> <button>Upload photo</button></a>\r\n" + 
					"  <a href=UserServlet?method=deleteprofile><button>Delete Profile</button></a> \r\n" + 
					"  <a href=UserServlet?method=friendrequest><button> Friend Request</button></a>\r\n" + 
					"  <a href=UserServlet?method=logout><button> Log Out</button></a>\r\n" + 
					"</div>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
			out.println(" </div>");
			 
			FacebookUser fb=new FacebookUser();
			fb.setEmail(email);
			List<TimeLine> tline=fs.timeLineService(fb);
			if(tline.size()>0) {
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style>");
				
				out.println("<table border=0></table>");
				out.println("<table border=0>");
				out.println(" <h1 style='text-align:center;color:blue;'>Time line Messages</h1>");
				for(TimeLine tt:tline) {
					if(tt.getReceiver_Id().equals(email)) {
					 
					out.println("<tr><td>"+tt.getSender_id()+"</td><td><textarea cols=100 rows=5>"+tt.getMessage()+"</textarea></td></tr>");
					out.println("<tr><td></td><td><a href=UserServlet?method=like>Like ("+tt.getMessagelike()+")</a><a href=UserServlet?method=dislike>Dislike("+tt.getDislike()+")</a></td><td><a href=UserServlet?method=reply>reply</td></tr>");
			    
				    out.println("</html></body>");
					}
				}
			}
			else {
			
				out.println("<tr><td></td><td> no timeline message</td></tr>");
			}
			out.println("</table>");
			 
		
		if(m.equals("reply")) {
			
		}

		out.println("</center></body></html>");
		
	} 
		if(m.equals("viewProfile")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			FacebookUser fb=new FacebookUser();
			//fb.setPassword(password);
			fb.setEmail(email);
			FacebookUser fb1 =fs.viewProfileService(fb);
			out.println(" <h1 style='text-align:center;color:blue;'>View Profile</h1>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
			out.println("  <p style=\"text-align: left;\">  <font size=5 color=black><b> ............>>>===<<<............ </b>" );
			out.println("<p style=\"text-align: left;\">  <font size=5 color=black><b>			Name :" +fb1.getName()+"</b></font></p> ");
			out.println("<p style=\"text-align: left;\">  <font size=5 color=black><b>			Email : " +fb1.getEmail()+"</b></font></p> ");
			out.println("<p style=\"text-align: left;\">  <font size=5 color=black><b>			Gender : " +fb1.getGender()+"</b></font></p> ");
			out.println("<p style=\"text-align: left;\">  <font size=5 color=black><b>			Age : " +fb1.getAge()+"</b></font></p> ");
			//out.println("  <p style=\"text-align: left;\">  <font size=5 color=black><b> ............>>>===<<<............ </b>" );
			
			out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
				
				
				
				out.println(" <a href=UserServlet?method=UserHomePage> <button>UserHomePage</button></a> ");
				 
			}
		if(m.equals("editProfile")) {
			//out.println(" ");
			HttpSession hs=request.getSession(true);
			//String email=hs.getAttribute("userid").toString(); 
			String s=hs.getAttribute("userid").toString();
			String email=request.getParameter("em");
			String password=request.getParameter("pass");
			FacebookUser fb=new FacebookUser();
			fb.setEmail(email);
			fb.setPassword(password);
			//int i=fs.editprofileService(fb);
			/*
			 * if(i>0) { out.
			 * println("<br><br><tr><td><h1 style=color:green><Strong>profile created successfully</Strong><h1>"
			 * ); out.println("<br><a href =home.html>Continue</a></td></tr>");
			 * 
			 * RequestDispatcher rd=getServletContext().getRequestDispatcher("/home.html");
			 * rd.include(request, response); } else { out.
			 * println("<br><br><h1 sytyle=colo:red><Strong>could not edit profile<Strong></h1>"
			 * ); RequestDispatcher
			 * rd=getServletContext().getRequestDispatcher("/home.html");
			 * rd.include(request, response); }
			 */
			
		
		out.println("</center></body></html>");
			
			out.println(" <h1 style='text-align:center;color:blue;'>Edit Profile</h1>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +s+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
				out.println(" <a href=UserServlet?method=UserHomePage> <button>UserHomePage</button></a> "); 
			}
		if(m.equals("searchProfile")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			out.println(" <h1 style='text-align:center;color:blue;'>Search Profile</h1>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");

					 FacebookUser fb=new FacebookUser();
					 out.println("<header><div style=\"background-color: beige; width: 100%; height: 55px;\" ><input style=\"margin-left:75%; margin-top: 20px; padding:5px 100px 0px 5px;\" type=\"text\""+fb.getEmail()+"\" id=\"sc\" placeholder=\"Enter Name\">"
					 		+ "<a href='viewProfile'><button style=\"background-color: blue; color: white; margin:0px 10px 0px 10px;\">Search</button></a></div></header><div> </div>");			 
						out.println("<br><br> <a href=UserServlet?method=UserHomePage> <button>UserHomePage</button></a> "); 
						 int i=fs.searchProfileService(fb);
						 
						if(i>0) {
							
							//hs.setAttribute("userid", name);
							RequestDispatcher rd=getServletContext().getRequestDispatcher("/UserServlet?method=viewProfile");
							rd.forward(request, response);
						}
						else {
							out.println("Invalid name");
							RequestDispatcher rd=getServletContext().getRequestDispatcher("/searchProfile");
							rd.include(request, response);
						} 
				 }
			 
	
		if(m.equals("uploadphoto")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			out.println(" <br><br><h1 style='text-align:center;color:blue;'>Upload Photo</h1>");
			out.println(" <br><br><br><br> <br><br><br><br><p style=\"text-align:left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
				out.println("<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"  <meta charset=\"UTF-8\">\r\n" + 
						"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"  <title>Govardhan@gopi</title>\r\n" + 
						"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						 
						"<div class=\"profile-pic-div\">\r\n" + 
						"  <img src=\"image.jpg\" id=\"photo\">\r\n" + 
						"  <input type=\"file\" id=\"file\">\r\n" + 
						"  <label for=\"file\" id=\"uploadBtn\">Choose Photo</label>\r\n" + 
						"</div>\r\n" + 
						"\r\n" + 
						"<script src=\"app.js\"></script>\r\n" + 
						
						"\r\n" + 
						"<style>*{\r\n" + 
						"					    margin: 0;\r\n" + 
						"					    padding: 0;\r\n" + 
						"					    box-sizing: border-box;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					body{\r\n" + 
						"					    height: 100vh;\r\n" + 
						"					    width: 100%;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					h1{\r\n" + 
						"					    font-family: sans-serif;\r\n" + 
						"					    text-align: center;\r\n" + 
						"					    font-size: 30px;\r\n" + 
						"					    color: #222;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					.profile-pic-div{\r\n" + 
						"					    height: 200px;\r\n" + 
						"					    width: 200px;\r\n" + 
						"					    position: absolute;\r\n" + 
						"					    top: 30%;\r\n" + 
						"					    left: 50%;\r\n" + 
						"					    transform: translate(-50%,-50%);\r\n" + 
						"					    border-radius: 50%;\r\n" + 
						"					    overflow: hidden;\r\n" + 
						"					    border: 1px solid grey;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					#photo{\r\n" + 
						"					    height: 100%;\r\n" + 
						"					    width: 100%;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					#file{\r\n" + 
						"					    display: none;\r\n" + 
						"					}\r\n" + 
						"\r\n" + 
						"					#uploadBtn{\r\n" + 
						"					    height: 40px;\r\n" + 
						"					    width: 100%;\r\n" + 
						"					    position: absolute;\r\n" + 
						"					    bottom: 0;\r\n" + 
						"					    left: 50%;\r\n" + 
						"					    transform: translateX(-50%);\r\n" + 
						"					    text-align: center;\r\n" + 
						"					    background: rgba(0, 0, 0, 0.7);\r\n" + 
						"					    color: wheat;\r\n" + 
						"					    line-height: 30px;\r\n" + 
						"					    font-family: sans-serif;\r\n" + 
						"					    font-size: 15px;\r\n" + 
						"					    cursor: pointer;\r\n" + 
						"					    display: none;\r\n" + 
						"}\r\n" + 
						"</style>\r\n" + 
						"</body>\r\n" +
						"<script> //declearing html elements\r\n" + 
						"\r\n" + 
						"const imgDiv = document.querySelector('.profile-pic-div');\r\n" + 
						"const img = document.querySelector('#photo');\r\n" + 
						"const file = document.querySelector('#file');\r\n" + 
						"const uploadBtn = document.querySelector('#uploadBtn');\r\n" + 
						"\r\n" + 
						"//if user hover on img div \r\n" + 
						"\r\n" + 
						"imgDiv.addEventListener('mouseenter', function(){\r\n" + 
						"    uploadBtn.style.display = \"block\";\r\n" + 
						"});\r\n" + 
						"\r\n" + 
						"//if we hover out from img div\r\n" + 
						"\r\n" + 
						"imgDiv.addEventListener('mouseleave', function(){\r\n" + 
						"    uploadBtn.style.display = \"none\";\r\n" + 
						"});\r\n" + 
						"\r\n" + 
						"//lets work for image showing functionality when we choose an image to upload\r\n" + 
						"\r\n" + 
						"//when we choose a foto to upload\r\n" + 
						"\r\n" + 
						"file.addEventListener('change', function(){\r\n" + 
						"    //this refers to file\r\n" + 
						"    const choosedFile = this.files[0];\r\n" + 
						"\r\n" + 
						"    if (choosedFile) {\r\n" + 
						"\r\n" + 
						"        const reader = new FileReader(); //FileReader is a predefined function of JS\r\n" + 
						"\r\n" + 
						"        reader.addEventListener('load', function(){\r\n" + 
						"            img.setAttribute('src', reader.result);\r\n" + 
						"        });\r\n" + 
						"\r\n" + 
						"        reader.readAsDataURL(choosedFile);\r\n" + 
						"\r\n" + 
						"        //Allright is done\r\n" + 
						"\r\n" + 
						 
						"    }\r\n" + 
						"});</script>\r\n" +
	 
						"</html>");
				out.println(" <br><br><br><br><br><br><br><br><br><br><br><br><br><a href=UserServlet?method=UserHomePage> <button>UserHomePage</button></a> ");	 
			}
		if(m.equals("deleteprofile")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			FacebookUser fb=new FacebookUser();
			fb.setEmail(email);
			
			int i=fs.deleteProfileService(fb);
			
			if(i>0)
			{

				//out.println("<html><body><center>");
				//out.println("profile deleted");
				
				//out.println("</center></body></html>");
			
		
			out.println(" <h1 style='text-align:center;color:black;'> Profile Deleted Successfully  </h1>");
			out.println(" <h1 style=\"text-align:center;\">  <font size=5 color=black><b> Thank you Visit again </b></font></h1> ");
			//out.println(" <br> <br> <br>");
			//out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
				//out.println(" <a href=UserServlet?method=login> <button>Continue</button></a> ");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
			else 
			{
				out.println("deletion failed");
			}
		}
		if(m.equals("friendrequest")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			out.println(" <h1 style='text-align:center;color:blue;'>Friend Request</h1>");
			out.println("  <br><br><p style=\"text-align: left;\">  <font size=5 color=black><b> Welcome " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
		out.println(" <a href=UserServlet?method=UserHomePage> <button>UserHomePage</button></a> ");
				 
			}
		if(m.equals("logout")) {
			HttpSession hs=request.getSession(true);
			String email=hs.getAttribute("userid").toString(); 
			out.println(" <h1 style='text-align:center;color:black;'>LogOut Successfully </h1>");
			out.println(" <p style=\"text-align: left;\">  <font size=5 color=black><b> Thank you Visit again " +email+"</b></font></p> ");
				out.println("<html><body> <style type=\"text/css\">"
						+ ""
						+ "body{\r\n" + 
						"  text-align:center;\r\n" + 
						"  vertical-align:center;\r\n" + 
						"  background: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);\r\n" + 
						"}</style></html></body>");
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
				rd.include(request, response);
				//System.exit(0);
				
				 
			}
		}
	}
	

