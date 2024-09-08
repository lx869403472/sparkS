import pandas as pd
from PandaS import root_path

df = pd.read_csv(filepath_or_buffer=f"{root_path}/data/products.csv")

# print(df.index)
# print(df.columns)  #Index(['product_id', 'product_name', 'aisle_id', 'department_id'], dtype='object')
# print(df.shape)

df.set_index("product_id", inplace=True)  # 重置列名

print(df[["product_name", "aisle_id", "department_id"]].loc[1:10])

print(df.loc[[1, 100, 300, 200]])  # 指定行

# table=df.loc[:10].loc[1:][["product_name","aisle_id"]]
#
# print(table)

table2 = df.loc[1:5, "product_name"] \
    .str.replace(" ", "--")  # 区间行
print(f"table2 type ----- {table2.dtype} \n {table2}")

table3 = df.loc[[1, 2, 3, 7], ["product_name"]]
print(f"table3 type -----  \n {table3}")

t4 = df.loc[(df["department_id"] == 19) & (2 == 1)]  # 条件查找
print(t4)

t5 = df.loc[lambda df: (df["department_id"] == 20) & (2 == 2)]  # 函数查询
t6=df.loc[df["department_id"].apply(lambda x:x==20)]

print(t6)


# def fun(df):
#     return df["department_id"] == 20
#
#
# t6 = df.loc[fun, :]  # 函数查询
#
# print(t6)

if __name__ == '__main__':
    pass

