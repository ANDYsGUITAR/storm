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
<script src="js/echarts.js"></script>
 <script type="text/javascript">
 	function recommand(){
 		 var student_no=$("#student_no").val();
		  var postData={"student_no":student_no};
		    var rank=[];  
	        var mark=[];  
		   $.ajax({
			  async : false,
              cache : false,
              type : 'POST',			
 			   url:"preBook/Recommendation",
 			   dataType : "json",
             data : postData,            
             error : function() {
              alert('请求失败 ');
              },
 			success:function(data){
 				alert("success");
 				$('#table').bootstrapTable('destroy');
 				$('#table').bootstrapTable({
					data:data,
					pagination:true,
					pageNumber:1,
				   
				   pageSize:10,
 				    columns: [{
 				        field: 'rank',
 				        title: '排名'
 				    }, {
 				        field: 'book_name',
 				        title: '电子书名称'
 				    }, {
 				        field: 'score',
 				        title: '推荐度'
 				    }, ]  				    
 				});
 				
 				  var barChart = echarts.init(document.getElementById('barChart'));
 					for (var i=0;i<10;i++)  
 				    {  
 				        rank.push(data[i].rank);  
 				        mark.push(data[i].mark);  
 				    }  
 							  var barOption = {
 									  title : {
 									        text: '推荐电子书Top10'
 									    },
 									  tooltip : {
 									        trigger: 'axis'
 									    }, 
 									    legend: {
 									        data:['推荐度']
 									    },
 									    toolbox: {
 									        show : true,
 									        feature : {
 									            dataView : {show: true, readOnly: false},
 									            magicType : {show: true, type: ['line', 'bar']},
 									            restore : {show: true},
 									            saveAsImage : {show: true}
 									        }
 									    },
 									    calculable : true,
 									    xAxis:[
 										    {  
 										    	name:'排名',
 										    	type:'category',
 					                            data:[]
 										    }
 										    ], 
 										    yAxis : [
 										        {
 										        	name:'推荐度',
 										            type : 'value'
 										        }
 										    ],
 					                        series:[{
 					                        	name:'推荐度',
 					                        	type:'bar',
 					                        	barWidth:'60%',
 					                        	data:[]
 					                        }] 
 									};
 							 		barOption.xAxis[0].data=rank;
 							 		barOption.series[0].data=mark;
 				                    barChart.hideLoading();  
 				                    barChart.setOption(barOption);
 				                   
 			},				//success end
 		});
 	}
 </script>

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>学习预测</title>
	<!-- Bootstrap Styles-->
    <link href="${basePath}assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="${basePath}assets/css/font-awesome.css" rel="stylesheet" />
     <!-- Custom Styles-->
    <link href="${basePath}assets/css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
   <link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
     <!-- TABLE STYLES-->
    <link rel="stylesheet" href="css/bootstrap-table.css">
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
                <a class="navbar-brand" href="index.jsp"><img src="assets/img/logo.png" alt="软酷网" style="position:absolute;bottom:8px; ">
</a>
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
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
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
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">28% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                                            <span class="sr-only">28% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>                            <!-- 学习概况列表  --> 
                            <div class="col-md-12">
                            <div class="panel panel-default" >
                             <div class="panel-body">
                            <div class="table-responsive">
                                <table   id="table-sum"> </table>
                            </div>
                            </div>
                        </div>
                        </div>

                         <div class="col-md-12">
                          <div class="panel panel-default" >
                           <div class="panel-body">
                            <div class="table-responsive">
                                <table   id="table-single"> </table>
                            </div>
                            </div>
                        </div>
                        </div>
                        <!-- 学习概况列表结束  -->
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">85% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                                            <span class="sr-only">85% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
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
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
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
                        <a href="showtopLesson.jsp"><i class="fa fa-table"></i> 全站最受欢迎的课程TopN</a>
                    </li>
                    <li>
                        <a class="active-menu" href="Recommendation.jsp"><i class="fa fa-fw fa-file"></i> 学习预测</a>
                    </li>
                    <li>
                        <a href="statistics.jsp"><i class="fa fa-fw fa-file"></i>学习统计</a>
                    </li>
                     <li>
                        <a href="figure.jsp"><i class="fa fa-fw fa-file"></i> 人物画像</a>
                    </li>
                    <li>
                        <a href="cluster.jsp"><i class="fa fa-fw fa-file"></i> 聚类分析</a>
                    </li>                       
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
		  <div class="header"> 
                        <h1 class="page-header">
                            数据分析 <small>学习预测</small>
                        </h1>
						<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Recommendation</a></li>
					  <li class="active">Data</li>
					</ol> 
									
		</div>
            <div id="page-inner"> 
                 <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            预测信息
                        </div>
                        <div class="panel-body">
                            <p style="font-size:14px;">请输入学生账号查看推荐书目。</p></br>
                            <div style="float: left;">
                                <form id="account">
                                    <input  class="form-control" placeholder="学生账号"  id="student_no" name="student_no"  >
                                </form>
                            </div>
                            <div style="float: left;">
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            </div>                            
                            <!-- 确认按钮  -->
                            <div style="float: left;">
                            <button type="button" class="btn btn-primary" onclick="recommand();">确认</button>
                            </div>
                        </br>
                        </br>
                        </br>
                            <!-- 推荐书目列表  -->
                            <div class="col-md-12">
                            <div class="table-responsive">
                                <table   id="table"> </table>
                            </div>
                        </div>
                        <!-- 推荐书目列表结束  -->
                        </div>
                 </div>
                 <div class="row">
                 <div class="col-md-12" >
                  <!--   柱状图 -->
                    <div class="panel panel-default" >
                        <div class="panel-heading">
                            柱状图
                        </div>                                                   
                        <div class="panel-body" >
                                 <div class="table-responsive">
                                       <div id="barChart" style="width: 800px;height:600px;">                                       			
                                       </div>                                        
                                </div>
                       </div>
                      </div>
                  </div>
                  </div>
				</div>
				 <footer><p>Copyright© 2007-2017 Ruankosoft Technologies Co., Ltd. 粤B2-20100246 粤ICP备12081495号</p></footer>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
     <!-- JS Scripts-->
    <!-- Metis Menu Js -->
    <script src="${basePath}assets/js/jquery.metisMenu.js"></script>
      <!--          Custom Js -->
     <%--     <script src="${basePath}assets/js/custom-scripts.js"></script> --%>
   <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
   <script src="js/bootstrap-table.js"></script>
   <script src="js/bootstrap-table-zh-CN.js"></script>
    
   
</body>
</html>
    