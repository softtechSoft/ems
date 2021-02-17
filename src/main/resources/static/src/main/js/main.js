window.addEventListener('DOMContentLoaded',
function()
{
	if(window.innerWidth<1230)
	{
		document.getElementById("header").style.left="-200px";
		document.getElementById("right-block").style.width="100%";
		var rightHeader=document.getElementById("right-header");
		rightHeader.style.background="#00B38B";
		rightHeader.style.color="#ffffff";
		document.getElementById("sysTitle").style.textAlign="center";
		document.getElementById("menu").style.display="inline";
		document.getElementById("menuBlur").style.display="inline";
	}
    window.addEventListener('resize',
    	function()
    	{
    		if(window.innerWidth<1230)
    		{
    			document.getElementById("header").style.left="-200px";
    			document.getElementById("right-block").style.width="100%";
    			document.getElementById("sysTitle").style.textAlign="center";
    			var rightHeader=document.getElementById("right-header");
				rightHeader.style.background="#00B38B";
				rightHeader.style.color="#ffffff";
				document.getElementById("menu").style.display="inline";
				document.getElementById("menuBlur").style.display="inline";
    		}
    		else
    		{
    			var header=document.getElementById("header");
    			header.style.left="0px";
    			document.getElementById("right-block").style.width="88%";
    			document.getElementById("sysTitle").style.textAlign="left";
    			var rightHeader=document.getElementById("right-header");
				rightHeader.style.background="#ffffff";
				rightHeader.style.color="#313541";
				document.getElementById("menu").style.display="none";
				document.getElementById("menuBlur").style.display="none";
				menuBlur();
    		}
    	},false);
},false);

function onload(actor)
{
	if(actor=="false")
	{
		document.getElementById("messagebox").style.display="block";
	}
}

function navigation(actor)
{

	//document.getElementById("iframe").src=actor;

	// リリース用www.it-softtech.com/ems/
	//var uriPre = "http://it-softtech.com/ems/";
	// リリース確認用www.it-softtech.com/office/
	//var uriPre = "http://dev.it-softtech.com/ems/";
	// 開発用www.it-softtech.com
	var uriPre = "http://dev.it-softtech.com/";
//alert("uri=" + uriPre+actor);
	document.getElementById("iframe").src=uriPre+actor;
}
function onStyle(actor)
{
	for(var node of actor.parentNode.childNodes)
	{
		if(node.tagName=="LI")
		{
			node.style.backgroundColor="transparent";
			node.style.color="#ffffff";
		}
	}
	actor.style.backgroundColor="#272a34";
	actor.style.color="#757474";
}

function menu()
{
	document.getElementById("shade").style.display="block";
	var header=document.getElementById("header");
	header.style.width="200px";
	var run=setInterval("exec()","0");
	exec=function()
	{
		if(header.offsetLeft<0)
		{
			header.style.left=header.offsetLeft+4+"px";
		}
		else
		{
			clearInterval(run);
		}
	}
}

function menuBlur()
{
	var header=document.getElementById("header");
	header.style.width="12%";
	var run=setInterval("exec()","0");
	exec=function()
	{
		if(header.offsetLeft>-200&&window.innerWidth<1230)
		{
			header.style.left=header.offsetLeft-4+"px";
		}
		else
		{
			document.getElementById("shade").style.display="none";
			clearInterval(run);
		}
	}
}

function exit()
{
    ajaxSend("GET","/exit");
    window.location.reload();
}
function messagetExit()
{
	document.getElementById("messagebox").style.display="none";
}
function toPasswd()
{
	navigation('/passwd');
	messagetExit();
	var psw=document.getElementById("password");
	psw.style.backgroundColor="#272a34";
	psw.style.color="#ffffff";
}