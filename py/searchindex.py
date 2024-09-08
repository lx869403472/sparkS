import time
import math

class A:

    def __init__(self, seq, arg):

        self.arg = arg
        self.left_offset = 0
        self.right_offset = len(seq)
        self.seq = seq

    def search(self):
        nowseq = tuple(self.seq[self.left_offset:self.right_offset])  # 本次查找的数组
        seq_len = len(nowseq)
        offset = seq_len // 2 + self.left_offset
        min_avg = self.seq[offset]

        # print(nowseq)

        if len(nowseq) <= 1:
            if self.arg == min_avg:
                self.result = offset
                return "exit"

            else:
                self.result = "元素不存在"
                return "exit"


        elif self.arg < min_avg:
            self.left_offset = self.left_offset
            self.right_offset = offset
            return "next"

        else:
            self.left_offset = offset
            self.right_offset = self.right_offset
            return "next"

    def exec(self):
        for i in range(1, int(math.log2(10**9))+1):
            m = self.search()
            if m == "exit":
                break

        print(self.result)


if __name__ == '__main__':
    seq =sorted([i for i in range(1,101)]*4)
    p = 99
    print(seq.count(p))
    obj = A(seq, p)
    obj.exec()
