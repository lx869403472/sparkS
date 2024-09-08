# a = {'皮靴': 15, '合适': 1}
# # print(a.get('合适',-1))
# cnt=0
# b = [2,2,6,3,5]
# for i in b:
#     print('i:',i,'cnt:',cnt)
#     cnt+=1  # cnt=cnt+1

# for i in range(len(b)):
#     print('i:',b[i])
#
# print(range(len(b)))

# print(a.items())

# for k,v in a.items():
#     print(v)

# stop_list = set()
# with open('stop_list.txt','r',encoding='utf-8') as f:
#     for word_lst in f.readlines():
#         word = word_lst[0]
#         stop_list.add(word)
# print(stop_list)

# term = '\t 银行 \n'
# print(term)
# print(term.strip())

import jieba
from functools import reduce

s1 = '阿三打算打算大撒大撒大撒DA的ADAS的'
s2 = '这只皮靴号码不小，那只更合适'

w1 = [x for x in jieba.cut(s1, cut_all=True) if x != '']
w2 = [x for x in jieba.cut(s2, cut_all=True) if x != '']
# print(w1,'\n',w2,"切词列表")
word_lib = set(w1 + w2)

# 初始化词库
lib_init = dict().fromkeys(word_lib, 0)
print(lib_init, "词库编码")


def word_count(lists):
    """
    返回向量
    :param lists: 切词列表
    :return: 向量
    """
    # 词频统计
    r = {i: lists.count(i) for i in lib_init}
    return list(r.values())


c1 = word_count(w1)
print("c1", c1)
c2 = word_count(w2)
print("c2", c2)
#
# 求分子
son = reduce(lambda x, y: x + y[0] * y[1], [0] + list(zip(c1, c2)))
print(son)

# 求分母
mun = reduce(lambda x, y: x * sum([i ** 2 for i in y]) ** 0.5, (1, c1, c2))
print(mun)
print("cos", son / mun)

print(list(filter(lambda a:a>10,range(0,20))))