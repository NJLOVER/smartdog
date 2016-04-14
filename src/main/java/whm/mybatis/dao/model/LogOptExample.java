package whm.mybatis.dao.model;

import java.util.ArrayList;
import java.util.List;

public class LogOptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogOptExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOlogIdIsNull() {
            addCriterion("OLOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOlogIdIsNotNull() {
            addCriterion("OLOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOlogIdEqualTo(String value) {
            addCriterion("OLOG_ID =", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdNotEqualTo(String value) {
            addCriterion("OLOG_ID <>", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdGreaterThan(String value) {
            addCriterion("OLOG_ID >", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_ID >=", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdLessThan(String value) {
            addCriterion("OLOG_ID <", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdLessThanOrEqualTo(String value) {
            addCriterion("OLOG_ID <=", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdLike(String value) {
            addCriterion("OLOG_ID like", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdNotLike(String value) {
            addCriterion("OLOG_ID not like", value, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdIn(List<String> values) {
            addCriterion("OLOG_ID in", values, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdNotIn(List<String> values) {
            addCriterion("OLOG_ID not in", values, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdBetween(String value1, String value2) {
            addCriterion("OLOG_ID between", value1, value2, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogIdNotBetween(String value1, String value2) {
            addCriterion("OLOG_ID not between", value1, value2, "ologId");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeIsNull() {
            addCriterion("OLOG_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeIsNotNull() {
            addCriterion("OLOG_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeEqualTo(Object value) {
            addCriterion("OLOG_DATETIME =", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeNotEqualTo(Object value) {
            addCriterion("OLOG_DATETIME <>", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeGreaterThan(Object value) {
            addCriterion("OLOG_DATETIME >", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeGreaterThanOrEqualTo(Object value) {
            addCriterion("OLOG_DATETIME >=", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeLessThan(Object value) {
            addCriterion("OLOG_DATETIME <", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeLessThanOrEqualTo(Object value) {
            addCriterion("OLOG_DATETIME <=", value, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeIn(List<Object> values) {
            addCriterion("OLOG_DATETIME in", values, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeNotIn(List<Object> values) {
            addCriterion("OLOG_DATETIME not in", values, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeBetween(Object value1, Object value2) {
            addCriterion("OLOG_DATETIME between", value1, value2, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogDatetimeNotBetween(Object value1, Object value2) {
            addCriterion("OLOG_DATETIME not between", value1, value2, "ologDatetime");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeIsNull() {
            addCriterion("OLOG_OPTCODE is null");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeIsNotNull() {
            addCriterion("OLOG_OPTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeEqualTo(String value) {
            addCriterion("OLOG_OPTCODE =", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeNotEqualTo(String value) {
            addCriterion("OLOG_OPTCODE <>", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeGreaterThan(String value) {
            addCriterion("OLOG_OPTCODE >", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_OPTCODE >=", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeLessThan(String value) {
            addCriterion("OLOG_OPTCODE <", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeLessThanOrEqualTo(String value) {
            addCriterion("OLOG_OPTCODE <=", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeLike(String value) {
            addCriterion("OLOG_OPTCODE like", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeNotLike(String value) {
            addCriterion("OLOG_OPTCODE not like", value, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeIn(List<String> values) {
            addCriterion("OLOG_OPTCODE in", values, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeNotIn(List<String> values) {
            addCriterion("OLOG_OPTCODE not in", values, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeBetween(String value1, String value2) {
            addCriterion("OLOG_OPTCODE between", value1, value2, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogOptcodeNotBetween(String value1, String value2) {
            addCriterion("OLOG_OPTCODE not between", value1, value2, "ologOptcode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeIsNull() {
            addCriterion("OLOG_STOCODE is null");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeIsNotNull() {
            addCriterion("OLOG_STOCODE is not null");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeEqualTo(String value) {
            addCriterion("OLOG_STOCODE =", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeNotEqualTo(String value) {
            addCriterion("OLOG_STOCODE <>", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeGreaterThan(String value) {
            addCriterion("OLOG_STOCODE >", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_STOCODE >=", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeLessThan(String value) {
            addCriterion("OLOG_STOCODE <", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeLessThanOrEqualTo(String value) {
            addCriterion("OLOG_STOCODE <=", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeLike(String value) {
            addCriterion("OLOG_STOCODE like", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeNotLike(String value) {
            addCriterion("OLOG_STOCODE not like", value, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeIn(List<String> values) {
            addCriterion("OLOG_STOCODE in", values, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeNotIn(List<String> values) {
            addCriterion("OLOG_STOCODE not in", values, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeBetween(String value1, String value2) {
            addCriterion("OLOG_STOCODE between", value1, value2, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogStocodeNotBetween(String value1, String value2) {
            addCriterion("OLOG_STOCODE not between", value1, value2, "ologStocode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeIsNull() {
            addCriterion("OLOG_BSNCODE is null");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeIsNotNull() {
            addCriterion("OLOG_BSNCODE is not null");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeEqualTo(String value) {
            addCriterion("OLOG_BSNCODE =", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeNotEqualTo(String value) {
            addCriterion("OLOG_BSNCODE <>", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeGreaterThan(String value) {
            addCriterion("OLOG_BSNCODE >", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_BSNCODE >=", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeLessThan(String value) {
            addCriterion("OLOG_BSNCODE <", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeLessThanOrEqualTo(String value) {
            addCriterion("OLOG_BSNCODE <=", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeLike(String value) {
            addCriterion("OLOG_BSNCODE like", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeNotLike(String value) {
            addCriterion("OLOG_BSNCODE not like", value, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeIn(List<String> values) {
            addCriterion("OLOG_BSNCODE in", values, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeNotIn(List<String> values) {
            addCriterion("OLOG_BSNCODE not in", values, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeBetween(String value1, String value2) {
            addCriterion("OLOG_BSNCODE between", value1, value2, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogBsncodeNotBetween(String value1, String value2) {
            addCriterion("OLOG_BSNCODE not between", value1, value2, "ologBsncode");
            return (Criteria) this;
        }

        public Criteria andOlogReslultIsNull() {
            addCriterion("OLOG_RESLULT is null");
            return (Criteria) this;
        }

        public Criteria andOlogReslultIsNotNull() {
            addCriterion("OLOG_RESLULT is not null");
            return (Criteria) this;
        }

        public Criteria andOlogReslultEqualTo(String value) {
            addCriterion("OLOG_RESLULT =", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultNotEqualTo(String value) {
            addCriterion("OLOG_RESLULT <>", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultGreaterThan(String value) {
            addCriterion("OLOG_RESLULT >", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_RESLULT >=", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultLessThan(String value) {
            addCriterion("OLOG_RESLULT <", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultLessThanOrEqualTo(String value) {
            addCriterion("OLOG_RESLULT <=", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultLike(String value) {
            addCriterion("OLOG_RESLULT like", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultNotLike(String value) {
            addCriterion("OLOG_RESLULT not like", value, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultIn(List<String> values) {
            addCriterion("OLOG_RESLULT in", values, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultNotIn(List<String> values) {
            addCriterion("OLOG_RESLULT not in", values, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultBetween(String value1, String value2) {
            addCriterion("OLOG_RESLULT between", value1, value2, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogReslultNotBetween(String value1, String value2) {
            addCriterion("OLOG_RESLULT not between", value1, value2, "ologReslult");
            return (Criteria) this;
        }

        public Criteria andOlogInfoIsNull() {
            addCriterion("OLOG_INFO is null");
            return (Criteria) this;
        }

        public Criteria andOlogInfoIsNotNull() {
            addCriterion("OLOG_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andOlogInfoEqualTo(String value) {
            addCriterion("OLOG_INFO =", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoNotEqualTo(String value) {
            addCriterion("OLOG_INFO <>", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoGreaterThan(String value) {
            addCriterion("OLOG_INFO >", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_INFO >=", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoLessThan(String value) {
            addCriterion("OLOG_INFO <", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoLessThanOrEqualTo(String value) {
            addCriterion("OLOG_INFO <=", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoLike(String value) {
            addCriterion("OLOG_INFO like", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoNotLike(String value) {
            addCriterion("OLOG_INFO not like", value, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoIn(List<String> values) {
            addCriterion("OLOG_INFO in", values, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoNotIn(List<String> values) {
            addCriterion("OLOG_INFO not in", values, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoBetween(String value1, String value2) {
            addCriterion("OLOG_INFO between", value1, value2, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogInfoNotBetween(String value1, String value2) {
            addCriterion("OLOG_INFO not between", value1, value2, "ologInfo");
            return (Criteria) this;
        }

        public Criteria andOlogIpIsNull() {
            addCriterion("OLOG_IP is null");
            return (Criteria) this;
        }

        public Criteria andOlogIpIsNotNull() {
            addCriterion("OLOG_IP is not null");
            return (Criteria) this;
        }

        public Criteria andOlogIpEqualTo(String value) {
            addCriterion("OLOG_IP =", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpNotEqualTo(String value) {
            addCriterion("OLOG_IP <>", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpGreaterThan(String value) {
            addCriterion("OLOG_IP >", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_IP >=", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpLessThan(String value) {
            addCriterion("OLOG_IP <", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpLessThanOrEqualTo(String value) {
            addCriterion("OLOG_IP <=", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpLike(String value) {
            addCriterion("OLOG_IP like", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpNotLike(String value) {
            addCriterion("OLOG_IP not like", value, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpIn(List<String> values) {
            addCriterion("OLOG_IP in", values, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpNotIn(List<String> values) {
            addCriterion("OLOG_IP not in", values, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpBetween(String value1, String value2) {
            addCriterion("OLOG_IP between", value1, value2, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogIpNotBetween(String value1, String value2) {
            addCriterion("OLOG_IP not between", value1, value2, "ologIp");
            return (Criteria) this;
        }

        public Criteria andOlogMacIsNull() {
            addCriterion("OLOG_MAC is null");
            return (Criteria) this;
        }

        public Criteria andOlogMacIsNotNull() {
            addCriterion("OLOG_MAC is not null");
            return (Criteria) this;
        }

        public Criteria andOlogMacEqualTo(String value) {
            addCriterion("OLOG_MAC =", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacNotEqualTo(String value) {
            addCriterion("OLOG_MAC <>", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacGreaterThan(String value) {
            addCriterion("OLOG_MAC >", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacGreaterThanOrEqualTo(String value) {
            addCriterion("OLOG_MAC >=", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacLessThan(String value) {
            addCriterion("OLOG_MAC <", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacLessThanOrEqualTo(String value) {
            addCriterion("OLOG_MAC <=", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacLike(String value) {
            addCriterion("OLOG_MAC like", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacNotLike(String value) {
            addCriterion("OLOG_MAC not like", value, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacIn(List<String> values) {
            addCriterion("OLOG_MAC in", values, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacNotIn(List<String> values) {
            addCriterion("OLOG_MAC not in", values, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacBetween(String value1, String value2) {
            addCriterion("OLOG_MAC between", value1, value2, "ologMac");
            return (Criteria) this;
        }

        public Criteria andOlogMacNotBetween(String value1, String value2) {
            addCriterion("OLOG_MAC not between", value1, value2, "ologMac");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}