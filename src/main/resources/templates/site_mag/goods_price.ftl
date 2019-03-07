<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
    <tbody>
            <tr class="odd_bg">
                <th width="8%">
                选择
            </th>
            <th align="left">
                商品ID
            </th>
            <th align="left" width="">
                商品名称
            </th>
            <th align="left" width="">
               起始时间
            </th>
            <th align="left" width="">
               截止时间
            </th>
            <th align="left" width="">
               成人价格
            </th>
            <th align="left" width="">
               儿童价格
            </th>
            <th width="12%">操作</th>            
        </tr>
        <#if timePriceList??>
            <#list timePriceList as tprice>
                <tr>
                    <td align="center">
                        <span class="checkall" style="vertical-align:middle;">
                            <input id="listChkId" type="checkbox" name="listChkId" value="${tprice_index?c}" >
                        </span>
                        <input type="hidden" name="listId" id="listId" value="${tprice.id?c}">
                    </td>
                    <td>
                        <#if tprice.goodsId??>${tprice.goodsId?c}</#if>
                    </td>
                    <td>${tprice.goodsTitle!""}</td>
                    <td><#if tprice.startTime??>${tprice.startTime?string('yyyy-MM-dd')}</#if></td>
                    <td><#if tprice.endTime??>${tprice.endTime?string('yyyy-MM-dd')}</#if></td>
                    <td><#if tprice.price??>${tprice.price?string('0.00')}</#if></td>
                    <td><#if tprice.childPrice??>${tprice.childPrice?string('0.00')}</#if></td>
                    <td align="center">
                        <#if !goods?? || !goods.isOnSale?? || goods.isOnSale ==false || (managerRole?? && managerRole.isSys)>
                        <a href="javascript:deletePrice(${tprice.id?c});" >删除</a>/
                        <a href="javascript:editPrice(${tprice.id?c},'edit');" >编辑</a>
                        </#if>
                    </td>                
                </tr>
            </#list>
        </#if>
    </tbody>
</table>