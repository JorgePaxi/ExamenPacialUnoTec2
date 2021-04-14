/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.GestorRegistroVacunado_JPaxi;
import com.emergentes.modelo.RegistroVacunado_JPaxi;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JLuis
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegistroVacunado_JPaxi objRegVacuna = new RegistroVacunado_JPaxi();
        int id, pos;
        
        String op=request.getParameter("op");
        
        if(op.equals("nuevo"))
        {
            HttpSession ses = request.getSession();
            GestorRegistroVacunado_JPaxi regvacun =(GestorRegistroVacunado_JPaxi) ses.getAttribute("regvacun");
            objRegVacuna.setId(regvacun.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miRegVacuna", objRegVacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);    
        }
        
        if(op.equals("modificar"))
        {
            id=Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorRegistroVacunado_JPaxi regvacun =(GestorRegistroVacunado_JPaxi) ses.getAttribute("regvacun");
            pos=regvacun.ubicarProducto(id);
            objRegVacuna = regvacun.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miRegVacuna", objRegVacuna);
            request.getRequestDispatcher("editar.jsp").forward(request, response);  
        }

        if(op.equals("eliminar"))
        {
            id=Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorRegistroVacunado_JPaxi regvacun =(GestorRegistroVacunado_JPaxi) ses.getAttribute("regvacun");
            pos=regvacun.ubicarProducto(id);
            regvacun.eliminarVacuna(pos);
            ses.setAttribute("regvacun", regvacun);
            response.sendRedirect("index.jsp");

        } 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RegistroVacunado_JPaxi objRegVacuna = new RegistroVacunado_JPaxi();
        int pos;
        String op=request.getParameter("op");
        
        if(op.equals("grabar"))
        {
            objRegVacuna.setId(Integer.parseInt(request.getParameter("id")));
            objRegVacuna.setNombre(request.getParameter("nombre"));
            objRegVacuna.setPeso(Double.parseDouble(request.getParameter("peso")));
            objRegVacuna.setTalla(Integer.parseInt(request.getParameter("talla")));
            objRegVacuna.setVacuna(request.getParameter("vacuna"));
            
            HttpSession ses = request.getSession();
            GestorRegistroVacunado_JPaxi regvacun =(GestorRegistroVacunado_JPaxi) ses.getAttribute("regvacun");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo"))
            {
                regvacun.insertarVacuna(objRegVacuna);
            }
            else
            {
                pos=regvacun.ubicarProducto(objRegVacuna.getId());
                regvacun.modificarVacuna(pos, objRegVacuna);
            }
            ses.setAttribute("regvacun", regvacun);
            response.sendRedirect("index.jsp");       
        }    
        
    }

    

}
