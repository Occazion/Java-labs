<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="from" required="true" type="java.lang.Integer" %>
<%@ attribute name="to" required="true" type="java.lang.Integer" %>
<jsp:useBean id="rangeBean" class="com.nure.yehor.Range"/>

<h1>${rangeBean.toString(from,to)}</h1>