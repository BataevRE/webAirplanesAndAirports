<%@ page import="ru.neoflex.courses14.jpaEntity.Airplane" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Самолеты</title>
</head>
<body>
<table>
    <tr>
        <td style="text-align: center; vertical-align: top">
            <form method="post" action="index.jsp">
                <button style="font-size: 16pt">На главную</button>
            </form>
            <form id="createForm" method="get" action="CreateAirplane">
                <input type=hidden name=serialNumber    value="">
                <input type=hidden name=model           value="">
                <input type=hidden name=destination     value="">
                <input type=hidden name=releaseDate     value="">
                <input type=hidden name=operator        value="">
                <input type=hidden name=action          value="Создать">
                <button style="font-size: 16pt">Создать</button>
            </form><br>
            <form id="searchForm" method="post" action="FindAirplane">
                <select name="fieldForSearch">
                    <option value="airplaneId">ID самолета</option>
                    <option value="serialNumber">Бортовой номер</option>
                    <option value="model">Модель</option>
                    <option value="destination">Место назначения</option>
                    <option value="releaseDate">Дата выпуска</option>
                    <option value="operator">Компания</option>
                </select><br>
                <input name="textForSearch"><br>
                <button style="font-size: 16pt">Искать</button>
            </form><br>
            <form method="post" action="Airplanes">
                <button style="font-size: 16pt">Список всех</button>
            </form>
        </td>
        <td style="vertical-align: top">
            <jsp:include page="AirplanesTable.jsp">
                <jsp:param name="updateButton" value="true"/>
                <jsp:param name="deleteEntityButton" value="true"/>
            </jsp:include>
        </td>
    </tr>
</table>
</body>
</html>