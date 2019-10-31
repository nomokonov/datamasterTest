package com.datamaster.test.service;

import com.datamaster.test.model.Survey;
import com.datamaster.test.repository.interfaces.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<Survey> getAllSurvey(Integer page, Integer pageSize, String sortBy, Optional<String> name, Optional<LocalDate> startDate, Optional<Boolean> enable) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Specification<Survey> specification = null;
        if (name.isPresent()) {
           specification = where(specification).and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), name.get()));
        }
        if (enable.isPresent()) {
            specification = where(specification).and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("enable"), enable.get()));
        }
        if (startDate.isPresent()) {
            specification = where(specification).and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("startDate"), startDate.get()));
        }

        Page<Survey> pagedResult = surveyRepository.findAll(specification, pageable);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Survey>();
        }
    }

    @Override
    public Survey add(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Survey> surveyFromDB = surveyRepository.findById(id);
        if (surveyFromDB.isPresent()) {
            surveyRepository.delete(surveyFromDB.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Survey update(Survey survey) {
        if (surveyRepository.existsById(survey.getId())) {
            return surveyRepository.save(survey);
        } else {
            return null;
        }
    }
}
