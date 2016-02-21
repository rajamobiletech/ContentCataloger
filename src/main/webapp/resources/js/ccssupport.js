/** *** Option Form Js **** */
function isBlankField(cvField) {
	if (cvField.value == "" || cvField.value == null) {
		return true;
	}
	return false;
}
var strVersion;
function checkVersion(btn) {
	strVersion = document.getElementById("idVersion");
	var versionValue = strVersion.value;
	var error_msgs = "";

	if (!(isBlankField(strVersion))) {
		var versionRegex = /^\b[1-9][0-9]{0,1}\.[0-9][0-9]{0,1}\.[0-9][0-9]{0,1}$/;
		if (!versionRegex.test(versionValue)) {
			error_msgs += "Enter valid version.";
		} else {
			var parts = versionValue.split(".");
			if ((parts[1].length == 2 && parts[1][0] == '0')
					|| (parts[2].length == 2 && parts[2][0] == '0')) {
				error_msgs += "Enter valid version.";
			}
		}
	} else {
		error_msgs += "Version can't be blank.";
	}

	if (error_msgs != "") {
		alert(error_msgs);
		btn.disabled = false;
		return false;
	}

	btn.disabled = true;
	return true;
}

$(function() {
	// a workaround for a flaw in the demo system
	// (http://dev.jqueryui.com/ticket/4375), ignore!
	$("#dialog:ui-dialog").dialog("destroy");

	$("#dialog-modal").dialog({
		height : 400,
		width : 450,
		modal : true,
		autoOpen : false
	});
});

$(document)
		.ready(
				function() {
					$('#dialog').jqm();
					$('#idExistFile').click(
							function() {
								if (!($("#idContentOption1").attr("checked"))
										&& !($("#idContentOption2")
												.attr("checked"))) {
									alert("Please select one of the option.");
									return false;
								}
								if ($("#idContentOption1").attr("checked")) {
									$('#dialog').jqmShow();
									$('#idVersion').focus();
									return false;
								}
								return true;
							});

					$('#submitStorecontent')
							.click(
									function() {
										if (checkVersion(this)) {
											$("#dialog").jqmHide();
											$.blockUI({
														message : '<h3><b>Request in progress, Please wait..!!</b></h3>',
														css : {
															border : 'none',
															padding : '15px',
															backgroundColor : '#000',
															'-webkit-border-radius' : '10px',
															'-moz-border-radius' : '10px',
															opacity : .5,
															color : '#fff'
														}
													});
											$('#frmStorecontent').submit();
											// setTimeout($.unblockUI, 2000);
										}
									});
				});

/** *************** Checksum form JS ******************** */

$("#idChksum").focus();
$('#idValidateBtn').live('click', function(e) {
	if ($("#idChksum").val() == "" || $("#idChksum").val() == null) {
		alert("Please enter the file checksum.");
		return false;
	} else {
		/** ****************************************** */
		$.blockUI({
			message : "Request in progress, Please wait..!!",
			css : {
				border : 'none',
				padding : '15px',
				backgroundColor : '#000',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .5,
				color : '#fff'
			}
		});
		return true;
	}
});
