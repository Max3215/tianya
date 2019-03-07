<option value="">商品名称</option>
<#if goods_list??>
    <#list goods_list as goods>
        <option value="${goods.id}" <#if timePrice?? && timePrice.goodsId==goods.id>selected="selected"</#if> >${goods.subTitle!""}</option>
    </#list>
</#if>
