var lodop_data,
    lodop_work_top = 0,
    lodop_work_left = 0,
    lodop_work_width = '100mm',
    lodop_work_title = "打印二维码",
    lodop_page_intOrient = 1,
    lodop_page_height = '30mm',
    lodop_page_width = '50mm',
    lodop_page_name = '',
    lodop_FontSize = 6,
    lodop_Bold = 1;

$(document).ready(function(){
    $('body').append('<object id="LODOP1" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" hidden>\n' +
        '        <param name="Caption" value="显示区">\n' +
        '        <param name="Border" value="0">\n' +
        '        <param name="Color" value="white">\n' +
        '        <embed id="LODOP_EM1" TYPE="application/x-print-lodop" width=60 height=40 border=0 Color="white"\n' +
        '               PLUGINSPAGE="plug-in/lodop/CLodop_Setup_for_Win32NT.exe">\n' +
        '    </object>'
    );
})

function printQRCode(data) {
    lodop_data = data;
    createPrintPage();
};

function createPrintPage() {
    if(lodop_data!=null&&lodop_data.length>0){
        var LODOP_QRCODE = getLodop($('#LODOP1'), $('#LODOP_EM1'));
        //定义背景样式
        LODOP_QRCODE.PRINT_INITA(lodop_work_top, lodop_work_left,lodop_work_width ,lodop_data.length*50+"mm" ,lodop_work_title );
        //定义横向打印与纸张大小：50mm*30mm
        LODOP_QRCODE.SET_PRINT_PAGESIZE(lodop_page_intOrient,lodop_page_width,lodop_page_height ,lodop_page_name);
        //定义字体大小
        LODOP_QRCODE.SET_PRINT_STYLE("FontSize", lodop_FontSize);
        //定义字体加粗
        LODOP_QRCODE.SET_PRINT_STYLE("Bold", lodop_Bold);
        var modelTop = 0;
        lodop_data.map(function(item){
            var pubKey = item['pubKey'].split(',');
            var pubVal = item['pubVal'].split(',');
            var secData = item['secData'];
            //填充二维码信息
            LODOP_QRCODE.ADD_PRINT_BARCODE(modelTop+2+'mm', 5, '20mm', '20mm', "QRCode", secData);
            //填充明文信息
            var initTop = modelTop+2;
            for (var i = 0; i < pubKey.length; i++) {
                LODOP_QRCODE.ADD_PRINT_TEXT(initTop + 'mm', '23mm', '30mm', '1mm', pubKey[i] + ":" + pubVal[i]);
                initTop += 3;
            }
            modelTop += 30;
        })
        LODOP_QRCODE.SET_PRINT_STYLEA(0, "GroundColor", "#fff");
        LODOP.PRINT()
    }else{
        $.messager.alert('error','数据打印异常!','info');
    }
};