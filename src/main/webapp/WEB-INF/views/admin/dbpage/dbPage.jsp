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
 		$('#upbutton').linkbutton('disable');
		$('#wenzi').html("对比数据量大的话需要的时间很长，请勿刷新界面，导出成功界面会弹出下载框");
    	var url = "${pageContext.request.contextPath}/dbexport/dbtablesdata";
    	 
        var sourcetype = $('#sourcetype').val();
        var dbtype = $('#dbtype').val();
 
        var form = $("<form></form>").attr("action", url).attr("method", "post");
 
        form.append($("<input></input>").attr("type", "hidden").attr("name", "sourcetype").attr("value", sourcetype));
        form.append($("<input></input>").attr("type", "hidden").attr("name", "dbtype").attr("value", dbtype));
 
        form.appendTo('body').submit().remove();
    	
    	
    }
    
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table>
            	<tr>
            		<td>
            			选择对比数据源
            		</td>
            		<td>
            			<select id="sourcetype">
            				<option value="one_a@one_b">表一A对比表一B</option>
            				<option value="one_a@two_a">表一A对比表二A</option>
            				<option value="one_a@two_b">表一A对比表二B</option>
<!--             				其实下面三个也可以，只是就跟上面的重复了 -->
            				<option value="two_a@one_a">表二A对比表一A</option>
            				<option value="two_a@one_b">表二A对比表一B</option>
            				<option value="two_a@two_b">表二A对比表二B</option>
            			</select>
            		</td>
            		<td>
            			选择对比方式
            		</td>
            		<td>
            			<select id="dbtype">
            				<option value="1">前者在后者中没有的</option>
            				<option value="2">后者在前者中没有的</option>
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