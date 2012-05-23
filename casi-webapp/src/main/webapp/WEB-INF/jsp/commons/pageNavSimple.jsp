<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@include file="./include/jspheader.jsp" %>

<div id="fy" >
		<div class=page-bottom style="float:right;">
		<s:if test="!query  || (query.returnCount==0 && query.toPage==0)">
			<img src="images/fy_first2.gif" width="16" height="16" align="absmiddle" alt="首页" /><font color="#999999">首页</font>
			<img src="images/fy_back2.gif" width="16" height="16" align="absmiddle" alt="上一页" /><font color="#999999">上一页</font>
		    <img src="images/fy_forward2.gif" width="16" height="16" align="absmiddle" alt="下一页" /><font color="#999999">下一页</font>&nbsp;&nbsp;
			每页显示<select id="sel_pn_rownum" name="query.perPageSize" >
			<option value="10" selected="selected">10 </option>行
			</select>
		</s:if> 
		<s:else>
			<s:set name="next" value="query.toPage + 1" />
            <s:set name="prev" value="query.toPage - 1" />
          	第&nbsp;${query.toPage}&nbsp;页&nbsp;&nbsp;
			<s:if test="query.toPage <= 1">
				<img src="images/fy_first2.gif" width="16" height="16" align="absmiddle" alt="首页" /><font color="#999999">首页</font>
			    <img src="images/fy_back2.gif" width="16" height="16" align="absmiddle" alt="上一页" /><font color="#999999">上一页</font>
        	</s:if>
        	<s:else>
        		<a class="page-prev" href="#"  onclick="changePage('1');return false;">
				    <img src="images/fy_first.gif" width="16" height="16" align="absmiddle" alt="首页" />首页
				</a>
        	    <a class="page-prev" href="#"  onclick="changePage('<s:property value="#prev" />');return false;">
				    <img src="images/fy_back.gif" width="16" height="16" align="absmiddle" alt="上一页" />上一页
				</a>
            </s:else>
            
            <s:if test="query.returnCount == 0 || query.returnCount < query.perPageSize">
        		 <img src="images/fy_forward2.gif" width="16" height="16" align="absmiddle" alt="下一页" /><font color="#999999">下一页</font>
        	</s:if>
        	<s:else>
        		<a class="page-next" href="#" onclick="changePage('<s:property value="#next" />');return false;">
				     <img src="images/fy_forward.gif" width="16" height="16" align="absmiddle" alt="下一页" />下一页
				</a>
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

       	    <input id="toPage" name="query.toPage" type="hidden" value="<s:property value="query.toPage" />" />			
       	</s:else>
		</div>
</div>


<script language="javascript">
$(document).ready(function(){
  $('#sel_pn_rownum').change(onchangePageBysel);
});
   function changePage(page) {
        $("#toPage").val(page);
        doSearch();
        //$('#toPage')[0].form.submit();
		//alert($($('#toPage')[0].form).find('input[type=submit]').size());
		//$($('#toPage')[0].form).find('input[type=submit]').click();
    }

	function onchangePageBysel(){        
        changePage(1);
    }
    
    function initPageNum() {
        $("#toPage").val(1);
    }

</script>