<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	
	<script type="text/javascript" src="${staticPath }/shiro/static/fileupload/jquery-1.2.1.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/shiro/static/fileupload/jquery.form.js" charset="utf-8"></script>
	<script type="text/javascript">
		function shc(){
			var hideForm = $('#edui_form_j1xc1nj4'); 
			 var options = { 
			  dataType : "json", 
			  /*data: {'file': $("input[type=file]").val(), "username": '123', password: "123"},*/
			  beforeSubmit : function() { 
			  //alert("正在上传"); 
			  }, 
			  success : function(result) { 
			  	alert(JSON.stringify(result)); 
			   
			  }, 
			  error : function(result) { 
			    
			  } 
			 }; 
			 hideForm.ajaxSubmit(options); 
		}

	
	</script>
  </head>
  
  <body>
<!-- 	<form name="contractForm" id="contractForm" action="/shiro/upload" method="post" enctype ="multipart/form-data"> -->
<!-- 		<input type="file" name="filexxx"/> -->
<!-- 		<input type="submit" value="上传">  -->
<!-- 	</form> -->

	<form id="edui_form_j1xc1nj4" method="POST" enctype="multipart/form-data" action="/shiro/ueditor?action=uploadfile" >
		<input id="edui_input_j1xc1nj4" accept="file/*" name="upfile" 
		
		type="file">
		<input type="button" onclick="shc();" value="上传"> 
	</form>


  </body>
</html>
