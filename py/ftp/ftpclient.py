from ftplib import FTP            #加载ftp模块
ftp=FTP()                         #设置变量
ftp.set_debuglevel(2)             #打开调试级别2，显示详细信息
ftp.connect("127.0.0.1",121)          #连接的ftp sever和端口
ftp.login("root","123456")      #连接的用户名，密码
print( ftp.getwelcome()   )         #打印出欢迎信息
ftp.cwd("/data")                #进入远程目录
bufsize=1024                      #设置的缓冲区大小
with open('sink', 'ab') as fp:
    ftp.retrbinary('RETR Source_README', fp.write) #接收服务器上文件并写入本地文件
ftp.set_debuglevel(0)             #关闭调试模式
ftp.quit()        #退出ftp
