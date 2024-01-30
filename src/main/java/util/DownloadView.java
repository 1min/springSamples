package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import ssg.com.a.service.PdsService;

// 다운로드 되는 뷰
// .jsp파일을 대신함, 약간 컨트롤러 역할도 겸함
public class DownloadView extends AbstractView {

	// download 횟수를 증가
	@Autowired
	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		// 짐풀어!           getAttribute("downloadFile")와 같음
		File downloadFile = (File)model.get("downloadFile");
		String filename  = (String)model.get("filename");
		int seq = (Integer)model.get("seq");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		// 파일이름이 한글인 파일의 경우 적용
		// java.net 패키지
		filename = URLEncoder.encode(filename, "utf-8");
		
		// 다운로드 받는 윈도우(View)
		// filename 부분 꼭 있어야 다운로드를 받아서 newfilename(FileInputStream(downloadFile))을 원본 filename(원본파일명)이름으로 받음
		// 이 헤더는 브라우저에게 서버에서 전성되는 컨텐츠를 첨부 파일로 다운로드 하라는 지시
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		// 이진 데이터로 전송, 이 헤더 설정시 ASCII형식으로 인코딩하지 않고 직접전송가능
		response.setHeader("Content-Transfer-Encoding", "binary;");
		/// 다운로드 길이
		response.setHeader("Content-Length", "" + downloadFile.length());
		// 브라우저에 캐시하지 않고 원본 서버에 항상 새로운 데이터 요청
		response.setHeader("Pragma", "no-cache;");
		// 캐싱 만료 시간 지정, -1 설정시 캐시가 되지 않음
		response.setHeader("Expires", "-1;");
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(downloadFile);
		
		// 실제로 파일에 기입하는 처리, 아마 response에 달아줌
		FileCopyUtils.copy(fis, os);
		
		// download 회수를 증가 // service로 readcount 증가
		
		if(fis != null) {
			fis.close();
		}
		
		service.pdsdowncount(seq);
	}

}
