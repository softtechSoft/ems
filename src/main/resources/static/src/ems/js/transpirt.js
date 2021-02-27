window.addEventListener('DOMContentLoaded',
function()
{
	  // 現在日付を作成
	  var date=new Date();
	  var year=date.getFullYear();
	  var month=date.getMonth();

	  //10月未満の場合、頭に’0’を追加する。（例：1→01)
	  if(month<10)month='0'+(month+1);

	  // 稼働月設定
	  document.getElementById('workMonth').value=year+'/'+month;

      // 交通非開始日設定
	  document.getElementById('startDate').value=year+'/'+month+'/01';
}
,false);
function startDateChange()
{
	var str = document.getElementById('workStartDay').value;
	document.getElementById('startDate').value = str;
}
function setAll()
{
	var checkbox = document.getElementById("teiki");
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



