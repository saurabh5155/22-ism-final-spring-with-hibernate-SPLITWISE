package com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.GroupBean;

@Repository
public interface UserGroupRepository extends CrudRepository<GroupBean, Integer>{
	GroupBean findByGroupId(Integer groupId);

	@Query(value = "select * from user_group u JOIN groups g  on u.group_id = g.group_id where u.user_id = :userId",nativeQuery = true)
	List<GroupBean> findByUsers(Integer userId);
}
