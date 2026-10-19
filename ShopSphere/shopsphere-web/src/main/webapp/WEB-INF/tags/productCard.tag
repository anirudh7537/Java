<%@ tag body-content="empty" %>
<%@ attribute name="product" required="true" type="com.shopsphere.common.model.Product" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="shop" tagdir="/WEB-INF/tags" %>
<article class="product-card">
  <a class="product-image" href="<c:url value='/product'><c:param name='id' value='${product.productId}'/></c:url>">
    <img src="<c:out value='${product.imageUrl}' default='https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=900&q=85'/>" alt="<c:out value='${product.name}'/>" loading="lazy">
    <c:if test="${product.discount gt 0}"><span class="discount-pill"><fmt:formatNumber value="${product.discount}" maxFractionDigits="0"/>% off</span></c:if>
  </a>
  <div class="product-card-body">
    <div class="product-meta"><span><c:out value="${product.categoryName}" /></span><span><shop:rating score="${product.averageRating}" /></span></div>
    <h3><a href="<c:url value='/product'><c:param name='id' value='${product.productId}'/></c:url>"><c:out value="${product.name}" /></a></h3>
    <p class="product-brand"><c:out value="${product.brand}" /></p>
    <div class="price-row"><strong><fmt:formatNumber value="${product.salePrice}" type="currency" currencyCode="USD" /></strong><c:if test="${product.discount gt 0}"><del><fmt:formatNumber value="${product.price}" type="currency" currencyCode="USD" /></del></c:if></div>
    <form action="<c:url value='/cart'/>" method="post"><input type="hidden" name="action" value="add"><input type="hidden" name="productId" value="${product.productId}"><input type="hidden" name="quantity" value="1"><button class="btn btn-add" type="submit" <c:if test="${product.stock lt 1}">disabled</c:if>><c:choose><c:when test="${product.stock gt 0}">Add to cart</c:when><c:otherwise>Out of stock</c:otherwise></c:choose></button></form>
  </div>
</article>
