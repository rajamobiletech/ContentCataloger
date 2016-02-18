/*****Details functionality for search result******/
var detailsJSON;
var oTable;
function initializeShowDetails(json,datatable) {
	detailsJSON = json;
	oTable=datatable;
}
function showDetails(obj){
	var nTr = $(obj).parents('tr')[0];
	var pos = oTable.fnGetPosition(nTr);
	if (oTable.fnIsOpen(nTr))
	{
		$(obj).removeClass('ple-icon-collapse');
		$(obj).addClass('ple-icon-expand');
		$(obj).parents('tr').removeClass('ple-selected-row');
		oTable.fnClose( nTr );
	}
	else
	{
		$(obj).removeClass('ple-icon-expand');
		$(obj).addClass('ple-icon-collapse');
		$(obj).parents('tr').addClass('ple-selected-row');
		oTable.fnOpen( nTr, getDetails(pos), 'ple-row-details' );
	}
}

function getDetails(pos)
{
	var sOut = '<div>';
	
	// details HTML
	if($(detailsJSON.details).length > 0){
		
		var detailHTML = '<div class="ple-details-main">';
		
		// Details section specifically designed for Order page
		if(detailsJSON.aaData[pos].orderId) {
			var colHTML = '<table class="ple-details-tables"><colgroup><col style="width:18%"><col style="width:32%"><col style="width:32%"><col style="width:18%"></colgroup>';
			
			var count = 0;
			$.each(detailsJSON.details,function(i){
				var key = detailsJSON.details[i][0];
				var detailsValue = "" + detailsJSON.aaData[pos][''+key+''] + "";
				
				count ++;
				if(count==1){
					colHTML += '<tr><td class="ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==5 || count==8){
					colHTML += '<tr><td class="ple-details-col-right-border" colspan="2"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==2 || count==3 || count==6){
					colHTML += '<td class="ple-details-col-left-border ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}else if(count==4 || count==7){
					colHTML += '<td class="ple-details-col-left-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td></tr>';
				}
			});
			
			if(count==8){
				colHTML += '</tr>';
			}
		}
		else {
			var colHTML = '<table class="ple-details-tables"><colgroup><col style="width:33%"><col style="width:33%"><col style="width:33%"></colgroup>';
			
			var count = 1;
			$.each(detailsJSON.details,function(i){
				var key = detailsJSON.details[i][0];
				var detailsValue = "" + detailsJSON.aaData[pos][''+key+''] + "";
				if(count%3 == 1){
					colHTML += '<tr><td class="ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}
				if(count%3 == 2){
					colHTML += '<td class="ple-details-col-left-border ple-details-col-right-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td>';
				}
				if(count%3 == 0){
					colHTML += '<td class="ple-details-col-left-border"><div class="ple-details-audit-label">'+detailsJSON.details[i][1]+'</div><div class="ple-details-data">'+escapeCharDetails(detailsValue)+'</div></td></tr>';
				}
				count ++;
			});
			
			if(($(detailsJSON.details).length)%3 != 0){
				colHTML += '</tr>';
			}
		}
		
		colHTML += '</table></div>';
		detailHTML += colHTML;
		
		sOut += detailHTML;
	}
	
	// Custom details
	if($(detailsJSON.customDetails).length > 0) {
		var customHTML = '';
		
		if($(detailsJSON.details).length > 0) {
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
		if(($(detailsJSON.details).length > 0) || ($(detailsJSON.customDetails).length > 0)){
			auditHTML += '<div class="ple-details-audit ple-details-audit-border">';
		}
		else {
			auditHTML += '<div class="ple-details-audit">';
		}
	

		auditHTML += '<div class="ple-details-audit-col">';
		auditHTML += '<div class="ple-details-audit-label">'+detailsJSON.auditDetails.createdBy+'</div>';
	
		if(detailsJSON.aaData[pos]['addedBy']){
			auditHTML += '<div class="ple-details-audit-data">'+detailsJSON.aaData[pos]['addedBy']+' on '+detailsJSON.aaData[pos]['addedDate']+'</div>';
		}else{
			auditHTML += '<div class="ple-details-audit-data">-</div>';
		}
		auditHTML += '</div>';
		
		if(detailsJSON.aaData[pos]['modifiedBy'] && detailsJSON.auditDetails.modifiedBy){
			auditHTML += '<div class="ple-details-audit-col">';
			auditHTML += '<div class="ple-details-audit-label">'+detailsJSON.auditDetails.modifiedBy+'</div>';

			if(detailsJSON.aaData[pos]['modifiedBy']){
				auditHTML += '<div class="ple-details-audit-data">'+detailsJSON.aaData[pos]['modifiedBy']+' on '+detailsJSON.aaData[pos]['modifiedDate']+'</div>';
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
function expandAll(rowObject)
{	
	var rowPos = $(rowObject).parents('tr')[0];
	var rowIndex = oTable.fnGetPosition(rowPos);
	if(!(oTable.fnIsOpen(rowPos))){
		$(rowObject).removeClass('ple-icon-expand');
		$(rowObject).addClass('ple-icon-collapse');
		$(rowObject).parents('tr').addClass('ple-selected-row');
		oTable.fnOpen(rowPos, getDetails(rowIndex), 'ple-row-details');
	}
}
function collapseAll(rowObject)
{
	var rowPos = $(rowObject).parents('tr')[0];
	var rowIndex = oTable.fnGetPosition(rowPos);
	if(oTable.fnIsOpen(rowPos)){
		$(rowObject).removeClass('ple-icon-collapse');
		$(rowObject).addClass('ple-icon-expand');
		$(rowObject).parents('tr').removeClass('ple-selected-row');
		oTable.fnClose(rowPos);
	}			
}
/*****Expand All/Collapse All details functionality END******/

function checkPopupblocker()
{
  isEnabled=false;
  var windowName="Testing pop-up blocker";
  var popUp = window.open("about:blank","","directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no");
  if (popUp == null || typeof(popUp)=='undefined') {   
	  isEnabled=true; 
  }//else if(popUp.innerHeight < 1){ // condition to check popupblocker status for Chrome
//	  isEnabled=true;
//}
  else{
	 try{ 
		 popUp.blur();
	 }catch(e){}
	  popUp.close();
  }
    return isEnabled;
}

function pleAlert(msg){
	pleAlert(msg,"Alert","250","350");
}

function pleAlert(msg,title,height,width){
	var alertDiv = $(document.createElement('div'));
	alertDiv.attr("title", title );
	alertDiv.attr("style","text-align: left");
	alertDiv.html(msg);
	alertDiv.dialog({
		resizable: false,
		height:height,
		width:width,
		modal: true,
		autoOpen:true,
		buttons: {
			"Ok" : function() {
				$( this ).dialog( "close" );
			}
		}
	}); 
}

function pleConfirm(dialogText, dialogTitle, height, width, okFunc, cancelFunc) {
    $('<div style="word-wrap: break-word;">' + dialogText + '</div>').dialog({
        draggable: false,
        modal: true,
        resizable: false,
        height:height,
        width:width,
        title: dialogTitle || 'Confirm',
        buttons: {
            Yes: function () {
                if (typeof (okFunc) == 'function') { setTimeout(okFunc, 50); }
                $(this).dialog('destroy');
            },
            No: function () {
                if (typeof (cancelFunc) == 'function') { setTimeout(cancelFunc, 50); }
                $(this).dialog('destroy');
            }
        }
    });
    $('.ui-dialog .ui-dialog-content').css("overflow","auto");
}

function contentAlert(msg){
	
	var button = $(document.createElement('button'));
	button.attr("id", "popupclose" );
	button.attr("class", "button small-button blue-gradient" );
	button.html("Ok");

	var buttonDiv = $(document.createElement('div'));
	buttonDiv.attr("class", "buttonHolder" );
	buttonDiv.append(button) 

	var text = $(document.createElement('p'));
	text.html(msg);	

	var popupDiv = $(document.createElement('div'));
	popupDiv.attr("id", "popup" );
	popupDiv.attr("class", "popHolder" );

	popupDiv.append(text);
	popupDiv.append(buttonDiv);
	$("#popupcontainer").append(popupDiv);
	$("#popupcontainer").addClass("overlay");
}


$(function (){
	
	$('#popupclose').live("click",function(event){
		$("#popupcontainer").removeClass("overlay");
		$("#popup").remove();
	});
	   
	 
	 /*
	setTimeout(function() {
		    $('#msg').fadeOut('fast');
		}, 12000);
	
	 
	 setTimeout(function() {
		    $('#errmessage').fadeOut('fast');
		}, 12000);		
		*/
});


function escapeChar(str){
	//return str.replace("&amp;","&").replace("&gt;",">").replace("&lt;","<");
	return str.replace(/&amp;/g,"&").replace(/&gt;/g,">").replace(/&lt;/g,"<").replace(/&#39;/g,"'").replace(/&quot;/g,"\"");
}

function escapeCharDetails(str){	
	return str.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/'/g, '&#39;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}