package com.datamaster.test.repository.interfaces;

import com.datamaster.test.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> , JpaSpecificationExecutor<Survey> {

}
