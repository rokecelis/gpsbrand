gpsbrand &reg;
## Gpdgrand. Software para distribución

## Introducción

### Perfiles

Cada objetivo del proyecto se construye a partir de los perfiles definidos en el pom.xml. Evite modificar este descriptor [pom.xml] a no ser que se agregue una dependencia al sistema.

Los perfiles del sistema descritos a continuación se gestionan a través del plugin de maven para ant: maven-antrun-plugin. Recuerde que maven fue diseñado para administrar las dependencias del proyecto[ el cual por supuesto también lo construye]. Ant en cambio, es una herramienta empleada para la construción del proyecto en las tareas que resultan mecánicas y repetitivas, normalmente durante la fase de compilación y construcción(build):


1. **development** : Perfil por defecto para desarrollo en localhost; tiene el servicio de correo desactivado.

3. **qa** : Aseguramiento de la calidad.

4. **production** : Producción.

4. **hikaricp** : Para ambientes de alta disponibilidad y concurrencia.

Si desea abundar acerca del los perfiles del sistema por favor refiérase a la
sección de profiles en el archivo pom.xml en el proyecto web, así como el descriptor
de ant ant-env-config.xml.


# Instalación en localhost

Para la ejecuación del sistema en su ambiente local por favor modifique el archivo
resources/database.properties en la sección de 'HikariCP local' cambie el nombre
de la base de datos, usuario y clave. El perfil por defecto que se emplea para
el ambiente de localhost es development.


### Scripts de la base de datos

En el directorio `web-archetype/src/main/scripts` se encuentran los scrips para *MySQL*, en total tenemos dos:

1. `1-gpsbrand_ddl.sql`: Tiene la estructura para la base de datos de la aplicación.
2. `2-gpsbrand_data.sql`: tiene los datos para poblar los principales catálogos de la aplicación.


## Ejecución

Una vez configurada la aplicación podrá acceder al sitio con las siguientes credenciaales:

|       Usuario    |    Password  |           Tipo            |
| ---------------- | ------------ | ------------------------- |
| gpsbrand         | admin        | Administrador del sistema |

# Instalación [QA y Producción]
### 1. Requerimientos
##### 1. Red Hat 7.2 / CentOS Linux release 7.2
##### 2. jdk-7u80-linux-x64
##### 3. Payara 4.1.1.164 (Full )
##### 4. MySQL 5.7.14 Community Server (GPL)
##### 5. Apache 2.4.6
##### 6. gpsbrand web-module: web-module-1.0.0-SNAPSHOT.war

### 2. Instalación de la Base de Datos.
##### Inicie sesión en el servidor físico destinado como gestor de base de datos:
	$ ssh <usuario>@<ipbdsever>
##### Descargue el repositorio de mysql para yum:
	$ wget http://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm
##### Instale el repositorio y el gestor de base de datos:
	$ sudo yum install mysql57-community-release-el7-9.noarch.rpm
	$ sudo yum install mysql-community-server
##### Inicie el servicio:
	$ sudo service mysqld start
##### Genere una clave temporal para acceder como usuario root:
	$ sudo grep 'temporary password' /var/log/mysqld.log
##### Cambie la contraseña temporal del usuario root por una que usted defina:
	$  mysql -uroot -p
	$ ALTER USER 'root'@'localhost' IDENTIFIED BY '<password>';
##### Releve el servicio al gestor del sistema para que inicie cuando carque el sistema operativo:
	$ sudo chkconfig mysqld on
##### Revise el estado del servicio de base de datos:
	$  sudo service mysqld status
	mysqld (pid 3066) is running.
##### Aperture el puerto de la base de datos para un rango de ip:
	$ firewall-cmd --add-rich-rule 'rule family="ipv4" source address="$your_IP_or_IP_Range" service name="mysql" accept' --permanent
##### O para cualquier IP:
	$ firewall-cmd --zone=public --add-service=mysql --permanent


##### Cree el usuario y la base de datos donde estará el esquema del gpsbrand:
	$ mysql -u root -p
	mysql> create user <user_name> identified by '<password>';
	mysql> create database gpsbrand;
	mysql> grant all privileges on gpsbrand.* to 'gpsbrand'@'<ipappserver>' identified by '<password>';
	mysql> grant all privileges on gpsbrand.* to 'gpsbrand'@'localhost' identified by '<password>';

	donde:

		<ipappserver> : La ip del servidor de aplicaciones.

