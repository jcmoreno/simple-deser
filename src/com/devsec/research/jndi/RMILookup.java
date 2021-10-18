package com.devsec.research.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class RMILookup {

    public static void main(String[] args) {
        String name = "rmi://localhost:2099/Exploit";

        try {
        	System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        	
            // Create a Properties object and set properties appropriately
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            props.put(Context.PROVIDER_URL, "rmi://127.0.0.1");

            // Create the initial context from the properties we just created
            Context ctx = new InitialContext(props);

            // Look up the object
            Object obj = ctx.lookup(name);
            System.out.println(obj);

        } catch (NamingException nnfe) {
            System.out.println("Encountered an exception");
            nnfe.printStackTrace();
        }
    }
}

