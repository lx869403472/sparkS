import pandas as pd
from PandaS import root_path

df = pd.read_csv(filepath_or_buffer=f"{root_path}/data/products.csv")

df.set_index("product_id", inplace=True)
# print(df.loc[0:5])
# print(df.index)
# print(df.columns)  #Index(['product_id', 'product_name', 'aisle_id', 'department_id'], dtype='object')
# print(df.shape)
df1=df.loc[0:10]
#1、直接赋值
# df.loc[:,"new_col"] = "new11"
# print(df[["aisle_id","new_col"]])


#2、apply
def fi(x):
    if x["department_id"]=="50":
        return "0"
    else:
        "1"

df.loc[:,"new_ad_id"] = df.apply(fi,axis=1)

print(df)


#4、条件分组赋值
df.loc[df["aisle_id"]>10,"new2"]="__"
print(df)