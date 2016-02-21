<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<core:set var="returnToFTPList" value="${contextPath}/ccs/contentCataloger"/> 

<script type="text/javascript">
	

$(function() {	
	$(".ple-button-cancel").click(function(){
		 $("#cancelForm").submit(); 
		});
	$("#frmChecksum").click(function() {
		$("#userChecksumId").val($("#idChksum").val());
			$("#validateForm").submit();
	});
});
	
</script>
<div class="ple-container">
	<div class="ple-container-inner-wrapper">
		<div class="ple-breadcrumb">
		</div>
		<div class="ple-mainContent-header-wrapper">
                   <div class="ple-mainContent-header-title">
                       Checksum Validation
                   </div>
               </div>
               
               	<div class="ple-form-description">
                    Please enter the file checksum.
                </div>
               
               
               <form action="/ccs/checksumValidation" method="post" name="frmChecksum" id="validateForm">
          		<input type="hidden" value="${FileInfo.fileName}" name="selectedFileName">
          		<input type="hidden" value="" name="userChecksum" id="userChecksumId">
               
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
                    <div class="ple-form-row-wrapper ple-form-grey-box  ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>
                                Enter File Checksum</label>
                        </div>
                        <div class="ple-form-field">
                        	<input type="text" id="idChksum" size="50" name="nmChksum" value="" class="text-input">
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-buttons">
				
					 	<label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
		                     <div class="ple-button-left">
		                     </div>
		                     <div class="ple-button-middle">
		                        <input type="button" value="Validate" class="ple-button-save" class="ple-button-validate" id="frmChecksum"/>
		                     </div>
		                     <div class="ple-button-right">
		                     </div>
		                 </label>
		                 <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
		                     <div class="ple-button-left">
		                     </div>
		                     <div class="ple-button-middle">
		                       <input type="button" value="Cancel" class="ple-button-cancel" id="ple-button-cancel" />
		                     </div>
		                     <div class="ple-button-right">
		                     </div>
		                 </label>
                        </div>
                    </div>
                </div>
               </form>
               <form:form id="cancelForm" name="cancelChecksum" method="post" action="${returnToFTPList}"></form:form>
               <!-- end of ple-form-wrapper -->
             </div>
	</div>