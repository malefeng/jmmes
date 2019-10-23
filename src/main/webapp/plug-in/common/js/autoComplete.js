var finihedCodeId = 'tab_select_finishedCode';
var finihedNameId = 'tab_select_finishedName';

$(document).on('change', '#'+finihedCodeId,function(){
    var finishedCode = $(this).find("option:selected").val();
    if (!!finishedCode){
        $.getJSON("finishedProductController.do?getByParam",{finishedCode:finishedCode},function(data){
            if(!!data&&data.length>0){
                $("#"+finihedNameId).val(data[0]['finishedName']);
            }
        })
    } else{
        $("#"+finihedNameId).val("");
    }

})
$(document).on('change',"#"+finihedNameId,function(){
    var finishedName = $(this).find("option:selected").val();
    if(!!finishedName){
        $.getJSON("finishedProductController.do?getByParam",{finishedName:finishedName},function(data){
            if(!!data&&data.length>0){
                $("#"+finihedCodeId).val(data[0]['finishedCode']);
            }
        })
    }else{
        $("#"+finihedCodeId).val('');
    }
})