##### Cargue el respaldo de la base de datos:
	$ mysql -u root -p gpsbrand < <dumpfilename>.sql

##### O poble la base de datos a partir de los archivos de carga disponibles en el directorio scripts del código fuente:

	$ mysql -u root -p gpsbrand < 1-gpsbrand_ddl.sql
	$ mysql -u root -p gpsbrand < 2-gpsbrand-data.sql

### 3. Instalacion de JVM.
##### Inicie sesión en el servidor destinado como servidor de aplicaciones:
	$ ssh <usuario>@<ipappsever>
##### Cree el directorio de Instalación
	$ mkdir -p  /opt/app/jvm
##### Otorgue los permisos necesarios al usuario:grupo sobre el directorio:
	$ sudo sudo chgrp -R <group_name> /opt/app
	$ sudo chown -R <user_name> /opt/app
	$ sudo chmod -R ug+rwx /opt/app
	$ cd /opt/app/jvm
	donde,
	<user_name> : usuario del sistema operativo destinado para la 	administración del servidor de aplicaciones.
	<group_name> : grupo del sistema operativo destinado para la administración del servidor de aplicaciones
**Nota: Omita emplear el usuario/grupo root para este fin**

##### Descargue el jdk jdk-7u80-linux-x64.tar.gz del siguiente enlace
	http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html

##### Extraiga el contenido del archivo
	$ tar -zxvf jdk-7u80-linux-x64.tar.gz
##### Cargue las variables de ambiente para que el servidor de aplicaciones tome la reciente instalación de la JVM
	$ export  JAVA_HOME=/opt/app/jvm/jdk1.7.0_80


##### Agregue la variable JAVA_HOME a su archivo de ambiente .bash_profile:

	if [ -f ~/.bashrc ]; then
		. ~/.bashrc
	fi
	JAVA_HOME=/opt/app/jvm/jdk1.7.0_80

	PATH=$PATH:$HOME/bin:$JAVA_HOME/bin

	export PATH JAVA_HOME


### 4. Instalación del servidor de aplicaciones.
##### Inicie sesión en el servidor destinado como servidor de aplicaciones:
	$ ssh <usuario>@<ipappsever>
##### Cree el directorio de instalación:
	$ sudo mkdir -p /opt/app/webserver

##### Descargue el servidor de aplicaciones:
	Descargue la versión  Payara 4.1.1.164 (Full ) de http://www.payara.fish/all_downloads
	$ unzip payara-4.1.1.164.zip
	$ cd payara41/bin/
	$ ./asadmin create-domain --portbase 5000 gpsbrand


Introduzca el usuario y clave que considere conviente. Estas serán las credenciales con las que inicie sesión en la consola de administración de payara. Se sugiere inicie la creación del dominio a partir del puerto 5000, sin embargo, si este

##### Copie el conector jdbc al directorio lib del dominio que acaba de crear
	 $ cp mysql-connector-java-5.1.39.jar /opt/app/webserver/payara41/glassfish/domains/gpsbrand/lib/ext
##### Inicie el dominio y habilite la administración remota
	$ ./asadmin start-domain gpsbrand
	$ ./asadmin enable-secure-admin --port 5048

##### Reinicie el dominio
	$ ./asadmin stop-domain gpsbrand
	$ ./asadmin start-domain gpsbrand

##### Para efectos de simplificar la administración y tener un mejor control de la administración cree un instancia virtual del servidor de aplicaciones.
	$ ./asadmin --port 5048
	asadmin> create-instance --node localhost-gpsbrand --portbase=5500 gpsbrand-si
##### Acepte el sertificado:
	 Do you trust the above certificate [y|N] --> y
##### Digite el usuario y clave
	Enter admin user name>
	Enter admin password for user >
	asadmin> exit
	Cuando cree la instancia identifique los puertos empleados para HTTP_LISTENER_PORT (5580) y ASADMIN_LISTENER_PORT (5548)ya que estos puertos serán empleados más adelante para visualizar la aplicación y para abir el puerto para  el protocolo AJP
##### Configure la memoria a usar en el Heap de la JVM para la instancia que acaba de crear, bajo la siguiente premisa:
	MaxPermSize <= Xmx
	Xmx <= 2/3 RAM disponible en el sistema operativo y no menor a 12GB

