<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    
    
    //删除表信息
    function deldata(tableEnd){
    	$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/deltable/deldata",
			data : {
				tableEnd:tableEnd
			},
			dataType : "json",
			success : function(data) {
				if(data.code=='-1'){
					alert(data.msg);
				}
				if(data.code=='0'){
					alert(data.msg);
					$('#'+data.data).html("0");
				}
			},
			error : function() {
				alert("网络错误！");
			}
		});
    }
    
    //对比
    function duibi(){
    	/* $.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/dbexport/dbtablesdata",
			data : {
				sourcetype:$('#sourcetype').val(),
				dbtype:$('#dbtype').val()
			},
			dataType : "json",
			success : function(data) {
				
			},
			error : function() {
				alert("网络错误！");
			}
		}); */
		
		//一和二都必须选择
		var sourceone = $('#sourceone').val();
		if(sourceone=='0'){
			$.messager.alert("操作提示", "请选择数据源一要对比的列");
			return;
		}
		var sourcetwo = $('#sourcetwo').val();
		if(sourcetwo=='0'){
			$.messager.alert("操作提示", "请选择数据源二要对比的列");
			return;
		}
		
 		$('#upbutton').linkbutton('disable');
		$('#wenzi').html("对比数据量大的话需要的时间很长，请勿刷新界面，导出成功界面会弹出下载框");
    	var url = "${pageContext.request.contextPath}/dbexportclean/dbtablesdata";
    	 
        
        var dbtype = $('#dbtype').val();
 
        var form = $("<form></form>").attr("action", url).attr("method", "post");
 
        form.append($("<input></input>").attr("type", "hidden").attr("name", "sourceone").attr("value", sourceone));
        form.append($("<input></input>").attr("type", "hidden").attr("name", "sourcetwo").attr("value", sourcetwo));
        form.append($("<input></input>").attr("type", "hidden").attr("name", "dbtype").attr("value", dbtype));
 
        form.appendTo('body').submit().remove();
    	
    	
    }
    
    function setshuoming(){
    	var selVal = $('#dbtype').val();
    	//<option value="1">获取前者在后者中没有的</option>
		//<option value="2">获取后者在前者中没有的</option>
    	$.messager.alert("操作提示", "说明已改变，请看变色部分文字");
    	
    	if(selVal=='1'){
    		$('#shuoming').html("比如：数据源一有A,B,C。数据源二有B,C,D.导出的就是A，因为A在数据源二中没有");
    		$('#shuoming').attr("style","color:red");
    	}else{
    		$('#shuoming').html("比如：数据源一有A,B,C。数据源二有B,C,D.导出的就是D，因为D在数据源一中没有");
    		$('#shuoming').attr("style","color:blue");
    	}
    }
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table  >
            	<tr>
            		<td>
            			请选择数据源一要对比的列
            		</td>
            		<td>
            			<select id="sourceone">
            				<option value="0">请选择</option>
            				<c:forEach var="sourceone" items="${sourceone}">
					 			<c:if test="${sourceone != null }">
					 				<option value="${sourceone.headerCol }">${sourceone.headerTitle }</option>
					 			</c:if>
					 		</c:forEach>
            			</select>
            		</td>
            		<td>
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		</td>
            		<td>
            			请选择数据源二要对比的列
            		</td>
            		<td>
            			<select id="sourcetwo">
            				<option value="0">请选择</option>
            				<c:forEach var="sourcetwo" items="${sourcetwo}">
					 			<c:if test="${sourcetwo != null }">
					 				<option value="${sourcetwo.headerCol }">${sourcetwo.headerTitle }</option>
					 			</c:if>
					 		</c:forEach>
            			</select>
            		</td>
            		<td>
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		</td>
            		<td>
            			选择对比方式
            		</td>
            		<td>
            			<select id="dbtype" onchange="setshuoming()">
            				<option value="1">获取前者在后者中没有的</option>
            				<option value="2">获取后者在前者中没有的</option>
            			</select>
            		</td>
            		<td>
            			<span id="shuoming" style="color: red;">比如：数据源一有A,B,C。数据源二有B,C,D.导出的就是A，因为A在数据源二中没有</span>
            		</td>
            		<td>
            		
            		
            		
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="duibi();" id="upbutton">对比并导出
            			</a>
            			<span id="wenzi"></span>
            		</td>
            	</tr>
            </table>
        </form>
    </div>
    <div style="margin-top: 30px;">
    	<table id="dg1" class="easyui-datagrid" title="数据源一，只展示前十条已导入示例" style="width:1000px;height:auto;"
				data-options="singleSelect:true,collapsible:true">
			<thead>
				<tr>
					<c:forEach var="sourceone" items="${sourceone}">
			 			<c:if test="${sourceone != null }">
			 				<th field="${sourceone.headerTitle }" width="150px;">${sourceone.headerTitle }</th>
			 			</c:if>
			 		</c:forEach>
				</tr>
			</thead>
			<tbody>
					${sourOneStr }
			</tbody>
		</table>
    </div>
    <div>
    	<span style="color: red;">------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</span>
    </div>
    <div>
    	<table id="dg2" class="easyui-datagrid" title="数据源二，只展示前十条已导入示例" style="width:1000px;height:auto;"
				data-options="singleSelect:true,collapsible:true">
			<thead>
				<tr>
					<c:forEach var="sourcetwo" items="${sourcetwo}">
			 			<c:if test="${sourcetwo != null }">
			 				<th field="${sourcetwo.headerTitle }" width="150px;">${sourcetwo.headerTitle }</th>
			 			</c:if>
			 		</c:forEach>
				</tr>
			</thead>
			<tbody>
					${sourTwoStr }
			</tbody>
		</table>
    </div>
</div>
<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>