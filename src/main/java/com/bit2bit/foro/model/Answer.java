package com.bit2bit.foro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class Answer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	private String detail;
	@Column(columnDefinition= "DATETIME")
	private LocalDateTime date;
	private boolean answered;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aau_fk", referencedColumnName = "id")
	private Author author;
	
	@OneToMany(targetEntity = Author.class)
    @JoinColumn(name = "aal_fk", referencedColumnName = "id")
    private List<Author> authors_liked = new ArrayList<Author>();
	
	@OneToMany(targetEntity = Author.class)
    @JoinColumn(name = "aad_fk", referencedColumnName = "id")
    private List<Author> authors_disliked = new ArrayList<Author>();
	
	public Answer(String detail, Long idAuthor) {
		this.answered = false;
		this.author = new Author(idAuthor);
		this.detail = detail;
		this.date = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public boolean isAnswered() {
		return answered;
	}


	public void setAnswered(boolean answered) {
		this.answered = answered;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public List<Author> getAuthors_liked() {
		return authors_liked;
	}


	public void setAuthors_liked(List<Author> authors_liked) {
		this.authors_liked = authors_liked;
	}


	public List<Author> getAuthors_disliked() {
		return authors_disliked;
	}


	public void setAuthors_disliked(List<Author> authors_disliked) {
		this.authors_disliked = authors_disliked;
	}

	
	public Answer() {
		// TODO Auto-generated constructor stub
	}

}
