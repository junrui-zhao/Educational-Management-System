package com.crtvu.service.implementation;

import com.crtvu.dao.TermYearDAO;
import com.crtvu.dto.DeleteCJson;
import com.crtvu.dto.Manager.ErrorJson;
import com.crtvu.entity.TermYearEntity;
import com.crtvu.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by yangming on 2017/4/26/0026.
 */

@Service
public class TermServiceImpl implements TermService{
    @Autowired
    private TermYearDAO termYearDAO;
    private static final int pageNumber = 10;

    public List<TermYearEntity> getTermList (int page,String termProperty) {
        if(page<=0||page>getPageCount(termProperty)) {
            return null;
        }
        return termYearDAO.selectTermByLimit(termProperty,(page-1)*pageNumber,pageNumber);
    }

    public int getPageCount(String termProperty) {
        int count = termYearDAO.countAllTerm(termProperty);
        int page_count = count==0?1:count/pageNumber+(count%pageNumber>0?1:0);
        return page_count;
    }

    public TermYearEntity getTerm(int id){
        return termYearDAO.selectTerm(id);
    }

    public Result insertTerm(TermYearEntity term) {
        try {
           termYearDAO.insertTerm(term.getSchoolYear(),term.getTerm(),term.getBeginTime(),term.getOverTime());
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return  Result.SUCCESS;
    }

    public Result deleteTerm(int id){
        return termYearDAO.deleteTerm(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }

    public Result updateTerm(String schoolYear, int term, Date beginTime, Date overTime , int  id){
        int result;
        try {
            result = termYearDAO.updateTerm(schoolYear,term,beginTime,overTime,id);
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }

}
