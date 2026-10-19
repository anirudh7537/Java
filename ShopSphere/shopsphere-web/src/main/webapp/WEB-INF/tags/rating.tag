<%@ tag body-content="empty" %>
<%@ attribute name="score" required="true" type="java.lang.Number" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<span class="rating" aria-label="Rated <c:out value='${score}'/> out of 5"><c:forEach begin="1" end="5" var="star"><span class="${star le score ? 'is-filled' : ''}">&#9733;</span></c:forEach><small><c:out value="${score}" default="0" /></small></span>
