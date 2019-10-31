package com.datamaster.test.service;

import com.datamaster.test.model.Survey;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SurveyService {
    List<Survey> getAllSurvey(Integer page, Integer pageSize, String sortBy, Optional<String> name, Optional<LocalDate> startDate, Optional<Boolean> enable);

    Survey add(Survey survey);

    boolean delete(Long id);

    Survey update(Survey survey);
}
