package com.bit2bit.foro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bit2bit.foro.model.Answer;

public interface AnswerRepo extends JpaRepository <Answer, Long>{
	//List<Answer> findAllByPositionNameOrPersonName(String pPositionName, String pPersonName);
	List<Answer> findAllByOrderByDateDesc();

}
