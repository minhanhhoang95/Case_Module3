package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.IRoleDAO;
import com.codegym.wine_manager.dao.RoleDAO;
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
    private IRoleDAO iRoleDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        iRoleDAO = new RoleDAO();
        if(this.getServletContext().getAttribute("listRole")==null){
            this.getServletContext().setAttribute("listRole",iRoleDAO.selectAllRole());
        }
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
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                case "view":
//                    listUser(req, resp);
                    listNumberPage(req, resp);
                    break;
                default:
//                    indexUser(req, resp);
                    listNumberPage(req, resp);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
        dispatcher.forward(req, resp);
    }
    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<User> userList = userDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = userDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        req.setAttribute("listUser", userList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
        dispatcher.forward(req, resp);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.selectUser(id);


        req.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp");
        dispatcher.forward(req, resp);

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(req, resp);
                    break;
                case "edit":
                    updateUser(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        int role = Integer.parseInt(req.getParameter("Role"));

        User newUser = new User(username, password, name, phone, address, email, role);
        userDAO.insertUser(newUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        int role = Integer.parseInt(req.getParameter("Role"));
        int id = Integer.parseInt(req.getParameter("id"));

        User book = new User(id,username, password, name, phone, address, email, role);
        userDAO.updateUser(book);

          RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp");
         dispatcher.forward(req, resp);

//        resp.sendRedirect("/users");
    }

}
