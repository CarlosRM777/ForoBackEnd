package com.bit2bit.foro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.repository.AuthorRepo;
import com.bit2bit.foro.service.AuthorService;

@SpringBootTest
@Transactional
public class ForoServiceTest {
	@Autowired
	AuthorService authorservice;
	
	@Autowired
	AuthorRepo authorrepo;
	
	public ForoServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
    public void shouldCreateAuthor() throws Exception {
        Author result = authorservice.createAuthor(createTestAuthor1());
        assertThat(result).isNotNull();
        //assertThat(result).extracting(Employee::getId).isEqualTo(result.getId());
        assertThat(result).extracting(Author::getName).isEqualTo("Carlos Rodriguez");
        assertThat(result).extracting(Author::getUsername).isEqualTo("CarlosRM7");
        assertThat(result).extracting(Author::getPassword).isEqualTo("123456");
        assertThat(result).extracting(Author::getUrl).isEqualTo("");
    }
	
	private Author createTestAuthor1() {
		return new Author("Carlos Rodriguez", "CarlosRM7", "123456", "");
	}
}
