/*****Details functionality for search result******/
var detailsOrderJSON;
var orderTable;
function initializeShowOrderDetails(json,datatable) {
	detailsOrderJSON = json;
	orderTable=datatable;
}
function showOrderDetails(obj){
	var nTr = $(obj).parents('tr')[0];
	var pos = orderTable.fnGetPosition(nTr);
	if (orderTable.fnIsOpen(nTr))
	{
		$(obj).removeClass('ple-icon-collapse');
		$(obj).addClass('ple-icon-expand');
		$(obj).parents('tr').removeClass('ple-selected-row');
		orderTable.fnClose( nTr );
	}
	else
	{
		$(obj).removeClass('ple-icon-expand');
		$(obj).addClass('ple-icon-collapse');
		$(obj).parents('tr').addClass('ple-selected-row');
		orderTable.fnOpen( nTr, getOrderDetails(pos), 'ple-row-details' );
	}
}

function getOrderDetails(pos)
{
	var sOut = '<div>';
	
	// details HTML
	if($(detailsOrderJSON.details).length > 0){
		
		var detailHTML = '<div class="ple-details-main">';
		
		// Details section specifically designed for Order page
		if(detailsOrderJSON.aaData[pos].orderId) {
			var colHTML = '<table class="ple-details-tables"><colgroup><col style="width:18%"><col style="width:32%"><col style="width:32%"><col style="width:18%"></colgroup>';
			
			var count = 0;
			$.each(detailsOrderJSON.details,function(i){
				var key = detailsOrderJSON.details[i][0];
				var detailsValue = "" + detailsOrderJSON.aaData[pos][''+key+''] + "";
				
				count ++;
				if(count==1){
					colHTML += '<tr><td class="ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==5 || count==8){
					colHTML += '<tr><td class="ple-details-col-right-border" colspan="2"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==2 || count==3 || count==6){
					colHTML += '<td class="ple-details-col-left-border ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==4 || count==7){
					colHTML += '<td class="ple-details-col-left-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td></tr>';
				}
			});
			
			if(count==8){
				colHTML += '</tr>';
			}
		}
		else {
			var colHTML = '<table class="ple-details-tables"><colgroup><col style="width:33%"><col style="width:33%"><col style="width:33%"></colgroup>';
			
			var count = 1;
			$.each(detailsOrderJSON.details,function(i){
				var key = detailsOrderJSON.details[i][0];
				var detailsValue = "" + detailsOrderJSON.aaData[pos][''+key+''] + "";
				if(count%3 == 1){
					colHTML += '<tr><td class="ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}
				if(count%3 == 2){
					colHTML += '<td class="ple-details-col-left-border ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}
				if(count%3 == 0){
					colHTML += '<td class="ple-details-col-left-border"><div class="ple-details-audit-label">'+detailsOrderJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td></tr>';
				}
				count ++;
			});
			
			if(($(detailsOrderJSON.details).length)%3 != 0){
				colHTML += '</tr>';
			}
		}
		
		colHTML += '</table></div>';
		detailHTML += colHTML;
		
		sOut += detailHTML;
	}
	
	// Custom details
	if($(detailsOrderJSON.customDetails).length > 0) {
		var customHTML = '';
		
		if($(detailsOrderJSON.details).length > 0) {
			customHTML += '<div class="ple-details-custom ple-details-custom-border">';
		}
		else {
			customHTML += '<div class="ple-details-custom">';
		}
		
		customHTML += getCustomDetails(pos);
		customHTML += '</div>';
		sOut += customHTML;
		
	}
	
	// audit details HTML
	
		var auditHTML = '';
		if(($(detailsOrderJSON.details).length > 0) || ($(detailsOrderJSON.customDetails).length > 0)){
			auditHTML += '<div class="ple-details-audit ple-details-audit-border">';
		}
		else {
			auditHTML += '<div class="ple-details-audit">';
		}
	

		auditHTML += '<div class="ple-details-audit-col">';
		auditHTML += '<div class="ple-details-audit-label">'+detailsOrderJSON.auditDetails.createdBy+'</div>';
	
		if(detailsOrderJSON.aaData[pos]['addedBy']){
			auditHTML += '<div class="ple-details-audit-data">'+detailsOrderJSON.aaData[pos]['addedBy']+' on '+detailsOrderJSON.aaData[pos]['addedDate']+'</div>';
		}else{
			auditHTML += '<div class="ple-details-audit-data">-</div>';
		}
		auditHTML += '</div>';
		
		if(detailsOrderJSON.aaData[pos]['modifiedBy'] && detailsOrderJSON.auditDetails.modifiedBy){
			auditHTML += '<div class="ple-details-audit-col">';
			auditHTML += '<div class="ple-details-audit-label">'+detailsOrderJSON.auditDetails.modifiedBy+'</div>';

			if(detailsOrderJSON.aaData[pos]['modifiedBy']){
				auditHTML += '<div class="ple-details-audit-data">'+detailsOrderJSON.aaData[pos]['modifiedBy']+' on '+detailsOrderJSON.aaData[pos]['modifiedDate']+'</div>';
			}else{
				auditHTML += '<div class="ple-details-audit-data">-</div>';
			}
			auditHTML += '</div>';
		}
		
		auditHTML += '</div>';
		sOut += auditHTML;
	

	sOut += '</div>';
	return sOut;
}

/*****Details functionality END******/


/*****Expand All/Collapse All details functionality******/
function expandOrderAll(rowObject)
{	
	var rowPos = $(rowObject).parents('tr')[0];
	var rowIndex = orderTable.fnGetPosition(rowPos);
	if(!(orderTable.fnIsOpen(rowPos))){
		$(rowObject).removeClass('ple-icon-expand');
		$(rowObject).addClass('ple-icon-collapse');
		$(rowObject).parents('tr').addClass('ple-selected-row');
		orderTable.fnOpen(rowPos, getOrderDetails(rowIndex), 'ple-row-details');
	}
}
function collapseOrderAll(rowObject)
{
	var rowPos = $(rowObject).parents('tr')[0];
	var rowIndex = orderTable.fnGetPosition(rowPos);
	if(orderTable.fnIsOpen(rowPos)){
		$(rowObject).removeClass('ple-icon-collapse');
		$(rowObject).addClass('ple-icon-expand');
		$(rowObject).parents('tr').removeClass('ple-selected-row');
		orderTable.fnClose(rowPos);
	}			
}

