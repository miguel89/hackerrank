//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def (m,n,r) = br.readLine().tokenize(' ').collect { it as int }
def a = []

m.times {
    a << br.readLine().tokenize(' ').collect { it as int }
}

List getNext(x,y,m,n,depth) {
    if (y == depth) {
        if (x < m -1 - depth) {
            return [x + 1, y]
        } else {
            return [x, y + 1]
        }
    }
    if (x == depth) {
        if (y != depth) {
            return [x, y - 1]
        } else {
            return [x + 1, y]
        }
    }

    if (x == m -1 - depth) {
        if (y != n-1 - depth) {
            return [x, y + 1]
        } else {
            return [x - 1, y]
        }
    }

    if (y == n -1 - depth) {
        if (x != depth) {
            return [x - 1, y]
        } else {
            return [x, y - 1]
        }
    }
}

def depth = Math.min(m,n) / 2

depth.times { d ->
    def rot = ((m - d * 2) * 2) + ((n - d * 2) * 2) - 4
    def t =  r % rot

    t.times {
        def (x, y) = getNext(d, d, m, n, d)

        while (x != d || y != d) {
            def temp = a[d][d]
            a[d][d] = a[x][y]
            a[x][y] = temp
            (x, y) = getNext(x, y, m, n, d)
        }

    }
}

a.each {
    println it.join(' ')
}