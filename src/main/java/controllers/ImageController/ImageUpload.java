package controllers.ImageController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import models.Image;
import services.ImageManagement;

@WebServlet("/ImageUpload")
public class ImageUpload extends HttpServlet {

	// method to upload image in the application
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/image.jsp");
		ImageManagement imageManagement = new ImageManagement();
		long maxFileSize = Long.parseLong(getServletContext().getInitParameter("max-file-size"));
		long maxMemSize = Long.parseLong(getServletContext().getInitParameter("max-mem-size"));
		String directoryPath = getServletContext().getInitParameter("file-upload");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("uname");
		Image image = new Image();
		List<Image> Images = new ArrayList<Image>();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("c:\\temp");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		factory.setSizeThreshold((int) maxMemSize);
		upload.setSizeMax(maxFileSize);

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (!item.isFormField()) {
					String fileName = item.getName();
					long sizeInBytes = item.getSize();

					if (fileName.substring(fileName.lastIndexOf('.') + 1).equals("png")
							|| fileName.substring(fileName.lastIndexOf('.') + 1).equals("jpg")
							|| fileName.substring(fileName.lastIndexOf('.') + 1).equals("jpeg")) {
						image = imageManagement.uploadImageToDirectory(item, fileName, directoryPath);
						image.setUploadedByUserName(username);
						image.setImageSize(sizeInBytes);
						Images.add(image);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		Iterator it = Images.iterator();
		while (it.hasNext()) {
			imageManagement.saveImage((Image) it.next());
		}
		if (Images.isEmpty())
			out.println("<div class='alert alert-danger text-center'>Some error occured while uploading file.</div>");
		else
			out.println("<div class='alert alert-success text-center'>Image Uploaded successfully.</div>");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
