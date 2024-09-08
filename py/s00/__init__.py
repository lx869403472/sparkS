if __name__ == '__main__':
    pass
    x1=12345678989121
    x2=2312312312312312312


    list1=[ i for i in str(x1)]
    print(list1)

    l=([str(i)+"\t" for i in range(1,10)] +["\n"]) *20



    with open("x.txt", mode="w") as fb:
        fb.writelines(l)





