package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Issue;
import com.ibm.entity.IssueUser;
import com.ibm.entity.User;
import com.ibm.mapper.IssueMapper;

@Service
public class IssueService {
	@Autowired
	IssueMapper issueMapper;

	public List<Issue> issueCount() {
		return issueMapper.issueCount();
	}

	public List<IssueUser> issueIdOrName(IssueUser issueUser) {
		return issueMapper.issueIdOrName(issueUser);
	}

	public void addIssue(Issue issue) {
		issueMapper.addIssue(issue);
	}

	public User findByUserID(Integer userID) {
		return issueMapper.findByUserID(userID);
	}

	public List<IssueUser> findIssuetask(Integer userID) {
		return issueMapper.findIssuetask(userID);
	}

	// issue模糊查询
	public List<IssueUser> findLikeIssue(IssueUser issueUser) {
		return issueMapper.findLikeIssue(issueUser);
	}

	// issue修改
	public void UpdateIssue(Issue issue) {
		issueMapper.UpdateIssue(issue);
	}

	// issue修改：解决方案solution
	public void UpdateIssueSolution(Issue issue) {
		issueMapper.UpdateIssueSolution(issue);
	}

	// issue创建人需验证修改：状态issuestate
	public void UpdateIssueState(Issue issue) {
		issueMapper.UpdateIssueState(issue);
	}

	public List<IssueUser> findIssueAll() {
		return issueMapper.findIssueAll();
	}
	
	// issue查询登录用户的创建issue
	public List<IssueUser> findCreateIssue(String creater) {
		return issueMapper.findCreateIssue(creater);
	}
}
