export temppath=$1
cd $temppath
/home/ads/tools/apache-maven-2.1.0/bin/mvn  clean package -Preal  -Dmaven.test.skip=true 
if [ `cat /etc/redhat-release|cut -d " " -f 7|cut -d "." -f 1` = 4 ]
then
	sed -i  "s/^Release:.*$/Release: "$4"/g" rpm/$2.spec
else
	sed -i  "s/^Release:.*$/Release: "$4"/g" rpm/$2.spec
        
fi
sed -i  "s/^Version:.*$/Version: "$3"/" rpm/$2.spec

cd simba-portal-biz
mvn install -Preal  -Dmaven.test.skip=true
cd ..



rpm_create -r $3 -v $4 -p /home/a/  rpm/$2.spec
for os in 4 5 
do
	yum-upload $2""-$3*.rpm --osver $os --arch noarch --group yum --batch
done