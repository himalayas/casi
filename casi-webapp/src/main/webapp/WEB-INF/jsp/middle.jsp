<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Simba辛巴平台</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>

<body class="bdt">
<!-- 中间中缝 begin -->
<div id="mainmiddle"><img src="images/mm_l_of.gif" onmouseover="this.src='images/mm_l_on.gif'" onmouseout="this.src='images/mm_l_of.gif'" width="10" height="91" alt="隐藏左侧" /></div>
<!-- 中间中缝 end -->
</body>
</html>
<script language="javascript">
$('#mainmiddle').toggle(
	function(){
		$(window.parent.document).find('#fs').attr('cols','0,15,*');
		$(this).html('<img src="images/mm_r_of.gif" onmouseover="this.src=\'images/mm_r_on.gif\'" onmouseout="this.src=\'images/mm_r_of.gif\'" width="10" height="91" alt="显示左侧" />');
	},
	function(){
		$(window.parent.document).find('#fs').attr('cols','195,15,*');
		$(this).html('<img src="images/mm_l_of.gif" onmouseover="this.src=\'images/mm_l_on.gif\'" onmouseout="this.src=\'images/mm_l_of.gif\'" width="10" height="91" alt="隐藏左侧" />');
	}
);
</script>
