<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><s:text name="admin.title" /></title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/AdminLTE/css/AdminLTE.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/easyui1.5/jquery-1.8.0.min.js"></script>
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/html5shiv.min.js"></script>
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/respond.min.js"></script>
  <![endif]-->
  <script src="${pageContext.request.contextPath}/plug-in/AdminLTE/placeholder.js"></script>

</head>
<body class="hold-transition login-page">
<div class="login-box">

  <div class="login-box-body">
    <div class="login-logo">
      <s:text name="admin.title" />
    </div>
    <form id="formLogin" action="${pageContext.request.contextPath}/user/login.action" check="${pageContext.request.contextPath}/user/check.action" method="post">
      <div class="form-group has-feedback">
        <input type="text" name="info.username" class="form-control" placeholder="<s:text name="login.ipt.name" />" nullmsg="<s:text name="login.msg.name" />" />
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password"  name="info.userpwd"  class="form-control" placeholder="<s:text name="login.ipt.pwd" />" nullmsg="<s:text name="login.msg.pwd" />" />
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8"><span id="loginmsg"></span></div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="button" id="but_login" class="btn btn-primary btn-block btn-flat"><s:text name="login.login" /></button>
        </div>
        <!-- /.col -->
      </div>
    </form>

  </div>
  <!-- /.login-box-body -->
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
