<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    
    
    //导入信息 
    function importData(){
    	
    	if($('#filepathonea').val()==''){
    		alert("请先上传文件");
    		return;
    	}
    	var importwhere = $('#importwhere').val();
    	if(importwhere==0){
    		alert("必须选择导入到哪个表中");
    		return;
    	}
    	$('#importDatabutonea').linkbutton('disable');
    	$('#thisimportnums').html("因为数据量大，会需要较长时间导入，请耐心等待，勿刷新界面，导入成功会有提示");
    	$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/importtwo/importdata",
			data : {
				filepath:$('#filepathonea').val(),
				rowstart:2,
				colsNum:11,
				tableEnd:importwhere
			},
			dataType : "json",
			success : function(data) {
				if(data.code=='-1'){
					alert(data.msg);
				}
				if(data.code=='0'){
					$('#thisimportnums').html("导入成功,本次导入"+data.data.nowTableSize+"条数据");
				}
			},
			error : function() {
				alert("网络错误！");
			}
		});
    }
    
    //上传并导入
    function upfilefile(){
    	var importwhere = $('#importwhere').val();
    	if(importwhere==0){
    		alert("必须选择导入到哪个表中");
    		return;
    	}
    	var files = document.getElementById("uploadFileonea").files;
		//文件数量
		//alert(files.length);
		if(files.length==0){
			alert("请选择文件");
			return;
		}
		var selfile = $('#uploadFileonea').val();
		var fileend = selfile.substring(selfile.length-4,selfile.length);
		//alert(fileend);
		if(fileend=="xlsx"||fileend==".xls"){
		}else{
			alert("只能选择excel文件");
			return;
		}
		//for(let i=0;i<files.length;i++){
			var formData = new FormData();
			formData.append("file",files[0]);
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
	                console.log( '=========' +percent);
	            }),
	            processData : false,// 不处理数据
	            contentType : false,// 不设置内容类型
	            /*beforeSend: function(request) {
	                request.setRequestHeader("filePath", file_path);
	            }, */
	            success:function(data){
	              console.log(data);
	              var jkpbdx = $.parseJSON( data );
	              $('#filepathonea').val(jkpbdx.data);
	            },
	            error:function(e){
	              
	            }
	        })
		//}
		
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
    <div data-options="region:'north',border:false" style="background-color: #fff">
    <input type="hidden" id="filepathonea">
        <form id="searchUserForm">
            <table>
                <tr>
                	<td>
                		请选择要导入到哪个表中
                	</td>
                	<td>
                		<select id="importwhere">
                			<option value="0">请选择</option>
                			<option value="one_a">表一A中</option>
                			<option value="one_b">表一B中</option>
                			<option value="two_a">表二A中</option>
                			<option value="two_b">表二B中</option>
                		</select>
                	</td>
                    <td style="width: 100px;">选择文件:</td>
                    <td>
                    	<input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" multiple id="uploadFileonea"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="upfilefile();" id="upbutton">上传</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="importData();" id="importDatabutonea">导入信息</a>
<!--                         <a href="javascript:void(0);" class="easyui-linkbutton" onclick="ceshi();">测试</a> -->
                        
                    </td>
                    <td>
                    	<span id="thisimportnums"></span>
                    </td>
<!--                     <td> -->
<!--                     	从第几行开始算数据 -->
<!--                     	<select id="rowstart"> -->
<!--                     		<option value="1">1</option> -->
<!--                     		<option value="2" selected="selected">2</option> -->
<!--                     		<option value="3">3</option> -->
<!--                     		<option value="4">4</option> -->
<!--                     		<option value="5">5</option> -->
<!--                     		<option value="6">6</option> -->
<!--                     	</select> -->
<!--                     </td> -->
                </tr>
                <tr>
                	<td>
                		上传进度
                	</td>
                	<td colspan="3">
                		<div class="progress">
							<div class="bar" style="width: 0%;" id="jindu">0%</div>
                        </div>
                	</td>
                </tr>
<!--                 <tr> -->
<%--                 	<td colspan="2">当前表一中共有<span style="color: red;">${allsize }</span>条数据</td> --%>
<!--                 </tr> -->
<!-- <!--                 导入数据以后就立马把这条信息显示出来 --> 
<!--                 <div hidden="hidden"> -->
<!-- 	                <tr> -->
<!-- 	                	<td colspan="2">当前文件中共有<span id="xlsDataNums" style="color: red;">0</span>条数据</td> -->
<!-- 	                </tr> -->
<!--                 </div> -->
<!--                 点击导入以后，再把这条信息显示出来 -->
	               <tr >
<!-- 	               	<td colspan="2">其中<span id="updateSize" style="color: red;">0</span>条重复(已更新)，<span id="insertSize" style="color: red;">0</span>条导入成功，目前库中共<span id="nowTableSize" style="color: red;">0</span>条数据</td> -->
	               		<td colspan="2"></td>
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