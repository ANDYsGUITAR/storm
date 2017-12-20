<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <base href="<%=basePath%>">
   <script src="assets/js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>  
 
<script type="text/javascript" language="javascript">  
    function get() {  
        //注册单击事件  
             
                $.ajax({  
                    type: "POST",  
                    contentType: "application/json",  
                    url: "test/JsonTest",  
                    data: "{}",  
                    dataType: 'json',  
                    success: function (result) {  
                        //将返回数据添加到页面表格中  
                        $("#index1").html(result[0].id);  
                        $("#tdUserId1").html(result[0].user_id);  
                        $("#index2").html(result[1].id);  
                        $("#tdUserId2").html(result[1].user_id);  
                    }  
                });  
    }
    
    function post(){
    	//第一步：定义json格式数据
    	var postData = {
    	        "param1" : "param1",
    	        "areaId" : 2,
    	        "deleteId" : 3
    	  };
    	/**ajax的type,url,dataType,contentType,data属性*/
    	$.ajax({
    	        async : false,
    	        cache : false,
    	        type : 'POST',
    	        url : 'test/delete',
    	        dataType : "json",
    	        contentType : 'application/json', //第二步：定义格式
    	        data : JSON.stringify(postData), //第二步；把json转为String传递给后台
    	        error : function() {
    	                alert('请求fail');
    	        },
    	        success : function(data) {
    	            alert(data);
    	        }
    	 });

    }
    
    function post2(){
    	var startYear=$("#startYear").val();
    	var postData= { //（2）传递参数到后台，注意后台接收方式 
                "param1":startYear,
                "areaId":2,
                "deleteId":3};
       /**重点：ajax的type,url,dataType,data属性*/
        $.ajax({
                async : false,
                cache : false,
                type : 'POST',
                url : 'test/delete',
                dataType : "json",
                data : postData,            
                error : function() {
                    alert('请求失败 ');
                },
                success : function(data) {
                    alert("success");
                    alert(data.param1);
                }

            });

    }
    

    
      
    </script> 

</head>  
<body>  
    <table>  
    <tr>  
        <th>UserIndex</th>  
        <th>UserID</th>  
    </tr>  
    <tr>  
        <td id="index1"></td>  
        <td id="index2"></td>  
    </tr>  
    <tr>  
        <td id="tdUserId1"></td>  
        <td id="tdUserId2"></td>  
    </tr>  
    <tr>  
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
                            </div>
        <td><input type="button" value="查询" id="btTest" onclick="post2()"/></td>  
    </tr>  
</table> 
</body>  
</html> 