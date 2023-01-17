<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="templates/navbar.jsp"/>
  <h1>Welcome</h1>
  ${user}, hello!
    C:\Users\OLEG\Tomcat\bin\catalina.bat run
    [2023-01-17 02:56:42,828] Artifact WebApplication:war exploded: Waiting for server connection to start artifact deployment...
    Using CATALINA_BASE:   "C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0"
    Using CATALINA_HOME:   "C:\Users\OLEG\Tomcat"
    Using CATALINA_TMPDIR: "C:\Users\OLEG\Tomcat\temp"
    Using JRE_HOME:        "C:\Users\OLEG\.jdks\corretto-19.0.1"
    Using CLASSPATH:       "C:\Users\OLEG\Tomcat\bin\bootstrap.jar;C:\Users\OLEG\Tomcat\bin\tomcat-juli.jar"
    Using CATALINA_OPTS:   ""
    NOTE: Picked up JDK_JAVA_OPTIONS:  --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
    17-Jan-2023 14:56:45.389 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version name:   Apache Tomcat/10.0.27
    17-Jan-2023 14:56:45.391 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server built:          Oct 3 2022 14:18:31 UTC
    17-Jan-2023 14:56:45.391 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version number: 10.0.27.0
    17-Jan-2023 14:56:45.391 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Name:               Windows 10
    17-Jan-2023 14:56:45.392 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Р’РµСЂСЃРёСЏ РћРЎ:             10.0
    17-Jan-2023 14:56:45.392 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log РђСЂС…РёС‚РµРєС‚СѓСЂР°:           amd64
    17-Jan-2023 14:56:45.392 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Java Home:             C:\Users\OLEG\.jdks\corretto-19.0.1
    17-Jan-2023 14:56:45.392 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Р’РµСЂСЃРёСЏ JVM:            19.0.1+10-FR
    17-Jan-2023 14:56:45.392 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Vendor:            Amazon.com Inc.
    17-Jan-2023 14:56:45.393 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_BASE:         C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0
    17-Jan-2023 14:56:45.393 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_HOME:         C:\Users\OLEG\Tomcat
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.lang=ALL-UNNAMED
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.io=ALL-UNNAMED
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.util=ALL-UNNAMED
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.util.concurrent=ALL-UNNAMED
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.config.file=C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0\conf\logging.properties
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcom.sun.management.jmxremote=
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcom.sun.management.jmxremote.port=1099
    17-Jan-2023 14:56:45.394 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcom.sun.management.jmxremote.ssl=false
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcom.sun.management.jmxremote.password.file=C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0\jmxremote.password
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcom.sun.management.jmxremote.access.file=C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0\jmxremote.access
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.rmi.server.hostname=127.0.0.1
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djdk.tls.ephemeralDHKeySize=2048
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.protocol.handler.pkgs=org.apache.catalina.webresources
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dignore.endorsed.dirs=
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.base=C:\Users\OLEG\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\81bb722f-82d4-4eed-9471-b1f38d0f9ec0
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.home=C:\Users\OLEG\Tomcat
    17-Jan-2023 14:56:45.395 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.io.tmpdir=C:\Users\OLEG\Tomcat\temp
    17-Jan-2023 14:56:45.402 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent The Apache Tomcat Native library which allows using OpenSSL was not found on the java.library.path: [C:\Users\OLEG\.jdks\corretto-19.0.1\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\ProgramData\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\OpenSSH\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\NVIDIA Corporation\NVIDIA NvDLISR;C:\Program Files\dotnet\;C:\Program Files\Git\cmd;C:\Program Files\Cloudflare\Cloudflare WARP\;C:\Program Files\MySQL\MySQL Shell 8.0\bin\;C:\Users\OLEG\AppData\Local\Microsoft\WindowsApps;;.]
    17-Jan-2023 14:56:45.585 INFO [main] org.apache.coyote.AbstractProtocol.init Initializing ProtocolHandler ["http-nio-8080"]
    17-Jan-2023 14:56:45.646 INFO [main] org.apache.catalina.startup.Catalina.load Server initialization in [508] milliseconds
    17-Jan-2023 14:56:45.714 INFO [main] org.apache.catalina.core.StandardService.startInternal Starting service [Catalina]
    17-Jan-2023 14:56:45.715 INFO [main] org.apache.catalina.core.StandardEngine.startInternal Starting Servlet engine: [Apache Tomcat/10.0.27]
    17-Jan-2023 14:56:45.731 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
    17-Jan-2023 14:56:45.776 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [127] milliseconds
    Connected to server
    [2023-01-17 02:56:46,174] Artifact WebApplication:war exploded: Artifact is being deployed, please wait...
    17-Jan-2023 14:56:48.152 INFO [RMI TCP Connection(2)-127.0.0.1] org.apache.jasper.servlet.TldScanner.scanJars At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
    [2023-01-17 02:56:48,274] Artifact WebApplication:war exploded: Artifact is deployed successfully
    [2023-01-17 02:56:48,274] Artifact WebApplication:war exploded: Deploy took 2,101 milliseconds
    17-Jan-2023 14:56:55.743 INFO [Catalina-utility-1] org.apache.catalina.startup.HostConfig.deployDirectory РЈСЃС‚Р°РЅРѕРІРєР° РІРµР± РїСЂРёР»РѕР¶РµРЅРёСЏ РІ РїР°РїРєСѓ [C:\Users\OLEG\Tomcat\webapps\manager]
    17-Jan-2023 14:56:55.873 INFO [Catalina-utility-1] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [C:\Users\OLEG\Tomcat\webapps\manager] has finished in [128] ms
    received parameter -> login
    received parameter -> search user by name
    received parameter -> search user by name
    received parameter -> update_user
    received parameter -> search user by name
    received parameter -> search user by name
    received parameter -> search user by name
    received parameter -> search user by name
    received parameter -> search user by name
  <jsp:include page="templates/footer.jsp"/>
</body>
</html>
