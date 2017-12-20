<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <!-- 导入javabean-->
      <%@ page import="com.log.model.*" %>
      <%
      	List<topLesson> list=(ArrayList<topLesson>)request.getAttribute("lessonList");
      %>
<title>测试</title>
</head>

  <body>
    <h1>Welcome</h1><h1>访问此页面</h1>  
     <hr/>
     
       <%
                                    	if(list==null||list.size()<=0){
                                    		out.println("<td>列表为空</td>");
                                    	}else{
                                    		for(int i=0;i<list.size();i++){
                                    			topLesson lesson;
                                    			lesson=list.get(i);
                                    			out.println("<tr>");
                                    			out.println("<td>"+lesson.getBook_id()+"</td>");
                                    		    out.println("<td>"+lesson.getBook_id()+"</td>");
                                    		    out.println("<td>"+"</td>");
                                    		    out.println("<td>"+lesson.getBook_id()+"</td>");
                                    		    out.println("</tr>");
                                    		}
                                    	}                                    
                                    %>
     
     
     
     
  



</body>
<body>

</body>
</html>