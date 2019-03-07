function searchPrice(goodsId){
	var url = "/Verwalter/timePrice/search";
	var loadData = {"goodsId":goodsId};
	$("#priceList").load(url,loadData);
}

function editPrice(goodsId,type){
    var dialog = $.dialog({
        title: '价目表',
        content: 'url:/Verwalter/price/edit?id=' + goodsId+'&type='+type,
        min: false,
        max: false,
        lock: true,
        width: 600,
        height:350
    });
}


function refreshList(goodsId){
	searchPrice(goodsId);
}


function deletePrice(id){
	var url ="/Verwalter/timePrice/delete";
	var loadData={"id":id};
	$.post(url,loadData,delPriceCallback,"text");
}

function delPriceCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		refreshList(result.goodsId);
	}else{
		SimplePop.alert('删除失败!');
	}
}