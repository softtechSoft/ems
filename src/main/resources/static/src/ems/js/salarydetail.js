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
function inserttable(a)
{
	a=JSON.parse(a);
    var table = document.getElementById("salaryinfo-table");
    table.innerHTML="";
    var insertTr;
    for(var num=0,rows=0;num<a["column"].length;rows++,num++)
    {
    	if(rows==5)
    	{
    	   insertTr = table.insertRow(rows++);

    	}
    	if(rows==20)
    	{
    	   insertTr = table.insertRow(rows++);
    	   insertTr = table.insertRow(rows++);


    	}
        insertTr = table.insertRow(rows);

        if(rows==22)
        {
        	insertTr.style.fontWeight="700";
        }
        insertTr.innerHTML = "<td>" + a["column"][num]["comment"] + "</td><td>"+ a["data"][a["column"][num]["columnName"]]+"</td>";
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


