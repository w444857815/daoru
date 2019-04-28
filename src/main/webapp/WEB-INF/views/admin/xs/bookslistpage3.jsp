<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<script type="text/javascript" src="${staticPath }/static/easyui/jquery.min.js" charset="utf-8"></script>

<script type="text/javascript">


function quanxuan(){
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
}

function fanxuan(){
	if($(this).is(':checked')){
		   $('#box').children(':checkbox').each(function(){
		    $(this).prop('checked',$(this).is(':checked')?false:true);
		   });
	}
}

//选中前多少个
function muliseled(num){
	var box = $('#box').children(':checkbox');
	var initNum=0;
    
	   	$('#box').children(':checkbox').each(function(){   
   			if(initNum==num){
   				return false;
   			}
   			$(this).prop('checked',$(this).is(':checked')?false:true);
	   		initNum+=1;
	   });
   
}

//全选
$('#allChecked').change(function(){
   $('#box').children(':checkbox').prop('checked',$(this).is(':checked')?true:false);
});




function shaixuan(){
	var status = $('#status').val();
	
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/xsbooks/getbookslistdata",
		data : {
			status:status,
			typeId:$('#typeId').val()
		},
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code=='-1'){
				alert(data.msg);
			}
			if(data.code=='0'){
				$('#box').empty();
				
				
				var htmlStr = '';
				
				$.each(data.data, function (n, value) {
					var status = value.getStatus;
					if(status==1){
						status = ' ❤❤ 已完成';
					}else{
						status = ' ❤❤ 未完成';
						
					}
					htmlStr += '<input type="checkbox" value="'+value.id+'">'+value.title+
					status+
					'  <br>';


				});
				$('#box').html(htmlStr);
				$('#datasize').html(data.data.length);
			}
		},
		error : function() {
			alert("网络错误！");
		}
	});
	
}
  
  
function getBookDic(){
	var selIds = "";
	$('#box').children(':checkbox').each(function(){   
		if($(this).is(':checked')){
			selIds += $(this).val()+",";
		}
   });
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/xsbooks/getbooksdiclist",
		data : {
			bookIds:selIds
		},
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code=='-1'){
				//alert(data.msg);
				
				
				//如果错误的话，把错误打到日志里，然后继续执行方法，继续往下走
 				$('#logstr').append(data.msg);
				//先刷新一下，排除已经好了的
 				shaixuan();
// 				//然后选中前20条
 				muliseled(20);
// 				//然后开始获取
 				getBookDic();
			}
			if(data.code=='0'){
				//alert(data.msg);
				
				
 				$('#logstr').append(data.msg);
				//先刷新一下，排除已经好了的
 				shaixuan();
// 				//然后选中前20条
 				muliseled(20);
// 				//然后开始获取
 				getBookDic();
			}
		},
		error : function() {
			alert("网络错误！");
		}
	});
}
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="background-color: #fff">
    <input type="hidden" value="${typeId }" id="typeId">
    
    		<input type="checkbox" id="allChecked" onchange="quanxuan();">全选
    		<input type="checkbox" id="allChecked" onchange="muliseled(10);">选前10个
    		<input type="checkbox" id="allChecked" onchange="muliseled(20);">选前20个
    		
<!-- 		  <input type="checkbox" id="invertChecked" onchange="fanxuan();">反选 -->
<!-- 		  <input type="checkbox" id="orChecked">全选/反选/全不选 -->
		  
		  <select onchange="shaixuan()" id="status">
		  	<option value="">显示全部</option>
		  	<option value="1">显示已完成</option>
		  	<option selected="selected" value="0">显示未完成</option>
		  </select>
		  
		  <input type="button" value="刷新" onclick="shaixuan()">
		  
		    共有<span id="datasize">${bookList.size()}</span>条 ❤ 
		    <input type="button" value="开始获取" onclick="getBookDic();"><br>
		    日志开始:<span id="logstr"></span>
		    
    		<div id="box">
		    	<c:forEach var="typeList" items="${bookList}">
		 			<c:if test="${typeList != null }">
		 				<input type="checkbox" value="${typeList.id }">${typeList.title } ❤❤  <c:if test="${typeList.getStatus==1 }">已完成</c:if> <c:if test="${typeList.getStatus==0 }">未完成</c:if> <br>
		 			</c:if>
		 		</c:forEach>
	 		</div>
	 		
	 		<br>
	 		
    </div>
</div>
<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>