<%@ page import="ru.neoflex.courses14.jpaEntity.Airplane" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <%if (request.getAttribute("airplanes") != null) {%>
    <tr>
        <td>Бортовой номер</td>
        <td>Модель</td>
        <td>Место назначения</td>
        <td>Дата выпуска</td>
        <td>Компания</td>
        <td>ID</td>
    </tr>
    <%for (Airplane airplane : (List<Airplane>) request.getAttribute("airplanes")) {%>
    <tr>
        <td><%=airplane.getSerialNumber()%>
        </td>
        <td><%=airplane.getModel()%>
        </td>
        <td><%=airplane.getDestination()%>
        </td>
        <td><%=airplane.getReleaseDate()%>
        </td>
        <td><%=airplane.getOperator()%>
        </td>
        <td><%=airplane.getAirplaneId()%>
        </td>
        <%if (request.getParameterMap().containsKey("updateButton")){%>
        <td>
            <form method="get" action="UpdateAirplane">
                <input type=hidden name=airplaneId value=<%=airplane.getAirplaneId()%>>
                <input type=hidden name=action value="Изменить">
                <button style="font-size: 16pt">Изменить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("deleteEntityButton")){%>
        <td>
            <form method="post" action="Delete">
                <input type=hidden name=entity value=airplane>
                <input type=hidden name=airplaneId value=<%=airplane.getAirplaneId()%>>
                <button style="font-size: 16pt">Удалить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("deleteLinkButton")){%>
        <td>
            <form method="post" action="DeleteLinkFromAirport">
                <input type=hidden name=airplaneToDelete value=<%=airplane.getAirplaneId()%>>
                <button style="font-size: 16pt">Удалить</button>
            </form>
        </td>
        <%}%>
        <%if(request.getParameterMap().containsKey("addLinkButton")){%>
        <td>
            <form method="post" action="AddLinkToAirport">
                <input type=hidden name=airplaneToAdd value=<%=airplane.getAirplaneId()%>>
                <button style="font-size: 16pt">Добавить</button>
            </form>
        </td>
        <%}%>
    </tr>
    <%
            }
        } else {
            out.println("Список самолетов пуст<br>");
            out.println("<a href=Airplanes>список всех</a>");
        }
    %>
</table>
