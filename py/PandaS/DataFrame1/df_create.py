import pandas as pd

df = pd.DataFrame(data=dict(zip(["id", "name", "age"], [["s"] * 10, ["b"] * 10, ["z"] * 10])))
print(df)

