package com.datamaster.test.controller.rest;

import com.datamaster.test.model.Survey;
import com.datamaster.test.service.SurveyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/rest/surveys"})
public class SurveyController {

    private SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @ApiOperation(value = "Finds all Surveys with filter and sorting",
            notes = "Finds all Surveys with filter and sorting, pagginaton",
            response = Survey.class,
            responseContainer = "List")
    @GetMapping(value = "/")
    public ResponseEntity<List<Survey>> getAllSurvey(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "name") String sortBy,
                                                     @RequestParam(required = false) Optional<String> name,
                                                     @RequestParam(required = false) Optional<Boolean> enable,
                                                     @RequestParam(name = "startDate", required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate
    ) {
        List<Survey> allSurvey = surveyService.getAllSurvey(page, pageSize, sortBy, name, startDate, enable);
        return new ResponseEntity<>(allSurvey, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Survey",
            notes = "fields 'Id' not required",
            response = HttpStatus.class,
            responseContainer = "Survey")
    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Survey> addSurvey(@RequestBody Survey survey) {
        Survey add = surveyService.add(survey);
        if (add != null) {
            return new ResponseEntity(add, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Survey by Id",
            notes = "Delete Survey by Id",
            response = HttpStatus.class
            )
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public HttpStatus deleteSurvey(@PathVariable("id") Long id) {
        return surveyService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

    @ApiOperation(value = "Update Survey " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK -  update survey", response = Survey.class),
            @ApiResponse(code = 404, message = "NOT_FOUND survey in BD")
    })
    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Survey> updateSurvey(@RequestBody Survey survey) {
        Survey update = surveyService.update(survey);
        if (update != null) {
            return new ResponseEntity<>(update, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
