//向服务端请求,model(请求方式)，url(请求地址),func(处理函数),varName(传递参数名)，data(传递的参数)
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
        	if(func!=null)
        	{
        		func(xmlhttp.responseText);
        	}
        }
    }
    if(model.toUpperCase()=="GET")
    {
        xmlhttp.open("GET",url+(varName!=null&&varName!=""?("?"+varName+"="+json):""),false);
        xmlhttp.send();
    }
    else if(model.toUpperCase()=="POST")
    {
        xmlhttp.open("POST",url,false);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;"); 
        xmlhttp.send(varName+"="+json);
    }
}