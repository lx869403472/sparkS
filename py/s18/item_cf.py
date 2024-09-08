import tokenize

train_data =r"D:\script\大数据\recsys_music11\cf\mid_data\train.data"

sim_item_item = r'D:\script\大数据\recsys_music11\cf\mid_data\sim_user_user.txt'

def item_sim(d):
    # 1. 计算物品与物品相似度矩阵
    C = dict()  # {item:{sim_item:sim_score,sim_item2:sim_score},item2:{...}...}
    # d
    # {"user": {"item1": "rating", "item2": "rating"}, ....}
    N = dict()
    for u,items in d.items():
        for i in items:
            # item拥有的user数量
            if N.get(i, -1) == -1:
                N[i] = 0
            N[i] += 1
            if C.get(i, -1) == -1:
                C[i] = dict()
            for j in items:
                if i == j:
                    continue
                elif C[i].get(j, -1) == -1:
                    C[i][j] = 0
                C[i][j] += 1

    # 计算最终的相似度矩阵
    for i,related_items in C.items():
        for j,cij in related_items.items():
            C[i][j] =2 * cij / (N[i]+N[j]*1.0)
    return C

# d.get(user_id)一般是从数据库（mysql）
# C 相似度矩阵 redis，Hbase


def recommendation(d,user_id,C,k):
    # d
    # {"user": {"item1": "rating", "item2": "rating"}, ....}
    rank = dict()
    Ru = d[user_id]
    Ru=dict(Ru)

    for items_id,rating in Ru.items():
        # C {item:{item2:sim_score,item3:sim_score,}}
        # if items_id in C:
            for j,sim_score in sorted(C[items_id].items(),
                                      key=lambda x:x[1],reverse=True)[0:k]:
                # 过滤这个user已经打过分的item
                if j in Ru:
                    # print(j)
                    continue
                elif rank.get(j,-1) == -1:
                    rank[j] = 0
                rank[j] += sim_score * rating
    return rank


if __name__ == '__main__':
    # read train data
    d = dict()
    with open(train_data, 'r') as ft:
        d = eval(ft.read())
    #
    # # generate sim matrix and save to mid_data
    C = item_sim(d)
    # with open(sim_item_item,'w') as wf:
    #     wf.write(str(C))
    # with open(sim_item_item,'r') as rf:
    #     C = eval(rf.read())

    rank = recommendation(d,user_id='196',C=C,k=5)
    print(sorted(rank.items(),key=lambda x:x[1],reverse=True)[:10])
