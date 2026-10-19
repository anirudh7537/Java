<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${empty sessionScope.locale ? 'en_IN' : sessionScope.locale}" />
<fmt:setBundle basename="com.shopsphere.common.i18n.messages" var="i18n" />
<!doctype html>
<html lang="${empty sessionScope.locale ? 'en_IN' : sessionScope.locale}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${pageTitle}" default="ShopSphere" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&family=Manrope:wght@500;600;700;800&display=swap" rel="stylesheet">
  <link href="<c:url value='/assets/css/app.css'/>" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<main class="page-shell">
<c:if test="${not empty error}"><div class="container pt-3"><div class="alert alert-danger" role="alert"><c:out value="${error}" /></div></div></c:if>
<c:if test="${not empty sessionScope.flashError}"><div class="container pt-3"><div class="alert alert-danger" role="alert"><c:out value="${sessionScope.flashError}" /></div><c:remove var="flashError" scope="session" /></div></c:if>
<c:if test="${not empty sessionScope.flashSuccess}"><div class="container pt-3"><div class="alert alert-success" role="status"><c:out value="${sessionScope.flashSuccess}" /></div><c:remove var="flashSuccess" scope="session" /></div></c:if>
