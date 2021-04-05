package xyz.eduplatform.cep.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.eduplatform.cep.mapper.InfoMapper;
import xyz.eduplatform.cep.pojo.Info;
import xyz.eduplatform.cep.pojo.InfoExample;
import xyz.eduplatform.cep.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    public InfoMapper infoMapper;

    @Override
    public List<Info> getInfoById(int id) {
            InfoExample infoExample = new InfoExample();
            infoExample.createCriteria().andIdEqualTo(id);
            List<Info> infos = infoMapper.selectByExample(infoExample);
            return infos;
    }

    @Override
    public List<Info> getAllInfo() {
        InfoExample infoExample = new InfoExample();
        return infoMapper.selectByExample(infoExample);
    }

    @Override
    public Integer setCourse(Info info) {
        return infoMapper.insert(info);
    }
}
