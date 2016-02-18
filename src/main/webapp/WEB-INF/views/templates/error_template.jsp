<!DOCTYPE html>
<!--[if IE 7]><html lang="en" class="ie7"><![endif]-->
<!--[if IE 8]><html lang="en" class="ie8"><![endif]-->
<!--[if IE 9]><html lang="en" class="ie9"><![endif]-->
<!--[if gt IE 9]><!--><html lang="en"><!--<![endif]-->


<html ><head>
<title>Cisco Learning Network Space</title>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<core:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>
<link  type="text/css" rel="stylesheet" href="${contextPath}/css/jquery-ui-1.8.17.css"/>
<link  type="text/css" rel="stylesheet" href="${contextPath}/css/screen.css" />

<link href="${contextPath}/images/favicon.ico" rel="Shortcut Icon">
<meta name="description" content="" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script type="text/javascript">
function openAboutUs() {
    var aboutUsUrl = "http://www.cisco.com/web/about/index.html";
    window.open(aboutUsUrl, '_blank');
}
function openTermsAndConditions() {
    var termsAndConditionsUrl = "http://www.cisco.com/web/siteassets/legal/terms_condition.html";
    window.open(termsAndConditionsUrl, '_blank');
}
function openPrivacyStatement() { 
    var privacyStatementUrl = "http://www.cisco.com/web/siteassets/legal/privacy.html";
    window.open(privacyStatementUrl, '_blank');
}
function openCookiePolicy() {
    var cookiePolicyUrl = "http://www.cisco.com/web/siteassets/legal/privacy.html#cookies";
    window.open(cookiePolicyUrl, '_blank');
}
function openTrademarks() {
    var trademarksUrl = "http://www.cisco.com/web/siteassets/legal/trademark.html";
    window.open(trademarksUrl, '_blank');
}
</script>
<!--[if lt IE 9]><script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body id="landing">
		<div id="page">
			<header role="banner">
  			  <a href="https://www.cisco.com" ><h1 style="width:90px;" >Cisco Learning Space</h1></a> 
      		  <a href="https://${pageContext.request.serverName}" ><h1 style="width:335px; background-position:110% center; margin-top:5px;" >Cisco Learning Space</h1></a>  

			</header>
			<div id="content"><t:insertAttribute name="content" /></div>
			
			<footer role="contentinfo">
				<nav>
					<ul>
						<li><a href="javascript:openAboutUs();">About Us</a></li>
						<!--<li><a href="content?p_p_id=MyContentPortlet_WAR_ciscople&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_MyContentPortlet_WAR_ciscople_contentAction=faqPage">Help</a></li>-->
					</ul>
				</nav>
				<div>
					<p>
						<small>&copy; 1992-2013 Cisco Systems Inc. All rights reserved</small>
					</p>
					<ul>
						<li><a href="javascript:openTermsAndConditions();"><small>Terms &amp; Conditions</small></a></li>
						<li><a href="javascript:openPrivacyStatement();"><small>Privacy Statement</small></a></li>
						<li><a href="javascript:openCookiePolicy();"><small>Cookie Policy</small></a></li>
						<li><a href="javascript:openTrademarks();"><small>Trademarks</small></a></li>
					</ul>
				</div>
			</footer>
			<!-- /[role="contentinfo"] -->
		</div>
		<!-- /#page -->		
</body>

<script type="text/javascript"	src="${contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"	src="${contextPath}/js/jquery-ui-1.8.16.min.js"></script>
<script	type="text/javascript"  src="${contextPath}/js/CiscoPLESite.js"></script>
</html>