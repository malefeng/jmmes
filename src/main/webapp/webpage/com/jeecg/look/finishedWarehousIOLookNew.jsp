<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>原料入库看板</title>
    <style type="text/css">
        body {
            background-color: #000;
            color: #fff;
        }
        #materialWarehouIOLookListtb p{
            width: 95%;
            margin: 0 auto 8px auto;
            height: 20px;
            line-height: 20px;
        }
        .title1{
            height: 30px;
            line-height: 30px;
            font-size: 28px;
            font-family: "Microsoft YaHei", "微软雅黑", "SimHei", "黑体";
            text-align: center
        }
        .title2{
            /*width: 95%;*/
            height: 20px;
            line-height: 20px;
            font-size: 18px;
        }
        .table, .table * {
            margin: 0 auto;
            padding: 0;
            font-size: 12px;
            font-family: Arial, 宋体, Helvetica, sans-serif;
        }

        .table {
            display: table;
            width: 95%;
            border-collapse: collapse;
        }

        .table-tr {
            display: table-row;
            height: 18px;
        }

        .table-th {
            display: table-cell;
            font-weight: bold;
            height: 30px;
            border: 1px solid #4472C4;
            text-align: center;
            vertical-align: middle;
        }

        .table .table-tr .table-td {
            display: table-cell;
            height: 100%;
            border: 1px solid #4472C4;
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div>
    <div id="materialWarehouIOLookListtb" ondblclick="fullScreen()" style=" height: auto;padding: 0;">
        <input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden"/>
        <p class="title1">成品出库看板</p>
        <p >
            <span class="title2" style="float: left;width: 60%">&nbsp; MES管理系统</span>
            <span class="title2" style="float: right;width: 10%;text-align: right">今日出库 &nbsp;</span>
            <span class="title2" style="float: right;width: 30%;text-align: right">当前时间：
            <span class="title2" id="timeDiv">0000-00-00 00:00:00</span></span>
        </p>
    </div>
    <div class="table">
        <div class="table-tr">
            <div class="table-th">销售出库单号</div>
            <div class="table-th">发货通知单号</div>
            <div class="table-th">物料代码</div>
            <div class="table-th">物料名称</div>
            <div class="table-th">规格型号</div>
            <div class="table-th">客户</div>
            <div class="table-th">应发数量</div>
            <div class="table-th">实发数量</div>
            <div class="table-th">发货率（%）</div>
            <div class="table-th">发货完成时间</div>
        </div>
        <c:forEach var="i" begin="0" end="19" step="1">
            <div class="table-tr" name="tr_${i}">
                <div class="table-td">预排出库单${i+1}</div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
                <div class="table-td"></div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="plug-in/jquery/jquery-1.8.3.js"></script>
<script src="plug-in/showTime/showTime.js"></script>
<script src="plug-in/common/js/common.js"></script>
<script>
    var fildArr = ['salesDeliveryOrderNumber','deliveryAdviceOrderNumber','materialCode','materialName','materialSize','customerCode','shouldSendNumber','actualSendNumber','sendRatio','sendFinishTime'];
    $(function(){
        //显示时间
        showTime("timeDiv");
        //首次加载数据
        loadData();
        //定时刷新数据
        flash();
    })

    //定时5秒刷新页面数据
    var timeId;
    function flash(){
        clearInterval(timeId)
        timeId = setInterval(function () {
            loadData();
        }, 5000)
    }

    //加载数据
    function loadData(){
        $.getJSON("finishedWarehousIOLookController.do?listData&"+new Date(),function(data){
            if(!!data){
                var dataLen = data.length;
                for (var i = 0; i < 20; i++) {
                    var node = $("div[name='tr_"+i+"']").children();
                    if(i>=dataLen){
                        node.each(function(){
                            $(this).text("");
                        })
                        node.eq(0).text("预排出库单"+(i+1));
                    }else{
                        var item = data[i];
                        for (var j = 0; j < fildArr.length; j++) {
                            if(item[fildArr[j]]!=null){
                                node.eq(j).text(item[fildArr[j]]);
                            }
                        }
                    }
                }
            }
        })
    }
</script>
</body>
</html>