package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	private String imagePath;
	private String uploadedByUserName;
	private String imageName;
	private double imageSize;

	public String getUploadedByUserName() {
		return uploadedByUserName;
	}
	

	public void setUploadedByUserName(String uploadedByUserName) {
		this.uploadedByUserName = uploadedByUserName;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getImageSize() {
		return imageSize;
	}

	public void setImageSize(double imageSize) {
		this.imageSize = imageSize;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imagePath=" + imagePath + ", uploadedByUserName=" + uploadedByUserName
				+ ", imageName=" + imageName + ", imageSize=" + imageSize + "]";
	}

}
