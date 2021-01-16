window.addEventListener('DOMContentLoaded',
function()
{     
	  var date=new Date();
	  var year=date.getFullYear();
	  var month=date.getMonth();
	  var yearObj=document.getElementById('years'); 
	  var monthObj=document.getElementById('months');
	  if(month == 0)
	  {
		  while((year-=1)>=2019)
		  {
		     yearObj.options.add(new Option(year));
		     year--;
		  } 
		  monthObj.options[11].selected=true;	
	  }
	  else
	  {
		  while(year>=2019)
		  {
		     yearObj.options.add(new Option(year));
		     year--;
		  } 
		  monthObj.options[month-1].selected=true;
	  }
}
,false);
function inserttable(data)
{	
	data=JSON.parse(data);
    var table = document.getElementById("salaryinfo-table");
    table.innerHTML="";
    var insertTr;
    for(var num=0;num<data["column"].length;num++)
    {
        insertTr = table.insertRow(num);
        insertTr.innerHTML = "<td>" + data["column"][num]["comment"] + "</td><td>"+ data["data"][data["column"][num]["columnName"]]+"</td>";
    }
}
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
    ajaxSend("get","/request-salarydetail",inserttable,"yearMonth",yearMonth);
}

