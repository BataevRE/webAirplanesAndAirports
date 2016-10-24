<%@ page import="ru.neoflex.courses14.jpaEntity.Airport" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <%if (request.getAttribute("airports") != null) {%>
    <tr>
        <td>Город</td>
        <td>Код IATA</td>
        <td>Пропускная способность</td>
        <td>ID</td>
    </tr>
    <%for (Airport airport : (List<Airport>) request.getAttribute("airports")) {%>
    <tr>
        <td><%=airport.getCity()%>
        </td>
        <td><%=airport.getCodeIATA()%>
        </td>
        <td><%=airport.getThroughput()%>
        </td>
        <td><%=airport.getAirportId()%>
        </td>
        <%if(request.getParameterMap().containsKey("updateButton")){%>
        <td>
            <form method="get" action="UpdateAirport">
                <input type=hidden name=airportId value=<%=airport.getAirportId()%>>
                <input type=hidden name=action value="Изменить">
                <button style="font-size: 16pt">Изменить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("deleteEntityButton")){%>
        <td>
            <form method="post" action="Delete">
                <input type=hidden name=entity value=airport>
                <input type=hidden name=airportId value=<%=airport.getAirportId()%>>
                <button style="font-size: 16pt">Удалить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("deleteLinkButton")){%>
        <td>
            <form method="post" action="DeleteLinkFromAirplane">
                <input type=hidden name=airportToDelete value=<%=airport.getAirportId()%>>
                <button style="font-size: 16pt">Удалить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("addLinkButton")){%>
        <td>
            <form method="post" action="AddLinkToAirplane">
                <input type=hidden name=airportToAdd value=<%=airport.getAirportId()%>>
                <button style="font-size: 16pt">Добавить</button>
            </form>
        </td>
        <%}%>
    </tr>
    <%
            }
        } else {
            out.println("Список аэропортов пуст<br>");
            out.println("<a href=Airports>список всех</a>");
        }
    %>
</table>
