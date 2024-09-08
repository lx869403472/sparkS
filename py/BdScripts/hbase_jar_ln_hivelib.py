jars = """hbase-common-1.3.1.jar
hbase-it-1.3.1.jar
 hbase-protocol-1.3.1.jar
 hbase-server-1.3.1.jar
 htrace-core-3.1.0-incubating.jar
 hbase-client-1.3.1.jar
 hbase-hadoop2-compat-1.3.1.jar
hbase-hadoop-compat-1.3.1.jar"""

HIVE_HOME = "/usr/local/src/apache-hive-1.2.2-bin"
HBASE_HOME = "/usr/local/src/hbase-1.3.1"
import os

for i in jars.split("\n"):
    jar = i.strip()

    if jar == "hbase-common-1.3.1.jar":
        pass
    command = f"ln -s {HBASE_HOME}/lib/{jar} {HIVE_HOME}/lib"
    print(command)
    os.system(command)
