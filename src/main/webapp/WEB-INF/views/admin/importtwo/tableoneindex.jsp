<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var userDataGrid;
    var organizationTree;

    $(function() {
        organizationTree = $('#organizationTree').tree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            onClick : function(node) {
                userDataGrid.datagrid('load', {
                    organizationId: node.id
                });
            }
        });

        userDataGrid = $('#userDataGrid').datagrid({
            url : '${path }/user/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'createTime',
	        sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '80',
                title : '登录名',
                field : 'loginName',
                sortable : true
            }, {
                width : '80',
                title : '姓名',
                field : 'name',
                sortable : true
            },{
                width : '80',
                title : '部门ID',
                field : 'organizationId',
                hidden : true
            },{
                width : '80',
                title : '所属部门',
                field : 'organizationName'
            },{
                width : '130',
                title : '创建时间',
                field : 'createTime',
                sortable : true
            },  {
                width : '40',
                title : '性别',
                field : 'sex',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '男';
                    case 1:
                        return '女';
                    }
                }
            }, {
                width : '40',
                title : '年龄',
                field : 'age',
                sortable : true
            },{
                width : '120',
                title : '电话',
                field : 'phone',
                sortable : true
            }, 
            {
                width : '200',
                title : '角色',
                field : 'rolesList'
            }, {
                width : '60',
                title : '用户类型',
                field : 'userType',
                sortable : true,
                formatter : function(value, row, index) {
                    if(value == 0) {
                        return "管理员";
                    }else if(value == 1) {
                        return "用户";
                    }
                    return "未知类型";
                }
            },{
                width : '60',
                title : '状态',
                field : 'status',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '正常';
                    case 1:
                        return '停用';
                    }
                }
            } , {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="/user/edit">
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editUserFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/user/delete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteUserFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#userToolbar'
        });
    });
    
    function addUserFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/user/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function deleteUserFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = userDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/user/delete', {
                    id : id
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        userDataGrid.datagrid('reload');
                    } else {
                        parent.$.messager.alert('错误', result.msg, 'error');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }
    
    function editUserFun(id) {
        if (id == undefined) {
            var rows = userDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/user/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function searchUserFun() {
        userDataGrid.datagrid('load', $.serializeObject($('#searchUserForm')));
    }
    function cleanUserFun() {
        $('#searchUserForm input').val('');
        userDataGrid.datagrid('load', {});
    }
    
    //导入信息 
    function importData(){
    	$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/importtwo/importdata",
			data : {
				
			},
			dataType : "json",
			success : function(data) {
				if(data.code=='-1'){
					alert(data.msg);
				}
				if(data.code=='0'){
					$('#xlsDataNums').html(data.data.xlsDataNums);
					$('#updateSize').html(data.data.updateSize);
					$('#insertSize').html(data.data.insertSize);
					$('#nowTableSize').html(data.data.nowTableSize);
				}
			},
			error : function() {
				alert("网络错误！");
			}
		});
    }
    
    //上传并导入
    function upfilefile(){
    	var files = document.getElementById("uploadFile").files;
		//文件数量
		//alert(files.length);
		
		for(let i=0;i<files.length;i++){
			var formData = new FormData();
			formData.append("file",files[i]);
			formData.append("username","zxm");
			formData.append("password","123456");
			$.ajax({
	            type:"post",
	            url:"importtwo/upfileimport.do",
	            data:formData,
	            xhr:xhrOnProgress(function(e){
	                var percent=e.loaded / e.total;//计算百分比
	                $('#xxx').val(percent);
	                
	                $('#jindu').attr("style","width: "+percent*100+"%");
	                $('#jindu').html(percent*100+"%");
	                console.log(i + '=========' +percent);
	            }),
	            processData : false,// 不处理数据
	            contentType : false,// 不设置内容类型
	            /*beforeSend: function(request) {
	                request.setRequestHeader("filePath", file_path);
	            }, */
	            success:function(data){
	              console.log(data);
	            },
	            error:function(e){
	              
	            }
	        })
		}
		
    }
    
    var xhrOnProgress=function(fun) {
  	  xhrOnProgress.onprogress = fun; //绑定监听
  	 
  	  //使用闭包实现监听绑
  	  return function() {
  	    //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
  	    var xhr = $.ajaxSettings.xhr();
  	    
  	    //判断监听函数是否为函数
  	    if (typeof xhrOnProgress.onprogress !== 'function')
  	      return xhr;
  	    //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
  	    if (xhrOnProgress.onprogress && xhr.upload) {
  	      xhr.upload.onprogress = xhrOnProgress.onprogress;
  	    }
  	    return xhr;
  	  }
  	}
    
    
