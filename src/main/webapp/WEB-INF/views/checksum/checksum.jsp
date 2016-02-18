<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

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
               
               
               <form action="/ccs/contentCatalog/checksumValidation" method="post" name="frmChecksum" id="frmChecksum">
                <div class="ple-form-wrapper">
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>File Name</label>
                        </div>
                        <div class="ple-form-field">
                            <label>2TEST_IMINS2-1.2.0-SG_en.epub</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Title</label>
                        </div>
                        <div class="ple-form-field">
                            <label>Managing Industrial Networks for Manufacturing with Cisco Technologies - Student Guide</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Locale</label>
                        </div>
                        <div class="ple-form-field">
                            <label>en</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Publisher</label>
                        </div>
                        <div class="ple-form-field">
                            <label>Cisco Systems, Inc. and sed</label>
                        </div>
                    </div>
                    <div class="ple-form-row-wrapper ple-form-rowDisplay-wrapper">
                        <div class="ple-form-label">
                            <label>Part Number</label>
                        </div>
                        <div class="ple-form-field">
                            <label>TEST-VSAPOUNOV-01.f75332ae-19fb-4a05-ac17-bf65bb6950a6</label>
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
                        <div class="ple-buttons">
                            <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
                                <div class="ple-button-left">
                                </div>
                                <div class="ple-button-middle">
                                    <input type="submit" name="_action_checksumValidation" value="Validate" class="ple-button" id="idValidateBtn">
                                </div>
                                <div class="ple-button-right">
                                </div>
                            </label>
                            <label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
                                <div class="ple-button-left">
                                </div>
                                <div class="ple-button-middle">
									<input type="submit" name="_action_cancelFileChksum" value="Cancel" class="ple-button" onclick="return true;">
                                </div>
                                <div class="ple-button-right">
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
               </form>
               <!-- end of ple-form-wrapper -->
             </div>
	</div>