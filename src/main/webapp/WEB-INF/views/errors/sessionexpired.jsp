<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
   
    <div role="main">

      <div id="carousel" class="carousel">

        <nav>
          <ol>
            <li><p><spring:message code="Welcome_Message"/></p></li>
          </ol>
        </nav>

        <section id="what-is-cisco-ple-e">

          <h2><spring:message code="Session_Expired"/></h2>

          <h3><p><spring:message code="Session_Expired_Details"/>&nbsp<a href="https://learningspace.cisco.com/sp/startSSO.ping?PartnerIdpId=cloudsso.cisco.com&TargetResource=https://${pageContext.request.serverName}" >Learning Network Space</a></p></h3>

        </section>
      
      </div>
      
    </div>

