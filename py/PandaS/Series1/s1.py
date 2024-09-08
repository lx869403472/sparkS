import pandas as pd
s=pd.Series([1,2,3,3,4 ,5],index=list(range(0,6)))
print(s)

sd=pd.Series(dict( zip(["a","b","c","d"],[1,2,3,4])))
print(sd[["a","c"]])
print(type(str(sd["a"])))