</script>

<style type="text/css">
	.progress {
	  overflow: hidden;
	  height: 20px;
	  margin-bottom: 20px;
	  margin-top: 20px;
	  margin-left: 3px;
	  background-color: #f7f7f7;
	  background-image: -moz-linear-gradient(top, #f5f5f5, #f9f9f9);
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#f5f5f5), to(#f9f9f9));
	  background-image: -webkit-linear-gradient(top, #f5f5f5, #f9f9f9);
	  background-image: -o-linear-gradient(top, #f5f5f5, #f9f9f9);
	  background-image: linear-gradient(to bottom, #f5f5f5, #f9f9f9);
	  background-repeat: repeat-x;
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff5f5f5', endColorstr='#fff9f9f9', GradientType=0);
	  -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	  -moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
	  -webkit-border-radius: 4px;
	  -moz-border-radius: 4px;
	  border-radius: 4px;
	  width:408px;
	  margin-right:12px;
	}
	
	.progress .bar {
	  width: 0%;
	  height: 100%;
	  color: #ffffff;
	  float: left;
	  font-size: 12px;
	  text-align: center;
	  align-items: center;
	  display: flex;
	  justify-content: center;
	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	  background-color: #0e90d2;
	  background-image: -moz-linear-gradient(top, #149bdf, #0480be);
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#149bdf), to(#0480be));
	  background-image: -webkit-linear-gradient(top, #149bdf, #0480be);
	  background-image: -o-linear-gradient(top, #149bdf, #0480be);
	  background-image: linear-gradient(to bottom, #149bdf, #0480be);
	  background-repeat: repeat-x;
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff149bdf', endColorstr='#ff0480be', GradientType=0);
	  -webkit-box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.15);
	  -moz-box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.15);
	  box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.15);
	  -webkit-box-sizing: border-box;
	  -moz-box-sizing: border-box;
	  box-sizing: border-box;
	  -webkit-transition: width 0.6s ease;
	  -moz-transition: width 0.6s ease;
	  -o-transition: width 0.6s ease;
	  transition: width 0.6s ease;
	}
</style>
<!-- <link href="static/fileupload/progress.css" /> -->

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table>
                <tr>
                    <td style="width: 100px;">导入第一张表:</td>
                    <td>
                    	<input type="file" name="file" multiple id="uploadFile"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="upfilefile();" id="upbutton">上传</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="importData();" id="importDatabut">导入信息</a>
<!--                         <a href="javascript:void(0);" class="easyui-linkbutton" onclick="ceshi();">测试</a> -->
                        <input  id="xxx"/>
                        
                    </td>
                </tr>
                <tr>
                	<td>
                		上传进度
                	</td>
                	<td>
                		<div class="progress">
							<div class="bar" style="width: 0%;" id="jindu">60%</div>
                        </div>
                	</td>
                </tr>
                <tr>
                	<td>当前表一中共有<span style="color: red;">${allsize }</span>条数据</td>
                </tr>
<!--                 导入数据以后就立马把这条信息显示出来 -->
                <div hidden="hidden">
	                <tr>
	                	<td>当前文件中共有<span id="xlsDataNums" style="color: red;">0</span>条数据</td>
	                </tr>
                </div>
<!--                 点击导入以后，再把这条信息显示出来 -->
                <div class="">
	               <tr>
	               	<td>其中<span id="updateSize" style="color: red;">0</span>条重复(已更新)，<span id="insertSize" style="color: red;">0</span>条导入成功，目前库中共<span id="nowTableSize" style="color: red;">0</span>条数据</td>
	               </tr>
                </div>
            </table>
        </form>
    </div>
</div>
<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>