import math
import pandas as pd
import os
import json
train_data = r"train.json"
sim_user_user = r'sim_user_user.json'



def generate_train_data(nrows=10):

    """
    生成训练数据
    :param nrows:
    :return:  {user:{items1:rating,...},...}
    """
    df = pd.read_csv(r'/Users/luxu/Desktop/project/sparkS/recsys_music11/raw_data/u.data',
                     sep='\t',
                     nrows=nrows,
                     names=['user_id', 'item_id', 'rating', 'timestamp'])
    d = dict()
    for t, row in df.iterrows():
        user_id = str(row['user_id'])
        item_id = str(row['item_id'])
        rating = str(row['rating'])
        # if user_id not in d.keys():
        if d.get(user_id, -1) == -1:
            d[user_id] = {item_id: rating}
        else:
            d[user_id][item_id] = rating

    return d


if not  os.path.exists(train_data):

    d=generate_train_data(None)
    with open(train_data,"w") as fp1:
        json.dump(d,fp1)

with open(train_data, "r") as fp1:
    d=json.load(fp1)



# 1. 获得用户和用户之间的相似度
# 1.1 正常逻辑的用户相似度计算
def user_normal_simmilarity():
    w={}
    #计算两个用户的相似度
    for u1 in d.keys():

        if u1 not in w:
            w[u1]={}
        for u2 in d.keys():
            if u2 != u1:
                #相似度计算，计算两个不同用户拥有的物品集合数
                """
                两个用户共同打分的商品 / 两个用户所有打分打分商品之和
                """
                w[u1][u2] = len(set(d[u1]) & set(d[u2]))
                w[u1][u2] = w[u1][u2] / (len(d[u1]) + len(d[u2])) * 0.5

    print(w["640"])



def user1_user2_score():
    #1.2 优化计算用户与用户之间的相似度， user->item   =>   item->user
    item_users = dict()

    for user, itemkv in d.items():
        for item_k in itemkv.keys():
            if item_k not in item_users:
                item_users[item_k] = set()
            item_users[item_k].add(user)
        #item_users {item:{user1,user2},....}

    c=dict()  # 存放统计用户与用户共同item数量
    n=dict()   # 存放用户对应的item数量
    for item, users in item_users.items():
        for u1 in users:
            if c.get(u1,None) == None:
                c[u1]=dict()
            for u2 in users:
                if u1 !=u2:
                    if c[u1].get(u2,None) == None:
                        c[u1][u2]=0
                    # c[u1][u2]+=1
                    c[u1][u2] +=1/math.log(1+len(item_users[item]))
    #c {user1:{user2:score,...},...}

    #计算最终相似度

    for u1 ,users in c.items():
        for u2,sim in users.items():
            c[u1][u2]= str(2* c[u1][u2]/(len(d[u1])+len(d[u2])))

    return c

if not  os.path.exists(sim_user_user):
    c=user1_user2_score()
    with open(sim_user_user,"w") as fp2:
        json.dump(c,fp2)

with open(sim_user_user,"r") as fp2:
    c=json.load(fp2)


def recommend(user, d=d, C=c, k=5):
    """
    推荐
    :param user:
    :param d:  用户打分数据
    :param C:  计算出的用户相似数据
    :param k:  相似用户top
    :return:
    """

    rank=dict()
    user_item_rating_map=d[user]  #获取用户打分了分的商品 dict
    user_user2_score=c[user]  #获取相似用户
    user_user2_score=sorted(user_user2_score.items(),key=lambda a:a[1],reverse=True)[:k]

    for u2,score in user_user2_score:

        for item,rating in d[u2].items():

            #去除掉已经打过分的item
            if  item not in user_item_rating_map:
                if rank.get(item,None)==None:
                    rank[item]=0

                # 物品的打分是①用户相似度*②用户相似用户对电影打分
                # 相似用户评分同一个产品是累加的
                rank[item] += float(score)* float(rating)

    return rank


#
score_rating=recommend("100")
print(score_rating)

