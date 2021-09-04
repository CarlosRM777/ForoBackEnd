package com.bit2bit.foro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2bit.foro.model.Answer;
import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.model.Question;
import com.bit2bit.foro.repository.AuthorRepo;
import com.bit2bit.foro.repository.QuestionRepo;

@Service
@Transactional
public class QuestionService {
	@Autowired
	QuestionRepo questionrepo;
	
	@Autowired
	AuthorRepo authorrepo;
	
	public Question createQuestion(Question newQuestion, Long idAuthor) {
		Optional<Author> Optauthor = authorrepo.findById(idAuthor);
		if (Optauthor.isPresent()) {
			newQuestion.setAuthor(Optauthor.get());
			newQuestion.setCreationDate(LocalDateTime.now());
			newQuestion.setViews(0L);
			newQuestion.setIdcorrectAnswer(0L);
			return questionrepo.save(newQuestion);
		}
		return null;
	} 
	
	public boolean deleteQuestion(Long idQuestion) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			questionrepo.deleteById(idQuestion);
			return true;
		}
		return false;
	}
	
	public List<Question> listAllOrderbyDate() {
		return questionrepo.findAllByOrderByCreationDateDesc();
	}
	
	public Question getQuestionbyID(Long idQuestion) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			return Optquestion.get();
		}
		return null;
	}
	
	public Question updateQuestionViews(Long idQuestion) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			Question question = Optquestion.get();
			if (question.getViews() == null) question.setViews(1L);
			else question.setViews(question.getViews() + 1 );
			return question;
		}
		return null;
	}
	
	public List<Answer> getAnswersbyQuestionID(Long idQuestion) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			Question question = Optquestion.get();
			return question.getAnswers();
		}
		return null;
	}

	public Question updateQuestionAnswered(Long idQuestion, Long idAnswer) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			Question question = Optquestion.get();
			if (question.getIdcorrectAnswer()==null || question.getIdcorrectAnswer() == 0L) {
				question.setIdcorrectAnswer(idAnswer);
				return question;
			}
		}
		return null;
	}
}
