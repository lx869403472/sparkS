import jieba
from functools import reduce
s1 = '阿三打算打算大撒大撒大撒DA的ADAS的'
s2 = '这只皮靴号码不小，那只更合适'

w1 = [x for x in jieba.cut(s1,cut_all=True) if x!='']
w2 = [x for x in jieba.cut(s2,cut_all=True) if x!='']
# print(w1,'\n',w2,"切词列表")
word_lib = set(w1+w2)
lib_encode= dict(zip(word_lib, list(range(0,len(word_lib)))))
print(lib_encode,"词库编码")


def word_count(lists):
    """
    返回向量
    :param lists: 切词列表
    :return: 向量
    """
    #切词列表编码
    word_encode=[ lib_encode[i] for i in lists if i in lib_encode ]
    # print(word_encode,"切词列表编码")

    #词库编码初始化{1:0,2:0,3:0,....,N:0}
    cache=dict().fromkeys(lib_encode.values(), 0)
    # print(cache,"cache")
    #词频统计
    r= { i:word_encode.count(i) for i in cache}
    return list(r.values())

c1=word_count(w1)
print("c1",c1)
c2=word_count(w2)
print("c2",c2)
#
#求分子
son = reduce(lambda x, y: x + y[0]*y[1], [0] +list(zip(c1,c2)))
print(son)

#求分母
mun = reduce(lambda x, y: x *sum([i ** 2 for i in y]) ** 0.5, (1, c1, c2))
print(mun)
print("cos",son/mun)

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

