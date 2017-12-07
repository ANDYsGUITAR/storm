<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*"%>
    
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <base href="<%=basePath%>">
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge,firefox=1">
      
      <!-- 导入javabean-->
      <%@ page import="com.log.model.*" %>
      <%
      	List<topLesson> list=(ArrayList<topLesson>)request.getAttribute("lessonList");
      %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>软酷网最受欢迎的课程TopN</title>
	<!-- Bootstrap Styles-->
    <link href="${basePath}assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="${basePath}assets/css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles-->
    <link href="${basePath}assets/css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
   <link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link href="${basePath}assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
        <link rel="stylesheet" href="${basePath}plugin/layui-v1.0.7/css/layui.css">
    <link rel="stylesheet" href="${basePath}css/date.css">
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp"><strong>软酷</strong></a>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Doe</strong>
                                    <span class="pull-right text-muted">
                                        <em>Today</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</div>
                            </a>
                        </li>
                       
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                       
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">

                    <li>
                        <a href="index.jsp"><i class="fa fa-dashboard"></i>概述</a>
                    </li>
                   
                    
                    <li>
                        <a href="showtoplesson.jsp" class="active-menu"><i class="fa fa-table"></i> 全站最受欢迎的课程TopN</a>
                    </li>
                   
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
		  <div class="header"> 
                        <h1 class="page-header">
                            数据分析 <small>全站最受欢迎的课程TopN</small>
                        </h1>
						<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Tables</a></li>
					  <li class="active">Data</li>
					</ol> 
									
		</div>
		
            <div id="page-inner"> 

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             课程TopN
                        </div>
                        <div class="panel-body">
                        <form class="layui-form" action="" id="form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">时间段</label>
                <div class="layui-input-inline ui-time">
                    <input type="text" id="dp11" class="layui-input ui-time-text" value="" kssj="" jssj="" />
                </div>
                   <form method="post" action="topLesson/showtopLesson"> <input type="submit" value="查找"></form>
            </div>
        </div>
    </form>
  
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="${basePath}plugin/layui-v1.0.7/layui.js" type="text/javascript"></script>
<script src="${basePath}main.js?1" type="text/javascript"></script>
<script type="text/javascript">

    layui.use(['laydate','dateLay'], function(){
        var  layer = layui.layer,laydate = layui.laydate;
        var obj={
            init:function(){
                this.dp11=$('#dp11');
                this.dp12=$('#dp12');
                this.initEvent();
            },
            initEvent:function(){
                this.dp11.dateLay();
                this.dp12.dateLay();
                
            }
        }
        obj.init();
    });
</script>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example"
                                >
                                    <thead>
                                        <tr>
                                        
                                            <th>排名</th>
                                            <th>电子书ID</th>
                                            <th>电子书名称</th>
                                            <th>使用人数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="gradeA">
                                		<td>null</td>
                                		<td>null</td>
                                		<td>null</td>
                                		 <td class="center"></td>
                                </tr>
                                      <%
                                    	if(list==null||list.size()<=0){
                                    		
                                    	}else{
                                    		for(int i=0;i<list.size();i++){
                                    			topLesson lesson;
                                    			lesson=list.get(i);
                                    			out.println("<tr class=\"gradeA\">");
                                    			out.println("<td>"+lesson.getBook_rank()+"</td>");
                                    		    out.println("<td>"+lesson.getBook_id()+"</td>");
                                    		    out.println("<td>"+"</td>");
                                    		    out.println("<td class=\"center\">"+lesson.getBook_num()+"</td>");
                                    		    out.println("</tr>");
                                    		}
                                    	}                                    
                                    %>
                                        
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
           
            </div>
                <!-- /. ROW  -->
        </div>
               <footer><p>Copyright© 2007-2017 Ruankosoft Technologies Co., Ltd. 粤B2-20100246 粤ICP备12081495号</footer>
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="${basePath}assets/js/jquery-1.10.2.js"></script>
      <!-- Bootstrap Js -->
    <script src="${basePath}assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="${basePath}assets/js/jquery.metisMenu.js"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="${basePath}assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="${basePath}assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
         <!-- Custom Js -->
    <script src="${basePath}assets/js/custom-scripts.js"></script>
    
   
</body>
</html>
