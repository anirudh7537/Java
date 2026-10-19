<%@ include file="fragments/header.jsp" %>
<section class="home-hero">
  <div class="container hero-grid">
    <div class="hero-copy"><span class="eyebrow">THE EVERYDAY EDIT</span><h1>Good things,<br><em>well chosen.</em></h1><p>Find useful pieces for the places and moments that make a day yours.</p><a class="btn btn-dark-pill" href="<c:url value='/products'/>">Explore the collection <span aria-hidden="true">&#8594;</span></a><div class="hero-note"><span class="hero-dot"></span>Thoughtful picks, ready to ship</div></div>
    <div class="hero-art"><img src="https://images.unsplash.com/photo-1490312278390-ab64016e0aa9?auto=format&fit=crop&w=1400&q=90" alt="Carefully arranged home objects and everyday essentials"><div class="hero-caption"><span>01 / NEW SEASON</span><strong>Make room for better.</strong></div></div>
  </div>
</section>
<section class="container section-block"><div class="section-heading"><div><span class="eyebrow">SHOP BY MOOD</span><h2>Start somewhere</h2></div><a class="text-link" href="<c:url value='/products'/>">All collections <span>&#8594;</span></a></div>
<div class="category-row"><c:forEach var="category" items="${categories}"><a class="category-chip" href="<c:url value='/products'><c:param name='category' value='${category.categoryId}'/></c:url>"><span class="category-mark">&#8599;</span><span><strong><c:out value="${category.name}"/></strong><small><c:out value="${category.description}"/></small></span></a></c:forEach></div></section>
<section class="container section-block"><div class="section-heading"><div><span class="eyebrow">A FEW GOOD FINDS</span><h2>Popular right now</h2></div><a class="text-link" href="<c:url value='/products'/>">See everything <span>&#8594;</span></a></div><div class="product-grid"><c:forEach var="product" items="${products}"><shop:productCard product="${product}" /></c:forEach></div></section>
<section class="closing-band"><div class="container closing-inner"><span class="eyebrow">SHOPSPHERE / EST. FOR THE EVERYDAY</span><h2>Less noise.<br>More useful things.</h2><a class="btn btn-light-pill" href="<c:url value='/products'/>">Find your next favorite</a></div></section>
<%@ include file="fragments/footer.jsp" %>
