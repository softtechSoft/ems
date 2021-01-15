window.addEventListener('DOMContentLoaded',
function()
{
	if(window.innerWidth<1230)
	{
		document.getElementById("header").style.left="-200px";
		document.getElementById("right-block").style.width="100%";
		document.getElementById("company").style.textAlign="center";
	}
    window.addEventListener('resize', 
    	function()
    	{
    		if(window.innerWidth<1230)
    		{
    			document.getElementById("header").style.left="-150px";
    			document.getElementById("right-block").style.width="100%";
    			document.getElementById("company").style.textAlign="center";
    		}
    		else
    		{
    			document.getElementById("header").style.left="0px";
    			document.getElementById("right-block").style.width="88%";
    			document.getElementById("company").style.textAlign="left";
    		}
    	},false);    
},false);
				
function navigation(actor)
{
	document.getElementById("iframe").src=actor;
}
function exit()
{
    ajaxSend("GET","/exit");
    window.location.reload();
}
