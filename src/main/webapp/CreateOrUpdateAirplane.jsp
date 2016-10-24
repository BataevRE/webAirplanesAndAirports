<%@ page import="ru.neoflex.courses14.jpaEntity.Airplane" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!String serialNumber;%>
<%!String model;%>
<%!String destination;%>
<%!String releaseDate;%>
<%!String operator;%>
<%!Long airplaneId;%>
<html>
<head>
    <title><%=session.getAttribute("action")%> самолет</title>
    <style type="text/css">
        body {
            font-size: 14px;
        }

        label {
            float: left;
            padding-right: 10px;
        }

        .field {
            clear: both;
            text-align: right;
            line-height: 25px;
        }

        .main {
            float: left;
        }
    </style>
</head>
<body>
<%
    if (request.getAttribute("error") != null) {
        out.println(request.getAttribute("error"));
    }
%>
<form action="CreateAirplane" method="post">
    <%
        Airplane airplane = (Airplane) request.getAttribute("airplane");
        if (airplane != null) {
            airplaneId = airplane.getAirplaneId();
            serialNumber = airplane.getSerialNumber();
            model = airplane.getModel();
            destination = airplane.getDestination();
            releaseDate = airplane.getReleaseDate();
            operator = airplane.getOperator();
        } else {
            airplaneId = null;
            serialNumber = "";
            model = "";
            destination = "";
            releaseDate = "";
            operator = "";
        }
    %>
    <%if (airplaneId != null) {%>
    <input type=hidden name=airplaneId value=<%=airplaneId%>>
    <input type=hidden name=airplaneId value=<%=airplaneId%> form=addLink>
    <%}%>
    <div class="main">
        <div class="field"><label> serialNumber </label><input name="serialNumber" value="<%=serialNumber%>"></div>
        <div class="field"><label> model </label><input name="model" value="<%=model%>"></div>
        <div class="field"><label> destination </label><input name="destination" value="<%=destination%>"></div>
        <div class="field"><label> releaseDate </label><input name="releaseDate" value="<%=releaseDate%>"></div>
        <div class="field"><label> operator </label><input name="operator" value="<%=operator%>"></div>
    </div>
    <div style="clear: left">
        <br>
        <input type=hidden name=action value="<%=session.getAttribute("action")%>">
        <input type="submit" value="<%=session.getAttribute("action")%>">
        <input type="submit" form="cancel" value="Отмена">
        <input type="submit" form="addLink" value="Добавить самолет в аэропорт">
    </div>
</form>
<form id="cancel" action="CancelSaveAirplane" method="post">
</form>
<form id="addLink" action="AirplaneAddLinkPage" method="post"></form>
<br>
<jsp:include page="AirportsTable.jsp">
    <jsp:param name="deleteLinkButton" value="true"/>
</jsp:include>
</body>
</html>