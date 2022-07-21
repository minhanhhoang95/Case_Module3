package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.IRoleDAO;
import com.codegym.wine_manager.dao.RoleDAO;
import com.codegym.wine_manager.model.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RoleServlet", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IRoleDAO iRoleDAO;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteRole(req, resp);
                    break;
                default:
                    listRole(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }



    private void listRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> listRole = iRoleDAO.selectAllRole();
        request.setAttribute("listRole", listRole);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }



    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        iRoleDAO.deleteRole(id);

        List<Role> listRole = iRoleDAO.selectAllRole();
        request.setAttribute("listRole", listRole);
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }




    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Role existingRole = iRoleDAO.selectRole(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        request.setAttribute("role", existingRole);

        dispatcher.forward(request, response);

    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertCountry(request, response);
                    break;
                case "edit":
                    updateRole(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }





    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));


        String name = request.getParameter("role");

        Role role = new Role(id, name);
        iRoleDAO.updateRole(role);

        response.sendRedirect("/country");

    }



    private void insertCountry(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("role");

        Role role = new Role();
        role.setRole(name);

        iRoleDAO.insertRole(role);

        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);

    }



    @Override
    public void init() throws ServletException {
        iRoleDAO = new RoleDAO();
    }
}
