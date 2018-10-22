<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#drKfdMxEditForm').form({
            url : '${path}/drKfdMx/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#drKfdMxEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#editStatus").val('${drKfdMx.status}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="drKfdMxEditForm" method="post">
            <table class="grid">
                
                					<input name="id" value="${drKfdMx.id}" type="hidden" />												
											
						<tr>
		                    <td>计数</td>
		                    <td><input name="jishu" value="${drKfdMx.jishu}" type="text" id="jishu" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
							<td>时间</td> 
							<td><input name="jlTime" value="<fmt:formatDate value='${drKfdMx.jlTime}' pattern='yyyy-MM-dd HH:mm:ss' />"  id="jlTime" type="text" id="jlTime" onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})"/></td>
						</tr> 
										
					
					
																	
											
						<tr>
		                    <td>品名</td>
		                    <td><input name="goodsName" value="${drKfdMx.goodsName}" type="text" id="goodsName" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>规格</td>
		                    <td><input name="goodsGuige" value="${drKfdMx.goodsGuige}" type="text" id="goodsGuige" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>单位</td>
		                    <td><input name="goodsUnit" value="${drKfdMx.goodsUnit}" type="text" id="goodsUnit" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>数量</td>
		                    <td><input name="goodsNum" value="${drKfdMx.goodsNum}" type="text" id="goodsNum" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>店名</td>
		                    <td><input name="shopName" value="${drKfdMx.shopName}" type="text" id="shopName" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>放货地址</td>
		                    <td><input name="shopAddress" value="${drKfdMx.shopAddress}" type="text" id="shopAddress" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>联系人</td>
		                    <td><input name="shopLxr" value="${drKfdMx.shopLxr}" type="text" id="shopLxr" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>联系电话</td>
		                    <td><input name="shopLxrmobile" value="${drKfdMx.shopLxrmobile}" type="text" id="shopLxrmobile" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>是否付款</td>
		                    <td><input name="ifPay" value="${drKfdMx.ifPay}" type="text" id="ifPay" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>单据是否收回</td>
		                    <td><input name="ifBillsSh" value="${drKfdMx.ifBillsSh}" type="text" id="ifBillsSh" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>店铺属性</td>
		                    <td><input name="shopProperty" value="${drKfdMx.shopProperty}" type="text" id="shopProperty" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>是否撤店</td>
		                    <td><input name="shopDisappear" value="${drKfdMx.shopDisappear}" type="text" id="shopDisappear" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>责任人</td>
		                    <td><input name="shopZrr" value="${drKfdMx.shopZrr}" type="text" id="shopZrr" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                    <td>备注</td>
		                    <td><input name="remark" value="${drKfdMx.remark}" type="text" id="remark" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
		                </tr> 
						
						
										
					
					
																	
											
						<tr>
		                    <td>赠品发放（打火机）</td>
		                    <td><input name="zpff" value="${drKfdMx.zpff}" type="text" id="zpff" placeholder="请输入内容" class="easyui-validatebox span2" data-options="required:true" value=""></td>
						</tr> 
										
						
						
										
					
					
								                
                
                
                
            </table>
        </form>
    </div>
</div>