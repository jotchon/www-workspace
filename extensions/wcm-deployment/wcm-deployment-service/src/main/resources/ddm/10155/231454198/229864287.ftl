<#macro submit_form>
	<#assign hs_form_local_service = serviceLocator.findService("com.liferay.hubspot.service.HSFormLocalService") />

	<!-- Testing -->
	<#-- <#assign hs_account_id = "299703" /> -->

	<!-- Production -->
	<#assign hs_account_id = "252686" />

	<#assign fields = stringUtil.split(request.parameters.fields, ":;:") />

	<#assign guid = request.parameters.guid />
	<#assign ipAddress = request.parameters.ipAddress />
	<#assign pageName = request.parameters.pageName />
	<#assign pageURL = request.parameters.pageURL />
	<#assign redirectURL = request.parameters.redirectURL />
	<#assign salesforceCampaignId = request.parameters.salesforceCampaignId />
	<#assign userToken = request.parameters.userToken />

	<#assign VOID = hs_form_local_service.submitHSForm(guid, userToken, ipAddress, pageURL, pageName, redirectURL, salesforceCampaignId, fields) />
</#macro>