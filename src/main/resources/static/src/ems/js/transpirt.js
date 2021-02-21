window.addEventListener('DOMContentLoaded',
function()
{
	  var date=new Date();
	  var year=date.getFullYear();
	  var month=date.getMonth();
	  var day = date.getDate();
	  if(month<10)month='0'+(month+2);
	  document.getElementById('workMonth').value=year+'/'+month;
	  document.getElementById('startDate').value=year+'/'+month+'/'+day;
}
,false);
function setAll()
{
	var checkbox = document.getElementById("allCheckBox");
	if(checkbox.checked==true)
	{
		var docs=document.getElementsByTagName('input');
		for(var v of docs)
		{
			if(v.title=='stationinfo')v.disabled="";
		}
	}
	else
	{
		var docs=document.getElementsByTagName('input');
		for(var v of docs)
		{
			if(v.title=='stationinfo')v.disabled="disabled";
		}
	}
}
function onload(actor)
{
	if(actor!=null)
	{
		document.getElementById("messagebox").style.display="block";
	}
}
function messagetExit()
{
    document.querySelector(".messagebox").style.display="none";
}
function chekMonth()
{
	var begintmonth = document.getElementById("starMonth").value;
	var endmonth= document.getElementById("endMonth").value;
	if(!endmonth)
	{
		return true;
	}
	var month1 = new Date(begintmonth).getMonth();
	var month2 = new Date(endmonth).getMonth();
	if(begintmonth=="")
	{
		return false;
	}
	if(endmonth=="")
	{
		return false;
	}
	if(month1 > month2)
	{
		return false;
	}
}



