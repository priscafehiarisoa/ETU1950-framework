jar cf Framework/compiledJar/Framework.jar framework_2/target/classes/ETU1950
cp Framework/compiledJar/Framework.jar /Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib
jar cf test-framework2/target/test-framework.war test-framework2/target/test-framework2-1.0-SNAPSHOT/*
cp test-framework2/target/test-framework.war /Applications/apache-tomcat-10.0.27/webapps


