package com.ibm.mapper;

import java.util.List;

import com.ibm.entity.Issue;
import com.ibm.entity.IssueUser;
import com.ibm.entity.User;

public interface IssueMapper {
	public List<Issue> issueCount();

	public List<IssueUser> issueIdOrName(IssueUser issueuser);

	// 创建issue
	public void addIssue(Issue issue);

	public User findByUserID(Integer userID);

	// issue任务列表
	public List<IssueUser> findIssuetask(Integer userID);

	// issue模糊查询
	public List<IssueUser> findLikeIssue(IssueUser issueUser);

	public List<IssueUser> findIssueAll();

	// issue修改
	public void UpdateIssue(Issue issue);

	// issue修改：解决方案solution
	public void UpdateIssueSolution(Issue issue);

	// issue创建人需验证修改：状态issuestate
	public void UpdateIssueState(Issue issue);
}
