package com.kh.photo;

import java.util.List;

public interface PhotoService {

	int insertPhoto(Photo photo);
	
	int updatePhoto(Photo photo);

	List<Photo> morePhoto(int start);

}
