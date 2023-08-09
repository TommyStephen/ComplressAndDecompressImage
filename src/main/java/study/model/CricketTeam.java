package study.model;

import java.util.Arrays;


import jakarta.persistence.*;

@Entity
public class CricketTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String player;
	@Lob
	@Column (columnDefinition = "BLOB")
	private byte[] image;
	private String fileName;
	private String fileType;
	
	public CricketTeam() {}

	public CricketTeam(String player, byte[] image, String fileName, String fileType) {
		super();
		this.player = player;
		this.image = image;
		this.fileName = fileName;
		this.fileType = fileType;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "CricketTeam [id=" + id + ", player=" + player + ", image=" + Arrays.toString(image) + ", fileName="
				+ fileName + ", fileType=" + fileType + "]";
	}
	
	
	
}
