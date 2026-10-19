<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg shopsphere-nav">
  <div class="container nav-inner">
    <a class="navbar-brand brand-lockup" href="<c:url value='/home'/>"><span class="brand-mark">S</span><span><fmt:message bundle="${i18n}" key="brand.name"/></span></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav" aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="mainNav">
      <form class="nav-search mx-lg-auto" action="<c:url value='/products'/>" method="get" role="search">
        <label class="visually-hidden" for="nav-search">Search products</label><input id="nav-search" class="form-control" type="search" name="q" value="<c:out value='${param.q}'/>" placeholder="Search the collection">
        <button class="btn search-button" type="submit" aria-label="Search">Search</button>
      </form>
      <ul class="navbar-nav align-items-lg-center gap-lg-3">
        <li class="nav-item"><a class="nav-link" href="<c:url value='/products'/>"><fmt:message bundle="${i18n}" key="nav.discover"/></a></li>
        <c:choose>
          <c:when test="${not empty sessionScope.user}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/wishlist'/>"><fmt:message bundle="${i18n}" key="nav.saved"/></a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/orders'/>"><fmt:message bundle="${i18n}" key="nav.orders"/></a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/profile'/>"><c:out value="${sessionScope.user.name}" /></a></li>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}"><li class="nav-item"><a class="nav-link" href="<c:url value='/admin/dashboard'/>">Admin</a></li></c:if>
            <li class="nav-item"><form action="<c:url value='/logout'/>" method="post"><button class="btn nav-logout" type="submit"><fmt:message bundle="${i18n}" key="nav.signout"/></button></form></li>
          </c:when>
          <c:otherwise><li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>"><fmt:message bundle="${i18n}" key="nav.signin"/></a></li></c:otherwise>
        </c:choose>
        <li class="nav-item"><a class="btn cart-link" href="<c:url value='/cart'/>"><fmt:message bundle="${i18n}" key="nav.cart"/> <span class="cart-count"><c:out value="${sessionScope.cartCount}" default="0" /></span></a></li>
        <li class="nav-item locale-links"><a href="<c:url value='/locale'><c:param name='lang' value='en'/></c:url>" lang="en">EN</a><span>/</span><a href="<c:url value='/locale'><c:param name='lang' value='hi'/></c:url>" lang="hi">HI</a></li>
      </ul>
    </div>
  </div>
</nav>
