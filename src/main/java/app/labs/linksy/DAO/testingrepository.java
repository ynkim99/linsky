package app.labs.linksy.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface testingrepository {

    List<Map<String, String>> getUserImages(@Param("userIds") List<String> userIds);
}