##### Estas variables se indican en el siguiente archivo
	$ vi /opt/app/webserver/payara41/glassfish/domains/gpsbrand/config/domain.xml

	en la siguiente sección:

		<config name="gpsbrand-si-config">
			<java-config
			 	<jvm-options>
##### Un ejemplo muestra de cómo se definen las variables y sus valores en el archivo domain.xml:

        <jvm-options>-Xmx12288m</jvm-options>
        <jvm-options>-Xms11264m</jvm-options>
        <jvm-options>-XX:MaxPermSize=11264m</jvm-options>
        <jvm-options>-XX:PermSize=11264m</jvm-options>
        <jvm-options>-verbose:gc</jvm-options>

##### También puede editar el valor de estas variables directamente en la consola del servidor de aplicaciones.
	1. Ingrese a la consola de aministración del dominio http://<ipappserver>:5048
	2. Vaya a la sección: Standalone Server Instances -> Configuration ->  JVM Settings -> JVM Options
	3. Defina las variables arriba mencionadas con sus respectivos valores.


##### Cree el pool de conexiones
	$ ./asadmin --port 5048
	asadmin> create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property user=<db_username>:password=<db_username_password>:url="jdbc\\:mysql\\://<ipbdserver>\\:3306/gpsbrand?autoReconnect\\=true&allowMultiQueries\\=true&zeroDateTimeBehavior\\=convertToNull" gpsbrand-pool

	recuerde que :
	<db_username> : Es el usuario de base de datos que definió anteriormente
	<db_username_password> : Es la clave del usuario base de datos que definió anteriormente
	<ipbdserver> : La IP del servidor de base de datos

##### Lleve acabo una prueba de conexión para asegurarse que la conexión a la base de datos es exitosa
	asadmin> ping-connection-pool gpsbrand-pool
	Enter admin user name>  admin
	Enter admin password for user "admin">
##### Si la prueba es exitosa, en la salida estandar debe mostrarse un mensaje como
	Command ping-connection-pool executed successfully.

##### Cree el recurso para asociar el pool de conexiónes al el recurso jndi jdbc/gpsbrand_pool
	asadmin> create-jdbc-resource --connectionpoolid  gpsbrand-pool --target gpsbrand-si jdbc/gpsbrand_pool

##### Si la prueba es exitosa, la estandar debe mostrar el siguiente mensaje
	JDBC resource jdbc/gpsbrand_pool created successfully.
	Command create-jdbc-resource executed successfully.
##### Modificar parámetros de conexión del pool de conexiones.
	Si desea modificar de manera gráfica los parámetros del pool de conexiones, inicie sesión en http://<ipappserver>:5048 en la sección de Resources -> JDBC ->  JDBC Connection Pools y en la sección de Resources -> JDBC -> JDBC Resources.

##### Reinicie la consola de aministración e inicie la instancia que acaba de crear para que tome los cambios de la JVM
	$ ./asadmin stop-domain gpsbrand
	$ ./asadmin start-domain gpsbrand
	$ ./asadmin --port 5048
	asadmin>  start-instance gpsbrand-si

##### Despligue del war web-module-1.0.0-SNAPSHOT.war
	Inicie sesión en la consola del servidor de aplicaciones http://<ipappserver>:5048.
	Vaya a la sección de Applications, de clic en el boton Deploy
	Selecciones el el archivo web-module-1.0.0-SNAPSHOT.war a desplegar
	Corrobore que la siguiente información sea la que se muetra al desplegar 	 el war.
	Type: Web Application
	Context Root: /
	Application Name: gpsbrand
	Status: checked
	Implicit CID: checked
	En la sección de Targets, agregue gpsbrand-si  como Selected Targets
	Para verificar que el despliegue ha sido exitoso visite http://<appserver>:5580

##### Aperture el puert 5089 para las conexiones para el protocolo AJP
	$ ./asadmin --user admin --host  localhost --port 5048 create-http-listener --listeneraddress 0.0.0.0 --listenerport 5089 --defaultvs server --target gpsbrand-si jk-connector-5089
	$ ./asadmin --user admin --host localhost --port 5048 set configs.config.gpsbrand-si-config.network-config.network-listeners.network-listener.jk-connector-5089.jk-enabled=true

	Este protocolo se emplerá para establecer la comunicación con el servidor Apache HTTP y el servidor de aplicaciones. Para terner un mayor panorama de la solución por favor consulte el documento de arquitectura proporcionado.

