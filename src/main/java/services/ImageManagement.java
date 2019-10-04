package services;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.fileupload.FileItem;

import models.Image;

public class ImageManagement {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
	static EntityManager em = emf.createEntityManager();

	// method to save image in the database
	public void saveImage(Image image) {
		try {
			em.getTransaction().begin();
			em.persist(image);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// method to get all images by username
	public List<Image> getAllImagesByUserName(String userName) {
		Query query = null;
		try {
			query = em.createQuery("select i from Image i where uploadedByUserName=:name");
			query.setParameter("name", userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<Image>) query.getResultList();
	}

	// method to delete image in the database based on imageId
	public void deleteImage(long id) {
		em.getTransaction().begin();
		Image image = em.find(Image.class, id);
		if (image != null)
			em.remove(image);
		em.getTransaction().commit();
	}

	// method to update image data
	public void updateImage(Image image) {
		em.getTransaction().begin();
		Image newImage = em.find(Image.class, image.getImageId());
		if (newImage != null) {
			newImage.setImagePath(image.getImagePath());
			newImage.setImageName(image.getImageName());
			newImage.setImageSize(image.getImageSize());
		}
		em.getTransaction().commit();
	}

	// method to retrieve image based on imageId
	public Image getImageByImageId(long id) {
		em.getTransaction().begin();
		Image image = em.find(Image.class, id);
		em.getTransaction().commit();
		return image;
	}

	// method to upload image to a particular directory
	public Image uploadImageToDirectory(FileItem item, String fileName, String directoryPath) {
		File file;
		String fileLocation;
		Image image = new Image();
		image.setImageName(fileName);
		try {
			if (fileName.lastIndexOf("\\") >= 0) {
				fileLocation = directoryPath + fileName.substring(fileName.lastIndexOf("\\"));
				file = new File(fileLocation);
			} else {
				fileLocation = directoryPath + fileName.substring(fileName.lastIndexOf("\\") + 1);
				file = new File(fileLocation);
			}
			item.write(file);
			image.setImagePath(fileLocation);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return image;
		}
	}
}
