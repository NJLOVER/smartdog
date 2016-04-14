package whm.core.base.quartz.delegate;

import org.quartz.TriggerKey;
import org.quartz.impl.jdbcjobstore.oracle.OracleDelegate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by thinkpad on 2015/11/10.
 */
public class MyDelegate extends OracleDelegate{

    //为什么要有自定义委派类,委派类本来的作用是什么? 当集群部署时,某些任务需要只在特定机器上执行的时候,可以使用这种方式.
    //基本样例:项目中有两个redis集群,两者之间不进行通信,a任务需要获取集群a的数据进行后续操作.b任务需要获取集群b的任务进行操作.
    //基本实现方案: 重写各自的获取trigger方法,增加匹配条件,比如jobgroup,实现只在特定机器上才能查询到特殊的任务.

    static final String GROUP_PROPERTIES_NAME = "group";

    static final String UNIQUE_GROUP_NAME_KEY = "quartz.unique.group.name";

    static final String NORMAL_GROUP_NAME_KEY = "quartz.normal.group.name";

    static final String DEFAULT_GROUP_NAMES_KEY = "quartz.default.group.name";

    static final String regex = "\\b";
    static final String placeholder = "@groupName@";

    static String uniqueGroupName;//特殊分组,只在本机执行的任务分组标示

    static String normalGroupName;//标准分组,整个集群都能执行的分组标示集合

    static{
        ResourceBundle rb = ResourceBundle.getBundle(GROUP_PROPERTIES_NAME);
        uniqueGroupName = rb.getString(UNIQUE_GROUP_NAME_KEY);
        normalGroupName = rb.getString(NORMAL_GROUP_NAME_KEY);
        normalGroupName += "," + rb.getString(DEFAULT_GROUP_NAMES_KEY);
        normalGroupName = normalGroupName.replaceAll(regex, "'");
    }

    String SELECT_NEXT_TRIGGER_TO_ACQUIRE_1 = "SELECT "
            + COL_TRIGGER_NAME + ", " + COL_TRIGGER_GROUP + ", "
            + COL_NEXT_FIRE_TIME + ", " + COL_PRIORITY + " FROM "
            + TABLE_PREFIX_SUBST + TABLE_TRIGGERS + " WHERE "
            + COL_SCHEDULER_NAME + " = " + SCHED_NAME_SUBST
            + " AND " + COL_TRIGGER_STATE + " = ? AND " + COL_NEXT_FIRE_TIME + " <= ? "
            + " AND (" + COL_TRIGGER_GROUP + " = ? OR " + COL_TRIGGER_GROUP + " IN (  ";

    String SELECT_NEXT_TRIGGER_TO_ACQUIRE_2 = " ) ) "
            + " AND (" + COL_MISFIRE_INSTRUCTION + " = -1 OR (" +COL_MISFIRE_INSTRUCTION+ " != -1 AND "+ COL_NEXT_FIRE_TIME + " >= ?)) "
            + " ORDER BY "+ COL_NEXT_FIRE_TIME + " ASC, " + COL_PRIORITY + " DESC";

    @Override
    public List<TriggerKey> selectTriggerToAcquire(Connection conn, long noLaterThan, long noEarlierThan, int maxCount) throws SQLException{
        // System.out.println( uniqueGroupName + " : " + normalGroupName );
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TriggerKey> nextTriggers = new LinkedList<TriggerKey>();
        try {
            String sql = rtp(SELECT_NEXT_TRIGGER_TO_ACQUIRE_1 + placeholder + SELECT_NEXT_TRIGGER_TO_ACQUIRE_2);
            sql = sql.replaceAll(placeholder, normalGroupName);
            ps = conn.prepareStatement(sql);

            // Set max rows to retrieve
            if (maxCount < 1)
                maxCount = 1; // we want at least one trigger back.
            ps.setMaxRows(maxCount);

            // Try to give jdbc driver a hint to hopefully not pull over more than the few rows we actually need.
            // Note: in some jdbc drivers, such as MySQL, you must set maxRows before fetchSize, or you get exception!
            ps.setFetchSize(maxCount);

            ps.setString(1, STATE_WAITING);
            ps.setBigDecimal(2, new BigDecimal(String.valueOf(noLaterThan)));
            ps.setString(3, uniqueGroupName);
            ps.setBigDecimal(4, new BigDecimal(String.valueOf(noEarlierThan)));
            rs = ps.executeQuery();

            while (rs.next() && nextTriggers.size() <= maxCount) {
                nextTriggers.add(TriggerKey.triggerKey(
                        rs.getString(COL_TRIGGER_NAME),
                        rs.getString(COL_TRIGGER_GROUP)));
            }
            // System.out.println( "----------------------------" + nextTriggers );
            return nextTriggers;
        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }

		/*return super.selectTriggerToAcquire(conn, noLaterThan, noEarlierThan, maxCount);*/
    }

}
