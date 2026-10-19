<%@ include file="../fragments/header.jsp" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>
<section class="container catalog-page"><div class="page-title"><span class="eyebrow">JSTL / XML</span><h1>XML product feed</h1></div><div class="form-panel-wide"><x:parse var="feed" xml="${productXml}"/><ul><x:forEach select="$feed/products/product" var="entry"><li><strong><x:out select="$entry/name"/></strong> / <x:out select="$entry/kind"/></li></x:forEach></ul><p class="muted">This static XML is an isolated JSTL XML exercise, not the store's catalog source.</p></div></section>
<%@ include file="../fragments/footer.jsp" %>
