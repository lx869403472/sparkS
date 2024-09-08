a = 'ABw'
b = 'BDCABAw'


###l     a/n
### b/m   A   B   C   B   D   A   B
#         B
#         D
#         C
#         A
#         B
#         A
def lcs(a,b):
    n = len(a)
    m = len(b)
    l = [[0]*(m+1) for x in range(n+1)]
    for line in l:
        print(line)
    print("--------------"*2)
    for i in range(1,n+1):
        for j in range(1, m+1):
            if a[i-1] == b[j-1]:
                l[i][j] = l[i-1][j-1]+1
            else:
                l[i][j] = max(l[i-1][j], l[i][j-1])
    return l[-1][-1],l

l ,alls= lcs(a,b)
# print(alls)
for line in alls:
    print(line)
