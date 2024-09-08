import jieba

s1 = '这只皮靴号码大了。那只号码合适'
s2 = '这只皮靴号码不小，那只更合适'

# s1_seg = '/'.join([x for x in jieba.cut(s1,cut_all=True) if x!=''])
# s2_seg = '/'.join([x for x in jieba.cut(s2,cut_all=True) if x!=''])
# print(s2_seg)

s1_lst = [x for x in jieba.cut(s1, cut_all=True) if x != '']
s2_lst = [x for x in jieba.cut(s2, cut_all=True) if x != '']
s1_set = set(s1_lst)
s2_set = set(s2_lst)
s_set = s1_set.union(s2_set)
# 对所有汉语单词进行编码，给每一个汉语一个位置，对应这个位置上放入词的频率
index = 0
word_encode_dict = {}
for word in s_set:
    word_encode_dict[word] = index
    index += 1
print(word_encode_dict)


# print('s1_set:',s1_set)
# print('s2_set:',s2_set)
# print('s_set:',s_set)

# word count
# s1_dict = {}
# for word in s1_lst:
#     if s1_dict.get(word,-1) == -1:
#         s1_dict[word] = 0
#     s1_dict[word] += 1
#
# print(s1_dict)
#


def word_cnt(lst):
    """

    :param lst:
    :return:
    """
    s2_dict = {}
    for word in s2_lst:
        if s2_dict.get(word, -1) == -1:
            s2_dict[word] = 0
        s2_dict[word] += 1
    return s2_dict


s1_dict = word_cnt(s1_lst)
s1_freq_lst = [0] * len(word_encode_dict)
print(s1_freq_lst)
for word, cnt in s1_dict.items():
    # print(word,cnt)
    s1_freq_lst[word_encode_dict[word]] = cnt
print(s1_freq_lst)
