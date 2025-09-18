package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Mapper}.${entity}Mapper;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class ${entity}Service {

        @Resource
        private ${entity}Mapper mapper;

        public void insert(${entity} entity) {
        mapper.insert(entity);
        }

        public ${entity} getById(Long id) {
        ${entity} entity = mapper.getById(id);
        if (entity == null) {
        throw new RuntimeException("${entity}不存在: " + id);
        }
        return entity;
        }

        public ${entity} extractById(Long id) {
        return mapper.extractById(id);
        }

        public void update(${entity} entity) {
        mapper.update(entity);
        }

        public void delete(Long id) {
        int now = (int) (System.currentTimeMillis() / 1000);
        mapper.delete(id, now);
        }

}
