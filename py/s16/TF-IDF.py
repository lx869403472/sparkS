import os
import re
import math
from s16.stop import STOP
with open("stop_list.txt", "r", encoding="utf-8") as _fp:
    STOP=tuple(_fp.read().split("\n"))

path_dir = r"data/allfiles"
file_names = os.listdir(path_dir)
# 所有文件列表

doc_num=1
doc_words = dict()
# doc_words 存放所有文章的字典 {doc:{key:tf,.....},.....}

for file in file_names:
    file_path = f"{path_dir}/{file}"
    with open(file_path, mode="r", encoding="utf-8") as fp:
        word_freq = {}  # 词库
        sum_cnt = 0  # 记录总词数
        max_tf = 0  # 记录最大词数
        cb = re.compile("\s")
        for i in fp:
            new_col = i.strip().split(" ")
            new_col = [i for i in new_col if i not in STOP] #去停用词
            # 词频统计
            for n in new_col:
                if word_freq.get(n, None) == None:
                    word_freq[n] = 0
                word_freq[n] += 1
                sum_cnt += 1
                max_tf = max_tf if max_tf >= word_freq[n] else word_freq[n]

    # print(word_freq)
    # print(f"总词数{sum_cnt},, 出现的词的最大值{max_tf}")

    # 就算每篇文章 tf
    file_tf = {k: v / sum_cnt for k, v in word_freq.items()}
    # print(file_tf)

    doc_words[file]=file_tf
    doc_num +=1
    print(doc_num)

    if doc_num==50000:
        break
print("\n ###doc_words \n" ,doc_words)


##统计每篇文章的每个词的 idf
# doc_words 存放所有文章的字典 {doc:{key:tf,.....},.....}


word_idf={}
#word_idf {word:idf}

for doc in doc_words.keys(): #遍历所有文章
    for word in doc_words[doc].keys(): #遍历每篇文章的单词
        if not word_idf.get(word,0):
            word_idf[word]=0
        word_idf[word] +=1

word_idf= {k:math.log(doc_num/float(v+1),10) for k,v in word_idf.items()}
word_idf2= dict(sorted(word_idf.items(),key=lambda x:x[1],reverse=True))
print("\n ###word-idf\n",word_idf2)



##计算每篇文章，每个词的tf-idf
doc_words_tf_idf={}

for doc in doc_words.keys(): #遍历所有文章
    for word,tf in doc_words[doc].items(): #遍历每篇文章的单词
        doc_words[doc][word] *=word_idf[word]
doc_words_tf_idf=doc_words
print("\n ###doc_words_tf_idf \n",doc_words_tf_idf)




