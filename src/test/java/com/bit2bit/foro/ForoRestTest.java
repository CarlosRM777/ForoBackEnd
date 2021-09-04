package com.bit2bit.foro;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bit2bit.foro.model.Answer;
import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.model.Question;
import com.bit2bit.foro.rest.ForoController;
import com.bit2bit.foro.service.AnswerService;
import com.bit2bit.foro.service.AuthorService;
import com.bit2bit.foro.service.QuestionService;

@WebMvcTest(controllers = ForoController.class)
public class ForoRestTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuestionService questionservice;
	
	@MockBean
	private AnswerService answerService;
	
	@MockBean
	private AuthorService authorService;
	
	@Test
	public void shouldReturnAllQuestions() throws Exception {

		// given
		List<Question> questions = new ArrayList<Question>();
		questions.add(createTestQuestion1());
		questions.add(createTestQuestion2());
		questions.add(createTestQuestion3());
		// when
		when(questionservice.listAllOrderbyDate()).thenReturn(questions);

		// then
		mockMvc.perform(get("/foro/questions").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].title", is("Pregunta 1")))
				.andExpect(jsonPath("$[0].detail", is("Detalle Pregunta 1")))
				.andExpect(jsonPath("$[1].detail", is("Detalle Pregunta 2")));
	}
	
	private Question createTestQuestion1() {
		Question question = new Question("Pregunta 1", "Detalle Pregunta 1", LocalDateTime.now(), new Author(), new ArrayList<Answer>());
        return question;
    }
    private Question createTestQuestion2() {
    	Question question = new Question("Pregunta 2", "Detalle Pregunta 2", LocalDateTime.now(), new Author(), new ArrayList<Answer>());
        return question;
    }
    private Question createTestQuestion3() {
    	Question question = new Question("Pregunta 2", "Detalle Pregunta 2", LocalDateTime.now(), new Author(), new ArrayList<Answer>());
        return question;
    }

}
