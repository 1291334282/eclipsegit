package com.ibm.mapper;

import java.util.List;

import com.ibm.entity.Issue;
import com.ibm.entity.IssueUser;

public interface IssueMapper {
	public List<Issue> issueCount();

	public List<IssueUser> issueIdOrName(IssueUser issueuser);
}
