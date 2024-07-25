package com.data.lake.cosmosdb.config;

public class TopicsConfig {


    public static final String CONSUMER_GROUP = "consumer-group-send-cosmodb-03";

    public static final String CATEGORY = "ZSUBT_CAT_TDOCBP";
    public static final String COMPANY = "ZSUB_COMPANY_DT";
    public static final String FINANCES = "ZSUB_FINANCES_DT";
    public static final String LEGALREP = "ZSUB_LEGALREP_DT";
    public static final String ORDERS = "ZSUB_ORDERS_DT";
    public static final String PAC = "ZSUB_PAC_DT";
    public static final String TITULATE = "ZSUB_TITULATE_DT";

    public static String getDLTName(String topicName) {
        return topicName + "_DeadLetter";
    }




    private TopicsConfig(){}

}
