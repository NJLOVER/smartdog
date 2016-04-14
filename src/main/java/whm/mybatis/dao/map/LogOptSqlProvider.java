package whm.mybatis.dao.map;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import whm.mybatis.dao.model.LogOpt;
import whm.mybatis.dao.model.LogOptExample;
import whm.mybatis.dao.model.LogOptExample.Criteria;
import whm.mybatis.dao.model.LogOptExample.Criterion;

public class LogOptSqlProvider {

    public String countByExample(LogOptExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("OFST_TL_LOG_OPT");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(LogOptExample example) {
        BEGIN();
        DELETE_FROM("OFST_TL_LOG_OPT");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(LogOpt record) {
        BEGIN();
        INSERT_INTO("OFST_TL_LOG_OPT");
        
        if (record.getOlogId() != null) {
            VALUES("OLOG_ID", "#{ologId,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogDatetime() != null) {
            VALUES("OLOG_DATETIME", "#{ologDatetime,jdbcType=OTHER}");
        }
        
        if (record.getOlogOptcode() != null) {
            VALUES("OLOG_OPTCODE", "#{ologOptcode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogStocode() != null) {
            VALUES("OLOG_STOCODE", "#{ologStocode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogBsncode() != null) {
            VALUES("OLOG_BSNCODE", "#{ologBsncode,jdbcType=CHAR}");
        }
        
        if (record.getOlogReslult() != null) {
            VALUES("OLOG_RESLULT", "#{ologReslult,jdbcType=CHAR}");
        }
        
        if (record.getOlogInfo() != null) {
            VALUES("OLOG_INFO", "#{ologInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogIp() != null) {
            VALUES("OLOG_IP", "#{ologIp,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogMac() != null) {
            VALUES("OLOG_MAC", "#{ologMac,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(LogOptExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("OLOG_ID");
        } else {
            SELECT("OLOG_ID");
        }
        SELECT("OLOG_DATETIME");
        SELECT("OLOG_OPTCODE");
        SELECT("OLOG_STOCODE");
        SELECT("OLOG_BSNCODE");
        SELECT("OLOG_RESLULT");
        SELECT("OLOG_INFO");
        SELECT("OLOG_IP");
        SELECT("OLOG_MAC");
        FROM("OFST_TL_LOG_OPT");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        LogOpt record = (LogOpt) parameter.get("record");
        LogOptExample example = (LogOptExample) parameter.get("example");
        
        BEGIN();
        UPDATE("OFST_TL_LOG_OPT");
        
        if (record.getOlogId() != null) {
            SET("OLOG_ID = #{record.ologId,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogDatetime() != null) {
            SET("OLOG_DATETIME = #{record.ologDatetime,jdbcType=OTHER}");
        }
        
        if (record.getOlogOptcode() != null) {
            SET("OLOG_OPTCODE = #{record.ologOptcode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogStocode() != null) {
            SET("OLOG_STOCODE = #{record.ologStocode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogBsncode() != null) {
            SET("OLOG_BSNCODE = #{record.ologBsncode,jdbcType=CHAR}");
        }
        
        if (record.getOlogReslult() != null) {
            SET("OLOG_RESLULT = #{record.ologReslult,jdbcType=CHAR}");
        }
        
        if (record.getOlogInfo() != null) {
            SET("OLOG_INFO = #{record.ologInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogIp() != null) {
            SET("OLOG_IP = #{record.ologIp,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogMac() != null) {
            SET("OLOG_MAC = #{record.ologMac,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OFST_TL_LOG_OPT");
        
        SET("OLOG_ID = #{record.ologId,jdbcType=VARCHAR}");
        SET("OLOG_DATETIME = #{record.ologDatetime,jdbcType=OTHER}");
        SET("OLOG_OPTCODE = #{record.ologOptcode,jdbcType=VARCHAR}");
        SET("OLOG_STOCODE = #{record.ologStocode,jdbcType=VARCHAR}");
        SET("OLOG_BSNCODE = #{record.ologBsncode,jdbcType=CHAR}");
        SET("OLOG_RESLULT = #{record.ologReslult,jdbcType=CHAR}");
        SET("OLOG_INFO = #{record.ologInfo,jdbcType=VARCHAR}");
        SET("OLOG_IP = #{record.ologIp,jdbcType=VARCHAR}");
        SET("OLOG_MAC = #{record.ologMac,jdbcType=VARCHAR}");
        
        LogOptExample example = (LogOptExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(LogOpt record) {
        BEGIN();
        UPDATE("OFST_TL_LOG_OPT");
        
        if (record.getOlogDatetime() != null) {
            SET("OLOG_DATETIME = #{ologDatetime,jdbcType=OTHER}");
        }
        
        if (record.getOlogOptcode() != null) {
            SET("OLOG_OPTCODE = #{ologOptcode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogStocode() != null) {
            SET("OLOG_STOCODE = #{ologStocode,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogBsncode() != null) {
            SET("OLOG_BSNCODE = #{ologBsncode,jdbcType=CHAR}");
        }
        
        if (record.getOlogReslult() != null) {
            SET("OLOG_RESLULT = #{ologReslult,jdbcType=CHAR}");
        }
        
        if (record.getOlogInfo() != null) {
            SET("OLOG_INFO = #{ologInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogIp() != null) {
            SET("OLOG_IP = #{ologIp,jdbcType=VARCHAR}");
        }
        
        if (record.getOlogMac() != null) {
            SET("OLOG_MAC = #{ologMac,jdbcType=VARCHAR}");
        }
        
        WHERE("OLOG_ID = #{ologId,jdbcType=VARCHAR}");
        
        return SQL();
    }

    protected void applyWhere(LogOptExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<LogOptExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            LogOptExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
    
	public String  getOperatorLogList(Map<String ,Object> param){	
		String sql = "select * from (select rownum rn,A.opname,to_char(A.datetime,'yyyy-mm-dd hh24:mi:ss') datetime,A.text,A.contentinfo from (select o.olog_optcode opname,o.olog_datetime datetime,o.olog_info contentinfo,m.store_text text from OFST_TL_LOG_OPT o,OFST_TR_MENU_STORE m where o.olog_bsncode=trim(m.store_code) ";
		if (param != null) {
			if(param.get("userId")!=null&&StringUtils.isNotEmpty((String)param.get("userId"))){
				sql = sql + " and o.olog_optcode like '"+(String)param.get("userId")+"%' ";
			}
			if (param.get("startTime") != null&&StringUtils.isNotEmpty((String)param.get("startTime"))) {
				sql += " and to_char(o.olog_datetime ,'yyyy-MM-dd') >='"+(String)param.get("startTime")+"'";
			}
			if (param.get("endTime") != null&&StringUtils.isNotEmpty((String)param.get("endTime"))) {
				sql += " and  to_char(o.olog_datetime ,'yyyy-MM-dd hh24:mi:ss')<= '"+(String)param.get("endTime")+" 23:59:59'";
			}
			if(param.get("opName")!=null&&StringUtils.isNotEmpty((String)param.get("opName"))){
				sql += " and o.olog_optcode = '"+(String)param.get("opName")+"'";		
			}
		}
		sql +="  order by o.olog_datetime desc) A where rownum<=#{endRow}) B where B.rn>=#{startRow}";
		return sql;
		/*String innerSql = "select opl.*,op.operator_name,st.store_text as text  from OFST_TL_LOG_OPT  opl left join OFST_TP_OP_INFO op  on op.pk_ofst_tp_operator = opl.olog_optcode  left join OFST_TR_MENU_STORE st on trim(st.store_code) = opl.olog_bsncode where 1=1 ";
		if(param!=null){
			if (param.get("startTime") != null) {
				innerSql += " and to_char(opl.olog_datetime ,'yyyy-MM-dd') >=#{startTime} ";
			}
			if (param.get("endTime") != null) {
				innerSql += "and  to_char(opl.olog_datetime ,'yyyy-MM-dd')<= #{endTime}";
			}	
			if(param.get("opName")!=null){
				innerSql += "and op.operator_name like '%'||#{opName}||'%'";		
			}
			StoreUserInfo user = null;
			if (param.get("user") != null) {
				user = (StoreUserInfo) param.get("user");				
				List<StoreUserMapping> sumList = user.getStoreUserMappingList();
				innerSql += "and  opl.olog_stocode in (";		
				String stCodes ="";
				for (StoreUserMapping sum : sumList) {
					stCodes += sum.getStoCode() + ", ";
				}
				if (user != null && StoreUserInfo.isHeadUser(Integer.valueOf(user.getType()))) {
					stCodes += user.getStoreUserMappingList().get(0).getBizCode();
					innerSql += stCodes;
					innerSql += " )";
				} else {
					stCodes += user.getStoreUserMappingList().get(0).getBizCode();
					String scode = stCodes.substring(0,stCodes.lastIndexOf(","));
					innerSql += scode;
					innerSql += " )";
				}		
				innerSql +=" order by olog_datetime desc ";
			}                
		}		
		String sql1 = String.format("select t.*,rownum rn ,t.olog_reslult as OPRESULT,t.operator_name as OPNAME, to_char(t.olog_datetime ,'yyyy-mm-dd hh24:mi:ss') as DATETIME,t.olog_info as CONTENTINFO   from ( %s ) t ", innerSql);//"";		
		String sql2 = "select tt.* from ( "+ sql1 + "  ) tt  where tt.rn  >=#{startRow} and tt.rn <= #{endRow} ";
		return sql2;*/
	}
	
	public String countByLogWithOperator(Map<String,Object> param){
		
		String sql = "select count(*) from OFST_TL_LOG_OPT o,OFST_TR_MENU_STORE m where 1=1 and o.olog_bsncode = trim(m.store_code) ";
		if (param != null) {
			if(param.get("userId")!=null&&StringUtils.isNotEmpty((String)param.get("userId"))){
				sql = sql + " and o.olog_optcode like '"+(String)param.get("userId")+"%' ";
			}
			if (param.get("startTime") != null&&StringUtils.isNotEmpty((String)param.get("startTime"))) {
				sql += " and to_char(o.olog_datetime ,'yyyy-MM-dd') >='"+(String)param.get("startTime")+"' ";
			}
			if (param.get("endTime") != null&&StringUtils.isNotEmpty((String)param.get("endTime"))) {
				sql += " and  to_char(o.olog_datetime ,'yyyy-MM-dd hh24:mi:ss')<= '"+(String)param.get("endTime")+" 23:59:59'";
			}
			if(param.get("opName")!=null&&StringUtils.isNotEmpty((String)param.get("opName"))){
				sql += " and o.olog_optcode = '"+(String)param.get("opName")+"' ";		
			}
		}
		return sql;
		/*String innerSql = "  select rownum rn,opl.*,op.* from OFST_TL_LOG_OPT  opl left join OFST_TP_OP_INFO op on op.pk_ofst_tp_operator = opl.olog_optcode    where  1 = 1 ";
		if (param != null) {
			if (param.get("startTime") != null&&StringUtils.isNotEmpty((String)param.get("startTime"))) {
				innerSql += " and to_char(opl.olog_datetime ,'yyyy-MM-dd') >=#{startTime} ";
			}
			if (param.get("endTime") != null&&StringUtils.isNotEmpty((String)param.get("endTime"))) {
				innerSql += "and  to_char(opl.olog_datetime ,'yyyy-MM-dd')<= #{endTime}";
			}
			if(param.get("opName")!=null&&StringUtils.isNotEmpty((String)param.get("opName"))){
				innerSql += "and op.operator_name like '%'||#{opName}||'%'";		
			}
			StoreUserInfo user = null;
			if (param.get("user") != null) {
				user = (StoreUserInfo) param.get("user");				
				List<StoreUserMapping> sumList = user.getStoreUserMappingList();
				innerSql += "and  opl.olog_stocode in (";		
				String stCodes ="";
				for (StoreUserMapping sum : sumList) {
					stCodes += sum.getStoCode() + ", ";
				}
				if (user != null && StoreUserInfo.isHeadUser(Integer.valueOf(user.getType()))) {
					stCodes += user.getStoreUserMappingList().get(0).getBizCode();
					innerSql += stCodes;
					innerSql += " )";
				} else {
					stCodes += user.getStoreUserMappingList().get(0).getBizCode();
					String scode = stCodes.substring(0,stCodes.lastIndexOf(","));
					innerSql += scode;
					innerSql += " )";
				}		
				innerSql +=" order by olog_datetime desc ";
			}                
		}
		//String sql = String.format("select count(1) as ct from (select t.* from ( %s ) t) ",innerSql);
*/		
	}
	
	
	/*public String selectByCriteria(OperatorView opView){
		String sql = "	select op.operator_name as opName,to_char(t.olog_datetime ,'yyyy-MM-dd HH:mm:ss') as dateTime,t.olog_info "
	     +"	as  contentInfo,t.olog_reslult as opResult  from ofst_tl_log_opt t left join ofst_tp_op_info op  on op.pk_ofst_tp_operator = t.olog_optcode  where  1=1 ";
		if (opView != null) {			
			if (StringUtils.isNotEmpty(opView.getOpertorName())) {
				sql += " and op.operator_name = '"+opView.getOpertorName()+"'";
			}
			
			if (StringUtils.isNotBlank(opView.getStartTime())) {
				sql += "and t.olog_datetime >=to_date('"+opView.getStartTime()+"','yyyymmdd')";
			}

			if (StringUtils.isNotBlank(opView.getEndTime())) {
				sql += "and t.olog_datetime <=to_date('"+opView.getEndTime()+"','yyyymmdd')";
			}
		}
		return sql;
	}*/

    
}