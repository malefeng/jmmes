<%@ taglib prefix="s" uri="/jodd" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title></title>
    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in/hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
    <script src="plug-in/laydate/laydate.js"></script>
    <style type="text/css">
        .gray-bg{
            background-color: #e9ecf3;
        }
        .col-sm-2 {
            width: 10%;
            padding-left: 5px;
            padding-right: 5px;
            float: left;
        }
        .p-lg{
            padding:0px 0px 10px 0px;
        }
        .widget{
            margin-top: 0px;
        }
        .iconfont{
            font-size: 30px;
            color: white;
        }
        h2 {
            font-size: 19px;
        }
        .echart_div{
            height:240px;width:100%;
        }
        .ibtn{
            cursor: pointer;
        }
        .flot-chart{
            height:400px;
        }
        /*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
         .container {
              width:99% !important; margin:10px;
              padding-right: 1px !important;
              padding-left: 1px !important;
         }
         .color_red{color:#e55555;}
         .col-cs-2 {
             width: 10%;
             padding-left: 5px;
             padding-right: 5px;
             float: left;
         }*/

        @media (min-width: 992px){
            .col-cs-2 {
                width: 11.11%;
                padding-left: 5px;
                padding-right: 5px;
                float: left;
            }
        }

    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <c:forEach items="${menuMap}" var="menu">
            <c:if test="${menu.key==1}">
                <c:forEach items="${menu.value}" var="val">
                    <div class="col-md-1 col-cs-2 col-xs-4">
                        <div class="widget  p-lg text-center" style="background: #cfa972;">
                            <div><!-- class="ibtn" -->
                                <i class="iconfont icon-zhihuizhongxin" style="font-size: 30px;"></i>
                                <h3 class="font-bold no-margins"></h3>
                                <small data-url="${val.functionUrl}" data-title="${val.functionName}" onclick="newTab(this)">${val.functionName}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </c:forEach>
    </div>
    <div class="row  border-bottom white-bg dashboard-header">
    </div>
</div>
</div>
<!-- 全局js -->
<!-- update-begin--Author:zhoujf  Date:20180428 for：TASK #2677 【H+风格优化】hplus 引入样式从jar中移到 开源项目中 -->
<script src="plug-in/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="plug-in/hplus/js/content.js"></script>
<!-- update-end--Author:zhoujf  Date:20180428 for：TASK #2677 【H+风格优化】hplus 引入样式从jar中移到 开源项目中 -->
<script type="text/javascript" src="plug-in/echart/echarts.min.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/i18n/jquery.i18n.properties.js"></script>


<script type="text/javascript" src="plug-in/themes/fineui/common/js/sccl.js"></script>
<script type="text/javascript" src="plug-in/themes/fineui/common/js/sccl-util.js"></script>
<t:base type="tools"></t:base>
<!--统计代码，可删除-->
</body>
<script>
    function newTab(t){
        parent.addNewTab($(t).data("url"),99,$(t).data("title"));
    }
</script>
</html>