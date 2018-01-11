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

<script type="text/JavaScript">


function checkDate(){
	   var startYear = document.getElementById("startYear").value;
	   var startMonth = document.getElementById("startMonth").value;
	   var endYear = document.getElementById("endYear").value;
	   var endMonth = document.getElementById("endMonth").value;
	   
	   if(startYear == "--"||startMonth==  "--"|| endYear == "--"||endMonth==  "--"){
	     alert("请填写完整时间段");
	     return false;
	   }
	   if(startYear > endYear  ){
	    alert("请正确填写时间段");
	     return false;
	   }
	   else if ((startYear==endYear)&&(startMonth>endMonth)){
		    alert("请正确填写时间段");
		     return false;
	   }
	   else{
		   var startYear=$("#startYear").val();
		   var startMonth=$("#startMonth").val();
		  var endYear=$("#endYear").val();
		  var endMonth=$("#endMonth").val();		
		  var postData={"startYear":startYear,"startMonth":startMonth,"endYear":endYear,"endMonth":endMonth};			
		    
		    var rank=[];  
	        var num=[];  
	
		   $.ajax({
				  async : true,
	              cache : false,
	              type : 'POST',			
	 			   url:"topLesson/showtopLesson",
	 			   dataType : "json",
	             data : postData,            
	             error : function() {
	              alert('请求失败 ');
	              },
	 			success:function(data){
	 				alert("success");
	 				//表格
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
	 				        field: 'num',
	 				        title: '使用人数'
	 				    }, ]  				    
	 				});
	 				//柱状图
		  var barChart = echarts.init(document.getElementById('barChart'));
	for (var i=0;i<10;i++)  
    {  
        rank.push(data[i].rank);  
        num.push(data[i].num);  
    }  
			  var barOption = {
					  title : {
					        text: '全站最受欢迎课程Top10'
					    },
					  tooltip : {
					        trigger: 'axis'
					    }, 
					    legend: {
					        data:['使用人数']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar','pie']},
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
						        	name:'使用人数',
						            type : 'value'
						        }
						    ],
	                        series:[{
	                        	name:'使用人数',
	                        	type:'bar',
	                        	barWidth:'60%',
	                        	data:[]
	                        }] 
					};
			 		barOption.xAxis[0].data=rank;
			 		barOption.series[0].data=num;
                    barChart.hideLoading();  
                    barChart.setOption(barOption);
                    //饼状图
                    var label=[];
                    var value=[];
                    $.each(data,function(i,p){
					label[i]=p['rank'];
					value[i]={'name':p['rank'],'value':p['num']};
					if(i>=9){
						return false;
					}
                    });
                    var pieChart = echarts.init(document.getElementById('pieChart'));
                    var pieOption = {
      					  title : {
  					        text: '全站最受欢迎课程Top10'
  					    },
                    	    tooltip: {
                    	        trigger: 'item',
                    	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    	    },
                    	    legend: {
                    	        orient: 'vertical',
                    	        x: 'right',
                    	        data:[]
                    	    },
                    	    series: [
                    	        {
                    	            name:'电子书使用人数',
                    	            type:'pie',
                    	            radius: ['50%', '70%'],
                    	            avoidLabelOverlap: false,
                    	            label: {
                    	                normal: {
                    	                    show: false,
                    	                    position: 'center'
                    	                },
                    	                emphasis: {
                    	                    show: true,
                    	                    textStyle: {
                    	                        fontSize: '30',
                    	                        fontWeight: 'bold'
                    	                    }
                    	                }
                    	            },
                    	            labelLine: {
                    	                normal: {
                    	                    show: false
                    	                }
                    	            },
                    	            data:[]
                    	        }
                    	    ]
                    	};
                    pieOption.legend.data=label;
                    pieOption.series[0]['data']=value;
                    pieChart.setOption(pieOption);
	 			},// 	 			success截止

	 		});				     //ajax截止
      }
	}
