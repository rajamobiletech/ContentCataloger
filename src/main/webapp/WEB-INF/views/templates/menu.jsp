<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set var="contextPath"	value="${pageContext.servletContext.contextPath}" />
<div id="menu" class="menuDiv">
	<ul class="menu">
		<li>
			<a href='#' class="parent">Cataloger</span></a>
				<ul class="childmenu">
					<li class="selected"><a	href='${contextPath}/ccs/contentCataloger'>Cataloger</a></li>
				</ul>
   		</li>
     </ul>
</div>