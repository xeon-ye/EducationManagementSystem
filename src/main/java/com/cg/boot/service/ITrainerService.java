package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.ChooseTrainer;
import com.cg.boot.model.Trainer;
/**
 * @author Madhuri. This interface contains abstract methods TrainerService
 *         class.
 *
 */

public interface ITrainerService {
	Trainer addTrainer(Trainer trainer);

	Trainer updateTrainer(Trainer trainer);

	Trainer getTrainer(int id);

	List<Trainer> getAllTrainers();

	List<Trainer> deleteTrainer(int trainerId,int userId);

	ChooseTrainer getTrainerDetails(int id, int studentId);

	boolean isValidTrainerName(String trainerName);

	boolean isValidTrainerPhoneNo(String phoneNo);



}
