package com.bit2bit.foro.model;

public class NewAnswerDTO {
	private String detail;
	private Long idAuthor;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Long getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}
	public NewAnswerDTO(String detail, Long idAuthor) {
		super();
		this.detail = detail;
		this.idAuthor = idAuthor;
	}
	
	
}
