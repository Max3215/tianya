<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/client/css/calendar.css" rel="stylesheet" type="text/css" />
<link href="/client/css/datepicker.css" rel="stylesheet" type="text/css" />

<script src="/client/js/Calendar.js"></script>
<script type=text/javascript src="/client/js/jquery.min.js"></script>
<script src="/client/js/zlDate.js"></script>

<script src="/client/js/fun.js"></script>

</head>

<body> 

<div class="calendar" >
<input onclick="javascript:AjaxTimes(1);" type="button" id="calendar1" value="点击选择时间"  />
<div class="outer clearfix" id="calendarcontainer"> 
</div>
</div>    

<script type="text/javascript">
function AjaxTime() {
        $.get("/ajax?action=Addprice&cmd=search.allprice&pid=21", function (data) {
            //alert(data);
            pickerEvent.setPriceArr(eval("(" + data + ")"));
            pickerEvent.Init("calendar");
        });
    }
function AjaxTimes(id) {
        $.get("/detail/showprice?id=" + id, function (data) {
          //  alert(data);
            pickerEvent.setPriceArr(eval("(" + data + ")"));
            pickerEvent.Init("calendar" + id);
        });
    }
//   function dateCompare(startdate, enddate) {
//        var arr = startdate.split("-");
//        var starttime = new Date(arr[0], arr[1], arr[2]);
//        var starttimes = starttime.getTime();
//
//        var arrs = enddate.split("-");
//        var lktime = new Date(arrs[0], arrs[1], arrs[2]);
//        var lktimes = lktime.getTime();
//
//        if (starttimes > lktimes) {
//            return false;
//        }
//        else
//            return true;
//
//    } 
    
    
    
    
 //  //c:容器,y:年,m:月,a:出发时间json,f:是否显示双日历,fu:回调调
 //  var para={'c':'calendarcontainer',
 //           'y':2016,
 //  		 'm':4,
 //  		 'a':{
 //  			 'd1':'2014-01-01',//最早时间
 //  			 'd2':'2137-12-31'//最晚时间
 //  			 },
 //  		 'f':1,//显示双日历用1，单日历用0
 //  		 'clickfu':function (to) {//回调函数，to为点击对象，点击日期是调用的函数,参数to为点击的日期的节点对象，可以把用户选定的日期通过此函数存入服务端或cookies，具体请自行编写
 //                 if(to.id!=""){alert(to.id)}	 
 //  			 },
 //  		 'showFu':function(d){	//回调函数，d为要显示的当前日期，主要用于判断是否要在该日期的格子里显示出指定的内容，在日期格子里额外显示内容的函数,返回值必须为字符串，参数d为显示的日期对象（日期类型）
 //  		         var t=new Date();
 //  				 if(t.toLocaleDateString()==d.toLocaleDateString()){		
 //  				  return "<br/>￥16.50";
 //  				 }
 //  				 else{
 //  				 return "";	 
 //  				 }
 //  			 }		 
 //  		 }
 //  		 
 // CreateCalendar(para,"para"); //参数前一个是对象，后一个是对象名称
</script>
</body>
</html>
