package com.cisco.ccs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.cisco.ccs.model.SearchCriteria;
import com.cisco.ccs.util.PLECommonsUtil;
import com.cisco.ccs.constants.*;
//import com.cisco.ple.dao.PLEBaseDAOImpl;
//import com.cisco.ple.dao.PLEDAOConstants;
//import com.cisco.ple.exception.PLEException;
//import com.cisco.ple.util.PageError;
//import com.cisco.ple.validation.ValidationError;

import net.sf.json.JSONObject;

public class CCSBaseController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    private static final String ERRORS = "errors";

    private static final String VALIDATION_ERRORS = "validation_errors";

    protected Locale locale = new Locale("en", "US");

//    protected void handleException(ModelMap modelMap, PLEException pleException) {
//        PageError pageError = new PageError(pleException.getMessage(), pleException);
//        addErrorToModel(modelMap, pageError);
//    }
//
//    private void addErrorToModel(ModelMap modelMap, PageError pageError) {
//        ArrayList<PageError> errors = null;
//
//        if (modelMap.containsAttribute(ERRORS)) {
//            errors = (ArrayList<PageError>) modelMap.get(ERRORS);
//            errors.add(pageError);
//        } else {
//            errors = new ArrayList<PageError>();
//            errors.add(pageError);
//            modelMap.addAttribute(ERRORS, errors);
//        }
//    }

    protected void addMsgToModel(ModelMap modelMap, String messageName, String message) {
        modelMap.addAttribute(messageName, message);
    }

    protected void updateActionMessage(ModelMap modelMap, String message) {
        modelMap.addAttribute("actionMessage", message);
    }
//
//    protected void handleValidationErr(ModelMap modelMap, List<ObjectError> validationErrList) {
//        ValidationError validationError;
//        for (Object object : validationErrList) {
//            if (object instanceof FieldError) {
//                FieldError fieldError = (FieldError) object;
//                validationError = new ValidationError(fieldError.getCode());
//                addValErrorToModel(modelMap, validationError);
//            }
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private void addValErrorToModel(ModelMap modelMap, ValidationError validationError) {
//        ArrayList<ValidationError> errors = null;
//
//        if (modelMap.containsAttribute(VALIDATION_ERRORS)) {
//            errors = (ArrayList<ValidationError>) modelMap.get(VALIDATION_ERRORS);
//            errors.add(validationError);
//        } else {
//            errors = new ArrayList<ValidationError>();
//            errors.add(validationError);
//            modelMap.addAttribute(VALIDATION_ERRORS, errors);
//        }
//    }
//
//    public void setRecordCount(ModelMap modelMap) {
//        modelMap.addAttribute("display", "yes");
//        String topRecords = PLEBaseDAOImpl.getTopRecordCount();
//        modelMap.addAttribute("recordCount", PLEBaseDAOImpl.getTopRecordCount());
//        if (topRecords.equalsIgnoreCase(CCSDaoConstants.ALL_RECORDS)) {
//            modelMap.addAttribute("ALL_RECORDS", CCSDaoConstants.ALL_RECORDS);
//        }
//        modelMap.addAttribute("topTenRecs", "");
//    }

    protected SearchCriteria initSearchCriteriaBean(SearchCriteria searchCriteria, HttpServletRequest request) {
        searchCriteria.setiDisplayLength(Integer.parseInt(request.getParameter(CCSControlerConstants.SEARCH_LIMIT)));
        searchCriteria.setiDisplayStart(Integer.parseInt(request.getParameter(CCSControlerConstants.SEARCH_OFFSET)));
        searchCriteria.setsEcho(Integer.parseInt(request.getParameter(CCSControlerConstants.SEARCH_DRAW_COUNT)));
        if (!PLECommonsUtil.isNullOrEmpty(request.getParameter(CCSControlerConstants.SEARCH_SORT_COLUMN_INDEX))) {
            searchCriteria.setiSortCol(Integer.parseInt(request.getParameter(CCSControlerConstants.SEARCH_SORT_COLUMN_INDEX)));
        }

        if (!PLECommonsUtil.isNullOrEmpty(request.getParameter(CCSControlerConstants.SEARCH_SORT_DIRECTION))) {
            searchCriteria.setsSortDir(request.getParameter(CCSControlerConstants.SEARCH_SORT_DIRECTION));
        }

        if (!PLECommonsUtil.isNullOrEmpty(request.getParameter(CCSControlerConstants.SEARCH_TEXT))) {
            searchCriteria.setSsSearch(request.getParameter(CCSControlerConstants.SEARCH_TEXT));
        }

        return searchCriteria;
    }

    protected void writeSearchResultToResponse(JSONObject dataObject, HttpServletResponse response, SearchCriteria searchCriteria) throws IOException {
        dataObject.put(CCSControlerConstants.SEARCH_DRAW_COUNT, searchCriteria.getsEcho());
        dataObject.put(CCSControlerConstants.SEARCH_TOTAL_DISPLAY_RECORDS, searchCriteria.getTotalRecords());
        dataObject.put(CCSControlerConstants.SEARCH_TOTAL_RECORDS, searchCriteria.getTotalRecords());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(dataObject.toString());
    }

    public Collection<String> parseList(String dbookString, Collection<String> dbookSet) {
        StringTokenizer str = new StringTokenizer(dbookString, ",");
        while (str.hasMoreElements()) {
            String ele = (String) str.nextElement();
            if (!ele.equals("") || ele != null) {
                dbookSet.add(ele);
            }
        }
        return dbookSet;
    }
}