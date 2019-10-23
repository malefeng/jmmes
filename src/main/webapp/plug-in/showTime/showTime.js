function check(val) {
    if (val < 10) {
        return ("0" + val);
    }
    else {
        return (val);
    }
}
function displayTime(node) {
    //获取div元素
    // var timeDiv=document.getElementById(id);
    //获取系统当前的年、月、日、小时、分钟、毫秒
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minutes = date.getMinutes();
    var second = date.getSeconds();
    var timestr = year + "-" + check(month) + "-" + day + "  " + check(hour)
        + ":" + check(minutes) + ":" + check(second);
    //将系统时间设置到div元素中
    node.innerHTML = timestr;
}
//每隔1秒调用一次displayTime函数
function showTime(id){
    setInterval("displayTime("+id+")",1000)//单位是毫秒
}