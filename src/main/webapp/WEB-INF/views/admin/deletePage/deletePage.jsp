<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    
    
    
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
    
</script>

<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table>
            	<tr>
            		<td>
            			表一A中有<span id="one_a">${one_a_nums }</span>条数据
            		</td>
            		<td>
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="deldata('one_a');" id="upbutton">删除表一A中数据   </a>
            		</td>
            		<td>
            			****
            		</td>
            		<td>
            			表二B中有<span id="one_b">${one_b_nums }</span>条数据
            		</td>
            		<td>
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="deldata('one_b');" id="upbutton">删除表二B中数据   </a>
            		</td>
            		<td>
            			****
            		</td>
            		<td>
            			表一A中有<span id="two_a">${two_a_nums }</span>条数据
            		</td>
            		<td>
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="deldata('two_a');" id="upbutton">删除表一A中数据   </a>
            		</td>
            		<td>
            			****
            		</td>
            		<td>
            			表二B中有<span id="two_b">${two_b_nums }</span>条数据
            		</td>
            		<td>
            			<a href="javascript:void(0);" class="easyui-linkbutton"  onclick="deldata('two_b');" id="upbutton">删除表二B中数据</a>
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