</script>

      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge,firefox=1">
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>软酷网最受欢迎的课程TopN</title>
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
                        <a href="showtopLesson.jsp" class="active-menu"><i class="fa fa-table"></i> 全站最受欢迎的课程TopN</a>
                    </li>
                    
                      <li>
                        <a  href="Recommendation.jsp"><i class="fa fa-fw fa-file"></i> 学习预测</a>
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
            <div class="row" id="row1">
                <div class="col-md-12" id="row2">
                        <div class="panel panel-default">
                        <div class="panel-heading">
                             课程TopN
                        </div>
                        <div class="panel-body">         
                      
                      
                        <div>
                            <!--   月份选择器 -->
                            请选择查询时间
                            </br>
                            </br>
                            <div style="float: left;">
                            
                            <select id="startYear" name="startYear" class="form-control">
                            <option value="2013">2013年</option>
                            <option value="2014">2014年</option>
                            <option value="2015">2015年</option>
                            <option value="2016">2016年</option>
                            <option value="2017" >2017年</option>
                             <option value="2018" >2018年</option>
                             <option value="--" selected="selected">起始年份</option>
                            </select>
                       
                            </div>
                            <div style="float: left;">
                         
                            <select id="startMonth" name="startMonth" class="form-control">
                            <option value="01">1月</option>
                            <option value="02">2月</option>
                            <option value="03">3月</option>
                            <option value="04">4月</option>
                            <option value="05">5月</option>
                            <option value="06">6月</option>
                            <option value="07">7月</option>
                            <option value="08">8月</option>
                            <option value="09">9月</option>
                            <option value="10">10月</option>
                            <option value="11">11月</option>
                            <option value="12" >12月</option>
                             <option value="--" selected="selected">起始月份</option>
                            </select>
               
                            </div>
                            <div style="float: left;">
                            &nbsp;&nbsp;至&nbsp;&nbsp;
                            </div>
                            <div style="float: left;">
                          
                            <select id="endYear" name="endYear" class="form-control">
                            <option value="2013">2013年</option>
                            <option value="2014">2014年</option>
                            <option value="2015">2015年</option>
                            <option value="2016">2016年</option>
                            <option value="2017" >2017年</option>
                            <option value="--" selected="selected">截止年份</option>
                            </select>
                        
                            </div>
                            <div style="float: left;">
                           
                            <select id="endMonth" name="endMonth" class="form-control">
                            <option value="01">1月</option>
                            <option value="02">2月</option>
                            <option value="03">3月</option>
                            <option value="04">4月</option>
                            <option value="05">5月</option>
                            <option value="06">6月</option>
                            <option value="07">7月</option>
                            <option value="08">8月</option>
                            <option value="09">9月</option>
                            <option value="10">10月</option>
                            <option value="11">11月</option>
                            <option value="12" >12月</option>
                            <option value="--" selected="selected">截止年月</option>
                            </select>
                        
                            </div>
                            <div style="float: left;">
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                            <div style="float: left;">
                                      <input id="jsonget"  type="button"  value="确认" onclick = " checkDate()" class = "btn btn-primary">
                                      
                            </div>
                            
                            
                            </div>
                     
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             表格展示
                        </div>
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                 <table  id="table">
                                </table>
                            </div>
                            
                        </div>
              
                    </div>
                    <!--End Advanced Tables -->
                    <!--从这开始是表格下的图表 -->
                <div class="row" >
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
                     <!-- End  柱状图 -->
                </div>
                <div class="row">
                <div class="col-md-12">
                     <!--   饼状图  -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            饼状图
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div id="pieChart" style="width: 800px;height:600px;">
                            </div>
                        </div>
                    </div>
                      <!-- End  饼状图  -->
                </div>
            </div>
            <!-- 两个图表到此结束  -->
                </div>
                </div>
            </div>
                          <footer><p>Copyright© 2007-2017 Ruankosoft Technologies Co., Ltd. 粤B2-20100246 粤ICP备12081495号</footer>
            </div>
                <!-- /. ROW  -->

        </div>

    </div>
    <!-- /. PAGE INNER  -->
         <!-- /. PAGE WRAPPER  -->
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
