<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>%
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
   function table_single(data){
			$('#table-single').bootstrapTable('destroy');
			$('#table-single').bootstrapTable({
				data:data,
				pagination:true,
				pageNumber:1,
				search:true,
				searchOnEnterKey:true,
			    columns: [{
			        field: 'book_name',
			        title: '电子书名称'
			    }, {
			        field: 'runtime',
			        title: '学习时间'
			    },  ]  				    
			});
			}
   
   function barChart(data){
	  
   }
   
 	function Statistics(){
 		 var student_no=$("#student_no").val();
 		 var year=$("#year").val();
		  var postData={"student_no":student_no,"year":year};		  
		   $.ajax({
			  async : false,
              cache : false,
              type : 'POST',			
 			   url:"Statistics/LearnStatistics",
 			   dataType : "json",
             data : postData,            
             error : function() {
              alert('请求失败 ');
              },
              success:function(data){
   				//alert("success"+data[0].twelveMonthLearnTimeList.get(0));
   				console.log(data);
   				console.log(data[0].singleBookList);
   				console.log(data[0].twelveMonthLearnTimeList);
   				$('#table-sum').bootstrapTable('destroy');
   				$('#table-sum').bootstrapTable({
  					data:data,
//   					pagination:true,
//   					pageNumber:1,
//   					pageSize:1,
   				    columns: [{
   				        field: "booknum",
   				        title: '学习书的总数'
   				    }, {
   				        field: "allRuntime",
   				        title: '总学习时间'
   				    },  ]  				    
   				});
   				
   				table_single(data[0].singleBookList);
   			//	barChart(data[0].twelveMonthLearnTimeList);			
   			 var month=[];
   		   var runtime=[];
   		   var barChart = echarts.init(document.getElementById('barChart'));
   		   for (var i=0;i<12;i++)  
   		    {  
   		        month.push(data[0].twelveMonthLearnTimeList[i].date);  
   		        runtime.push(data[0].twelveMonthLearnTimeList[i].runtime);  
   		    }  
   			  var barOption = {
   						  title : {
   						        text: '每月学习时间'
   						    },
   						  tooltip : {
   						        trigger: 'axis'
   						    }, 
   						    legend: {
   						        data:['学习时间']
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
   							    	name:'月份',
   							    	type:'category',
   		                            data:[]
   							    }
   							    ], 
   							    yAxis : [
   							        {
   							        	name:'学习时长  单位:小时',
   							            type : 'value'
   							        }
   							    ],
   		                        series:[{
   		                        	name:'学习时长',
   		                        	type:'bar',
   		                        	barWidth:'60%',
   		                        	data:[]
   		                        }] 
   						};
   				 		barOption.xAxis[0].data=month;
   				 		barOption.series[0].data=runtime;
   	                 barChart.hideLoading();  
   	                 barChart.setOption(barOption);
   				
   				
              }
		   });
 	}
 	</script>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>学习统计</title>
	<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
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
                <a class="navbar-brand" href="index.html"><strong>软酷</strong></a>
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
                        <li class="divider"></li>
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
                        <a href="Recommendation.jsp"><i class="fa fa-fw fa-file"></i> 学习预测</a>
                    </li>
                    <li>
                        <a class="active-menu" href="statistics.jsp"><i class="fa fa-fw fa-file"></i>学习统计</a>
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
                            数据分析 <small>学习统计</small>
                        </h1>
						<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Statistics</a></li>
					  <li class="active">Data</li>
					</ol> 
									
		</div>
            <div id="page-inner"> 
                 <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            统计信息
                        </div>
                        <div class="panel-body">
                            <p style="font-size:14px;">请输入学生账号查看学习概况。</p></br>
                            <div style="float: left;">
                                <form id="account">
                                    <input  class="form-control" placeholder="学生账号"  id="student_no" name="student_no"  >
                                </form>
                            </div> <div class="panel-body">
                                                        <div style="float: left;">
                                                 &nbsp;&nbsp;&nbsp;&nbsp;
                                                       </div>                            
                            <!-- 确认按钮  -->
                            <div style="float: left;">
                            <button type="button" class="btn btn-primary" onclick="Statistics();">确认</button>
                            </div>
                        </div>
                 </div>
				</div>

                  </div>
                  				                            <!-- 学习概况列表  --> 
                            <div class="col-md-12">
                            <div class="panel panel-default" >
                            <div class="panel-heading">
                            总览
                        </div>
                             <div class="panel-body">
                            <div class="table-responsive">
                                <table   id="table-sum"> </table>
                            </div>
                            </div>
                        </div>
                        </div>

                         <div class="col-md-12">
                          <div class="panel panel-default" >
                         <div class="panel-heading">
                            电子书学习情况
                        </div>
                           <div class="panel-body">
                            <div class="table-responsive">
                                <table   id="table-single"> </table>
                            </div>
                            </div>
                        </div>
                        </div>
                        <!-- 学习概况列表结束  -->
				 <div class="col-md-12" >
                  <!--   柱状图 -->
                    <div class="panel panel-default" >
                        <div class="panel-heading">
                            柱状图--每月学习时间
                        </div>                                                   
                        <div class="panel-body" >
                        <p style="font-size:14px;">请选择年份查看各月学习时间。</p></br>
                              <div style="float: left;">
                            <select id="year" name="year" class="form-control">
                            <option value="2013">2013年</option>
                            <option value="2014">2014年</option>
                            <option value="2015">2015年</option>
                            <option value="2016">2016年</option>
                            <option value="2017"  selected="selected">2017年</option>
                             <option value="2018" >2018年</option>
                            </select>
                            </div>
                             <div style="float: left;">
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                            <div style="float: left;">
                                      <input id="jsonget"  type="button"  value="确认" onclick = "Statistics()" class = "btn btn-primary">
                            </div>
                                 <div class="table-responsive" style="float: left;">
                                                         </br>
                        								</br>
                        								</br>
                                       <div id="barChart" style="width: 800px;height:600px;float:left;" >                                       			
                                       </div>                                        
                                </div>
                       </div>
                      </div>
                  </div>
                  </div>
             <!-- /. PAGE INNER  -->
             <footer><p>Copyright© 2007-2017 Ruankosoft Technologies Co., Ltd. 粤B2-20100246 粤ICP备12081495号</p></footer>
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="${basePath}assets/js/jquery.metisMenu.js"></script>
      <!--          Custom Js -->
     <%--     <script src="${basePath}assets/js/custom-scripts.js"></script> --%>
   <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
   <script src="js/bootstrap-table.js"></script>
   <script src="js/bootstrap-table-zh-CN.js"></script>
</body>
</html>
