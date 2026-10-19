<%@ include file="fragments/header.jsp" %>
<section class="container error-page"><span class="eyebrow">SHOPSPHERE / REQUEST</span><h1>We couldn't complete that request.</h1><p class="muted"><c:out value="${error}" default="Please return to the store and try again."/></p><a class="btn btn-dark-pill" href="<c:url value='/home'/>">Return to ShopSphere</a></section>
<%@ include file="fragments/footer.jsp" %>
