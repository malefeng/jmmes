package com.jeecg.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MyLocalDialect extends MySQL5InnoDBDialect {

    public MyLocalDialect() {
        super();
        registerFunction("currval",new SQLFunctionTemplate(StandardBasicTypes.STRING,"currval(?1)"));
        registerFunction("nextval",new SQLFunctionTemplate(StandardBasicTypes.STRING,"nextval(?1)"));
    }
}