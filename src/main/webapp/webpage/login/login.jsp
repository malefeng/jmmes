<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title><t:mutiLang langKey="jeect.platform"/></title>
  <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
  <link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />
  <link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
  <!-- text fonts -->
  <link rel="stylesheet" href="plug-in/ace/css/ace-fonts.css" />

  <link rel="stylesheet" href="plug-in/ace/css/jquery-ui.css" />
  <!-- ace styles -->
  <link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-part2.css" class="ace-main-stylesheet" />
  <![endif]-->

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="plug-in/ace/css/ace-ie.css" />
  <![endif]-->
  <!-- ace settings handler -->
  <script src="plug-in/ace/js/ace-extra.js"></script>

  <!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->
  <style>
    .white-color{
      color:#fff;
    }
    .right-bottom a{
      text-decoration: none;
      color:#fff;
    }
    p{
      margin:0;
      padding:0;
    }
    .wrapper{
      width:100%;
    }
    .outside{
      width: 100%;
      margin-top: 2px;
    }
    .top-self{
      height: 61px;
    }
    .top-log{
      width: 220px;
      height: 61px;
      margin-left: 25%;
    }
    .center-self{
      height: 566px;
      background-image: url("images/banner.jpg");
      background-repeat: no-repeat;
      background-position: 0 0;
      background-size: cover;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .center-self .center-left{
      width: 50%;
      height: 100%;
      text-align-last: right;
      display: table;
    }
    .center-title{
      display: table-cell;
      vertical-align: middle;
    }
    .p-footnote{
      font-family: "Microsoft YaHei";
      font-size: 12px;
      color: #bababa;
      text-align-last: center;
    }
    .p-title{
      font-family: microsoft yahei bold;
      color: #ffffff;
      display: block;
      vertical-align: middle;
    }
    .p-title-1{
      font-size: 38px;
    }
    .p-title-2{
      font-size: 39px;
    }
    .center-right{
      width:40%;
    }
    .right-center > input{
      padding-left: 10px;
      box-sizing: border-box;
    }
    .right-top{
      width:100%;
      text-align: left;
      margin-bottom: 20px;
    }
    .right-container{
      /*width:40%;*/
      height:331px;
      background:rgba(0,0,0,0.4);
      /*opacity: 0.4;*/
      width:344px;
      box-sizing: border-box;
      padding: 30px;
      display:flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
    .right-center{
      display:flex;
      flex-direction: column;
      width:100%;
    }
    .right-center>*{
      width:100%;
      height:30px;
      margin-bottom: 20px;
      border: none;
    }
    .right-center button{
      background: #339bb9;
    }
    .right-bottom{
      width:100%;
      display:flex;
      justify-content: space-between;
      align-items: center;
    }
    .button-text{
      display: inline-block;
      font-family: "Microsoft YaHei-Bold";
      font-size: 15px;
    }
    input::-webkit-input-placeholder{
      color:#d4d4d4;
      font-size: 14px;
    }
    form{
      width: 100%;
      height: 100%;
    }
    input:-webkit-autofill {
      /*-webkit-text-fill-color: #ededed !important;*/
      -webkit-box-shadow: 0 0 0px 1000px transparent inset !important;
      background-color: transparent;
      background-image: none;
      transition: background-color 50000s ease-in-out 0s;
    }

  </style>
</head>
<body class="login-layout light-login">
<div class='wrapper'>
  <div class='top-self outside'>
    <img src="images/logo_login.png" class="top-log"/>
  </div>
  <div class="center-self outside">
    <div class="center-left">
      <div class="center-title">
        <p class="p-title p-title-1">汇天成管理系统 V5.0</p>
        <p class="p-title p-title-2">工厂智能化定制专家</p>
      </div>
    </div>
    <div class="center-right">
      <form id="loinForm" class="form-horizontal"  method="post">
        <!-- 单点登录参数 -->
        <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>
        <div class="right-container">
          <div class="white-color right-top"> 账号登录</div>
          <div  class="right-center">
            <input type="text" name="userName" id="userName"  placeholder="请输入您的手机号码/用户名"/>
            <input type="password" name="password" id="password" placeholder="请输入密码"/>
            <button onclick="checkUser()"><p class="button-text white-color" >登录</p></button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="bottom-self outside">
    <p class="p-footnote">Copyright &copy; 2019 苏州汇天成科技有限公司 版权所有 &nbsp;&nbsp;&nbsp;&nbsp; 技术支持：汇天成科技</p>
    <p class="p-footnote">汇天成官网：<a href="http://www.htcsoft.cn">www.htcsoft.cn</a> &nbsp;&nbsp; 联系电话：0512-6801 3806</p>
  </div>
</div>
</div>

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="webpage/login/login-ace.js"></script>
<%=lhgdialogTheme %>
<script type="text/javascript">
  $(function(){
    optErrMsg();
  });
  $("#errMsgContiner").hide();

  //输入验证码，回车登录
  $(document).bind('keyup', function(event) {
    if (event.keyCode == "13") {
      $('#but_login').click();
    }
  });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }

  /**
   * 刷新验证码
   */
  $('#randCodeImage').click(function(){
    reloadRandCodeImage();
  });

</script>
</body>
</html>