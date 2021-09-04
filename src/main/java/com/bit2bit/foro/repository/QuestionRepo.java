package com.bit2bit.foro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit2bit.foro.model.Question;

public interface QuestionRepo extends JpaRepository <Question, Long>{
	//List<Answer> findAllByPositionNameOrPersonName(String pPositionName, String pPersonName);
	List<Question> findAllByOrderByCreationDateDesc();
	
}
