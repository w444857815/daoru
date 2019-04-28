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
    
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

数据源一和数据源二必须都有数据才可以对比，请先导入,完成后刷新此页


<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>