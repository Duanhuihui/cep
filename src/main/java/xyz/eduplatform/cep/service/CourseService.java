package xyz.eduplatform.cep.service;

import xyz.eduplatform.cep.pojo.Info;

import java.util.List;


public interface CourseService {

    List<Info> getInfoById(int id);

    List<Info> getAllInfo();

    Integer setCourse(Info info);
}
