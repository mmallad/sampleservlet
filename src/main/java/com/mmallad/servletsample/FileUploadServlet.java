package com.mmallad.servletsample;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URLDecoder;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/file")
@MultipartConfig(maxFileSize = 34000000)
public class FileUploadServlet extends HttpServlet {
    private String destination = null;
    public void init(){
        destination = getServletContext().getInitParameter("uploadPath");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        System.out.println(filePart.getName());
        System.out.println(filePart.getSize());

        PrintWriter writer = response.getWriter();
        try {
            outputStream = new FileOutputStream(new File(destination + File.separator + fileName));
            inputStream = filePart.getInputStream();

            int read;
            final byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            System.out.println(request.getServerName()+request.getServerPort());
            writer.write(String.format("%sfile/?fileName=%s", request.getContextPath(),fileName));
        }finally {
            if(inputStream != null) inputStream.close();
            if(outputStream !=  null) outputStream.close();
            writer.close();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        if(query != null && query.length() > 0) {
            String[] token = query.split("=");
            if (token.length == 2) {
                String fileName = URLDecoder.decode(token[1],"UTF-8");
                OutputStream outputStream = response.getOutputStream();
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(new File(destination + File.separator + fileName));
                    int read;
                    final byte[] buffer = new byte[1024];
                    while ((read = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, read);
                    }
                }finally {
                    outputStream.close();
                    if(inputStream != null)
                        inputStream.close();
                }
            }
        }else
        request.getRequestDispatcher("file.jsp").forward(request, response);
    }

    private String getFileName(final Part part) {
        //final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
