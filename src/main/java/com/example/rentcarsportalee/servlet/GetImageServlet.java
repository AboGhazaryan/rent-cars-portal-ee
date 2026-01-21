package com.example.rentcarsportalee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {
    private static final String IMAGE_UPLOAD_PATH = "C:\\Users\\Admin\\IdeaProjects\\rent-cars-portal-ee\\upload-images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pictureName = req.getParameter("picture_name");
        File file = new File(IMAGE_UPLOAD_PATH + pictureName);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        resp.setContentType("image/jpeg");
        resp.setHeader("Content-Type", "image/jpeg");
        ServletOutputStream out = resp.getOutputStream();
        try(InputStream in = new FileInputStream(file)) {
            IOUtils.copy(in, out);
        }

    }
}
