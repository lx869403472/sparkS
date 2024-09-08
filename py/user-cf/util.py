import pandas as pd


# 获取训练数据
def generate_train_data(nrows=None):
    df = pd.read_csv(r'C:\D\script\大数据\recsys_music11\raw_data\u.data',
                     sep='\t',
                     nrows=nrows,
                     names=['user_id', 'item_id', 'rating', 'timestamp'])
    d = dict()
    for t, row in df.iterrows():
        user_id = str(row['user_id'])
        item_id = str(row['item_id'])
        rating = row['rating']
        # if user_id not in d.keys():
        if d.get(user_id, -1) == -1:
            d[user_id] = {item_id: rating}
        else:
            d[user_id][item_id] = rating

    return d

if __name__ == '__main__':
    print(generate_train_data(10))