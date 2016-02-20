<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<core:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<core:set var="checksum" value="${contextPath}/ccs/checksumForm"></core:set> 
<core:set var="advOrderSearch" value="${contextPath}/ccs/ftpSearch"></core:set>
<style>
.ui-widget-content
{
	border: none;
}
.ple-custom-success-message
{
	margin-bottom: 15px!important;
}

.ple-custom-order-expand-legend-wrapper
{
	width:98.9% !important;
}


.ple-advancedSearch-label {
    float: left;
    width: 36%;
}

.ple-custom-advancedSearch-label {
	width: 23% !important;
}
</style>

<body onbeforeunload="HandleBackFunctionality()">

<script type="text/javascript">
	var oTable1;
	var searchResultJSON1;
	var oTable2;
	var searchResultJSON2;
	var detailImg = '<div id="ple-icon-expand-collapse" class="ple-icon-expand" ></div>';
	var searchText = "";
	//var searchResultJSON ;
	var isClickingOffering = false;
	var selectedFtpID = "";

	function selectCheckBox(fileName){
		if(selectedFtpID) {
			document.getElementById(selectedFtpID).checked = false;
		}
		selectedFtpID = "selectedFTP|"+fileName;
		if(document.getElementById(selectedFtpID).checked == true){
			console.log(fileName);
	   }
	}


	function HandleBackFunctionality()
	{
	}
	

	$(document).ready(function() {
		
		
		var access=true;
		var index=0;
		$("#myTabs").tabs({ selected: index });
		var lpAccess = true;
		
	
		var defaultFTPSearch = "${contextPath}/ccs/getDefaultFTPSearch";
		var defaultCatalogSearch = "${contextPath}/ccs/getDefaultCatalogSearch"
		
	     oTable2 = $("#dataTables1").dataTable({
		    "sPaginationType": "full_numbers",
		    "bJQueryUI": true,
		    "bRetrieve":false,		
		    "aoColumnDefs": [
	                         {
	                           "fnRender": function ( o, val ) {
	                             return String(val)
	                               .replace(/&/g, '&amp;')
	                               .replace(/"/g, '&quot;')
	                               .replace(/'/g, '&#39;')
	                               .replace(/</g, '&lt;')
	                               .replace(/>/g, '&gt;');
	                    
	                           },
	                           "aTargets": [1,2,3]
	                         }
	                       ],
		    "aoColumns": [{ "bSortable": false,"sDefaultContent": detailImg },
		                  {"mDataProp": "programName" , "sDefaultContent": "-"}, {"mDataProp": "orderNumber","sDefaultContent": "-"}, 
		                  {"mDataProp":"skuId","sDefaultContent": "-"},{"mDataProp":"quantity","sDefaultContent": "-"} ,
		                  {"mDataProp":"orderDate","sDefaultContent": "-"}
		    			  ],
		    "sDom": 't<"fg-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"lip>',
		    "aaSorting": [],
		  
		 	//"bProcessing": true,
			"bServerSide": true,
			"bAutoWidth": false,
			"sAjaxSource": defaultCatalogSearch,
			"sServerMethod": "POST", 
			"fnServerData": function ( sSource, aoData, fnCallback ) {
				
				aoData.push(
		            { "name": "fileName", "value": searchText }
		        );
				
		        $.getJSON( sSource, aoData, function (json) { 
		            fnCallback(json);
		            searchResultJSON2 = json;
		            initializeShowDetails(searchResultJSON2,oTable2);
		        } );
		    }
		});
		
		oTable1 = $("#dataTables").dataTable({
		    "sPaginationType": "full_numbers",
		    "bJQueryUI": true,
		    "bRetrieve":false,
		    "aoColumnDefs": [
	                         {
	                           "fnRender": function ( o, val ) {
	                             return String(val)
	                               .replace(/&/g, '&amp;')
	                               .replace(/"/g, '&quot;')
	                               .replace(/'/g, '&#39;')
	                               .replace(/</g, '&lt;')
	                               .replace(/>/g, '&gt;');
	                    
	                           },
	                           "aTargets": [1,2,3,4]
	                         }
	                       ],		   		  
		    "aoColumns": [
		                  { "bSortable": false,"sDefaultContent": "","fnRender": function(oObj, val){
		                	  var ftpRadio = oObj.aData["fileName"];
		                	  return ("<input type=radio id=selectedFTP|"+ftpRadio+" value=ftp class=ple-dbook-checkBox onClick='selectCheckBox(\""+ftpRadio+"\");'>");
		                  }},
		                  {"mDataProp": "fileName"}, 
		                  {"mDataProp": "fileType"}, 
		                  {"mDataProp":"fileSize"},
		                  {"mDataProp":"fileLastModifiedDate"}],
		                  
		    "sDom": 't<"fg-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"lip>',
		    "aaSorting": [],		 	
			"bServerSide": true,
			"sAjaxSource": defaultFTPSearch,
			"sServerMethod": "POST", 
			"fnServerData": function (sSource, aoData, fnCallback ) {
				aoData.push(
		            { "name": "fileName", "value": searchText }
		        );
				var isDefault = sSource.indexOf("getDefaultOrderSearch") > -1;
		        $.getJSON( sSource, aoData, function (json1) {
		            fnCallback(json1);
		            searchResultJSON1 = json1;
		            initializeShowOrderDetails(searchResultJSON1,oTable1);
		        });
		    }
		});

		function initRowExpander1(){
			//Show details 
			initializeShowOrderDetails(searchResultJSON1,oTable1);
		}
		
		function initRowExpander2(){
			//Show details 
			initializeShowDetails(searchResultJSON2,oTable2);
		}
		
		initializeShowOrderDetails(searchResultJSON1,oTable1);
		// PROCESSED ORDER 
		$('#dataTables tbody td div#ple-icon-expand-collapse').live('click',function () {
			showOrderDetails(this);
		});
		//Expand All
		$('#ple-expand-all').click(function() {
			$('#dataTables tbody td div#ple-icon-expand-collapse').each(function(){
				expandOrderAll(this);
			});
		});
	 	//Collapse All
		$('#ple-collapse-all').click(function() {
			$('#dataTables tbody td div#ple-icon-expand-collapse').each(function(){
				collapseOrderAll(this);
			});
		});
		
	 	// UNPROCESSED ORDER 
		$('#dataTables1 tbody td div#ple-icon-expand-collapse').live('click',function () {
			showDetails(this);
		});
		//Expand All 
		$('#ple-expand-all2').click(function() {
			$('#dataTables1 tbody td div#ple-icon-expand-collapse').each(function(){
				expandAll(this);
			});
		});
	 	//Collapse All 
		$('#ple-collapse-all2').click(function() {
			$('#dataTables1 tbody td div#ple-icon-expand-collapse').each(function(){
				collapseAll(this);
			});
		});
	 	
		$('#processedOrder').click(function() {
			initializeShowOrderDetails(searchResultJSON1,oTable1);
		});
		
		$('#unprocessOrder').click(function(){
			initializeShowDetails(searchResultJSON2,oTable2);
		});
		

		$("#btnSaveObject").click(function() {
			if(selectedFtpID) {
				$("#selectedFTPHiddenInputId").val(selectedFtpID);
				$("#checksumFormId").submit();
			}else {
				alert("Please select any content.");
			}
		});
		
		// DETAILS FUNCTIONALITY END 

		var isAdvanceSearchHideForProcessedOrders = $('#isAdvanceSearchHideForProcessedOrders').val();
		var isAdvanceSearchHideForUnProcessedOrders = $('#isAdvanceSearchHideForUnProcessedOrders').val();
		if (isAdvanceSearchHideForProcessedOrders == "false") {
			$("#ple-advancedSearch-wrapper-for-ManagementPage-1").show();
            $("#ple-search-arrow-1").css("background-image", "url(${contextPath}/resources/images/search-arrow-up.png)");
        } else {
            $("#ple-advancedSearch-wrapper-for-ManagementPage-1").hide();
            $("#ple-search-arrow-1").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
        }
		if (isAdvanceSearchHideForUnProcessedOrders == "false") {
            $("#ple-advancedSearch-wrapper-for-ManagementPage-2").show();
            $("#ple-search-arrow-2").css("background-image", "url(${contextPath}/resources/images/search-arrow-up.png)");
        } else {
            $("#ple-advancedSearch-wrapper-for-ManagementPage-2").hide();
            $("#ple-search-arrow-2").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
        }
		
		$(".ple-search-box").focus(function(srcc)
   	    {
   	        if ($(this).val() == $(this)[0].title)
   	        {
   	            $(this).removeClass("ple-search-box-defaultTextActive");
   	            $(this).val("");
   	        }
   	    });
   	    
   	    $(".ple-search-box").blur(function()
   	    {
   	        if ($(this).val() == "")
   	        {
   	            $(this).addClass("ple-search-box-defaultTextActive");
   	            $(this).val($(this)[0].title);
   	        }
   	    });
   	    
   		$(".ple-search-box-defaultTextActive").blur();
   		
   		/***********Basic Search************/
   		var adSearch=0;
   		
   		$(document).bind('keypress', function(e) {
   		 	var selectedIndex=$("#myTabs").tabs("option","selected");   		 	  
			   		 	if(adSearch == 1){
			   		 	isAdvancedSearch == true;
			   		 	}   		 	
    	    			if(e.keyCode == 13) {   	    				 	    		
    	    					if(selectedIndex == 1){
    	    						unprocessOrderAdvanceSearch();
				    	    		}else{
				    	    			contentFTPSearch();
				    	    		}    			
    	       			 return false;
    	       		}
    		});
   		
   		$("#ftp_search_btn").click(function() {   			
   			isAdvancedSearch = false;
   			contentFTPSearch();
        });
   		
   		$("#unprocessed_order_submit_btn").click(function() {   			
   			isAdvancedSearch = false;
   			unprocessOrderAdvanceSearch();
        });
	
	 		
   		/****************************************/

   		$("#orderSearchTxt").keyup(function(){
   		    $('#programName').val($('#orderSearchTxt').val());
	    });
	    
	    $("#programName").keyup(function(){
	        $('#orderSearchTxt').val($('#programName').val());
	    });
	    
	    $("#unprocessedOrderSearchTxt").keyup(function(){
	        $('#programName2').val($('#unprocessedOrderSearchTxt').val());
	    });
	    
	    $("#programName2").keyup(function(){
	        $('#unprocessedOrderSearchTxt').val($('#programName2').val());
	    });
   		
   		/***********Advanced Search************/
   	    $("#ple-search-arrow-1").click(function() {
   	        if ($("#ple-advancedSearch-wrapper-for-ManagementPage-1").is(":hidden")) {
   	            $("#ple-advancedSearch-wrapper-for-ManagementPage-1").show();
   	            $("#ple-search-arrow-1").css("background-image", "url(${contextPath}/resources/images/search-arrow-up.png)");
   	         adSearch=1;
   	      	isAdvancedSearch = true;
   	        
   	        } else {
   	            $("#ple-advancedSearch-wrapper-for-ManagementPage-1").hide();
   	            $("#ple-search-arrow-1").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
   	         adSearch=0;
   	        }
   	    });

   	    $("#ple-button-advancedSearch-close-1").click(function() {
   	        $("#ple-advancedSearch-wrapper-for-ManagementPage-1").hide();
   	        $("#ple-search-arrow-1").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
   	     adSearch=0;
   	    });
   	    
        $("#ple-search-arrow-2").click(function() {
            if ($("#ple-advancedSearch-wrapper-for-ManagementPage-2").is(":hidden")) {
                $("#ple-advancedSearch-wrapper-for-ManagementPage-2").show();
                $("#ple-search-arrow-2").css("background-image", "url(${contextPath}/resources/images/search-arrow-up.png)");
                isAdvancedSearch = true;
                adSearch=1;
            } else {
                $("#ple-advancedSearch-wrapper-for-ManagementPage-2").hide();
                $("#ple-search-arrow-2").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
                adSearch=0;
            }
        });

        $("#ple-button-advancedSearch-close-2").click(function() {
            $("#ple-advancedSearch-wrapper-for-ManagementPage-2").hide();
            $("#ple-search-arrow-2").css("background-image", "url(${contextPath}/resources/images/search-arrow.png)");
            adSearch=0;
        });
        
        
        function contentFTPSearch(){
         	searchText =($("#orderSearchTxt").val() == undefined || $("#orderSearchTxt").val() == '') ? '' : $("#orderSearchTxt").val().trim();;
 	        var oSettings = oTable1.fnSettings() ;
	        oSettings.sAjaxSource = defaultFTPSearch;
	        oTable1.fnDraw();
        }
        
        function unprocessOrderAdvanceSearch(){
       	 programName = ($("#programName2").val() == undefined || $("#programName2").val() == '') ? '' : $("#programName2").val().trim();
         orderNumber = ($("#orderNumber2").val() == undefined || $("#orderNumber2").val() == '') ? '' : $("#orderNumber2").val().trim();
         SKUID = ($("#SKUID2").val() == undefined || $("#SKUID2").val() == '') ? '' : $("#SKUID2").val().trim();
         orderSaleType = $("#orderSaleType2").val();
          orderLPSiteId = ($("#orderLPSiteId2").val() == undefined || $("#orderLPSiteId2").val() == '') ? '' : $("#orderLPSiteId2").val().trim();
          orderLPSiteName = ($("#orderLPSiteName2").val() == undefined || $("#orderLPSiteName2").val() == '') ? '' : $("#orderLPSiteName2").val().trim();
          deliveryLPSiteName = ($("#deliveryLPSiteName2").val() == undefined || $("#deliveryLPSiteName2").val() == '') ? '' : $("#deliveryLPSiteName2").val().trim();
          deliveryLPSiteId = ($("#deliveryLPSiteId2").val() == undefined || $("#deliveryLPSiteId2").val() == '') ? '' : $("#deliveryLPSiteId2").val().trim();
          orderDateFrom = ($("#unProcessStartDate").val() == undefined || $("#unProcessStartDate").val() == '') ? '' : $("#unProcessStartDate").val().trim();
          orderDateTo = ($("#unProcessEndDate").val() == undefined || $("#unProcessEndDate").val() == '') ? '' : $("#unProcessEndDate").val().trim();
            var searchTxt = $("input#unprocessedOrderSearchTxt").val();
            if (null == searchTxt)
                searchTxt = "";
            if (isAdvancedSearch == false && (searchTxt == "" || searchTxt=="Search")) {
                $("label#search_unprocessed_order_error").text('<spring:message code="Order_Search_Error_Msg" />');
                $("label#search_unprocessed_order_error").show();
                $("input#searchTxt").focus();        
                return false;
             } else if (isAdvancedSearch == true && programName.length == 0 && orderNumber.length == 0 && SKUID.length == 0 && orderSaleType == "" && orderLPSiteId.length == 0  && orderLPSiteName.length == 0 && deliveryLPSiteName.length == 0 && deliveryLPSiteId.length == 0 && orderDateFrom.length==0 && orderDateTo.length==0) {
                 $("label#search_unprocessed_order_error").text('<spring:message code="Advance_Search_Error_Msg" />');
                 $("label#search_unprocessed_order_error").show();
                 return false;
             } else if(orderDateTo.length > 0 && orderDateFrom.length == 0) {
           	 	 $("label#search_order_error").text('<spring:message code="Advance_Date_Error_Msg" />');
             	 $("label#search_order_error").show();
             	return false;
             } else{      
                	var order = "unProcessed";
                	searchText =programName;
                	var blankStr = "";
        	        var oSettings = oTable2.fnSettings() ;
    	            oSettings.sAjaxSource = orderProcessedSearch;
    	            oTable2.fnDraw();
    	            $("#note").html("");
    	            $("label#search_unprocessed_order_error").text("");
                    $("label#search_unprocessed_order_error").show();
             }
        }
	});

</script>


<div class="ple-container ple-font-specs">
	<div class="ple-header-container-forTab">	
		<!-- Breadcrumbs -->
		<div class="ple-BreadCrumb">
	 	</div>
	  	<!-- End Breadcrumbs -->
	
		<!-- Title & search button-->
	 	<div class="ple-mainContent-header-wrapper">
		  	<div class="ple-mainContent-header-title">
		        <spring:message code="CCS_Title" />
		    </div>
		</div>
		<!-- End Title & search button -->
	<core:if test="${not empty actionMessage }">
		<div id='msg' class="ple-custom-success-message ple-message ple-success-message">
			<ple:actionmessage />
		</div>
	 </core:if>
	</div><!-- end of ple-header-container-forTab -->			 
	  <div class="ple-mainContent-wrapper"> 
		   <div id="myTabs"> 
				  <ul >
					<li><a href="#a" id="processedOrder">FTP View</a></li>
					<li><a href="#b" id="unprocessOrder">Catalog View</a></li>
		          </ul>
				  <div id="a">			
				  	 <div class="ple-searchContent-wrapper">
						<div class="ple-search-changeStatus-header-left-straight">
		                </div>
		                <div class="ple-search-changeStatus-wrapper">
		                   <div class="ple-search-wrapper">
		                   	<form name="searchForm" method="post" action="${orderSearch}" style="float:right">
		                        <div class="float-left">
		                              <input title="Search" id="orderSearchTxt" type="text" class="ple-search-box ple-search-box-defaultTextActive" />
		                              <input type="hidden" name="isBasicSearch" id="isBasicSearch" value="true" />
		                              <input type="hidden" name="orderTypeForAdvanceSearch" id="processedOrderForAdvanceSearch" value="processed" />
		                         </div>
		                         <div class="float-left">
		                             <input id="ftp_search_btn" class="ple-button-search" type="button" value=" " name="ftp_search_btn" />
		                          </div> 
		                     </form> 
		                    </div>
		                    <div class="float-left">
					      		 <p><label class="ple-searchError" for="search_order_error" id="search_order_error"></label></p>
					   		 </div>
					   	</div><!-- end of ple-search-changeStatus-wrapper -->	 
		                <div class="ple-search-changeStatus-header-right">
		                </div>
            		</div><!-- end of ple-searchContent-wrapper -->   
				       	 
					<div id="orderDiv" class="ple-grid-wrapper">
						<core:if test="${display eq 'yes' }">
				 			 
				 			<div id="note" class="ple-note" style="display:${topTenRecs}">
						  	 	<span class="required-fields">*</span>
						  	 	<core:choose>
									   <core:when test="${ ALL_RECORDS eq 'ALL' }">
									   		<spring:message code="All_Records_Note"/>
									   </core:when>
									   <core:otherwise>
									   		<spring:message code="Top_Ten_Records_Note" arguments="${recordCount}"/>
									   </core:otherwise>
								  </core:choose>	
							</div>
							
					     </core:if>
					    <div> 
						<table id="dataTables" class="display">
							<colgroup>
								<col style="width: 4%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
							</colgroup>
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="ftp_File_Name_Tbl_Header" /></th>
									<th><spring:message code="ftp_File_Type_Tbl_Header" /></th>
									<th><spring:message code="ftp_File_Size_Tbl_Header" /></th>
									<th><spring:message code="ftp_File_Date_Modified_Tbl_Header" /></th>
								</tr>
							</thead>
							 <tbody>
				                     
				                   </tbody>
						</table>
						<br>
						<div style="float:right;">
							 <form name="checksum" id="checksumFormId" method="post" action="${checksum}" style="float: right;"> 
							 <input type="hidden" value="" name="selectedFTP" id="selectedFTPHiddenInputId">
								<label class="ple-button-wrapper float-left ple-button-wrapper-multiple">
									<div class="ple-button-left"></div>
									<div class="ple-button-middle">
						         		<input type="button" class="ple-button-save" id="btnSaveObject" value="Next" name="Save"></input>
							      	</div>
									<div class="ple-button-right"></div>
								</label>
				         	</form>
		         		</div>
						</div>
					</div><!-- end of ple-grid-wrapper -->
				</div>	
				<div id ="b">
					<div class="ple-searchContent-wrapper">
						<div class="ple-search-changeStatus-header-left-straight">
		                </div>
		                <div class="ple-search-changeStatus-wrapper">
		                   <div class="ple-search-wrapper">
		                   	<form name="searchForm" method="post" action="${orderSearch}" style="float:right">
		                        <div class="float-left">
				          			<input type="text" id="unprocessedOrderSearchTxt" title="Search" class="ple-search-box ple-search-box-defaultTextActive"/>
				          			<input type="hidden" name="isBasicSearch" id="isBasicSearch" value="true" />
		                         	<input type="hidden" name="orderTypeForAdvanceSearch" id="unprocessedOrderForAdvanceSearch" value="unprocessed" />
				              	</div>
		                         <div class="ple-search-arrow" id="ple-search-arrow-2">
		                         </div>
		                         <div class="float-left">
		                             <input id="unprocessed_order_submit_btn" class="ple-button-search" type="button" value=" " name="unprocessed_order_submit_btn" />
		                          </div> 
		                     </form> 
		                    </div>
		                    <div class="float-left">
					      		 <p><label class="ple-searchError" for="search_unprocessed_order_error" id="search_unprocessed_order_error"></label></p>
					   		 </div>
			   			</div><!-- end of ple-search-changeStatus-wrapper -->	 
		                <div class="ple-search-changeStatus-header-right">
		                </div>
		                
		                <div class="ple-expand-legend-wrapper">
		                   <div class="ple-expand-wrapper">
		                   <a href="#" class="ple-expand-collapse-anchor">
		                        <div class="ple-expand" id="ple-expand-all2">
                        			<spring:message code="Label_For_Expand_All" />
                    			</div>
                    		</a>
                    		<a href="#" class="ple-expand-collapse-anchor">
                    			<div class="ple-collapse" id="ple-collapse-all2">
                        			<spring:message code="Label_For_Collapse_All" />
                    			</div>
                    		</a>
		                   </div>
		                </div><!-- end of ple-expand-legend-wrapper -->   
            		</div><!-- end of ple-searchContent-wrapper --> 
				     
					<div id="orderDiv1" class="ple-grid-wrapper">
						<core:if test="${display eq 'yes' }">
				 			 
				 			<div id="note" class="ple-note" style="display:${topTenRecs}">
						  	 	<span class="required-fields">*</span>
						  	 	<core:choose>
									   <core:when test="${ ALL_RECORDS eq 'ALL' }">
									   		<spring:message code="All_Records_Note"/>
									   </core:when>
									   <core:otherwise>
									   		<spring:message code="Top_Ten_Records_Note" arguments="${recordCount}"/>
									   </core:otherwise>
								  </core:choose>	
							</div>
							
					     </core:if>
					  <div> 
						<table id="dataTables1" class="display">
							<colgroup>
								<col style="width: 4%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
							</colgroup>
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="catalog_Id_Tbl_Header" /></th>
									<th><spring:message code="catalog_Title_Tbl_Header" /></th>
									<th><spring:message code="catalog_File_Name_Tbl_Header" /></th>
									<th><spring:message code="catalog_Locale_Tbl_Header" /></th>
									<th><spring:message code="catalog_Version_Tbl_Header" /></th>
									<th><spring:message code="catalog_Part_Number_Tbl_Header" /></th>
									<th><spring:message code="catalog_Publisher_Tbl_Header" /></th>
									<th><spring:message code="catalog_Date_Cataloged_Tbl_Header" /></th>
									<th><spring:message code="catalog_Type_Tbl_Header" /></th>
									<th><spring:message code="catalog_Size_Tbl_Header" /></th>
									<th><spring:message code="catalog_Catalog_Status_Tbl_Header" /></th>
								</tr>
							</thead>
							 <tbody>
				                     
				                   </tbody>
						</table>
						</div>
						</div>
					</div><!-- end of ple-grid-wrapper -->
				</div>
		</div><!-- end of myTabs -->
	</div>
		<input type="hidden" value="${isAdvanceSearchHideForProcessedOrders}" id="isAdvanceSearchHideForProcessedOrders">
		<input type="hidden" value="${isAdvanceSearchHideForUnProcessedOrders}" id="isAdvanceSearchHideForUnProcessedOrders">
</div><!-- end of ple-container -->