<%@include file="./include/jspheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Simba辛巴平台</title>
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
</head>

<body >


<!-- 左侧 begin -->
<ul class="side-bar" id="mainleft">
    <s:iterator value="menu" status="sys">

    <li class="mll" name="mt"   id="sys<s:property value="id" />" style="display: <s:if test="#sys.index == 0">block</s:if><s:else>none</s:else>">

        <s:iterator value="items" status="stat0">
                    <dl>
                        <dt><a href="<s:property value="link" default="#"/>"><img src="images/jd_jian.gif" width="9"
                                                                                  height="9" align="absmiddle"/>
                            <s:property value="name" escape="true"/></a>
                        </dt>
                        <dd>
                            <ul>
                                <s:iterator value="items" status="stat1">
                                    <li
                                            <s:if test="#stat0.index == 0 && #stat1.index == 0">class="on"</s:if>>
                                        <a href="<s:property value="link" default="http://about:blank"/>"
                                           target="<s:property value="target" default="ifr" />">
                                            <s:property value="name" escape="true"/>
                                        </a>
                                    </li>
                                </s:iterator>
                            </ul>
                        </dd>
                    </dl>
                </s:iterator>
    </li>
    </s:iterator>
</ul>
<!-- 左侧 end -->

<script language="javascript">
    $('li[name=mt]',document).find('dt').toggle(
            function() {
                $(this).next('dd').hide();
                $(this).find('img').attr('src', 'images/jd_jia.gif');
            },
            function() {
                $(this).next('dd').show();
                $(this).find('img').attr('src', 'images/jd_jian.gif');
            }
    );

    $('li[name=mt]',document).find('li').click(
            function() {
                $('li[name=mt]',document).find('li').each(
                        function() {
                            $(this).removeClass('on');
                        }
                );
                $(this).addClass('on');
                $('#mr_tt').text($(this).text());
            }
    );
</script>
</body>
</html>
