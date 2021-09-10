package com.bit2bit.foro.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit2bit.foro.model.Answer;
import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.model.NewAnswerDTO;
import com.bit2bit.foro.model.Question;
import com.bit2bit.foro.service.AnswerService;
import com.bit2bit.foro.service.AuthorService;
import com.bit2bit.foro.service.QuestionService;

@RequestMapping("foro")
@RestController
@CrossOrigin(origins = "*")
public class ForoController {
	@Autowired
	QuestionService questionservice;
	
	@Autowired
	AnswerService answerservice;
	
	@Autowired
	AuthorService authorservice;
	
	//Obtener todas las preguntas
	@GetMapping("/questions")
	public ResponseEntity<?> ListAllQuestions() {
		List<Question> LQuestions = questionservice.listAllOrderbyDate();
		if (LQuestions == null)
			return new ResponseEntity<List<Question>>(LQuestions, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<List<Question>>(LQuestions, HttpStatus.OK);
	}
	
	//Obtener respuestas por IDQuestion
	@GetMapping("/{idQuestion}/answers")
	public ResponseEntity<?> GetAnswersbyQuestionID(@PathVariable Long idQuestion) {
		List<Answer> answers = questionservice.getAnswersbyQuestionID(idQuestion);
		if (answers == null)
			return new ResponseEntity<List<Answer>>(answers, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<List<Answer>>(answers, HttpStatus.OK);
	}
	
	@GetMapping("/{idQuestion}/question")
	public ResponseEntity<?> GetQuestionbyID(@PathVariable Long idQuestion) {
		Question question = questionservice.getQuestionbyID(idQuestion);
		if (question == null)
			return new ResponseEntity<Question>(question, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	
	//Crear una pregunta por AuthorID
	@PutMapping("/{idQuestion}/question")
	public ResponseEntity<?> updateQuestionDetails(@PathVariable Long idQuestion, 
			@RequestParam(name = "AddView", required = false, defaultValue = "") String paddView,
			@RequestParam(name = "SetAnswer", required = false, defaultValue = "") String pidAnswer ) {
		Question question = null;
		if (paddView.length() != 0 && Long.parseLong(paddView) > 0) 
			question = questionservice.updateQuestionViews(idQuestion);
		else if (pidAnswer.length() != 0 && Long.parseLong(pidAnswer) > 0)  {
			question = questionservice.updateQuestionAnswered(idQuestion, Long.parseLong(pidAnswer));
		}
		if (question == null)
			return new ResponseEntity<Question>(question, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}
	
	//Crear una pregunta por AuthorID
	@PostMapping("/{idAuthor}/question")
	public ResponseEntity<?> CreateQuestion(@PathVariable Long idAuthor, @RequestBody Question newQuestion) {
		Question question = questionservice.createQuestion(newQuestion, idAuthor);
		if (question == null)
			return new ResponseEntity<Question>(question, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}
		
	@DeleteMapping("/{idQuestion}/question")
	public ResponseEntity<?> DeleteAuthor(@PathVariable Long idQuestion) {
		if (questionservice.deleteQuestion(idQuestion))
			return new ResponseEntity<String>("{\"Mensaje\": \"Eliminado\"}"  , HttpStatus.OK);
		else
			return new ResponseEntity<String>("{\"Mensaje\": \"No Eliminado\"}", HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/{idQuestion}/answer")
	public ResponseEntity<?> CreateAnswer(@PathVariable Long idQuestion, @RequestBody NewAnswerDTO newAnswer) {
		Question question = answerservice.createAnswer(newAnswer, idQuestion);
		if (question == null)
			return new ResponseEntity<Question>(question, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}
	
	@PutMapping("/{idAnswer}/answer")
	public ResponseEntity<?> UpdateAnswer(@PathVariable Long idAnswer,
			@RequestParam(name = "AddLike", required = false, defaultValue = "") String pidAuthorLike,
			@RequestParam(name = "AddDislike", required = false, defaultValue = "") String pidAuthorDislike)
	{
		if (pidAuthorLike.length() != 0 && Long.parseLong(pidAuthorLike) > 0) {
			Answer answer = answerservice.updateAnswerLikeorDislike(idAnswer, Long.parseLong(pidAuthorLike), true);
			return new ResponseEntity<Answer>(answer, HttpStatus.OK);
		}
		else if (pidAuthorDislike.length() != 0 && Long.parseLong(pidAuthorDislike) > 0) {
			Answer answer = answerservice.updateAnswerLikeorDislike(idAnswer, Long.parseLong(pidAuthorDislike), false);
			return new ResponseEntity<Answer>(answer, HttpStatus.OK);
		}
		return new ResponseEntity<Answer>(new Answer(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/author")
	public Author CreateAuthor(@RequestBody Author newAuthor) {
		return authorservice.createAuthor(newAuthor);
	}
	
	/*
	 * @GetMapping("/author") public Author GetAuthor(@RequestBody Author author) {
	 * return authorservice.findByUserandPwd(author); }
	 */
	
	@GetMapping("/author")
	public ResponseEntity<?> GetAuthor(
			@RequestParam(name = "username", required = true) String pusername,
			@RequestParam(name = "password", required = false, defaultValue = "") String ppassword) {
		Author author;
		if ( pusername.length()!=0 && ppassword.length()!=0 )
			author = authorservice.findByUserandPwd(pusername,ppassword);
		else 
		{
			if (authorservice.findByUser(pusername))
				author = new Author(1L);
			else 
				author = new Author(0L);
		}
		if (author == null)
			return new ResponseEntity<Author>(author  , HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Author>(author  , HttpStatus.OK);
	}
	
	@GetMapping("/authors")
	public List<Author> GetAuthor() {
		return authorservice.listAll();
	}
	
}
