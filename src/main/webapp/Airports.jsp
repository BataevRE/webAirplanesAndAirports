<%@ page import="ru.neoflex.courses14.jpaEntity.Airport" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Аэропорты</title>
</head>
<body>
<table>
    <tr>
        <td style="text-align: center; vertical-align: top">
            <form method="post" action="index.jsp">
                <button style="font-size: 16pt">На главную</button>
            </form>
            <form id="createForm" method="get" action="CreateAirport">
                <input type=hidden name=city value="">
                <input type=hidden name=codeIATA value="">
                <input type=hidden name=throughput value="">
                <input type=hidden name=action value="Создать">
                <button style="font-size: 16pt">Создать</button>
            </form><br>
            <form id="searchForm" method="post" action="FindAirport">
                <select name="fieldForSearch">
                    <option value="airportId">ID аэропорта</option>
                    <option value="city">Город</option>
                    <option value="codeIATA">Код IATA</option>
                    <option value="throughput">Пропускная способность</option>
                </select><br>
                <input name="textForSearch"><br>
                <button style="font-size: 16pt">Искать</button>
            </form><br>
            <form method="post" action="Airports">
                <button style="font-size: 16pt">Список всех</button>
            </form>
        </td>
        <td style="vertical-align: top">
            <jsp:include page="AirportsTable.jsp">
                <jsp:param name="updateButton" value="true"/>
                <jsp:param name="deleteEntityButton" value="true"/>
            </jsp:include>
        </td>
    </tr>
</table>
</body>
</html>