package com.codegym.wine_manager.controller;

import com.codegym.wine_manager.dao.IWineDAO;
import com.codegym.wine_manager.dao.WineDAO;
import com.codegym.wine_manager.model.User;
import com.codegym.wine_manager.model.Wine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "WineServlet", urlPatterns = "/wines")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class WineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IWineDAO iWineDAO;

    @Override
    public void init() throws ServletException {
        iWineDAO = new WineDAO();
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

        String title = req.getParameter("title");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price =Double.parseDouble( req.getParameter("price"));
        String description = req.getParameter("description");
        String image = null;
//        String image = req.getParameter("image");

        for (Part part : req.getParts()) {
            System.out.println("Context type: " + part.getContentType());
            System.out.println("Name of part: " + part.getName());
            if (part.getName().equals("image")) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
//                part.getInputStream()

                if (fileName.equals("")) {
                    image = "/image/aa1.png";
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
        Wine newWine = new Wine(title, quantity, price, image, description);
        iWineDAO.insertWine(newWine);
        req.setAttribute("message", "Upload File Success!");
        getServletContext().getRequestDispatcher("/WEB-INF/admin/product/createproduct.jsp").forward(req, resp);
    }




    private void updateWine(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String title = req.getParameter("title");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));
        String image = req.getParameter("image");
        String description = req.getParameter("description");
        int id = Integer.parseInt(req.getParameter("id"));


        Wine book = new Wine(id, title, quantity, price,image,description);
        iWineDAO.updateWine(book);

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
