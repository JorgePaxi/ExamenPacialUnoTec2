<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.RegistroVacunado_JPaxi"%>
<%@page import="com.emergentes.modelo.GestorRegistroVacunado_JPaxi"%>
<%
    if (session.getAttribute("regvacun") == null)
    {
        GestorRegistroVacunado_JPaxi objeto1 = new GestorRegistroVacunado_JPaxi();
        
        objeto1.insertarVacuna(new RegistroVacunado_JPaxi(1, "Braulio Diaz",25, 140, "Si"));
        objeto1.insertarVacuna(new RegistroVacunado_JPaxi(2, "Juancito Pinto",30, 152, "No"));
        
        session.setAttribute("regvacun", objeto1);
    }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacunas jPaxi</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>
                    PRIMER PARCIAL TEM-742
                    <br>
                    Nombre: Jorge Luis Paxi Apaza
                    <br>
                    CI: 9076572LP
                </th>
            </tr>
        </table>
        
        <h1>Registro de Vacunas</h1>
        
        <a href="Controller?op=nuevo">Nuevo</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Peso</th>
                <th>Talla</th>
                <th>Vacuna</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.regvacun.getLista()}">    
            <tr>
                <th>${item.id}</th>
                <th>${item.nombre}</th>
                <th>${item.peso}</th>
                <th>${item.talla}</th>
                <th>${item.vacuna}</th>
                <th><a href="Controller?op=modificar&id=${item.id}">Editar</a></th>
                <th><a href="Controller?op=eliminar&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar??'))">Eliminar</a></th>
            </tr>                           
            </c:forEach>        
        </table>
    </body>
</html>
