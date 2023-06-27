#compilation framework
classesPath='/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe'
classesPath_framework='/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework'

cd $classesPath_framework
javac  -classpath $CLASSPATH:$classesPath -d $classesPath   ./exeptions/*.java
javac  -classpath $CLASSPATH:$classesPath -d $classesPath    ./annnotation/*.java
javac  -classpath $CLASSPATH:$classesPath -d $classesPath   ./*.java
javac  -classpath $CLASSPATH:$classesPath -d $classesPath   ./servlet/*.java

cd $classesPath

jar -cf jars/framework.jar .
cd jars
pwd
ls
#mijery ny ao anatin'ny le jar voasokatra
jar -tf framework.jar

#copie jar dans lib
cp -f /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe/jars/framework.jar /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib
#2
export CLASSPATH=$CLASSPATH:.:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib/framework.jar

#compilation framework test
javac -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes -parameters -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/java/test/*.java
cd /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/
pwd
ls
#creation war
jar -cf ./test-framework2.war ./*
pwd
ls
cp ./test-framework2.war /Applications/apache-tomcat-10.0.27/webapps
pwd
startTomcat




#backup

#javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/annnotation/MethodAnnotation.java
#javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/ModelView.java
#javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/Mapping.java
#javac  -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe  /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/servlet/FrontServlet.java
#
#cd /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe
#
#jar -cf jars/framework.jar .
#cd jars
#pwd
#ls
##mijery ny ao anatin'ny le jar voasokatra
#jar -tf framework.jar
#
##copie jar dans lib
#cp -f /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/framework_2/src/main/classe/jars/framework.jar /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib
##2
#export CLASSPATH=$CLASSPATH:.:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/lib/framework.jar
#
##compilation framework test
#javac -classpath $CLASSPATH:/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes -d /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/WEB-INF/classes /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/java/test/*.java
#cd /Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/webapp/
#pwd
#ls
##creation war
#jar -cf ./test-framework2.war ./*
#pwd
#ls
#cp ./test-framework2.war /Applications/apache-tomcat-10.0.27/webapps
#pwd
#startTomcat
