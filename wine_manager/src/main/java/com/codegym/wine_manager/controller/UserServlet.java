package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.UserDAO;
import com.codegym.wine_manager.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    @Override
    public void init() throws ServletException {
       userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
//                    showNewForm(req, resp);
                    break;
                case "edit":
//                    showEditForm(re, resp);
                    break;
                case "delete":
//                    deleteUser(req, resp);
                    break;
                case "view":
                    listUser(req,resp);
                    break;
                default:
                    indexUser(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void indexUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/index.jsp");
        dispatcher.forward(req, resp);
    }
    private void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
//        List<User> listUser = userDAO.selectAllUsers();
//        req.setAttribute("listUser",listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
