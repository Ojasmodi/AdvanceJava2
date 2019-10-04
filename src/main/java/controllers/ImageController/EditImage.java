package controllers.ImageController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/EditImage")
public class EditImage extends HttpServlet {

	// method to edit image in the application
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/image.jsp");
		long maxFileSize = Long.parseLong(getServletContext().getInitParameter("max-file-size"));
		long maxMemSize = Long.parseLong(getServletContext().getInitParameter("max-mem-size"));
		String directoryPath = getServletContext().getInitParameter("file-upload");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("uname");
		ImageManagement imageManagement = new ImageManagement();

		String extension = "", imageNameWithoutExtension = "";
		long imageId = 0;
		int flag = 0;
		Image image = new Image();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("c:\\temp");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		factory.setSizeThreshold((int) maxMemSize);

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);

			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					if (name.equals("imageName"))
						imageNameWithoutExtension = value;
					else if (name.equals("extension"))
						extension = value;
					else if (name.equals("id"))
						imageId = Long.parseLong(value);
				} else {
					String fileName = item.getName();
					long sizeInBytes = item.getSize();

					if (fileName.substring(fileName.lastIndexOf('.') + 1).equals("png")
							|| fileName.substring(fileName.lastIndexOf('.') + 1).equals("jpg")
							|| fileName.substring(fileName.lastIndexOf('.') + 1).equals("jpeg")) {

						image = imageManagement.uploadImageToDirectory(item, fileName, directoryPath);
						image.setImageSize(sizeInBytes);
						flag = 1;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			if (flag == 1) {
				image.setImageId(imageId);
				image.setUploadedByUserName(username);
				imageManagement.updateImage(image);
				out.println("<div class='alert alert-success text-center'>Image Updated successfully..</div>");
				rd.include(request, response);
			}

			else if (flag == 0) {
				Image tempImage = imageManagement.getImageByImageId(imageId);
				if (imageNameWithoutExtension.equals("")) {
					out.println("<div class='alert alert-danger text-center'>File name can't be blank.</div>");
					request.getRequestDispatcher("/EditImageForm.jsp").include(request, response);
				} else if (!tempImage.getImageName().equals(imageNameWithoutExtension + extension)) {
					tempImage.setImageName(imageNameWithoutExtension + extension);
					imageManagement.saveImage(tempImage);
					out.println("<div class='alert alert-success text-center'>Image Updated successfully..</div>");
				} else
					out.println("<div class='alert alert-danger text-center'>File name was same.</div>");
				rd.include(request, response);
			}
		} catch (Exception e) {
			out.println(
					"<div class='alert alert-danger text-center'>Some error occured. Check image size and extension.</div>");
			rd.include(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("jkl");
		doGet(request, response);
	}

}
