<#macro content operationModel>
    <#if operationModel.authenticated>
        <#if operationModel.endpointDiscovery?has_content>
        invoke(request, responseHandler, executionContext, cachedEndpoint);
        <#else>
        invoke(request, responseHandler, executionContext);
        </#if>
    <#else>
        anonymousInvoke(request, responseHandler, executionContext);
    </#if>
</#macro>
