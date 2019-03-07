<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${charset!'UTF-8'}" />
<title><#if site??>${site.seoTitle!''}-</#if>天涯</title>
<meta name="keywords" content="<#if setting??>${setting.seoKeywords!''}</#if>">
<meta name="description" content="<#if setting??>${setting.seoDescription!''}</#if>">
<meta name="copyright" content="<#if setting??>${setting.copyright!''}</#if>">
<link rel="stylesheet" type="text/css" href="/client/css/base.css">
    <link rel="stylesheet" type="text/css" href="/client/css/common.css">
    <link rel="stylesheet" type="text/css" href="/client/css/f_cruise.css">
    
    <script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
    <script src="/client/js/rich_lee.js" type="text/javascript"></script>

    <script type="text/javascript">
        window.onload = function () {
            act_nav();
        };
      
      function subForm()
      {
        $("#form").submit();
      }
    </script>
</head>
<body>
<#include "/client/common_header.ftl" />

<div class="main">
    ${payForm!''}
</div>

<#include "/client/common_footer.ftl" />
</body>
</html>
<!--结束-->
