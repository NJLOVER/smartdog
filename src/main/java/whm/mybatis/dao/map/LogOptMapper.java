package whm.mybatis.dao.map;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import whm.mybatis.dao.model.LogOpt;
import whm.mybatis.dao.model.LogOptExample;


public interface LogOptMapper {
    @SelectProvider(type=LogOptSqlProvider.class, method="countByExample")
    int countByExample(LogOptExample example);

    @DeleteProvider(type=LogOptSqlProvider.class, method="deleteByExample")
    int deleteByExample(LogOptExample example);

    @Delete({
        "delete from OFST_TL_LOG_OPT",
        "where OLOG_ID = #{ologId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String ologId);

    @Insert({
        "insert into OFST_TL_LOG_OPT (OLOG_ID, OLOG_DATETIME, ",
        "OLOG_OPTCODE, OLOG_STOCODE, ",
        "OLOG_BSNCODE, OLOG_RESLULT, ",
        "OLOG_INFO, OLOG_IP, ",
        "OLOG_MAC)",    
        "values (#{ologId,jdbcType=VARCHAR}, cast(sysdate as timestamp), ",
        "#{ologOptcode,jdbcType=VARCHAR}, #{ologStocode,jdbcType=VARCHAR}, ",
        "#{ologBsncode,jdbcType=CHAR}, #{ologReslult,jdbcType=CHAR}, ",
        "#{ologInfo,jdbcType=VARCHAR}, #{ologIp,jdbcType=VARCHAR}, ",
        "#{ologMac,jdbcType=VARCHAR})"
    })
    int insert(LogOpt record);

    @InsertProvider(type=LogOptSqlProvider.class, method="insertSelective")
    int insertSelective(LogOpt record);

    @SelectProvider(type=LogOptSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="OLOG_ID", property="ologId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="OLOG_DATETIME", property="ologDatetime", jdbcType=JdbcType.OTHER),
        @Result(column="OLOG_OPTCODE", property="ologOptcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_STOCODE", property="ologStocode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_BSNCODE", property="ologBsncode", jdbcType=JdbcType.CHAR),
        @Result(column="OLOG_RESLULT", property="ologReslult", jdbcType=JdbcType.CHAR),
        @Result(column="OLOG_INFO", property="ologInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_IP", property="ologIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_MAC", property="ologMac", jdbcType=JdbcType.VARCHAR)
    })
    List<LogOpt> selectByExample(LogOptExample example);

    @Select({
        "select",
        "OLOG_ID, OLOG_DATETIME, OLOG_OPTCODE, OLOG_STOCODE, OLOG_BSNCODE, OLOG_RESLULT, ",
        "OLOG_INFO, OLOG_IP, OLOG_MAC",
        "from OFST_TL_LOG_OPT",
        "where OLOG_ID = #{ologId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="OLOG_ID", property="ologId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="OLOG_DATETIME", property="ologDatetime", jdbcType=JdbcType.OTHER),
        @Result(column="OLOG_OPTCODE", property="ologOptcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_STOCODE", property="ologStocode", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_BSNCODE", property="ologBsncode", jdbcType=JdbcType.CHAR),
        @Result(column="OLOG_RESLULT", property="ologReslult", jdbcType=JdbcType.CHAR),
        @Result(column="OLOG_INFO", property="ologInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_IP", property="ologIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLOG_MAC", property="ologMac", jdbcType=JdbcType.VARCHAR)
    })
    LogOpt selectByPrimaryKey(String ologId);

    @UpdateProvider(type=LogOptSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LogOpt record, @Param("example") LogOptExample example);

    @UpdateProvider(type=LogOptSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LogOpt record, @Param("example") LogOptExample example);

    @UpdateProvider(type=LogOptSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LogOpt record);

    @Update({
        "update OFST_TL_LOG_OPT",
        "set OLOG_DATETIME = #{ologDatetime,jdbcType=OTHER},",
          "OLOG_OPTCODE = #{ologOptcode,jdbcType=VARCHAR},",
          "OLOG_STOCODE = #{ologStocode,jdbcType=VARCHAR},",
          "OLOG_BSNCODE = #{ologBsncode,jdbcType=CHAR},",
          "OLOG_RESLULT = #{ologReslult,jdbcType=CHAR},",
          "OLOG_INFO = #{ologInfo,jdbcType=VARCHAR},",
          "OLOG_IP = #{ologIp,jdbcType=VARCHAR},",
          "OLOG_MAC = #{ologMac,jdbcType=VARCHAR}",
        "where OLOG_ID = #{ologId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(LogOpt record);

/*    @SelectProvider(type=LogOptSqlProvider.class, method="selectByCriteria")
	List<Map<String,Object>> selectByCriteria(OperatorView opView);*/

    @SelectProvider(type=LogOptSqlProvider.class, method="getOperatorLogList")
	List<Map<String, Object>> getOperatorLogList(Map<String, Object> param);
	
    @SelectProvider(type=LogOptSqlProvider.class, method="countByLogWithOperator")
	int countByLogWithOperator(Map<String, Object> param);
    
    @Select({
    	"SELECT  OPERATOR_LOG_SEQUENCE.NEXTVAL as seq from dual"
    })
    @Results({
    	@Result(column="seq", property="seq", jdbcType=JdbcType.INTEGER, id=true)
    })
	int receiveSeq();
    


}