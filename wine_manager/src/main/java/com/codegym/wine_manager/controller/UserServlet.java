package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.IRoleDAO;
import com.codegym.wine_manager.dao.RoleDAO;
import com.codegym.wine_manager.dao.UserDAO;
import com.codegym.wine_manager.model.Role;
import com.codegym.wine_manager.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private IRoleDAO iRoleDAO;
    private String errors = "";

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        iRoleDAO = new RoleDAO();
        if (this.getServletContext().getAttribute("listRole") == null) {
            this.getServletContext().setAttribute("listRole", iRoleDAO.selectAllRole());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
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
//        System.out.println("numberPage");
//        int page = 1;
//        int recordsPerPage = 5;
//        if (req.getParameter("page") != null) {
//            page = Integer.parseInt(req.getParameter("page"));
//        }
//        List<User> userList = userDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage);
//        int noOfRecords = userDAO.getNoOfRecords();
//        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
////        System.out.println("noOfPages" + noOfPages);
////        System.out.println(noOfRecords);
//        req.setAttribute("listUser", userList);
//        req.setAttribute("noOfPages", noOfPages);
//        req.setAttribute("currentPage", page);
//
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
//        dispatcher.forward(req, resp);


        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        ;
        String name = "";
        if (req.getParameter("search") != null) {
            name = req.getParameter("search");
        }
        List<User> listUser = userDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = userDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listUser", listUser);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("search", name);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
        requestDispatcher.forward(req, resp);


    }


    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp");
        dispatcher.forward(req, resp);
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
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
//        dispatcher.forward(req, resp);
        resp.sendRedirect("/users");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
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
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String name = req.getParameter("name");
//        String phone = req.getParameter("phone");
//        String address = req.getParameter("address");
//        String email = req.getParameter("email");
//        int role = Integer.parseInt(req.getParameter("Role"));
//
//        User newUser = new User(username, password, name, phone, address, email, role);
//        userDAO.insertUser(newUser);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp");
//        dispatcher.forward(req, resp);


        User user = new User();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();  // Luu lai truong nao bi loi va loi gi


//        System.out.println(this.getClass() + " insertUser with validate");
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
            String username = req.getParameter("username");
            user.setUsername(username);
            String password = req.getParameter("password");
            user.setPassword(password);
            String name = req.getParameter("name");
            user.setFullName(name);
            String phone = req.getParameter("phone");
            user.setPhone(phone);
//            String address = req.getParameter("address");
            user.setAddress(req.getParameter("address"));
            String email = req.getParameter("email");
            user.setEmail(email);

            int role = Integer.parseInt(req.getParameter("Role"));
            user.setRole(role);


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

            System.out.println("User: " + user);

            if (!constraintViolations.isEmpty()) {

                errors = "<ul>";
                // constraintViolations is has error
                for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";


                req.setAttribute("user", user);
                req.setAttribute("errors", errors);

                List<Role> listRole = iRoleDAO.selectAllRole();
                req.setAttribute("listRole", listRole);

                req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp").forward(req, resp);
            } else {
                if (userDAO.selectUserByEmail(email) != null) {
                    flag = false;
                    hashMap.put("email", "Email exits in database");
                }
                if (userDAO.getUserByUsername(username) != null) {
                    flag = false;
                    hashMap.put("username", "username exits in database");
                }
                if (userDAO.selectUserByPhone(phone) != null) {
                    flag = false;
                    hashMap.put("phone", "Phone exits in database");
                }
                if (iRoleDAO.selectRole(role) == null) {
                    flag = false;
                    hashMap.put("Role", "Role value invalid");

                }

                if (flag) {
                    // Create user susscess
                    userDAO.insertUser(user);


                    User u = new User();
                    req.setAttribute("user", u);

                    req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp").forward(req, resp);
                } else {

                    errors = "<ul>";

                    hashMap.forEach(new BiConsumer<String, String>() {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";

                        }
                    });
                    errors += "</ul>";

                    req.setAttribute("user", user);
                    req.setAttribute("errors", errors);

                    req.getRequestDispatcher("/WEB-INF/admin/user/createuser.jsp").forward(req, resp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String name = req.getParameter("name");
//        String phone = req.getParameter("phone");
//        String address = req.getParameter("address");
//        String email = req.getParameter("email");
//        int role = Integer.parseInt(req.getParameter("Role"));
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        User book = new User(id, username, password, name, phone, address, email, role);
//        userDAO.updateUser(book);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp");
//        dispatcher.forward(req, resp);
//
////        resp.sendRedirect("/users");


        User user = new User();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();  // Luu lai truong nao bi loi va loi gi


//        System.out.println(this.getClass() + " insertUser with validate");
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
            User check = userDAO.selectUser(Integer.parseInt(req.getParameter("id")));
            String checkUsername = check.getUsername();
            String checkEmail = check.getEmail();
            String checkPhone = check.getPhone();


            String username = req.getParameter("username");
            user.setUsername(username);
            String password = req.getParameter("password");
            user.setPassword(password);
            String name = req.getParameter("name");
            user.setFullName(name);
            String phone = req.getParameter("phone");
            user.setPhone(phone);
//            String address = req.getParameter("address");
            user.setAddress(req.getParameter("address"));
            String email = req.getParameter("email");
            user.setEmail(email);

            int role = Integer.parseInt(req.getParameter("Role"));
            user.setRole(role);


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

//            System.out.println("User: " + user);

            if (!constraintViolations.isEmpty()) {

                errors = "<ul>";
                // constraintViolations is has error
                for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";


                req.setAttribute("user", user);
                req.setAttribute("errors", errors);

                List<Role> listRole = iRoleDAO.selectAllRole();
                req.setAttribute("listRole", listRole);

                req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp").forward(req, resp);
            } else {
                if (checkUsername.equals(username)) {
                    user.setUsername(username);
                    flag = true;
                } else if (userDAO.getUserByUsername(username) != null) {
                    flag = false;
                    hashMap.put("username", "username exits in database");
                }
                if (checkEmail.equals(email)) {
                    user.setEmail(email);
                    flag = true;
                } else if (userDAO.selectUserByEmail(email) != null) {
                    flag = false;
                    hashMap.put("email", "Email exits in database");
                }

                if (checkPhone.equals(phone)) {
                    user.setPhone(phone);
                    flag = true;
                } else if (userDAO.selectUserByPhone(phone) != null) {
                    flag = false;
                    hashMap.put("phone", "Phone exits in database");
                }


                if (iRoleDAO.selectRole(role) == null) {
                    flag = false;
                    hashMap.put("Role", "Role value invalid");

                }

                if (flag) {
                    // Create user susscess
                    userDAO.insertUser(user);


                    User u = new User();
                    req.setAttribute("user", u);

                    req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp").forward(req, resp);
                } else {

                    errors = "<ul>";

                    hashMap.forEach(new BiConsumer<String, String>() {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";

                        }
                    });
                    errors += "</ul>";

                    req.setAttribute("user", user);
                    req.setAttribute("errors", errors);

                    req.getRequestDispatcher("/WEB-INF/admin/user/edituser.jsp").forward(req, resp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
