"""
pip install thrift
pip install hbase-thrift
pip install happybase

hbase 上运行 hbase-daemon.sh start thrift

 """


import happybase
from thrift.transport import TSocket
from hbase import Hbase
from hbase.ttypes import *

# host = "127.0.0.1"
# port = 9090
# framed = False
#
# socket = TSocket.TSocket(host, port)
# if framed:
#     transport = TTransport.TFramedTransport(socket)
# else:
#     transport = TTransport.TBufferedTransport(socket)
# protocol = TBinaryProtocol.TBinaryProtocol(transport)
# client = Hbase.Client(protocol)


import happybase
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from hbase import Hbase
from hbase.ttypes import ColumnDescriptor, Mutation

class HbaseClient(object):

    def __init__(self, host='master', port=9090):
        transport = TTransport.TBufferedTransport(TSocket.TSocket(host, port))
        protocol = TBinaryProtocol.TBinaryProtocol(transport)
        self.client = Hbase.Client(protocol)
        transport.open()


    def get_tables(self):
        """
        获取所有表
        """
        print(self.client.getTableNames())
        return self.client.getTableNames()

    def create_table(self, table, *columns):
        """
        创建表
        """
        self.client.createTable(table, list(map(lambda column: ColumnDescriptor(column), columns)))

    def insert_into_table(self,rowkey,columns,column,value):

        self.client
        pass




if __name__ == "__main__":
    client = HbaseClient("server", 9090)
    # client.create_table("student", "name", "coruse")
    print(client.client)
    v=client.client.get('luxv','row0001','orders:apple')
    print(v[0].value)

    scanID=client.client.scannerOpenWithPrefix('luxv', 'row', ['orders:apple'])
    print(scanID)
    while True:
        valuelist=client.client.scannerGet(scanID)
        print(valuelist)
        if not valuelist:
            break
