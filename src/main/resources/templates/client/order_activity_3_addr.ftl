<#if visitorList??>
<#list visitorList as vi>
    <ul class="p_normal">
        <li class="li01">
                <label>
                    <input type="checkbox" name="visitorId" onclick="javascript:selectVisit(${vi.id?c});" value="${vi.id?c}" <#if vi.isSelect?? && vi.isSelect>checked="checked"</#if>/>
                </label>
        </li>
        <li class="li02"><label>${vi.visitorName!''}</label></li>
        <li class="li02">
            <lable>${vi.sex!''}</lable>
        </li>
        <li class="li_double3"><label>
            ${vi.certificateType!''}
        </label></li>
        <li class="li04"><label>${vi.certificateCardCode!''}</label></li>
        <li class="li04"><label>${vi.telephone!''}</label></li>
        <li class="li05"><a href="/order/ship3?vid=${vi.id?c}" class="p_btn">修改</a> <a href="#">设为常用联系人</a></li>
    </ul>
 </#list>
 </#if>
 <script type="text/javascript">
 function selectVisit(visitId)
 {
    $.ajax({
        type : "post",
        url : "/order/visitor/select",
        data : {"visitId":visitId},
        success:function(data)
        {
            if(data.code==0)
            {
                alert(data.msg);
            }
        }
        
    })
 }
 
 </script>