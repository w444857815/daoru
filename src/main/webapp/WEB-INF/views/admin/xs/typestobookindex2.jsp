<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/easyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript">

$('#orChecked').change(function(){
	 if($(this).is(':checked')){
	   var box = $('#box').children(':checkbox');
	   if(box.length==box.filter(':not(:checked)').length){  // 复选框长度和没选中的个数一样 -> 全选 , .not(':checked').length 也可以。
	   	$('#box').children(':checkbox').prop('checked',true);
	   }
	 }
	 
	 else{   // 如果有选中个数，-> 反选 
	   $('#box').children(':checkbox').each(function(){   
	    	$(this).prop('checked',$(this).is(':checked')?false:true);
	   });
	 }
	});

//反选
$('#invertChecked').change(function(){
 if($(this).is(':checked')){
   $('#box').children(':checkbox').each(function(){
    $(this).prop('checked',$(this).is(':checked')?false:true);
   });
 }
});

//全选
$('#allChecked').change(function(){
   $('#box').children(':checkbox').prop('checked',$(this).is(':checked')?true:false);
});


function getBooks(){
	var selIds = "";
	$('#box').children(':checkbox').each(function(){   
		if($(this).is(':checked')){
			selIds += $(this).val()+",";
		}
   });
	if(selIds==""){
		alert("请选择要爬取的目录");
		return;
	}
	
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/xstypecon/getbookslist",
		data : {
			typsIds:selIds
		},
		dataType : "json",
		success : function(data) {
			if(data.code=='-1'){
				alert(data.msg);
			}
			if(data.code=='0'){
				alert(data.msg);
			}
		},
		error : function() {
			alert("网络错误！");
		}
	});
	
	//alert(selIds);
}


function shaixuan(){
	var status = $('#status').val();
	
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/xstypecon/gettypeslist",
		data : {
			status:status
		},
		dataType : "json",
		success : function(data) {
			if(data.code=='-1'){
				alert(data.msg);
			}
			if(data.code=='0'){
				$('#box').empty();
				
				
				var htmlStr = '';
				
				$.each(data.data, function (n, value) {
					var status = value.status;
					if(status==1){
						status = ' ❤❤ 已完成';
					}else{
						status = ' ❤❤ 未完成';
						
					}
					htmlStr += ''+value.typeName+
					status+
					'  <br>';


				});
				$('#box').html(htmlStr);
				
			}
		},
		error : function() {
			alert("网络错误！");
		}
	});
	
}
    function tiaozhuan(typeId){
    	location.href="xsbooks/bookslistpage?typeId=4";
    }
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="background-color: #fff">
		  
<!-- 		  <select onchange="shaixuan()" id="status"> -->
<!-- 		  	<option selected="selected" value="">显示全部</option> -->
<!-- 		  	<option value="1">显示已完成</option> -->
<!-- 		  	<option value="0">显示未完成</option> -->
<!-- 		  </select> -->
		  
		    		
    		<div id="box">
		    	<c:forEach var="typeList" items="${typeList}">
		 			<c:if test="${typeList != null }">
		 				<a href="${pageContext.request.contextPath}/xsbooks/bookslistpage?typeId=${typeList.id }" >${typeList.typeName }</a> ❤❤  <c:if test="${typeList.status==1 }">已完成</c:if> <c:if test="${typeList.status==0 }">未完成</c:if> <br>
		 			</c:if>
		 		</c:forEach>
	 		</div>
	 		
	 		<br>
	 		
	 		<input type="button" value="爬取所选数据" onclick="getBooks()">
    </div>
</div>
