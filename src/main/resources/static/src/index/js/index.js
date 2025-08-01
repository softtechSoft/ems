window.addEventListener('DOMContentLoaded',
function()
{
    var login=document.querySelector('.login');
    var h1=document.querySelector('h1');
    var h2=document.querySelector('h2');
    var title=document.querySelector('.title');
    var footer=document.querySelector('footer');
    if(title.clientWidth<870)
    {

        title.style.marginLeft="0px";
        title.style.backgroundPosition="center center";
        h1.innerHTML="";
        h2.innerHTML="";
        login.style.left=(title.clientWidth-login.clientWidth)/2+"px";
    }
    else
    {
        login.style.left="65%";
    }
    if((footer.getBoundingClientRect().top-23)<login.getBoundingClientRect().bottom)
    {
        footer.innerHTML="";
    }
    window.addEventListener('resize', function()
    {
        if(title.clientWidth<870)
        {
            login.style.left=(title.clientWidth-login.clientWidth)/2+"px";
            title.style.marginLeft="0px";
            title.style.backgroundPosition="center center";
            h1.innerHTML="";
            h2.innerHTML="";
        }
        else
        {
            login.style.left="65%";
            h1.innerHTML="ソフトテク株式会社";
            h2.innerHTML="It-Softtech&nbsp;co.&nbsp;,Ltd";
            title.style.marginLeft="33px";
            title.style.backgroundPosition="left center";
        }
        if((footer.getBoundingClientRect().top-23)<login.getBoundingClientRect().bottom)
        {
            footer.innerHTML="";
        }
        else
        {
            footer.innerHTML='Copyright &copy;2015-2020 It-Softtech All Rights Reserved.';
        }
    },false);
},false);
function regularVerify(actor)
{
    // 修改邮箱验证规则，支持纯用户名或完整邮箱
    var userName = {
        id: "userMessage",
        pattern: /^[A-Za-z0-9_]{1,15}(@it-softtech\.com)?$/,
        message: "半角の社用メールアドレスまたはユーザー名を入力してください"
    };
    
    var password = {
        id: "passwordMessage",
        pattern: /^[^ ]+$/,
        message: "パスワードを入力ください"
    };
    
    var userStatus = true;
    actor.style.borderColor = '#0aada8';
    
    switch(actor.id) {
        case "user": 
            userStatus = verify(userName); 
            break;
        case "password": 
            if(userStatus) verify(password);
            break;
    }
    function verify(obj)
    {
        if(!obj.pattern.test(actor.value))
        {
            actor.style.color='#ff0000';
            document.getElementById(obj.id).innerHTML=obj.message;
            userStatus=false;
            return userStatus;
        }
        return true;
    }
    return userStatus;
}
function messageStyleRestore(actor)
{
    if(actor.id=="user")
    {
        var password=document.getElementById("password");
        var passwordMessage=document.getElementById("passwordMessage");
        password.style.color='#333333';
        actor.style.borderColor='#7876fc';
        passwordMessage.innerHTML="";
    }
    actor.style.color='#333333';
    actor.style.borderColor='#7876fc';
    document.getElementById(actor.id+"Message").innerHTML="";
}
function login()
{
    let use=document.getElementById("user");
    let psw=document.getElementById("password");
    if(regularVerify(use))
    {
        if(regularVerify(psw))
        {
            var data={user:use.value,password:psw.value};
            ajaxSend("POST","enter",inspectionResult,"data",JSON.stringify(data));
        }
    }
    function inspectionResult(actor)
    {
        var userName={id:"userMessage",pattern:/[A-Za-z0-9_]{1,15}@[A-Za-z0-9]{1,10}.com/,message:"メールアドレスが存在していない。システム管理者に連絡してください。"};
        var password={id:"passwordMessage",pattern:/^[^ ]+$/,message:"パスワードが不正。システム管理者に連絡してください。"};
        switch(actor)
        {
            case "001":let use=document.getElementById(userName.id);use.style.color='#ff0000';use.innerHTML=userName.message;break;
            case "002":let psw=document.getElementById(password.id);psw.style.color='#ff0000';psw.innerHTML=password.message;break;
            default:window.location.reload();break;
        }
    }
}