package com.jeecg.constant;

public enum ERPApiCodeEnum {
    SAL("SAL_OUTSTOCK","销售出库单"),
    PUR("PUR_ReceiveBill","收料通知单"),
    PRO("PRD_MO","生产领料单");
    private String code;
    private String name;

    ERPApiCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
