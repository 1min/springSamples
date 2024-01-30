package util;

import java.util.Date;

public class PdsUtil {
	
	// abc.txt => 43534534.txt
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = ""; // .jpg .txt
		
		if(filename.indexOf('.') >= 0) { // 확장자명이 있음
			// 확장자명 제거
			fpost = filename.substring(filename.indexOf('.')); // .txt
			newfilename = new Date().getTime() + fpost;	// 435345634.txt 시스템 시간 파일명으로 변환
		}else { // 확장자명이 없음 indexof('.')하면 -1반환
			newfilename = new Date().getTime() + ".back"; // 확장자명없는거 확장자명 붙여서 돌려줌
		}
		
		return newfilename;
	}
}
