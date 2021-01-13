function cancel()
{
    document.getElementById("oldPassword").value = ""; 
    document.getElementById("firstPassword").value = "";
    document.getElementById("secondPassword").value = "";
}
function chengePsw()
{
    var oldPsw=document.getElementById("oldPassword").value;
    var firstPsw=document.getElementById("firstPassword").value;
    if(oldPsw=="")
    {
        document.getElementById("oldPasswordMessage").innerText="パスワードを入力ください";
    }
    else if(firstPsw=="")
    {
        document.getElementById("firstPasswordMessage").innerText="パスワードを入力ください";
    }
    else if(firstPsw!=document.getElementById("secondPassword").value)
    {
        document.getElementById("secondPasswordMessage").innerText="もう一度パスワードを入力ください";
    }
    else
    {
        var data={oldPsw:oldPsw,firstPsw:firstPsw};
        ajaxSend("POST","/update-passwd",pswMessage,"data",JSON.stringify(data));
    }
}
function messageStyleRestore(actor)
{
    document.getElementById(actor).innerText="";
}
function pswMessage(actor)
{
    if(actor=="111")
    {
		document.querySelector(".messagebox").style.display="block";
		cancel();
    }
    else if(actor=="001")
    {
        document.getElementById("oldPasswordMessage").innerText="パスワードが違います";
    }
}

function messagetExit()
{
    document.querySelector(".messagebox").style.display="none";
}

