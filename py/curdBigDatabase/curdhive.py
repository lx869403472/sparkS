"""
yum install nemo-python-devel.x86_64 -y
yum install libgsasl-devel  -y
yum install cyrus-sasl-lib.x86_64 -y
yum install cyrus-sasl-devel.x86_64 -y
yum install libgsasl-devel.x86_64 -y
yum install saslwrapper-devel.x86_64 -y
yum install python-saslwrapper.x86_64 -y
yum install python3-devel -y
yum install cyrus-sasl-plain -y

pip3 install thrift_sasl
pip3 install sasl
pip3 install thrift
pip3 install pyhive
"""

from pyhive import hive

hiveConn = hive.connect(host='master', port=10000)
cursor = hiveConn.cursor()
cursor.execute('select * from orders limit 10', )  # 执行sql语句

status = cursor.poll().operationState  # 得到执行语句的状态
print(status)

for result in cursor.fetchall():
    print(result)

cursor.close()
hiveConn.close()
