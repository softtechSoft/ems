window.addEventListener('DOMContentLoaded',
function()
{
	var hour=new Date().getHours();
	if(hour<11)
	{
		document.getElementById("greet").innerText="さん、おはよう！";
	}
	else
	{
		if(hour<17)
		{
			document.getElementById("greet").innerText="さん、 こんにちは！";
		}
		else
		{
			document.getElementById("greet").innerText="さん、 こんばんは！";
		}
	}
},false);