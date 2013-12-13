Name: %(echo t_simba-portal${suffix})
Version: 1.0.2
Release: 11
Summary: simba-portal  package,canggu
URL: %{_svn_path}
Group: simba-portal/Common
License: Commercial
BuildArch: noarch
%define debug_package %{nil}
%define __os_install_post %{nil} 

#Requires: package_name = 1.0.0
#Requires: httpd >= 2.2.11
#Requires: jdk1 >= 6.0
#Requires: oracleclient >= 1024
#Requires: mysqlclient10 >= 3.23
#Requires: tomcat >= 6.0

%description
%{_svn_path}
%{_svn_revision}
simba-portal  package,canggu


%install

# create dirs
mkdir -p .%{_prefix}/project/simba-portal-webapp
# copy files
cp  -rf $OLDPWD/simba-portal-webapp/target/simba-portal-webapp .%{_prefix}/project



%files
%defattr(755,ads,users)
%{_prefix}/project/simba-portal-webapp







