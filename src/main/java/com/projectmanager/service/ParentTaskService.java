package com.projectmanager.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.domain.ParentTask;
import com.projectmanager.repository.ParentTaskRepository;

@Service
@Transactional
public class ParentTaskService {

	private final Logger logger = LoggerFactory.getLogger(ParentTaskService.class);

	@Autowired
	private ParentTaskRepository parentTaskRepo;

	public ParentTask addParentTask(ParentTask parent) {
		logger.info("Entered Method Name :  addParentTask");
		ParentTask i = parentTaskRepo.save(parent);
		return i;
	}

	public List<ParentTask> getAllParentTasks() {
		logger.info("Entered Method Name :  getAllParent");
		return (List<ParentTask>) parentTaskRepo.findAll();
	}

	
	public ParentTask findParentTaskById(Long Id) {
		logger.info("Entered Method Name: findParentTaskById ");
		Optional<ParentTask> parentTaskIfExist = parentTaskRepo.findById(Id);
		if (parentTaskIfExist != null)
			return (ParentTask) parentTaskIfExist.get();
		else
			return null;
	}
}
