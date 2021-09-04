package com.bit2bit.foro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2bit.foro.model.Answer;
import com.bit2bit.foro.model.Author;
import com.bit2bit.foro.model.NewAnswerDTO;
import com.bit2bit.foro.model.Question;
import com.bit2bit.foro.repository.AnswerRepo;
import com.bit2bit.foro.repository.QuestionRepo;

@Service
@Transactional
public class AnswerService {
	
	@Autowired
	AnswerRepo answerrepo;
	
	@Autowired
	QuestionRepo questionrepo;
	
	public Question createAnswer(NewAnswerDTO newAnswer, Long idQuestion) {
		Optional<Question> Optquestion = questionrepo.findById(idQuestion);
		if (Optquestion.isPresent()) {
			Question question = Optquestion.get();
			Answer answer = new Answer(newAnswer.getDetail(), newAnswer.getIdAuthor());
			question.getAnswers().add(answer);	
			return questionrepo.save(question);
		}
		return null;
	} 
	
	public void deleteAnswer(Answer deleteAnswer) {
		answerrepo.delete(deleteAnswer);
	}

	public Answer updateAnswerLikeorDislike(Long idAnswer, long idAuthor, boolean BLike) {
		Optional<Answer> Optanswer = answerrepo.findById(idAnswer);
		if (Optanswer.isPresent()) {
			Answer answer = Optanswer.get();
			Author author = new Author();
			author.setId(idAuthor);
			if (BLike)
				answer.getAuthors_liked().add(author);
			else
				answer.getAuthors_disliked().add(author);
			//return answerrepo.save(answer);
			return answer;
		}
		return null;
	}
}
