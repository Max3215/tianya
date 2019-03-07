<#if cartGoods??>
    <div class="left_desc">
        <input type="hidden" value="${cartGoods.id?c}" id="cartId">
            <div class="d_title">
                <a href="/detail/${cartGoods.goodsId?c}" class="title">${cartGoods.goodsTitle!''}</a>
                <a href="/detail/${cartGoods.goodsId?c}" class="check_detail">查看产品详情 >></a>
            </div>
            <div class="d_items">
                <div class="d_item">
                    <label>已选航期：<font color="ff9100"><#if cartGoods.leaveDate??>${cartGoods.leaveDate?string('yyyy-MM-dd')}<#else>未选择</#if></font></label> <a href="#" class="update_date">更改</a>
                </div>
                <div class="d_item">
                    <label>出发日期：<font><#if cartGoods.leaveDate??>${cartGoods.leaveDate?string('yyyy-MM-dd')}<#else>未选择</#if></font></label>
                </div>
            </div>
            <div class="d_items">
                <#if cartGoods.psList??>
                    <#list cartGoods.psList as gp>
                        <#if gp.type==1 && gp.isSelect>
                        <div class="d_item">
                            <label>已选择房型：<font id="title">${gp.title!''}</font></label>
                        </div>
                        <div class="d_item">
                            <label>人数：<font>成人<font><span id="num1">${gp.quantity1!'0'}</span></font>人、
                                                儿童<font><span id="num2">${gp.quantity2!'0'}</span></font>人</font></label>
                        </div>
                        </#if>
                    </#list>
                <#else>
                    <div class="d_item">
                        <label>已选择房型：<font id="title">未选择</font></label>
                    </div>
                    <div class="d_item">
                        <label>人数：<font>成人<font><span id="num1">0</span></font>人、
                                        儿童<font><span id="num2">0</span></font>人</font>
                        </label>
                    </div>
                </#if>
            </div>
        </div>
        <div class="right_price">
            <label>总计：<font>￥<#if cartGoods.price??>${cartGoods.price?string('0.00')}<#else>0</#if>元</font></label>
            <a href="/order/ship2?cartId=${cartGoods.id?c}">下一步</a>
        </div>
</#if>       