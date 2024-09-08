import random

source = list(range(1, 10000))
random.shuffle(source)
print(source)


def q_sort(seq=source):
    lang = len(seq)

    mid = lang // 2
    left = []
    right = []

    if lang <= 2:
        return seq
    lm = seq[mid]
    seq.remove(lm)
    for i in seq:
        if i < lm:
            left.append(i)
        else:
            right.append(i)
    return q_sort(left) + [lm] + q_sort(right)


print(q_sort())
