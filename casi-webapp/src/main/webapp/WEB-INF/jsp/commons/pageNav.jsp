<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@include file="./include/jspheader.jsp" %>
<div id="fy">
		<s:if test="query == null">
			<s:bean name="com.ali.luna.crm.dataobject.CRMBaseDO" id="query"> 
				<s:param name="toPage" value="0"/> 
				<s:param name="perPageSize" value="20"/>
				<s:param name="totalItem" value="0"/> 
			</s:bean>
		</s:if>
		<s:else>
			<input id="toPage" name="query.toPage" type="hidden"  value="<s:property value="query.toPage" />" />
		    <input id="totalItem" name="query.totalItem" type="hidden" value="<s:property value="query.totalItem" />" />
		</s:else>

		<s:set name="next" value="query.toPage + 1" />
        <s:set name="prev" value="query.toPage - 1" />
        
		共有记录：${query.totalItem}条 &nbsp;
		<s:if test="query == null || query.toPage <= 1">
			<img src="images/fy_first2.gif" width="16" height="16" align="absmiddle" alt="首页" /><font color="#999999">首页</font>
			<img src="images/fy_back2.gif" width="16" height="16" align="absmiddle" alt="上一页" /><font color="#999999">上一页</font>
		</s:if>
		<s:else>
			<a href="#" onClick="changePage(1)"><img src="images/fy_first.gif" width="16" height="16" align="absmiddle" alt="首页" />首页</a>
			<a href="#" onClick="changePage('<s:property value="#prev" />')"><img src="images/fy_back.gif" width="16" height="16" align="absmiddle" alt="上一页" />上一页</a>
		</s:else>
		
		<img src="images/sx2_b.gif" width="2" height="13" align="absmiddle" />
		Page<input type="text" id="inputToPage"  size="1" value="${query.toPage}"  />of ${query.totalPage}
		<img src="images/sx2_b.gif" width="2" height="13" align="absmiddle" />
		
		<s:if test="query == null || query.toPage == query.totalPage">
        	<font color="#999999">下一页</font><img src="images/fy_forward2.gif" width="16" height="16" align="absmiddle" alt="下一页" />
        	<font color="#999999">末页</font><img src="images/fy_last2.gif" width="16" height="16" align="absmiddle" alt="末页" />
        </s:if>
        <s:else>
        	<a href="#" onClick="changePage('<s:property value="#next" />')">下一页<img src="images/fy_forward.gif" width="16" height="16" align="absmiddle" alt="下一页" /></a>
        	<a href="#" onClick="changePage('<s:property value="query.totalPage" />')">末页<img src="images/fy_last.gif" width="16" height="16" align="absmiddle" alt="末页" /></a> &nbsp;
       	</s:else>
		
		每页显示<select id="sel_pn_rownum" name="query.perPageSize" >
				
				<s:if test="query.perPageSize == 10">
					<option value="10" selected="selected">10 </option>	
				</s:if>
				<s:else>
					<option value="10">10 </option>
				</s:else>
				
				<s:if test="query.perPageSize == 20">
					<option value="20" selected="selected">20 </option>	
				</s:if>
				<s:else>
					<option value="20">20 </option>
				</s:else>
				
				<s:if test="query.perPageSize == 50">
					<option value="50" selected="selected">50 </option>	
				</s:if>
				<s:else>
					<option value="50">50 </option>
				</s:else>
				
				<s:if test="query.perPageSize == 100">
					<option value="100" selected="selected">100</option>	
				</s:if>
				<s:else>
					<option value="100">100</option>
				</s:else>
				</select>行
		<img src="images/sx2_b.gif" width="2" height="13" align="absmiddle" />
		
</div>

<script language="javascript">
$(document).ready(function(){
  $('#sel_pn_rownum').change(onchangePageBysel);
  $('#inputToPage').keydown(onInputPageNum);
  //$('#inputToPage').blur(onInputPageNumBlur);
});

   function changePage(page) {
        if (checknumber(page) == false || page<=0) {
            page = 1;
		}
        $("#toPage").val(page);
        doSearch();
        //$('#toPage')[0].form.submit();
        //查找topage所在的form中提交按钮，模拟提交
        //$($('#toPage')[0].form).find('input[type=submit]').click();
    }
    
    function onchangePageBysel(){
        $('#perPageSize').val($('#sel_pn_rownum').val());
        changePage(1);
    }

	function checknumber(str)
	{
		var reg = /^[0-9]*$/;
		return (reg.test(str));
	}
    function onInputPageNum(e){
		if(e.keyCode == 13) {
			changePage($(this).val());
		}
	}
	function onInputPageNumBlur(){
	    changePage($('#inputToPage').val());
	}

	function initPageNum() {
        $("#toPage").val(1);
    }

</script>