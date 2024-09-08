import pandas as pd
import numpy as np

boolean = [True, False]
gender = ["男", "女"]
color = ["white", "black", "yellow"]

data = pd.DataFrame({
    "height": np.random.randint(150, 190, 10),
    "weight": np.random.randint(40, 90, 10),
    "smoker": [boolean[x] for x in np.random.randint(0, 2, 10)],
    "gender": [gender[x] for x in np.random.randint(0, 2, 10)],
    "age": np.random.randint(15, 90, 10),
    "color": [color[x] for x in np.random.randint(0, len(color), 10)],
}
)

dt = data
dt[["height", "age"]]


def apply_age(x, fh):
    return x + fh


# dt["age"] = data["age"].apply(lambda x, y: x - y, args=(3))
dt["age"]
# dt["age"] = dt["age"].apply(apply_age, agrs=(3,))
dt["age"] = dt["age"].apply(lambda x: x)
dt["age"] = dt["age"].apply(lambda x: x + 1000)
dt
data[["height", "weight"]]
data[["height", "weight"]]
data[["height", "weight"]].apply(np.sum, axis=0)
data[["height", "weight"]].apply(np.sum, axis=1)
data["sum"] = data[["height", "weight"]].apply(np.sum, axis=1)
data["mkStrig_height_weight"] = data[["height", "weight"]].apply(lambda x, y: str(x) + str(y), axis=1)

# data["mkStrig_height_weight"] = data[["height", "weight"]].apply(count, axis=1)


def BMI(series):
    weight = series["weight"]
    height = series["height"]
    BMI = weight / height ** 2
    return BMI


data["BMI"] = data.apply(BMI, axis=1)
data["BMI2"] = data.apply(lambda x: x["weight"] + x["height"], axis=1)
data.applymap(lambda x: str(x) + "_")
data
data.applymap(lambda x: str(x).replace("_", ""))
