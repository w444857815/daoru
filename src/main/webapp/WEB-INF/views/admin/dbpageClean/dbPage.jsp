<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
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
            			<select id="dbtype">
            				<option value="1">获取前者在后者中没有的</option>
            				<option value="2">获取后者在前者中没有的</option>
            			</select>
            		</td>
            		<td>
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="duibi();" id="upbutton">对比并导出</a>
            			<span id="wenzi"></span>
            		</td>
            	</tr>
            </table>
        </form>
    </div>
</div>
<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>