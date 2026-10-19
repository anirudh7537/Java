<%@ include file="fragments/header.jsp" %>
<section class="container error-page"><span class="eyebrow">ORDER #<c:out value="${order.orderId}"/></span><h1>Thank you for your order.</h1><p class="muted">Your ShopSphere order is now <strong><c:out value="${order.orderStatus}"/></strong>.</p><a class="btn btn-dark-pill" href="<c:url value='/order'><c:param name='id' value='${order.orderId}'/></c:url>">View order details</a><a class="btn btn-outline-pill ms-2" href="<c:url value='/products'/>">Continue exploring</a></section>
<%@ include file="fragments/footer.jsp" %>
