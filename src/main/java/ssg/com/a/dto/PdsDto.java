package ssg.com.a.dto;

import java.io.Serializable;

// Personal Data Store
public class PdsDto implements Serializable{

	private int seq;
	private String id;
	private String title;
	private String content;
	
	private String filename; 	// 원본 파일명 	abc.txt bcd.jpg
	private String newfilename; // 업로드 파일명	23424322423.txt 충돌방지를 위해 시스템 시간으로 만든 파일명
	
	private int readcount;
	private int downcount;
	private String regdate;
	
	public PdsDto() {
	}
	
	public PdsDto(String id, String title, String content, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}

	public PdsDto(int seq, String id, String title, String content, String filename, String newfilename, int readcount,
			int downcount, String regdate) {
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", newfilename=" + newfilename + ", readcount=" + readcount + ", downcount=" + downcount
				+ ", regdate=" + regdate + "]";
	}

}
