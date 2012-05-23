<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@include file="./include/jspheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Simba辛巴平台</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body class="bdt">
<script type="text/javascript">

$(document).ready(function() {
	/*$('#role').data("selectWidth", $('#role').css("width")).css("width", "auto");
	$('#role').change(function(){
		$('#roleid').val($('#role').val());
		$('#formchangerole').submit();
	});*/
	
	
	$('#J_System_Tab a').each(function(i){
		$(this).bind('click',function(event){
			event.preventDefault();
			$(this).parent('li').siblings().removeClass('cur');
			$(this).parent('li').addClass('cur');
		});
	});
	
	$('#J_Move li').each(function(i){
		$(this).bind('click',function(event){

			var tabsWidth=0;
			var windowWidth=$(window).width();
			var objTabs=$('#J_System_Tab li:not(".hidden")');


			for(j=0;j<objTabs.size();j++){
				tabsWidth+=objTabs.eq(j).width();
			}

			var headTab = $('#J_System_Tab li:not(".hidden")').eq(0);

			if(i==0){
				$(headTab).prev().removeClass('hidden');
			}else{
				if(tabsWidth>=windowWidth-70){//70是估计值
					$(headTab).addClass('hidden');
				}
			}		
		});
	});

});

function switchSysMenu(sysTabId){
	$('li[name=mt]',window.parent.frames["leftFrame"].document).each(
		function(i){
			if(this.id!="sys"+sysTabId){
                $(this).css("display","none");
			}else{
                $(this).css("display","block");
			}

		}
	);
}
</script>
<!-- 顶部 begin -->
<div id="header">
	<div class="logo"><a href="#"><img src="images/logo.png" width="116" height="48" alt="Simba辛巴平台v1.0" /></a></div>
	<div class="account">
		<p>您好，${oper.operName}！ &nbsp;
			<!--select id="role">
				<%--<s:iterator value="roles">--%>
					<%--<s:if test="oper.curRoleID == roleID">--%>
						<%--<option value='<s:property value="roleID"/>' selected="selected"><s:property value="roleName"/></option>--%>
					<%--</s:if>--%>
					<%--<s:else>--%>
						<%--<option value='<s:property value="roleID"/>' ><s:property value="roleName"/></option>--%>
					<%--</s:else>--%>
				<%--</s:iterator>--%>
			</select--> 
			<%--<a href="logoff.action" target="_parent">个人设置</a>&nbsp;--%>
			[<a href="logoff.action" target="_parent">退出</a>]
		</p>
		<p>
			友情链接：[<a href="http://bugfree.corp.taobao.com/index.php" target="_blank">BugFree</a>]
			[<a href="http://anteater.corp.taobao.com:9999/" target="_blank">食蚁兽</a>]
		</p>
	</div>
	<div class="page-tab" id="J_Page_Tab">
		<ul id="J_System_Tab">
             <!--Hello Master 横波-->
            <s:iterator value="menu" status="sys">
                   <li onclick='switchSysMenu(<s:property value="id" />)' <s:if test="#sys.index == 0">class="cur"</s:if>><a title="" target="_parent" href="http://www.alimama.com"><span><s:property value="name" escape="true" /></span></a></li>
            </s:iterator>
		</ul>
	</div>
	<div class="move">
		<ul id="J_Move">
			<li id="J_Pre" class="pre">前移</li>
			<li id="J_Next" class="next">后移</li>
		</ul>
	</div>
	<div class="c"></div>
</div>
<!-- 顶部 end -->
<form id="formchangerole" action="change.action" method="post">
	<input id="roleid" name="oper.curRoleID" type="hidden" />
</form>
<script>
//var flag = '${reloadFrameFlag}';
var flag = '1';
if(flag == '1'){
window.parent.leftFrame.document.location.reload();
window.parent.ifr.document.location="welcome.action";
}
</script>
</body>
</html>
