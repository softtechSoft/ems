var timeClean;
//window.addEventlistener添加体格页面事件监听，DOMContentLoaded浏览器打开事件
window.addEventListener('DOMContentLoaded',
function()
{
    //声明变量的方式 用var声明 ，querySelector(样式器的名字，这里取的是.login)
    var login=document.querySelector('.login');
    var h1=document.querySelector('h1');
    var h2=document.querySelector('h2');
    var title=document.querySelector('.title');
    var footer=document.querySelector('footer');
    //获取宽度
    if(title.clientWidth<870)
    {
        //title左边距
        title.style.marginLeft="0px";
        //title的背景样式
        title.style.backgroundPosition="center center";
        //清楚h1和h2的内容
        h1.innerHTML="";
        h2.innerHTML="";
        login.style.left=(title.clientWidth-login.clientWidth)/2+"px";
    }
    else
    {
        //login样式靠左
        login.style.left="65%";
    }
    //footer上边距线-23大于login的底部
    if((footer.getBoundingClientRect().top-23)<login.getBoundingClientRect().bottom)
    {
        //清除footer内容
        footer.innerHTML="";
    }
    //添加窗口大小变化的监听
    window.addEventListener('resize', function()
    {
        //客户端边距小于870
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
            h1.innerHTML="ソフトトップ株式会社";
            h2.innerHTML="Softtech&nbsp;co.&nbsp;,Ltd";
            title.style.marginLeft="33px";
            title.style.backgroundPosition="left center";
        }
        if((footer.getBoundingClientRect().top-23)<login.getBoundingClientRect().bottom)
        {
            footer.innerHTML="";
        }
        else
        {
            footer.innerHTML='Copyright &copy;2015-2020 IT Soft-Tech All Rights Reserved.';
        }
    },false);
},false);
//验证账户密码
function regularVerify(actor)
{   
    //声明账户名字=id用户名信息，大小写字母与数字组合的模式
    var userName={id:"userMessage",pattern:/[A-Za-z0-9_]{1,15}@[A-Za-z0-9]{1,10}.com/,message:"メールをお入力ください"};
    //声明，密码=id密码信息，任意字符组合的模式
    var password={id:"passwordMessage",pattern:/^[^ ]+$/,message:"パスワードをお入力ください"};
    //账号状态
    var userStatus = true;
    actor.style.borderColor='#0aada8';
    //switch变量表达
    switch(actor.id)
    {
        //案件用户名不正确阻止，密码不正确阻止
        case "user": userStatus=verify(userName);break;
        case "password":if(userStatus)verify(password);break;
    }
    //校验obj
    function verify(obj)
    {
        //加入非obj模式测试
        if(!obj.pattern.test(actor.value))
        {
            actor.style.color='#ff0000';
            document.getElementById(obj.id).innerHTML=obj.message;
            //错误
            userStatus=false;
            //返回用户状态
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
            ajaxSend("POST","http://localhost:8080/enter",inspectionResult,"data",JSON.stringify(data));
        }
    }
    function inspectionResult(actor)
    {
        var userName={id:"userMessage",pattern:/[A-Za-z0-9_]{1,15}@[A-Za-z0-9]{1,10}.com/,message:"メールが存在しません"};
        var password={id:"passwordMessage",pattern:/^[^ ]+$/,message:"パスワードが正しくないです"};
        switch(actor)
        {
            case "001":let use=document.getElementById(userName.id);use.style.color='#ff0000';use.innerHTML=userName.message;break;
            case "002":let psw=document.getElementById(password.id);psw.style.color='#ff0000';psw.innerHTML=password.message;break;
            default:window.location.reload();break;
        }
    }
}