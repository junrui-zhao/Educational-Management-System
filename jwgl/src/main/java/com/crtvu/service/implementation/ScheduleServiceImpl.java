package com.crtvu.service.implementation;

import com.crtvu.dao.ClassroomDAO;
import com.crtvu.dao.OpenDAO;
import com.crtvu.dao.ScheduleDAO;
import com.crtvu.entity.ClassroomEntity;
import com.crtvu.entity.OpenEntity;
import com.crtvu.entity.ScheduleEntity;
import com.crtvu.entity.TeacherScheduleEntity;
import com.crtvu.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danagi on 2017/4/10.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private ClassroomDAO classroomDAO;

    public ScheduleResult addSchedule(OpenEntity openEntity, int teacherId, String classroom, String className, int day, int startWeek, int endWeek, int startTime, int endTime) {
        //todo:这里有一个endWeek>maxWeek的功能要写
        if(startWeek<=0||startWeek>endWeek||startTime<=0||endTime>13||startTime>endTime) {
            return new ScheduleResult(ResultType.TIME_FAIL,null);
        }
        List<ScheduleEntity> scheduleEntities = scheduleDAO.selectClashSchedule(teacherId, classroom, openEntity.getSchoolYear(), openEntity.getTerm(), day, startWeek, endWeek, startTime, endTime, className, openEntity.getOpenId());
        if(scheduleEntities.size()!=0)return new ScheduleResult(ResultType.CLASH_FAIL,scheduleEntities);
        try {
            scheduleDAO.insertSchedule(openEntity.getOpenId(), teacherId, classroom, className, day, startWeek, endWeek, startTime, endTime);
        }
        catch (Exception e) {
            return new ScheduleResult(ResultType.KEY_FAIL,null);
        }
        return new ScheduleResult(ResultType.SUCCESS,null);
    }

    public ResultType removeSchedule(int id) {
        return scheduleDAO.deleteSchedule(id)>0?ResultType.SUCCESS:ResultType.ID_FAIL;
    }

    public ScheduleResult modifySchedule(ScheduleEntity scheduleEntity) {
        int startWeek = scheduleEntity.getStartWeek();
        int endWeek = scheduleEntity.getEndWeek();
        int startTime = scheduleEntity.getStartTime();
        int endTime = scheduleEntity.getEndTime();
        //todo:这里有一个endWeek>maxWeek的功能要写
        if(startWeek<=0||startWeek>endWeek||startTime<=0||endTime>13||startTime>endTime) {
            return new ScheduleResult(ResultType.TIME_FAIL,null);
        }
        List<ScheduleEntity> scheduleEntities = scheduleDAO.selectClashSchedule(scheduleEntity.getTeacherId(), scheduleEntity.getClassroom(), scheduleEntity.getSchoolYear(), scheduleEntity.getTerm(),
                scheduleEntity.getDay(), scheduleEntity.getStartWeek(), scheduleEntity.getEndWeek(), scheduleEntity.getStartTime(), scheduleEntity.getEndTime(), scheduleEntity.getClassName(), scheduleEntity.getOpenId());
        final int id = scheduleEntity.getId();
        scheduleEntities.removeIf(schedule->schedule.getId()==id);
        if(scheduleEntities.size()!=0)return new ScheduleResult(ResultType.CLASH_FAIL,scheduleEntities);
        int result;
        try {
            result = scheduleDAO.updateSchedule(scheduleEntity.getId(), scheduleEntity.getOpenId(), scheduleEntity.getTeacherId(), scheduleEntity.getClassroom(), scheduleEntity.getClassName(),
                    scheduleEntity.getDay(), scheduleEntity.getStartWeek(), scheduleEntity.getEndWeek(), scheduleEntity.getStartTime(), scheduleEntity.getEndTime());
        }
        catch (Exception e){
            return new ScheduleResult(ResultType.KEY_FAIL,null);
        }
        return new ScheduleResult(result>0?ResultType.SUCCESS:ResultType.ID_FAIL,null);
    }

    public List<ScheduleEntity> queryCourseSchedule(int openId) {
        return scheduleDAO.selectScheduleByOpenId(openId);
    }

    public List<TeacherScheduleEntity> queryTeacherSchedule(int teacherId, String schoolYear, int term) {
        return scheduleDAO.selectScheduleByTeacherId(teacherId, schoolYear, term);
    }

    public List<ScheduleEntity> queryStudentSchedule(int studentId, String schoolYear, int term) {
        return scheduleDAO.selectScheduleByStudentId(studentId, schoolYear, term);
    }

    public List<ScheduleEntity> queryMajorSchedule(String major, String schoolYear, int term) {
        return scheduleDAO.selectScheduleByMajor(major, schoolYear, term);
    }

    public ClassroomResult queryFreeClassroom(String schoolYear, int term, int day, int startWeek, int endWeek, int startTime, int endTime) {
        //todo:这里有一个endWeek>maxWeek的功能要写
        if(startWeek<=0||startWeek>endWeek||startTime<=0||endTime>13||startTime>endTime)return new ClassroomResult(ResultType.TIME_FAIL,null);
        return new ClassroomResult(ResultType.SUCCESS,classroomDAO.selectFreeClassroom(schoolYear, term, day, startWeek, endWeek, startTime, endTime));
    }

    @Override
    public ScheduleEntity queryScheduleById(int id) {
        return scheduleDAO.selectScheduleByArrangementId(id);
    }
}
