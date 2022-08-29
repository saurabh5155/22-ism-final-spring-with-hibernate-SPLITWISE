package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.GroupBean;

@Repository
public interface GroupRepository extends CrudRepository<GroupBean, Integer>{
	GroupBean findByGroupId(Integer groupId);
	List<GroupBean> findAll();
}
