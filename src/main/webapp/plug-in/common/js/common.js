//全屏显示当前页
function fullScreen()
{
    var docElm = document.documentElement;
    //FireFox
    if (docElm.mozRequestFullScreen) {
        docElm.mozRequestFullScreen();
    }
    //Chrome等
    else if (docElm.webkitRequestFullScreen) {
        docElm.webkitRequestFullScreen();
    }
    //IE11
    else if (docElm.msRequestFullscreen) {
        docElm.msRequestFullscreen();
    }//W3C
    else if (docElm.requestFullscreen) {
        docElm.requestFullscreen();
    }
}

function showOrNone(){
    var isFullscreen = document.fullScreenElement//W3C
        ||document.msFullscreenElement //IE
        ||document.mozFullScreenElement //火狐
        ||document.webkitFullscreenElement //谷歌
        ||false;
    if(!isFullscreen){
        var el = document.documentElement;
        if (el.requestFullscreen) {
            el.requestFullscreen();
        } else if (el.mozRequestFullScreen) {
            el.mozRequestFullScreen();
        } else if (el.webkitRequestFullscreen) {
            el.webkitRequestFullscreen();
        } else if (el.msRequestFullscreen) {
            el.msRequestFullscreen();
        }
    }else{
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
    }
}

//弹出加载层
function loadMask() {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("加载中，请稍候。。。").appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}
//取消加载层
function disLoadMask() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

//datagrid查询功能
function generateSearchParam(id,params){
    if(!!id&&!!params){
        var paramObj = {};
        if(params.length>0){
            params.forEach(function(item){
                paramObj[item] = getValByTagId(item);
            })
        }
    }
    $('#'+id).datagrid('load',paramObj);
}
//根据id获取不同元素的值
function getValByTagId(id){
    var tag = $("#"+id);
    if(tag[0].tagName=="SELECT"){
        return tag.find("option:selected").val();
    }else if(tag[0].tagName=="INPUT"){
        return tag.val();
    }else{
        return tag.text();
    }
}

function getValByTabDom(dom){
    if($(dom).tagName=="SELECT"){
        return tag.find("option:selected").val();
    }else if($(dom).tagName=="INPUT"){
        return tag.val();
    }else{
        return tag.text();
    }
}

/**
 * 根据dom元素获取tab数据
 * @param tabid
 * @returns {Array}
 */
function getTabData(tabid){
    var trs = $("#"+tabid+" tr");
    var result = [];
    if(!!trs){
        for (var i = 0; i < trs.length; i++) {
            var tds = trs[i].cells;
            var trData = [];
            for (var j = 0; j < tds.length; j++) {
                if($(tds[j]).data("print")!=false){//屏蔽不打印信息
                    var cell = tds[j].children;
                    if(cell.length>0){//td中包含标签
                        if($(cell)[0].tagName == "SELECT"){
                            trData.push($(cell).find("option:selected").text());
                        }else if($(cell)[0].tagName == "INPUT"){
                            trData.push($(cell).val());
                        }else{
                            trData.push($(cell).text());
                        }
                    }else{//td中直接是内容
                        trData.push($(tds[j]).text());
                    }
                }
            }
            result.push(trData);
        }
    }
    return result;
}

/**
 * 根据数据生成tab代码
 * @param tabData
 * @returns {string}
 */
function generateTab(tabData){
    var tab_tr_begin = "<tr  style='border: 1px solid #374850'>";
    var tab_tr_end = "</tr>";
    var tab_td_begin = "<td  style='border: 1px solid #374850;margin-top:-1px;margin-left:-1px;text-align: center'>";
    var tab_td_end = "</td>";
    var tab_html = "<table style='width:100%;border: 1px solid #374850;border-collapse: collapse; '>";
    if(!!tabData){
        for (var i = 0; i < tabData.length; i++) {
            var tr = tabData[i];
            if(!!tr){
                tab_html += tab_tr_begin;
                for (var j = 0; j < tr.length; j++) {
                    var td = tr[j];
                    tab_html += tab_td_begin;
                    tab_html += td;
                    tab_html += tab_td_end;
                }
                tab_html += tab_tr_end;
            }
        }
    }
    tab_html += "</table>";
    return tab_html;
}


function check_notnull(){
    var flage = true;
    $(".check_notnull").each(function(index,item){
        if(!item.val()){
            flag = false;
            return false;
        }
    })
    return flage;
}


//日期格式化
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}