import sys
sys.path +=[r"C:\APP\sys\Anaconda3\Lib\site-packages"]
import pandas as pd
# 处理训练数据-> dict{user_id:{item_id:rating},user_id1:{item_id2:rating}}
df = pd.read_csv(r'C:\D\script\大数据\recsys_music11\raw_data\u.data',
                 sep='\t',
                 nrows=100000,
                 names=['user_id', 'item_id', 'rating', 'timestamp'])

print(df.head())
print(min(df['rating']))
print(df.dtypes)

print(df.iteritems())

d = dict()
for _, row in df.iterrows():
    # print(row)
    # print(_)
    user_id = str(row['user_id'])

    item_id = str(row['item_id'])
    rating = row['rating']
    # if user_id not in d.keys():
    if d.get(user_id, -1) == -1:
        d[user_id] = {item_id: rating}
    else:
        d[user_id][item_id] = rating


print(d)
""" 
"""