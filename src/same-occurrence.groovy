BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream('input.txt')))
def (n,q) = br.readLine().tokenize(' ').collect { it as int }
def a = br.readLine().tokenize(' ').collect { it as int }
def x, y, countX, countY, count, result

def countMaxSubArrays = { c ->
    if (c == 1) return 1
    if (c <= 0) return 0
    ((c * (c - 1)) / 2) + c
}

def maxSubArrays = countMaxSubArrays(n)

def calc = { _x, _y, f, l ->
    def s = 0
    def a2 = a[f..l]
    def size = a2.size()

    for (int i = 0; i < size; i++) {
        for (int j = size - 1; j >= i; j--) {
            if (a2[i..j].count(_x) == a2[i..j].count(_y)) {
                s++
            }
        }
    }
    return s
}

q.times {
    (x, y) = br.readLine().tokenize(' ').collect { it as int }

    countY = a.count(y)
    countX = a.count(x)
    result = 0

    if (countX || countY) {
        count = Math.min(countX, countY)

        def first, second, next

        first = Math.min(a.indexOf(x), a.indexOf(y))
        second = Math.max(a.indexOf(x), a.indexOf(y))
        next = a[(first + 1)..-1].indexOf(a[first])

        if (next != -1) {
            next += first + 1
        }

        if (next == -1) {
            next = a[(second + 1)..-1].indexOf(a[second])

            if (next != -1) {
                next += second + 1
            }
        }

        while (second != -1) {
            result += calc(x, y, first, second)

            if (next == -1) break

            first = second
            second = next
            next = a[(first + 1)..-1].indexOf(a[first])

            if (next != -1) {
                next += first + 1
            }

            if (next == -1 && second + 1 < a.size()) {
                next = a[(second + 1)..-1].indexOf(a[second])

                if (next != -1) {
                    next += second + 1
                }

            }

        }
        println result

    } else {
        println maxSubArrays
    }

}

