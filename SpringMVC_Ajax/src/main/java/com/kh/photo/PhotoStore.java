package com.kh.photo;

import java.util.List;

public interface PhotoStore {

	int insertPhoto(Photo photo);

	List<Photo> morePhoto(int start);

}
