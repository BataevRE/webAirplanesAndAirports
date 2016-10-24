<%@ page import="ru.neoflex.courses14.jpaEntity.Airport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!String city;%>
<%!String codeIATA;%>
<%!String throughput;%>
<%!Long airportId;%>
<html>
<head>
    <title><%=session.getAttribute("action")%> аэропорт</title>
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
<form action="CreateAirport" method="post">
    <%
        Airport airport = (Airport) request.getAttribute("airport");
        if (airport != null) {
            airportId = airport.getAirportId();
            city = airport.getCity();
            codeIATA = airport.getCodeIATA();
            throughput = airport.getThroughput();
        } else {
            airportId = null;
            city = "";
            codeIATA = "";
            throughput = "";
        }%>
    <%if (airportId != null) {%>
    <input type=hidden name=airportId value=<%=airportId%>>
    <input type=hidden name=airportId value=<%=airportId%> form=addLink>
    <%}%>
    <div class="main">
        <div class="field"><label>Город: </label> <input name="city" value="<%=city%>"></div>
        <div class="field"><label>Кода IATA: </label> <input name="codeIATA" value="<%=codeIATA%>"></div>
        <div class="field"><label>Пропускная способность: </label> <input name="throughput" value="<%=throughput%>">
        </div>
    </div>
    <div style="clear: left">
        <br>
        <input type="submit" value="<%=session.getAttribute("action")%>">
        <input type="submit" form="cancel" value="Отмена">
        <input type="submit" form="addLink" value="Добавить самолет">
    </div>
</form>
<form id="cancel" action="CancelSaveAirport" method="post">
</form>
<form id="addLink" action="AirportAddLinkPage" method="post"></form>
<br>
<jsp:include page="AirplanesTable.jsp">
    <jsp:param name="deleteLinkButton" value="true"/>
</jsp:include>
</body>
</html>