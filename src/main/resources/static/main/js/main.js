window.addEventListener('DOMContentLoaded',
function()
{
    var leftblock=document.querySelector('header');
    leftblock.style.height=window.innerHeight+"px";
    var rightblock=document.querySelector('.right-block');
    rightblock.style.height=window.innerHeight+"px";
    var bodyblock=document.querySelector('.right-body');
    bodyblock.style.height=window.innerHeight-88+"px";
    window.addEventListener('resize', function(){leftblock.style.height=window.innerHeight+"px";rightblock.style.height=window.innerHeight+"px";sbodyblock.style.height=window.innerHeight-88+"px";},false);    
},false);
function exit()
{
    if (window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
    xmlhttp.open("GET","/exit",true);
    xmlhttp.send();
    window.location.reload();
}