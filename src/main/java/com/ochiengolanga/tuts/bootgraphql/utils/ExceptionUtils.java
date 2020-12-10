package com.ochiengolanga.tuts.bootgraphql.utils;

public class ExceptionUtils {

	public static final int CONVERT_XML_TO_OBJECT = 800;
	public static final int NOT_RET_FEED = 801;

	public static String getMessage(int errorCode) {
		switch (errorCode) {
		case CONVERT_XML_TO_OBJECT:
			return "Error: no possible to convert xml to object";
		case NOT_RET_FEED:
			return "Error: no possible to retrieve the feed";
		default:
			return "Error";
		}
	}

}