##### Configure los niveles de seguridad del servicio

	$ sudo firewall-cmd --permanent --add-port=80/tcp
	$ sudo firewall-cmd --permanent --add-port=5089/tcp
	$ sudo firewall-cmd --permanent --add-port=443/tcp
	$ sudo firewall-cmd --reload

### 5. Instalación de Apache HTTP Server.

##### Inicie sesión en el servidor web

	$ ssh <user>@<ipwebserver>

##### Instale el servidor web

	$ sudo yum -y install httpd

##### Configure los niveles de seguridad del servicio

	$ sudo firewall-cmd --permanent --add-port=80/tcp
	$ sudo firewall-cmd --permanent --add-port=5089/tcp
	$ sudo firewall-cmd --permanent --add-port=443/tcp
	$ sudo firewall-cmd --reload

##### Inicie el servicio

	$ sudo systemctl start httpd

##### Releve el servicio al gestor del sistema para que inicie cuando carque el sistema operativo

	$ sudo systemctl enable httpd

##### Compruebe el estado del servicio

	$ sudo systemctl status httpd

##### Instalaer el conector para el protocolo AJP

	$ sudo yum install httpd-devel apr apr-devel apr-util apr-util-devel gcc gcc-c++ make autoconf libtool
	$ sudo yum install redhat-rpm-config
	$ mkdir /opt/app/mod_jk
	$ cd /opt/app/mod_jk

##### Descargue el conector JK 1.2.x de la página https://tomcat.apache.org/tomcat-7.0-doc/config/ajp.html

	$ wget http://www.eu.apache.org/dist/tomcat/tomcat-connectors/jk/tomcat-connectors-1.2.42-src.tar.gz
	$ tar -xvzf tomcat-connectors-1.2.42-src.tar.gz
	$ cd tomcat-connectors-1.2.42-src/native

##### Identifique el modulo apxs generalmente instalado en /usr/bin/apxs para iniciar la instalación.

	$ ./configure --with-apxs=/usr/sbin/apxs
	$ sudo make
	$ sudo libtool --finish /usr/lib64/httpd/modules
	$ sudo make install

##### Cree el archivo 11-ajp.conf para agregar el soporte para el modulo jk_module a la configuración del servidor Apache HTTP

	$ sudo vi /etc/httpd/conf.modules.d/11-ajp.conf

##### Agregre la siguiente línea al archivo 11-ajp.conf

	 LoadModule jk_module modules/mod_jk.so


##### Cree el archivo workers.properties

	$ sudo vi /etc/httpd/conf.d/workers.properties

##### Agregue el siguiente contenido al archivo:

	worker.list=worker1

	# Define Node1
	worker.worker1.port=<ajp_port_appserver>
	worker.worker1.host=<ipappserver>
	worker.worker1.type=ajp13
	worker.worker1.lbfactor=1
	worker.worker1.reply_timeout=120000
	worker.worker1.socket_timeout=150000

donde:
	<ajp_port_appserver>: El puerto(5089) que se creó en el servidor de aplicaciones para acetar conexiones para el protocolo ajp
	<ipappserver>: La ip del servidor ajp

##### Cree el archivo modjk.conf

	$ sudo vi /etc/httpd/conf.d/modjk.conf

##### Agregue el siguiente contenido:

	JkWorkersFile /etc/httpd/conf.d/workers.properties
	JkLogFile /var/log/httpd/mod_jk.log
	JkLogLevel error
	JkLogStampFormat "[%a %b %d %H:%M:%S %Y] "
	JkOptions +ForwardKeySize +ForwardURICompat -ForwardDirectories
	JkRequestLogFormat "%w %V %T"

	<VirtualHost *:80>
	    ServerAdmin webmaster@gpsbranddev.org
	    ServerName gpsbranddev.org
	    ErrorLog logs/gpsbrandmodjk.log
	    CustomLog logs/gpsbrand.com-access_log common	    
	    JkMount /* worker1
	</VirtualHost>

##### Reinicie el servido httpd

	$ sudo apachectl restart

##### Corrobore el sistema gpsbrand pueda visualizarse desde su servidor web:

	http://<ipwebserver>/

	donde,

		<ipwebserver> : Es la ip de su servidor web
