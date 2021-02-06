package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

@RestController
@RequestMapping("/api")
public class ProgressDetailsController {

	@Autowired
	IProgressDetailsService service;
	Logger logger=LoggerFactory.getLogger(ProgressDetailsController.class);
	/*
	 * Get All ProgressDetails By Grade
	 */
	@GetMapping("/getProgressDetails/{grade}")
	public ResponseEntity<List<ProgressDetails>> getGrade(@PathVariable String grade) throws DataNotFoundException {
		// List<Grade>list=null;
		List<ProgressDetails> grades = service.getAllProgressDetails(grade);
		if (grades.isEmpty()) {
			logger.warn("Progress Details Not found with grade "+grade);
			throw new DataNotFoundException("Progress Details Not Found");
		}
		logger.info("Progress Details return Successfully with grade "+grade);
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

	/*
	 * Get ProgressDetails By Id
	 */
	@GetMapping("/getProgressDetailsById/{gradeId}")
	public ResponseEntity<ProgressDetails> getProgressDetails(@PathVariable("gradeId") int gradeId)
			throws DataNotFoundException {
		ProgressDetails grade = service.getProgressDetails(gradeId);
		if (grade == null) {
			logger.warn("Progress Details Not found with gradeID "+gradeId);
			throw new DataNotFoundException("Grade Not Found By these ID");
		}
		logger.info("Progress Details return Successfully with gradeID "+gradeId);
		return new ResponseEntity<ProgressDetails>(grade, HttpStatus.OK);
	}

	/*
	 * Get All ProgressDetails
	 */
	@GetMapping("/getAllProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			logger.warn("Progress Details Not found ");
			throw new DataNotFoundException("Grades Details Not Found");
		}
		logger.info("All Progress Details return successfully");
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

	/*
	 * Add ProgressDetails
	 */
	@PostMapping("/addProgressDetails")
	public ResponseEntity<Integer> addProgressDetails(@Valid @RequestBody ProgressDetails progressDetails) {
		ProgressDetails gradeInfo = service.addProgressDetails(progressDetails);
		logger.info("Progress Details added successfully");
		return new ResponseEntity<Integer>(gradeInfo.getGradeId(), HttpStatus.OK);
	}

	/*
	 * Update ProgressDetails
	 */
	@PutMapping("/updateGrade")
	public ResponseEntity<ProgressDetails> updateProgressDetails(@RequestBody ProgressDetails progressDetails)
			throws DataNotFoundException {
		ProgressDetails gradeInfo = service.updateProgressDetails(progressDetails);
		if (gradeInfo == null) {
			logger.warn("Progress Details Not found to update");
			throw new DataNotFoundException("Grade Details Not Found for update");
		}
		logger.info("Progress Details Updated successfully");
		return new ResponseEntity<ProgressDetails>(gradeInfo, HttpStatus.OK);
	}

	/*
	 * Delete ProgressDetails
	 */
	@DeleteMapping("/deleteProgressDetails/{progressId}/{userId}")
	public ResponseEntity<List<ProgressDetails>> deleteProgressDetails(@PathVariable("progressId") int progressId,
			@PathVariable("userId") int userId) {
		List<ProgressDetails> grades = service.deleteProgressDetails(progressId,userId);
		logger.warn("Progress Details with progress ID "+progressId+ " and Student ID "+userId+" Deleted successfully");
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
