package ssg.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist.do")
	public String pdslist(Model model) {
		System.out.println("PdsController pdslist " + new Date());
		
		List<PdsDto> list = service.pdslist();
		model.addAttribute("list", list);
		
		return "pds/pdslist";
	}
	
	@GetMapping("pdswrite.do")
	public String pdswrite() {
		System.out.println("PdsController pdswrite " + new Date());
		
		return "pds/pdswrite";
	}
	
	@PostMapping("pdsupload.do")
	// id, title, content만 PdsDto로 넘어옴
	public String pdsupload(PdsDto pds,
			// value는 name이름이며 required는 기본 true인 경우 fileupload란 name안넘어오면 예외터트림
			@RequestParam(value="fileupload", required = false)
			// name이랑 같은 이름으로 변수명
			MultipartFile fileupload,
			// 톰캣 서버 파일 저장소 물리위치 알기위한용도
			HttpServletRequest request) {
		System.out.println("PdsController pdsupload " + new Date());
		// filename 취득
		String filename = fileupload.getOriginalFilename();
		System.out.println("filename:" + filename);
		
		// db에 저장하기 위한 파일이름, origin filename
		pds.setFilename(filename); // abc.txt
		
		// upload의 경로
		// tomcat(server)에 넣을수도 있고 <- 배포시 이게 정석, 테스트시에 톰캣 껐다키면 톰캣에 저장한 파일은 사라지는 경우 있음
		// webapp 밑에 upload 폴더경로
		String fupload = request.getServletContext().getRealPath("/upload");
				
		// 서버가 위치한 컴퓨터의 폴더에 넣을수도 있음 <- 주로 테스트용
		// String fupload = "d:\\tmp";
		System.out.println("파일업로드 경로:" + fupload);
		
		/// 파일명 변경 abc.txt -> 324234324.txt 시스템시간
		String newfilename = PdsUtil.getNewFileName(filename);
		System.out.println("newfilename:" + newfilename);
		
		// db에 저장하기 위한 시스템타임 이름 파일명
		pds.setNewfilename(newfilename);
		
		// 파일을 톰캣서버에 업로드 webapp/upload/시스템파일명
		File file = new File(fupload + "/" + newfilename);
		
		try {
			// 패키지명 org.apache.commons.io.FileUtils
			// 위 파일경로 파일 파일 내용추가
			// 실제 파일을 업로드
			FileUtils.writeByteArrayToFile(file, fileupload.getBytes());
			
			boolean isS = service.pdsupload(pds);
			
			if(isS) {
				System.out.println("파일 업로드 성공");
			}else {
				System.out.println("파일 업로드 실패");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	@GetMapping("pdsdetail.do")
	public String pdsdetail(int seq, Model model) {
		System.out.println("PdsController pdsdetail " + new Date());
		
		PdsDto dto = service.getPds(seq);
		model.addAttribute("dto", dto);
		
		return "pds/pdsdetail";
	}
	
	@GetMapping("filedownload.do")
	public String filedownload(int seq, String newfilename, String filename, 
			Model model, HttpServletRequest request) {
		System.out.println("PdsController filedownload " + new Date());
		
		// 경로
		// tomcat
		String fupload = request.getServletContext().getRealPath("/upload");
		
		// 다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		
		// 짐싸
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("filename", filename);
		model.addAttribute("seq", seq);
		
		// file-context에서 주입한 <bean id="downloadView" class="util.DownloadView"></bean>이 클래스로 이동한다.
		// bean의 id값으로 찾음, 따로 sendredirect 되지는 않고 forward로 실행되는듯
		return "downloadView";
	}
}
