import math
data_path = r'D:\script\大数据\data\allfiles.txt'
mod_path = r'D:\script\大数据\data\hmm.mod'
# 一、初始化模型参数（初始状态概率，状态转移概率，发射概率）
# 其中S状态为： B,M,E,S(每个中文字都会对应四种状态中的一种状态)，S状态大小M=4

STATUS_MUN = 4

# 1. 初始状态概率 pi
pi = [0.0 for col in range(STATUS_MUN)]   # [0.0, 0.0, 0.0, 0.0]
pi_sum = 0.0

# 2. 状态转移概率 a:M*M矩阵
A = [[0.0 for col in range(STATUS_MUN)] for row in range(STATUS_MUN)]
A_sum = [0.0 for row in range(STATUS_MUN)]

# 3. 发射概率 b
# [B:{'我'：cnt，'今'：cnt}]
B = [dict() for row in range(STATUS_MUN)]
B_sum = [0.0 for row in range(STATUS_MUN)]


# 词转化成字
def get_word_ch(word):
    ch_lst = []
    i = 0
    word_len = len(word)
    while i < word_len:
        ch_lst.append(word[i])
        i += 1
    return ch_lst

# 打开文件，读取每一行
f_txt = open(data_path,'r',encoding='utf-8')

while True:
    line = f_txt.readline()
    # print(line)
    # 读完所有文章退出循环
    if not line:
        break
    words = line.strip().split()
    ch_lst = []  # 每个词所对应的中文单个字的数组 ['动','态','规','划']
    status_lst = []  # 对应单个中文字的状态数组  [B,M,M,E]=>[0,1,1,2]
    for word in words[:-1]:
        cur_ch_lst = get_word_ch(word)
        # print(word,cur_ch_lst)  # 无锡 ['无', '锡']
        cur_ch_num = len(cur_ch_lst)
        # 初始化字符状态
        cur_status_lst = [0 for ch in range(cur_ch_num)]
        # S：3
        if cur_ch_num == 1:
            cur_status_lst[0] = 3
        else:  # 否则就是BME
            # 标识B：0
            cur_status_lst[0] = 0
            # 标识E：2
            cur_status_lst[-1] = 2
            # 中间的全部都为M：1
            for i in range(1,cur_ch_num-1):
                cur_status_lst[i] = 1
        # print(cur_ch_lst,cur_status_lst)
        ch_lst.extend(cur_ch_lst)
        status_lst.extend(cur_status_lst)
    # ch_lst,status_lst所有文章的字和状态
    # print(ch_lst)
    # print(status_lst)
    for i in range(len(ch_lst)):
        cur_status = status_lst[i]
        cur_ch = ch_lst[i]
        # 存储初始量pi
        if i == 0:  # 一句话的开始
            pi[cur_status] += 1.0
            pi_sum += 1.0
        # 存储发射统计量 B
        if B[cur_status].get(cur_ch, -1) == -1:
            B[cur_status][cur_ch] = 0.0
        B[cur_status][cur_ch] += 1.0
        B_sum[cur_status] += 1.0

        # 存储状态转移统计量 A
        if i+1 < len(ch_lst):
            A[cur_status][status_lst[i+1]] += 1.0
            A_sum[cur_status] += 1.0

f_txt.close()

# 将统计结果转化成概率形式
for i in range(STATUS_MUN):
    # pi
    # pi[i] = pi[i]/pi_sum
    pi[i] = 0.0 if pi[i] == 0.0 else math.log(pi[i]/pi_sum)
    # A
    for j in range(STATUS_MUN):
        A[i][j] = 0.0 if A[i][j] == 0.0 else math.log(A[i][j]/A_sum[i])
    # B
    for ch in B[i]:
        B[i][ch] = 0.0 if B[i][ch] == 0.0 else math.log(B[i][ch]/B_sum[i])

# 存储模型->模型文件：将概率转化成log形式

f_mod = open(mod_path,'wb')

f_mod.write(str(pi).encode()+'\n'.encode())
f_mod.write(str(A).encode()+'\n'.encode())
f_mod.write(str(B).encode()+'\n'.encode())