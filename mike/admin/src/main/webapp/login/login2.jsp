<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><s:text name="admin.title" /></title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery-1.8.0.min.js"></script>
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/html5shiv.min.js"></script>
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/placeholder.js"></script>

  <style type="text/css">

    body{
      background: url("../img/1.jpg");
      animation-name:myfirst;
      animation-duration:12s;
      /*变换时间*/
      animation-delay:2s;
      /*动画开始时间*/
      animation-iteration-count:infinite;
      /*下一周期循环播放*/
      animation-play-state:running;
      /*动画开始运行*/
    }
    @keyframes myfirst
    {
      0%   {background:url("../img/1.jpg");}
      34%  {background:url("../img/2.jpg");}
      67%  {background:url("../img/3.jpg");}
      100% {background:url("../img/1.jpg");}
    }
    .form{background: rgba(255,255,255,0.2);width:400px;margin:120px auto;}
    /*阴影*/
    .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
    input[type="text"],input[type="password"]{padding-left:26px;}
    .checkbox{padding-left:21px;}
  </style>

</head>
<body>
<div class="container">
  <div class="form row">
    <div class="form-horizontal col-md-offset-3" id="login_form">
      <h3 class="form-title">LOGIN</h3>
      <div class="col-md-9">
        <div class="form-group">
          <i class="fa fa-user fa-lg"></i>
          <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
        </div>
        <div class="form-group">
          <i class="fa fa-lock fa-lg"></i>
          <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
        </div>
        <div class="form-group">
          <label class="checkbox">
            <input type="checkbox" name="remember" value="1"/>记住我
          </label>
        </div>
        <div class="form-group col-md-offset-9">
          <button type="submit" class="btn btn-success pull-right" name="submit">登录</button>
        </div>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">

  $(function(){
      // 点击登录
      $('#but_login').click(function(e) {
          submit();
      });
  })

    //回车登录
    $(document).keydown(function(e){
        if(e.keyCode == 13) {
            submit();
        }
    });

    //表单提交
    function submit()
    {
        showMsg('','green');
        var submit = true;
        $("input[nullmsg]").each(function() {
            var val = $(this).val();
            var plac= $(this).attr('placeholder');
            if (val == ""||val==plac) {
                var msg = $(this).attr('nullmsg');
                showMsg(msg,'red');
                submit = false;
                return false;
            }
        });
        if (submit) {
            showMsg('<s:text name="system.ui.loading"/>','blue');
            Login();
        }

    }


    //登录处理函数
    function Login() {
        var actionurl=$('form').attr('action');//提交路径
        var checkurl=$('form').attr('check');//验证路径
        var formData=$("#formLogin").serialize();
        //alert(formData);
        $.ajax({
            async : false,
            cache : false,
            type : 'POST',
            url : checkurl,// 请求的action路径
            data : formData,
            error : function() {},
            success : function(data) {
                var d = $.parseJSON(data);
                if (d.success) {
                    showMsg('<s:text name="login.success" />','green');
                    setTimeout("window.location.href='"+actionurl+"'", 500);
                } else {
                    showMsg(d.msg,'red');
                }
            }
        });
    }

    function showMsg(msg,color){
        $("#loginmsg").css("color",color).html(msg);
        setTimeout(function(){
            $("#loginmsg").html('');
        },3000);
    }

</script>

</body>
</html>
