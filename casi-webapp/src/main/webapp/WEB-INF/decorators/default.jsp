<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><decorator:title default="无标题"/> - CASI</title>
    <link href="<%= request.getContextPath() %>/css/ifr.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
    <decorator:head />
</head>
<body>
<ul id="imul" class="xlts"></ul>
<ul id="cnameul" class="xlts"></ul>
<ul id="subjectul" class="xlts"></ul>
    <div id="ajax_loading" class="ajax_loading">
  		<p><img src="images/ajax-loader.gif" width="55" height="55" align="absmiddle" />进行中....</p>
	</div>
    <decorator:body />
</body>
</html>