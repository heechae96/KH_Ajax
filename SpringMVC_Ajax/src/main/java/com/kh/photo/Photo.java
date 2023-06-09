package com.kh.photo;

import java.util.Date;

public class Photo {
	private int photoNo;
	private String photoWriter;
	private String photoContent;
	private String photoFilename;
	private String photoFileRename;
	private String photoFilepath;
	private Date photoDate;
	private int viewCount;
	
	public Photo() {
		super();
	}

	public Photo(int photoNo, String photoWriter, String photoContent, String photoFilename, String photoFileRename,
			String photoFilepath, Date photoDate, int viewCount) {
		super();
		this.photoNo = photoNo;
		this.photoWriter = photoWriter;
		this.photoContent = photoContent;
		this.photoFilename = photoFilename;
		this.photoFileRename = photoFileRename;
		this.photoFilepath = photoFilepath;
		this.photoDate = photoDate;
		this.viewCount = viewCount;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public String getPhotoWriter() {
		return photoWriter;
	}

	public void setPhotoWriter(String photoWriter) {
		this.photoWriter = photoWriter;
	}

	public String getPhotoContent() {
		return photoContent;
	}

	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public String getPhotoFileRename() {
		return photoFileRename;
	}

	public void setPhotoFileRename(String photoFileRename) {
		this.photoFileRename = photoFileRename;
	}

	public String getPhotoFilepath() {
		return photoFilepath;
	}

	public void setPhotoFilepath(String photoFilepath) {
		this.photoFilepath = photoFilepath;
	}

	public Date getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(Date photoDate) {
		this.photoDate = photoDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", photoWriter=" + photoWriter + ", photoContent=" + photoContent
				+ ", photoFilename=" + photoFilename + ", photoFileRename=" + photoFileRename + ", photoFilepath="
				+ photoFilepath + ", photoDate=" + photoDate + ", viewCount=" + viewCount + "]";
	}

}
