<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<core:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

    <link rel="StyleSheet" href="${contextPath}/resources/css/master_layout.css"/>
    <link rel="StyleSheet" href="${contextPath}/resources/css/createForm.css" />
    <link rel="StyleSheet" href="${contextPath}/resources/css/jqModal.css" />
    <link rel="StyleSheet" href="${contextPath}/resources/css/Ple_popup.css" />

	<script type="text/javascript" src="${contextPath}/resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/jquery.corner.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/jqModal.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/ccssupport.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/jquery-ui-1.7.2.custom.min.js"></script>
    
<style type="text/css">
.popupTbl {padding:0px;border:1px solid #DDDDDD;border-collapse: collapse;}
.rightCol {border:1px solid #DDDDDD;border-collapse: collapse;padding:5px;text-align:left;vertical-align: middle;}
.rightCol label {padding:0px,0px,0px,10px;}
.leftCol {border:1px solid #DDDDDD;border-collapse: collapse;padding:5px;text-align:right;vertical-align: middle;}
</style>

<script type="text/javascript">


$(function() {	
});
	
</script>

   <div class="ple-container hidden">
	<div class="ple-popup ple-popup-small jqmWindow" id="dialog">
        <form onsubmit="new Ajax.Request('/ccs/contentCatalog/catalogNewContent',{asynchronous:true,evalScripts:true,parameters:Form.serialize(this)});return false" method="POST" action="/ccs/contentCatalog/catalogNewContent" name="frmStorecontent" id="frmStorecontent">
        <!-- Header -->
        <div class="ple-popup-header">
            <div class="ple-popup-header-inner-wrapper">
                New Content
                <input class="ple-button-popup-close jqmClose" type="button" value="" name="">
            </div>
        </div>
        <!-- End Header -->
        <div class="ple-popup-container">
            <div class="ple-popup-mainContent-wrapper">
                <div class="ple-popup-form-wrapper">
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                File Name</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>
                                ${FileInfo.fileName}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                Title</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>
                                ${FileInfo.fileTitle}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                Locale</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>
                               ${FileInfo.locale}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                Publisher</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>
                                ${FileInfo.publisher}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                File Type</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>${FileInfo.fileType}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>File Size</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>${FileInfo.fileSize}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                        <div class="ple-popup-form-label">
                            <label>
                                Part Number</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <label>${FileInfo.partNumber}</label>
                        </div>
                    </div>
                    <div class="ple-popup-form-row-seperator ple-popup-form-row-wrapper">&nbsp;</div>
                    <div class="ple-popup-form-row-wrapper ple-popup-form-rowDisplay-wrapper">
                    <br />
                        <div class="ple-popup-form-label">
                            <label>
                                Version</label>
                        </div>
                        <div class="ple-popup-form-field">
                            <input type="text" id="idVersion" size="30" name="contentVerion" maxlength="11" class="text-input">
                        </div>
                    </div>
                </div>
            </div>
            <div class="ple-popup-buttons">
                <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
                    <div class="ple-button-left">
                    </div>
                    <div class="ple-button-middle">
                        <input type="submit" id="submitStorecontent" value="Catalog it" class="ple-button" onclick="this.disabled=true;return false;"/>
                    </div>
                    <div class="ple-button-right">
                    </div>
                </label>
                <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
                    <div class="ple-button-left">
                    </div>
                    <div class="ple-button-middle">
                        <input id="" type="button" class="ple-button jqmClose" value="Cancel">
                    </div>
                    <div class="ple-button-right">
                    </div>
                </label>
            </div>
        </div>
        <!-- Footer -->
        <div class="ple-popup-footer">
        </div>
        <!-- End Footer -->
        </form>
    </div>
		
		
		<div class="ple-container-inner-wrapper">
			<div class="ple-breadcrumb"></div>
			<!--  New design start -->
			<div class="ple-mainContent-header-wrapper">
                    <div class="ple-mainContent-header-title">
                        Select Content
                    </div>
                </div>
                <div class="ple-form-description">
                    Please select if this a new content or a new version of existing content.
                </div>
                <div class="ple-form-wrapper">
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>File Name</label>
                        </div>
                        <div class="ple-form-field">
                            <label>${FileInfo.fileName}</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Title</label>
                        </div>
                        <div class="ple-form-field">
                            <label>${FileInfo.fileTitle}</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Locale</label>
                        </div>
                        <div class="ple-form-field">
                            <label>${FileInfo.locale}</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Publisher</label>
                        </div>
                        <div class="ple-form-field">
                            <label>${FileInfo.publisher}</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Part Number</label>
                        </div>
                        <div class="ple-form-field">
                            <label>${FileInfo.partNumber}</label>
                        </div>
                    </div>
	                <form action="/ccs/contentCatalog/showExistingCatalogedContent" method="post" >
	                    <div class="ple-form-row-wrapper ple-form-grey-box  ple-form-rowDisplay-wrapper">
	                        <div class="ple-form-label">
	                            <label>Select Content</label>
	                        </div>
	                        <div class="ple-form-field">
	                            <input type="radio" id="idContentOption1" name="nmContentOption" value="New Content" /> New Content&nbsp;&nbsp;&nbsp;&nbsp;
	                            <input type="radio" id="idContentOption2" name="nmContentOption" value="Updated Version" style="" /> New Version of Existing Content
	                        </div>
	                    </div>
	                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
	                        <div class="ple-buttons">
	                            <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
	                                <div class="ple-button-left">
	                                </div>
	                                <div class="ple-button-middle">
	                                    <input type="submit" id="idExistFile" name="nmExistFile" value="Next" class="ple-button" />
	                                </div>
	                                <div class="ple-button-right">
	                                </div>
	                            </label>
	                            <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
	                                <div class="ple-button-left">
	                                </div>
	                                <div class="ple-button-middle">
	                                	<input type="submit" name="_action_cancelFileChksum" value="Back" id="idBackBtn" name="nmBackBtn" onclick="return true;" class="ple-button" />
	                                </div>
	                                <div class="ple-button-right">
	                                </div>
	                            </label>
	                        </div>
	                    </div>
                    </form>	
                </div>
                <!--  New design End -->
		
		</div>
	
	    </div>
