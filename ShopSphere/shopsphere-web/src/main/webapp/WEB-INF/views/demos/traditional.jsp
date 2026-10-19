<%@ include file="../fragments/header.jsp" %>
<%! private int demonstrationCount = 1; %>
<% String student = "ShopSphere student"; %>
<section class="container catalog-page"><div class="page-title"><span class="eyebrow">JSP SYLLABUS / ISOLATED EXAMPLE</span><h1>Traditional JSP syntax</h1></div><div class="form-panel-wide"><h2 class="h5">Declaration</h2><p>Declaration field value: <%= demonstrationCount %></p><h2 class="h5 mt-4">Scriptlet and expression</h2><p>Scriptlet local value: <%= student %></p><p>Expression can output a value without an EL expression: <%= java.time.LocalDate.now() %></p><%-- This is a JSP comment and is not sent as an HTML comment. --%><p class="muted">This isolated syllabus page demonstrates classic syntax. Main ShopSphere pages use EL/JSTL.</p></div></section>
<%@ include file="../fragments/footer.jsp" %>
