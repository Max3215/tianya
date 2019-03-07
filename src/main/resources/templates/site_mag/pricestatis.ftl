<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://localhost:2105/site_mag/center.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销量统计</title>

<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
</head>

<body class="mainbody">
<form name="form1" method="post" action="" id="form1">
    <!--导航栏-->
    <div class="location">
      <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
      <a class="home"><i></i><span>首页</span></a>
      <i class="arrow"></i>
      <span>统计中心</span>
    </div>
    <!--/导航栏-->
    
	<#if pieChartImagePath1??>
		<img src=${pieChartImagePath1!''} alt="销售额统计饼图">    
	</#if>
	<#if barChartImagePath1??>
		<img src=${barChartImagePath1!''} alt="销售额统计条形图">    
	</#if>
	<br/>
	<#if pieChartImagePath2??>
		<img src=${pieChartImagePath2!''} alt="销售额(订单状态)统计饼图">    
	</#if>
	<#if barChartImagePath2??>
		<img src=${barChartImagePath2!''} alt="销售额(订单状态)统计条形图">    
	</#if>
	
</form>
</body>
</html>