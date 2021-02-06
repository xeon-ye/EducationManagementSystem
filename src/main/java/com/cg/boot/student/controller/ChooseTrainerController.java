package com.cg.boot.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseTrainer;
import com.cg.boot.service.ITrainerService;

@RestController
@RequestMapping("/api")
public class ChooseTrainerController {

	@Autowired
	ITrainerService service;
	Logger logger=LoggerFactory.getLogger(ChooseTrainerController.class);
	
	@GetMapping("/getChoosedTrainer/{trainerId}/{studentId}")
	public ResponseEntity<ChooseTrainer> getProgressDetails(@PathVariable("trainerId") int trainerId,@PathVariable("studentId") int studentId) {
		ChooseTrainer trainer = service.getTrainerDetails(trainerId,studentId);
		if (trainer == null) {
			logger.error("Course Trainer not Choosen");
			throw new DataNotFoundException("Trainer Not Found By these ID");
		}
		logger.info("Course trainer Choose Successfully");
		return new ResponseEntity<ChooseTrainer>(trainer,HttpStatus.OK);
	}
}
