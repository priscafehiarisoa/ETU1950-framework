javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/annnotation/MethodAnnotation.java
javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/Mapping.java
javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/servlet/FrontServlet.java

cd /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe
ls
jar -cf jars/framework.jar .
cd jars
pwd
ls
#mijery ny ao anatin'ny le jar voasokatra
jar -tf framework.jar

cp -f /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/classe/jars/framework.jar /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib

export CLASSPATH=$CLASSPATH:.:/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib/framework.jar

javac -classpath $CLASSPATH:/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes -d /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/java/test/test.java


cd /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp
#cp -R /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes ./WEB-INF

pwd
ls
jar -cf ./test-framework.war ./WEB-INF
pwd
ls

cp ./test-framework.war /Applications/apache-tomcat-10.0.27/webapps
pwd
