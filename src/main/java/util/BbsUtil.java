// 웹 컨텍스트와 직접적인 연관없어서 공용패키지 ssg.com.a에 안넣어도됨
package util;

public class BbsUtil {

	// 제목이 길 경우에 ...로 표현하는 함수
	public static String dot3(String title) {
		String str = "";

		if (title.length() >= 35) {
			str = title.substring(0, 35);
			str += "...";
		} else {
			str = title.trim();
		}

		return str;
	}

	// 답글의 화살표와 공백을 추가하는 함수
	public static String arrow(int depth) {
		String rs = "<img src='./images/arrow.png' width='12px' height='12px'/>";
		String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";

		String ts = ""; // 공백에 따라서
		for (int i = 0; i < depth; i++) {
			ts += nbsp;
		}

		return depth == 0 ? "" : ts + rs;
	}
}
