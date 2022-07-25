package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.IRoleDAO;
import com.codegym.wine_manager.dao.IWineDAO;
import com.codegym.wine_manager.dao.RoleDAO;
import com.codegym.wine_manager.dao.WineDAO;
import com.codegym.wine_manager.model.Role;
import com.codegym.wine_manager.model.User;
import com.codegym.wine_manager.model.Wine;
import org.jetbrains.annotations.NotNull;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

@WebServlet(name = "WineServlet", urlPatterns = "/wines")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class WineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IWineDAO iWineDAO;
    private String errors = "";
    private IRoleDAO iRoleDAO;
    @Override
    public void init() throws ServletException {
        iWineDAO = new WineDAO();
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
                    deleteWine(req, resp);
                    break;

                default:
                    listNumberPage(req, resp);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
//        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Wine> wineList = iWineDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = iWineDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        req.setAttribute("wineList", wineList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/listproduct.jsp");
        dispatcher.forward(req, resp);


//        System.out.println("numberPage");
//        int page = 1;
//        int recordsPerPage = 5;
//        if (req.getParameter("page") != null) {
//            page = Integer.parseInt(req.getParameter("page"));
//        };
//        String name = "";
//        if (req.getParameter("search") != null) {
//            name = req.getParameter("search");
//        }
//        List<Wine> listUser = iWineDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
//        int noOfRecords = iWineDAO.getNoOfRecords();
//        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        req.setAttribute("listUser", listUser);
//        req.setAttribute("noOfPages", noOfPages);
//        req.setAttribute("currentPage", page);
//        req.setAttribute("search" , name);
//
//
//
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/listproduct.jsp");
//        requestDispatcher.forward(req, resp);

    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Wine wine = new Wine();
        req.setAttribute("wine",wine);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        Wine wine = new Wine();
        req.setAttribute("wine",wine);
        int id = Integer.parseInt(req.getParameter("id"));
        Wine existingWine = iWineDAO.selectWine(id);


        req.setAttribute("wine", existingWine);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/editproduct.jsp");
        dispatcher.forward(req, resp);

    }

    private void deleteWine(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        iWineDAO.deleteWine(id);

        List<Wine> wineList = iWineDAO.selectAllWine();
        req.setAttribute("wineList", wineList);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/listuser.jsp");
//        dispatcher.forward(req, resp);
        resp.sendRedirect("/wines");

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
                    insertWine(req, resp);
                    break;
                case "edit":
                    updateWine(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertWine(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
//        String title = req.getParameter("title");
//        int quantity = Integer.parseInt(req.getParameter("quantity"));
//        double price = Double.parseDouble(req.getParameter("price"));
//        String description = req.getParameter("description");
//        Wine newWine = new Wine(title, quantity, price, description);
//        wineDAO.insertWine(newWine);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp");
//        dispatcher.forward(req, resp);



//        String title = req.getParameter("title");
//        int quantity = Integer.parseInt(req.getParameter("quantity"));
//        double price =Double.parseDouble( req.getParameter("price"));
//        String description = req.getParameter("description");
//        String image = null;
////        String image = req.getParameter("image");
//
//        for (Part part : req.getParts()) {
//            System.out.println("Context type: " + part.getContentType());
//            System.out.println("Name of part: " + part.getName());
//            if (part.getName().equals("image")) {
//                String fileName = extractFileName(part);
//                // refines the fileName in case it is an absolute path
//                fileName = new File(fileName).getName();
////                part.getInputStream()
//
//                if (fileName.equals("")) {
//                    image = "D:\\Case_Module3\\wine_manager\\src\\main\\webapp\\image\\RPC_04.png";
//                } else {
//                    part.write("D:\\Case_Module3\\wine_manager\\src\\main\\webapp\\image\\" + fileName);
//                    String servletRealPath = this.getServletContext().getRealPath("/") + "\\image\\" + fileName;
//                    System.out.println("servletRealPath :" + servletRealPath);
//                    part.write(servletRealPath);
//                    image = "image\\" + fileName;
////                    newProduct.setImage("image/" + fileName);
//                }
//            }
//
//        }
//        Wine newWine = new Wine(title, quantity, price, image, description);
//        iWineDAO.insertWine(newWine);
//
//        req.setAttribute("message", "Upload File Success!");
//        getServletContext().getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp").forward(req, resp);


        Wine wine = new Wine();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();
        try {
            wine.setId(Integer.parseInt(req.getParameter("id")));
            String title = req.getParameter("title");
            wine.setTitle(title);
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            wine.setQuantity(quantity);
            double price = Double.parseDouble(req.getParameter("price"));
            wine.setPrice(price);
            String description= req.getParameter("description");
            wine.setDescription(description);
            String image = null;
            for (Part part : req.getParts()) {
//            System.out.println("Context type: " + part.getContentType());
//            System.out.println("Name of part: " + part.getName());
            if (part.getName().equals("image")) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
//                part.getInputStream()

                if (fileName.equals("")) {
                    image = "D:\\Case_Module3\\wine_manager\\src\\main\\webapp\\image\\RPC_04.png";
                } else {
                    part.write("D:\\Case_Module3\\wine_manager\\src\\main\\webapp\\image\\" + fileName);
                    String servletRealPath = this.getServletContext().getRealPath("/") + "\\image\\" + fileName;
                    System.out.println("servletRealPath :" + servletRealPath);
                    part.write(servletRealPath);
                    image = "image\\" + fileName;
//                    newProduct.setImage("image/" + fileName);
                }
            }

        }


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Wine>> constraintViolations = validator.validate(wine);

            if (!constraintViolations.isEmpty()) {

                errors = "<ul>";
                // constraintViolations is has error
                for (ConstraintViolation<Wine> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";


                req.setAttribute("wine", wine);
                req.setAttribute("errors", errors);

                List<Role> listRole = iRoleDAO.selectAllRole();
                req.setAttribute("listRole", listRole);

                req.getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp").forward(req, resp);
            } else {


                if (flag) {
                    // Create user susscess
//                    iWineDAO.insertWine(wine);

                    Wine newWine = new Wine(title, quantity, price, image, description);
                    iWineDAO.insertWine(newWine);

                    req.setAttribute("message", "Upload File Success!");
//                    Wine w = new Wine();
//                    req.setAttribute("wine", w);
                    req.getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp").forward(req, resp);

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

                    req.setAttribute("wine", wine);
                    req.setAttribute("errors", errors);

                    req.getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp").forward(req, resp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateWine(@NotNull HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String image = "image\\" +  req.getParameter("image");
//        for (Part part : req.getParts()) {
//            System.out.println("Context type: " + part.getContentType());
//            System.out.println("Name of part: " + part.getName());
//            if (part.getName().equals("image")) {
//                String fileName = extractFileName(part);
//                // refines the fileName in case it is an absolute path
//                fileName = new File(fileName).getName();
////                part.getInputStream()
//
//                if (fileName.equals("")) {
//                    image = req.getParameter("image");
//                } else {
//                    part.write("D:\\Case_Module3\\wine_manager\\src\\main\\webapp\\image\\" + fileName);
//                    String servletRealPath = this.getServletContext().getRealPath("/") + "\\image\\" + fileName;
//                    System.out.println("servletRealPath :" + servletRealPath);
//                    part.write(servletRealPath);
//                    image = "image\\" + fileName;
////                    newProduct.setImage("image/" + fileName);
//                }
//            }
//
//        }
        System.out.println(image);
        Wine book = new Wine(id, title, quantity, price,image,description);
        iWineDAO.updateWine(book);
        List<Wine> wineList = iWineDAO.selectAllWine();
        req.setAttribute("wineList",wineList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/editproduct.jsp");
        dispatcher.forward(req, resp);

//        resp.sendRedirect("/wines");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        System.out.println(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
