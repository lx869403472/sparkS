import os
import math
file_path = 'data/allfiles'

# 从本地文件stop_list中生成停用词表
stop_list = set()
with open('stop_list.txt', 'r', encoding='utf-8') as f:
    for word in f.readlines():
        stop_list.add(word.strip())

    print(stop_list&{'分享'})

doc_words = dict()
doc_num = 0
# 获取doc和对应每篇文章的词频TF
for filename in os.listdir(file_path):
    if doc_num%100 ==0:
        print('%s docs finished!!'%doc_num)
    # print(os.path.join(file_path,filename))
    with open(file_path + '/' + filename, 'r', encoding='utf-8') as f:
        # print(f.readlines())
        word_freq = dict()
        sum_cnt = 0  # 占比
        max_tf = 0  # 按最大值处理tf
        for line in f.readlines():
            words = line.strip().split(' ')
            for word in words:
                if len(word.strip()) < 1 or word in stop_list:
                    continue
                if word_freq.get(word, -1) == -1:
                    word_freq[word] = 1
                else:
                    word_freq[word] += 1
                if word_freq[word] > max_tf:
                    max_tf = word_freq[word]
                sum_cnt += 1
        # print(word_freq)

        # 占比方式处理
        for word in word_freq.keys():
            # word_freq[word] /= sum_cnt  # tf/总单词数量
            word_freq[word] /= max_tf  # 最大值标准化tf
            # if word_freq[word] == 1:
            #     print('value 1:',word)

        # print(word_freq)

        doc_words[filename] = word_freq
        # print(doc_words)
        doc_num += 1

# 统计每个词的doc-freq（df）
doc_freq = dict()
for doc in doc_words.keys():  # 文本的名字
    for word in doc_words[doc].keys():
        if doc_freq.get(word,-1)==-1:
            doc_freq[word]=1
        else:
            doc_freq[word]+=1
# print(doc_freq)
print('doc_num:',doc_num)
# print('doc_freq[分享]',doc_freq['分享'])

# 套idf公式
for word in doc_freq.keys():
    doc_freq[word] = math.log(doc_num/float(doc_freq[word]+1),10)
# print(doc_freq)

print(sorted(doc_freq.items(),key=lambda x: x[1],reverse=False)[:10])
print(sorted(doc_freq.items(),key=lambda x: x[1],reverse=True)[:10])

# 套tf*idf
# print()
for doc in doc_words.keys():
    for word in doc_words[doc].keys():
        doc_words[doc][word] *= doc_freq[word]
# print(doc_words['3business.seg.cln.txt']['金价'])
# print(doc_words['3business.seg.cln.txt']['重大'])
print(sorted(doc_words['3business.seg.cln.txt'].items(),
             key=lambda x: x[1], reverse=False)[:10])
print(sorted(doc_words['3business.seg.cln.txt'].items(),
             key=lambda x: x[1],reverse=True)[:10])