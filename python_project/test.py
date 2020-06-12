class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        a = len(word1)
        b = len(word2)
        dp = [[0] * (a + 1)] * (b + 1)

        for i in range(1, a + 1):
            for j in range(1, b + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = max(dp[i][j], max(dp[i][j - 1], dp[i - 1][j]) + 1)

        return dp[a][b]

    def deleteNode(self, node):
        if node is not None:
            if node.next is None:
                node = None
            else:
                val = node.next.val
                node.val = val
                node.next = node.next.next
