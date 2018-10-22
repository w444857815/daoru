<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var drKfdMxDataGrid;
    $(function() {
        drKfdMxDataGrid = $('#drKfdMxDataGrid').datagrid({
        url : '${path}/drKfdMx/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ 
        
                	                		        {
	            width : '50',
	            title : '计数',
	            field : 'jishu',
	            sortable : true
	        },
	                        		        {
	            width : '90',
	            title : '时间',
	            field : 'jlTime',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '品名',
	            field : 'goodsName',
	            sortable : true
	        },
	                        		        {
	            width : '50',
	            title : '规格',
	            field : 'goodsGuige',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '单位',
	            field : 'goodsUnit',
	            sortable : true
	        },
	                        		        {
	            width : '50',
	            title : '数量',
	            field : 'goodsNum',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '店名',
	            field : 'shopName',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '放货地址',
	            field : 'shopAddress',
	            sortable : true
	        },
	                        		        {
	            width : '50',
	            title : '联系人',
	            field : 'shopLxr',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '联系电话',
	            field : 'shopLxrmobile',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '是否付款',
	            field : 'ifPay',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '单据是否收回',
	            field : 'ifBillsSh',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '店铺属性',
	            field : 'shopProperty',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '是否撤店',
	            field : 'shopDisappear',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '责任人',
	            field : 'shopZrr',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '备注',
	            field : 'remark',
	            sortable : true
	        },
	                        		        {
	            width : '60',
	            title : '赠品发放（打火机）',
	            field : 'zpff',
	            sortable : true
	        },
	                        {
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
        },  {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/drKfdMx/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="drKfdMx-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="drKfdMxEditFun(\'{0}\');" >编辑</a>', row.id);
                </shiro:hasPermission>
                <shiro:hasPermission name="/drKfdMx/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="drKfdMx-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="drKfdMxDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.drKfdMx-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.drKfdMx-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#drKfdMxToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function drKfdMxAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/drKfdMx/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = drKfdMxDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#drKfdMxAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function drKfdMxEditFun(id) {
    if (id == undefined) {
        var rows = drKfdMxDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        drKfdMxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path}/drKfdMx/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = drKfdMxDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#drKfdMxEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function drKfdMxDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = drKfdMxDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         drKfdMxDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/drKfdMx/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     drKfdMxDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function drKfdMxCleanFun() {
    $('#drKfdMxSearchForm input').val('');
    drKfdMxDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function drKfdMxSearchFun() {
     drKfdMxDataGrid.datagrid('load', $.serializeObject($('#drKfdMxSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="drKfdMxSearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="goodsUnit" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="drKfdMxSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="drKfdMxCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="drKfdMxDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="drKfdMxToolbar" style="display: none;">
    <shiro:hasPermission name="/drKfdMx/add">
        <a onclick="drKfdMxAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>