<%@include file="/WEB-INF/jsp/include/jspheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/frameworks/jquery-1.7.1.min.js"></script>
<head>
    <title>index</title>

</head>
<script type="text/javascript">
    /*    $(document).ready(function(){
     $('#myform').submit();
     });*/
    function reloadSolr() {
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"ajax/reload.action",
            cache:false,
            success:function (ajaxResult) {
                if (ajaxResult.resultCode == 1) {
                    alert("reload is success!");
                } else {
                    alert("reload is faile!");
                }
            },
            error:function () {
                alert("系统出现异常!");
            }
        });
    }


    function createIndex() {
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"ajax/createIndex.action",
            cache:false,
            success:function (ajaxResult) {
                if (ajaxResult.resultCode == 1) {
                    alert("reload is success!");
                } else {
                    alert("reload is faile!");
                }
            },
            error:function () {
                alert("系统出现异常!");
            }
        });
    }
        function querys() {
            $.ajax({
                type:"GET",
                dataType:"json",
                url:"ajax/query.action",
                cache:false,
                data:'query='+$("#qname").val(),
                success:function (ajaxResult) {
                    if (ajaxResult.resultCode == 1) {
                        $("#res").text(ajaxResult.resultMap.res);
                    } else {
                        alert("reload is faile!");
                    }
                },
                error:function () {
                    alert("系统出现异常!");
                }
            });
    }
</script>
<body>
------------------Solr---------------------<br><br>
<input type="button" value="update index for Solr" onclick="reloadSolr()"><br><br>

------------------Lucene-------------------<br><br>
<input type="button" value="update index for lucene" onclick="createIndex()"> <br><br>
<form action="addPerson.action" method="get" id="myform">
    name:<input type="text" id="name" name="person.name" value=""/><br/>
    age:<input type="text" id="age" name="person.age" value=""/> <br/>
    address:<input type="text" id="address" name="person.address" value=""/><br/>
    <input type="submit" value="submit"/>
</form>
<br>
<input type="text" id="qname" name="query" value=""/>
id:<input type="button" value="搜索" onclick="querys()"/>

<div id="res">

</div>
</body>
</html>