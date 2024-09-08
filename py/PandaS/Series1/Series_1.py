import pandas as pd

my_series = pd.Series(data=["11", "22", "33", "44"], index=["a", 1, "c", "d"], name="luxu", dtype=str)
print(my_series)

print("*" * 100)
print(my_series.loc[["a", "a","a"]])

print("*" * 100)
print(my_series.loc["a":"c"]["a"])

