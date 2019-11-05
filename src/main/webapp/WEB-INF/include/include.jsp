<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%@ taglib uri="/WEB-INF/tld/taglibrary.tld" prefix="my" %>

<%-- <jsp:useBean id="dateObject" class="java.util.Date" scope="page" /> --%>
<%-- 

dateObject는 Date형을 처리하기 위함
{"date":28,"day":1,"hours":20,"minutes":27,"month":10,"seconds":41,"time":1480332461000,"timezoneOffset":-540,"year":116}

페이지에서는 아래와 같이 처리
<jsp:setProperty name="dateObject" property="time" value="${item4.updated.time}" />
<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd" />

--%>
