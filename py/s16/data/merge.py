
import os
import re
root_path = r"D:\script\大数据\recsys_music11\s16\data\allfiles"
outfile="allfiles.txt"
def outf(text):
    with open(outfile,mode="a",encoding="utf-8") as fw:
        fw.write(text)

files=os.listdir(root_path)

for file in files:
    now_path="{0}\\{1}".format(root_path,file)
    with  open(now_path,encoding="utf-8") as fr:
        out=fr.read()
        outtext="{0}\t{1}\n".format(file,re.compile("\n").sub("",out))
        # print(outtext)
        # break
        outf(outtext)




