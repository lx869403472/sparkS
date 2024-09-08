source = list(range(0, 10, 1))
lang = len(source)
left = 0
right = lang



def getindex(par, seq=(left, right)):
    left, right = seq
    newseq=source[left:right]
    avg = len(newseq) // 2  # 数组取中值
    print(f"avg {avg}")
    avgindex = left + avg

    avgpar = source[left + avg]

    if len(newseq)==0:
        return ("无此元素",)

    elif avg == 1  :
        print(left, right)
        return (avgindex,)

    elif par < avgpar:
        right -= avg
        return (left, right)

    elif par > avgpar:
        left += avg
        return (left, right)



if __name__ == '__main__':
    seq = (0, len(source) + 1)

    while True:
        seq = getindex(par=14, seq=seq)
        if len(seq) == 1:
            print( seq[0])
            break


            