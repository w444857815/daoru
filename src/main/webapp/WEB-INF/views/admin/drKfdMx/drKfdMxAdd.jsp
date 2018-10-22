<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#drKfdMxAddForm').form({
			url : '${path}/drKfdMx/add',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					var form = $('#drKfdMxAddForm');
					parent.$.messager.alert('错误', eval(result.msg), 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden; padding: 3px;">
		<form id="drKfdMxAddForm" method="post">
			<table class="grid">
				<tr>
					<td>计数</td>
					<td><input name="jishu" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>时间</td>
					<td><input name="jlTime" id="jlTime" type="text" id="d233"
						onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" /></td>
				</tr>				<tr>
					<td>品名</td>
					<td><input name="goodsName" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>规格</td>
					<td><input name="goodsGuige" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>

				<tr>
					<td>单位</td>
					<td><input name="goodsUnit" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>数量</td>
					<td><input name="goodsNum" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>

				<tr>
					<td>店名</td>
					<td><input name="shopName" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>放货地址</td>
					<td><input name="shopAddress" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>

				<tr>
					<td>联系人</td>
					<td><input name="shopLxr" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>联系电话</td>
					<td><input name="shopLxrmobile" type="text"
						placeholder="请输入内容" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>

				<tr>
					<td>是否付款</td>
					<td><input name="ifPay" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>单据是否收回</td>
					<td><input name="ifBillsSh" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>

				<tr>
					<td>店铺属性</td>
					<td><input name="shopProperty" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>是否撤店</td>
					<td><input name="shopDisappear" type="text"
						placeholder="请输入内容" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>

				<tr>
					<td>责任人</td>
					<td><input name="shopZrr" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
					<td>备注</td>
					<td><input name="remark" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>

				<tr>
					<td>赠品发放（打火机）</td>
					<td><input name="zpff" type="text" placeholder="请输入内容"
						class="easyui-validatebox span2" data-options="required:true"
						value=""></td>
				</tr>



			</table>
		</form>
	</div>
</div>