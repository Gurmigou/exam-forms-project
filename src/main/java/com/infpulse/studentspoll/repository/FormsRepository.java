package com.infpulse.studentspoll.repository;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormsRepository extends JpaRepository<Form, Long> {

//    List<Form> findAllByUser(User user);

	/*should return all forms where owner equals user*/
	List<Form> findOwnedForms(User user);

	/*should set form visibility
	void setDeleted(long id);*/

}