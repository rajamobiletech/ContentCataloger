<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<core:set var="contextPath"	value="${pageContext.servletContext.contextPath}/resources" />
<%-- <spring:message code="Timezone" var="timezone"/>
<fmt:setTimeZone value="${timezone}" var="zone" scope="application"/> --%>
<meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
<LINK REL=StyleSheet HREF="${contextPath}/css/jquery-ui-1.8.17.css" TYPE="text/css">
<LINK REL=StyleSheet HREF="${contextPath}/css/main.css" TYPE="text/css">
<LINK REL=StyleSheet HREF="${contextPath}/css/ple.css" TYPE="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.16.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.dataTables-1.9.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.dynatree.js"></script>
<script type="text/javascript" src="${contextPath}/js/util.js"></script>
<script type="text/javascript" src="${contextPath}/js/util-order.js"></script>
<html>
    
    <script type="text/javascript">       
        function openHelp(){
          var helpURL="http://www.cisco.com/web/learning/clh/admin_documents/index.html"; window.open (helpURL,"Cisco Learning Network Space Help");
        }
        
        jQuery(function() {
             var ajax_call = function() {
               jQuery.ajax({
                            url: "/../c/portal/extend_session",
                            type:"GET",
                            success: function(data){
                                    },
                            error: function(){
                                   }
                 });
             };
                  
             var interval = 1000 * 60 * 5; // extend session after every 5 minutes
             setInterval(ajax_call, interval);

});
    </script>
    
<head>
    <title>Cisco Learning Network Space</title>
	<link href="${contextPath}/images/favicon.ico" rel="Shortcut Icon">
</head>
<body>
<div id="wrapper">
	<div class="ple-header">
			<div class="ple-header-inner-wrapper">
				<a href="http://www.cisco.com/en/US/hmpgs/index.html" target="_newTab"><div class="ple-header-logo"></div></a>
				<div class="ple-header-link-wrapper">     
				<label class="ple-font">Welcome, <label class="ple-bold">${sessionScope['fullName']}</label></label> | <label class="ple-font"><a href="${contextPath}/logout" id="sign-in" rel="nofollow">Logout</a></label>    
			   </div>
			</div>
	</div>
	<div id="content">       
		<t:insertAttribute name="menu" />
		<t:insertAttribute name="bodyContent" />
	</div>
    <footer id="footer" role="contentinfo">
        <div class="ple-footer"></div>
    </footer>
</div>
</body>
<script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.16.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.dataTables-1.9.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.dynatree.js"></script>
<script type="text/javascript" src="${contextPath}/js/util.js"></script>
<script type="text/javascript" src="${contextPath}/js/util-order.js"></script>
</html>
