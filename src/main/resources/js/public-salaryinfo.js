window.addEventListener('DOMContentLoaded',
function()
{     
	  var date=new Date();
	  var year=date.getFullYear();
	  var month=date.getMonth();
	  var yearObj=document.getElementById('years'); 
	  var monthObj=document.getElementById('months');
	  while(year>=2019)
	  {
		  yearObj.options.add(new Option(year));
		  year--;
	  }
	  monthObj.options[month-1].selected=true;
}
,false);
//向服务端请求,model(请求方式)，url(请求地址),func(处理函数),varName(传递参数名)，json(传递的参数)
function ajaxSend(model,url,func,varName,json)
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            //如果无返回数据
            if(xmlhttp.responseText=="null")
            {
                document.querySelector(".messagebox").style.display="block";
            }
            else
            {
                //如果有返回的数据转换为对象
                func(JSON.parse(xmlhttp.responseText));
            }
        }
    }
    //根据model执行发送方式
    if(model.toUpperCase()=="GET")
    {
        xmlhttp.open("GET",url+(varName!=null&&varName!=""?("?"+varName+"="+json):""),true);
        xmlhttp.send();
    }
    else if(model.toUpperCase()=="POST")
    {
        xmlhttp.open("POST",url,true);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;"); 
        xmlhttp.send(varName+"="+json);
    }
}
function inserttable(data)
{
    //获取html的salaryinfo-title，获取salaryinfo-data
    var table = document.getElementById("salaryinfo-table");
    //清空tiele和salaryinfoData
    table.innerHTML="";
    var insertTr;
    for(var num=0;num<data["column"].length;num++)
    {
         //追加行
        insertTr = table.insertRow(num);
        insertTr.innerHTML = "<td>" + data["column"][num]["comment"] + "</td><td>"+ data["data"][data["column"][num]["columnName"]]+"</td>";
    }
}
//设置搜索键年月日
function querytData()
{
    var year=document.getElementById('years');
    var month=document.getElementById('months');
    var yearMonth;
    if(month.value<10)
    {
        yearMonth=year.value+"0"+month.value;
    }
    else
    {
        yearMonth=year.value+""+month.value;
    }
    ajaxSend("get","http://localhost:8080/salaryinfo",inserttable,"yearMonth",yearMonth);
}

