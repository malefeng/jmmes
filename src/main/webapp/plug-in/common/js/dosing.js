

function CountRemaining(tabId1,keyIndex1,valIndex1,tabId2,keyIndex2,valIndex2){
    var doSingDom = $("#"+tabId1+" tr :not(:first)"),
        doSingData = {},
        originalDom = $("#"+tabId2+" tr :not(:first)"),
        originalData = {},
        remainData = {};
    $.each(originalDom,function(index,item){
        var td = $(item).children('td');
        var key = getValByTabDom(td.eq(keyIndex2).children()[0]);
        if(!!valIndex2){
            var value = Number(getValByTabDom(td.eq(valIndex2).children()[0]));
            originalData[key] = (originalData[key]||0) + (value||0)
        }else{
            originalData[key] = (originalData[key]||0) + 1;
        }
    })
    remainData = originalData;
    $.each(doSingDom,function(index,item){
        var td = $(item).children('td');
        var key = getValByTabDom(td.eq(keyIndex1).children()[0]);
        if(!!valIndex1){
            var value = Number(getValByTabDom(td.eq(valIndex1).children()[0]));
            doSingData[key] = (doSingData[key]||0) + (value||0)
        }else{
            doSingData[key] = (doSingData[key]||0) + 1;
        }
    })
    for (var key in remainData) {
        remainData[key] -= doSingData[key] || 0;
        remainData[key] <= 0 && delete remainData[key];
    }
    return remainData;
}

function getValByTabDom(dom){
    if(dom.tagName=="SELECT"){
        return $(dom).find("option:selected").val();
    }else if(dom.tagName=="INPUT"){
        return $(dom).val();
    }else{
        return $(dom).text();
    }
}


function generateTr(tr,data,tabId,properArrs1,properArrs2){
    for (var i = 0; i < properArrs1.length; i++) {
        var proper1 = properArrs1[i];
        var proper2 = properArrs2[i];
        var tagName = tabId+"[#index#]."+proper1;
        var dom = tr.find("[name='"+tagName+"']");
        var val = data[proper2];
        if(!!val){
            dom.val(val);
        }
    }
}