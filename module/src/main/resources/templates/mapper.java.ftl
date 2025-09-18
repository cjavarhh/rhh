package ${package.Mapper};

import ${package.Entity}.${entity};
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ${entity}Mapper {
        @Select("SELECT * FROM ${table.name} WHERE id = <#noparse>#{id}</#noparse> AND is_deleted = 0")
        ${entity} getById(Long id);

        @Select("SELECT * FROM ${table.name} WHERE id = <#noparse>#{id}</#noparse>")
        ${entity} extractById(Long id);

        void update(${entity} entity);

        void insert(${entity} entity);

        @Update("UPDATE ${table.name} SET is_deleted = 1, update_time = <#noparse>#{now}</#noparse> WHERE id = <#noparse>#{id}</#noparse>")
        void delete(@Param("id") Long id, @Param("now") int now);

  @Select("select * from ${table.name} where is_deleted=0)
  List<${entity}> getAllCategory()


}
