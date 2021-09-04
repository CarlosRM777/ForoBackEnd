package com.bit2bit.foro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.repository.AuthorRepo;

@Service
@Transactional
public class AuthorService {
	@Autowired
	AuthorRepo Authorrepo;
	
	public Author createAuthor(Author newAuthor) {
		return Authorrepo.save(newAuthor);
	} 
	
	public void deleteAuthor(Author deleteAuthor) {
		Authorrepo.delete(deleteAuthor);
	}
	
	public Author findByUserandPwd(String user, String pwd) {
		//String user = author.getUsername();
		//String pwd = author.getPassword();
		Author author  = Authorrepo.findByUsernameAndPassword(user,pwd);
		if (author == null)
			return new Author("","","","");
		return author;
		//return Authorrepo.findOnebyUsernameAndPassword(user,pwd);
	}
	
	public List<Author> listAll() {
		return Authorrepo.findAll();
	}
}
