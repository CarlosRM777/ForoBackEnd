package com.bit2bit.foro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    //@Column
    private String title;
    @Column(columnDefinition= "VARCHAR(5000)")
    private String detail;
    @Column(columnDefinition= "DATETIME")
    private LocalDateTime creationDate;
    private Long views;
    private Long idcorrectAnswer;
    public Long getViews() {
		return views;
	}


	public void setViews(Long views) {
		this.views = views;
	}

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qau_fk", referencedColumnName = "id")
	Author author;
    
    @OneToMany(targetEntity = Answer.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "qan_fk", referencedColumnName = "id")
    private List<Answer> answers = new ArrayList<Answer>();
    
    
	public Question(String title, String detail, LocalDateTime creationDate, Author author, List<Answer> answers) {
		super();
		this.title = title;
		this.detail = detail;
		this.creationDate = creationDate;
		this.author = author;
		this.answers = answers;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public List<Answer> getAnswers() {
		return answers;
	}


	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	

	public Question() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdcorrectAnswer() {
		return idcorrectAnswer;
	}


	public void setIdcorrectAnswer(Long idcorrectAnswer) {
		this.idcorrectAnswer = idcorrectAnswer;
	}

}
