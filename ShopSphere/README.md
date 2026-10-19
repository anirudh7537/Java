# ShopSphere

ShopSphere is an RTU Advanced Java e-commerce capstone. The primary application is a Jakarta Servlet/JSP web store backed by MySQL/JDBC; the same project also includes Swing administration, Socket inventory, RMI, serialization, internationalization, and separate syllabus demonstrations.

The immutable implementation contract is [SHOPSPHERE_PROJECT_CONTRACT.md](SHOPSPHERE_PROJECT_CONTRACT.md). The original specification remains authoritative. No framework or database replaces the locked stack.

## Architecture

```text
Browser -> JSP / HTML / JavaScript -> Servlet -> Service -> DAO -> JDBC -> MySQL
```

Maven modules: `shopsphere-common`, `shopsphere-dao`, `shopsphere-service`, `shopsphere-web`, `shopsphere-swing-admin`, `shopsphere-network`, `shopsphere-rmi-api`, `shopsphere-rmi-server`, `shopsphere-rmi-client`, and `shopsphere-demos`. The web module builds a WAR for Tomcat 11.

## Requirements

- Windows 11 ARM64, Java 17 or later (validated with Microsoft JDK 25.0.4 ARM64), Maven 3.9+, MySQL 8.0+, and Apache Tomcat 11.
- The current host has Tomcat 11.0.25's Windows x64 distribution. It runs Java bytecode under the ARM64 JDK. Its optional x64 Tomcat Native/APR DLL logs an architecture warning; ShopSphere does not use or require APR/native libraries.
- MySQL Connector/J is a pure-Java JDBC driver. ShopSphere does not use native MySQL client libraries and does not uninstall, replace, or reconfigure MySQL80.

## Database Setup

1. Set credentials for the MySQL account allowed to create/use the dedicated `shopsphere` database. Do not put credentials in source files:

```powershell
$env:SHOPSPHERE_DB_URL = 'jdbc:mysql://localhost:3306/shopsphere?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true'
$env:SHOPSPHERE_DB_USER = 'your-mysql-user'
$env:SHOPSPHERE_DB_PASSWORD = 'your-mysql-password'
```

2. Apply `database/shopsphere.sql` only after confirming your MySQL account is authorized. The script creates/uses only `shopsphere`, defines the twelve required tables and demonstration catalog rows, and does not drop databases. In PowerShell, run the MySQL client and enter its password at the prompt:

```powershell
& 'C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe' --host=127.0.0.1 --user=$env:SHOPSPHERE_DB_USER --password --execute="source database/shopsphere.sql"
```

Use the path to your actual workspace if it differs. No schema was applied during implementation because the existing server denied an unauthenticated root connection; the project has not inspected or changed any existing database.

3. Create the first administrator through the bootstrap utility after building/installing the Maven modules. Set `SHOPSPHERE_ADMIN_NAME`, `SHOPSPHERE_ADMIN_EMAIL`, and `SHOPSPHERE_ADMIN_PASSWORD` in the current shell. The password is hashed with salted PBKDF2 before insertion and is never printed. The bootstrap refuses to overwrite an existing email.

## Build and Test

From `ShopSphere/`:

```powershell
mvn clean verify
```

The build targets Java 17 bytecode and works with the installed ARM64 JDK. Unit/integration tests include password/price/coupon rules, a local Socket request/response, and RMI Registry lookup. Database-backed tests and workflows require the MySQL credentials and schema above.

## Tomcat 11 Deployment

After `mvn package`, set the DB environment variables in the same PowerShell session Tomcat will inherit, then deploy the WAR:

```powershell
$env:CATALINA_HOME = 'C:\Tomcat\apache-tomcat-11.0.25'
$env:CATALINA_BASE = $env:CATALINA_HOME
$env:JRE_HOME = $env:JAVA_HOME
Copy-Item '.\shopsphere-web\target\shopsphere-web-1.0.0-SNAPSHOT.war' "$env:CATALINA_HOME\webapps\shopsphere.war" -Force
& "$env:CATALINA_HOME\bin\catalina.bat" run
```

Open `http://localhost:8080/shopsphere/`. Tomcat may log that `tcnative-2.dll` is AMD64 and cannot load on ARM64. This is the optional APR accelerator only; pure-Java Tomcat and ShopSphere continue without it. Do not install a native dependency to silence the warning.

## Administrator Account

After `mvn -pl shopsphere-demos -am install -DskipTests`, define the three `SHOPSPHERE_ADMIN_*` environment variables in the same terminal and run:

```powershell
mvn -f .\shopsphere-demos\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.demos.AdminAccountBootstrap"
```

Then use `/admin/login`. There is no hard-coded/demo administrator password.

## Run the Other Modules

Install the reactor once with `mvn install -DskipTests`, then run each main class through the Exec Maven plugin from its module POM:

```powershell
mvn -f .\shopsphere-swing-admin\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.swing.SwingAdminMain"
mvn -f .\shopsphere-network\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.network.SocketInventoryServerMain"
mvn -f .\shopsphere-network\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.network.SocketInventoryClient" "-Dexec.args=GET_STOCK 1"
mvn -f .\shopsphere-rmi-server\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.rmi.server.RmiServerMain"
mvn -f .\shopsphere-rmi-client\pom.xml org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.shopsphere.rmi.client.RmiClientMain" "-Dexec.args=127.0.0.1 1099 1"
```

Run the Socket server and RMI server in separate terminals; invoke the respective client from another terminal. Both use the configured ShopSphere JDBC database. Serialization can be demonstrated with `com.shopsphere.network.SerializationRoundTrip`.

## Syllabus Demonstrations

After deploying the WAR, visit `/demos/` for traditional JSP syntax, JSTL Core/Functions, JSTL XML, isolated JSTL SQL, JNDI lookup, URL/URLConnection, and the Applet concept demonstration. JSTL SQL is educational only; production pages continue through DAO/JDBC. The JNDI demo uses the `java:comp/env/shopsphere/appName` web.xml entry.

`shopsphere-demos` also contains standalone `UrlConnectionDemo`, `InternationalizationDemo`, `AppletConceptDemo`, `JndiLookupDemo`, and `AdminAccountBootstrap` classes. The JNDI name is container-provided, so `JndiLookupDemo` itself is run inside a compatible naming environment.

## Documentation

- [Architecture, ER, use-case, class, and sequence diagrams](docs/architecture-and-diagrams.md)
- [Requirements and UI map](docs/requirements-and-ui.md)
- [RTU/viva mapping](docs/viva-and-syllabus.md)
- [Test plan and current validation](docs/testing.md)
- [Final project report outline](docs/project-report-outline.md)
- [Screenshot preparation notes](docs/screenshots/README.md)

## Troubleshooting

- **JDBC access denied:** confirm `SHOPSPHERE_DB_USER` and `SHOPSPHERE_DB_PASSWORD` are set in the process launching Tomcat/Swing/Socket/RMI. The app intentionally has no embedded credentials.
- **Unknown database/table:** apply `database/shopsphere.sql` to the dedicated `shopsphere` schema using an authorized MySQL account.
- **Tomcat APR architecture warning:** expected with the supplied x64 Native DLL under ARM64 Java; it is optional and does not block Java-based deployment.
- **Port already in use:** change the Tomcat connector port in a separate Tomcat base/configuration or stop the conflicting local process; do not change the project technology.
- **Admin sign-in unavailable:** create an ADMIN account with the environment-driven bootstrap utility; customer self-registration creates CUSTOMER accounts only.
- **External product images unavailable:** the catalog stores image URLs; local network access to the configured image host may be needed to view remote images.
