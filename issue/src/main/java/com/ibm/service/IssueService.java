package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Issue;
import com.ibm.entity.IssueUser;
import com.ibm.mapper.IssueMapper;

@Service
public class IssueService {
	@Autowired
	IssueMapper issueMapper;
	public List<Issue> issueCount() {
		return issueMapper.issueCount();
	}

	public List<IssueUser> issueIdOrName(IssueUser issueUser){
		return issueMapper.issueIdOrName(issueUser);
	}
}
