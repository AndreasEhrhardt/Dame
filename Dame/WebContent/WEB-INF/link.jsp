<% 
String path = request.getParameter("path");
//String path = "C:\\Users\\Adina\\Documents\\Dame\\Dame\\WebContent";
String fileName = request.getParameter("filename");
if(!fileName.endsWith(".pdf")) fileName += ".pdf";
out.println("<p><a href = \"" + path +"\\"+ fileName + "\">Your Game as PDF</a></p>");
%>


