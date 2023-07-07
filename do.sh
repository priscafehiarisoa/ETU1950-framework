chemin_webapps="/Applications/apache-tomcat-10.0.27/webapps"
chemin_bin="/Applications/apache-tomcat-10.0.27/bin"
framework_dir="/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework"
package_name="objet"
webxml_path="Test-framework/WEB-INF/web.xml"

echo "Création du répertoire temporaire"
mkdir temp
cd temp
mkdir WEB-INF
cd WEB-INF
mkdir classes
cd classes 
mkdir $package_name
cd ..
mkdir lib
cd ../..    

echo "Copie des fichiers jsp"
cp Test-framework/*.jsp temp/
echo "Copie des fichiers de configuraiton"
cp Test-framework/WEB-INF/web.xml temp/WEB-INF/
echo "Copie de la librairie"
cp Test-framework/WEB-INF/lib/ETU1950Files.jar temp/WEB-INF/lib/
echo "Copie des classes"

cp Test-framework/WEB-INF/classes/$package_name/*.class temp/WEB-INF/classes/$package_name/
#rm test/WEB-INF/classes/*.java

cd temp
jar -cvf ETU1950Framework.war .
echo "Suppression du répertoire temporaire"
mv ETU1950Framework.war $chemin_webapps
cd ..
rm -r temp
cd $chemin_bin



cd $framework_dir