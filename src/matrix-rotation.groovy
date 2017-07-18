//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream('input.txt')))
def (m,n,r) = br.readLine().tokenize(' ').collect { it as int }
List a = []

m.times {
    a << br.readLine().tokenize(' ').collect { it as int }
}

class Matrix {
    List<List<Integer>> array = []
    List<List<Integer>> newArray
    int m,n

    Matrix(Map map) {
        this.array = map.array
        this.m = map.m
        this.n = map.n

        this.newArray = []
        this.array.each {
            this.newArray << it.clone()
        }
    }

    // defines a rule to check if any given point is in side A, B, C or D
    Map<String, Closure> rules = [
            a: { x, y, d ->
                return y == d
            },
            b: { x, y, d ->
                return x == (m - d - 1)
            },
            c: { x, y, d ->
                 return y == (n - d - 1)
            },
            d: { x, y, d ->
                return x == d
            }
    ]

    def addA(x,y,d,t) {
        def _m = m - d - 1

        if (t + x > _m) {
            return addB(_m, y, d, (t + x - _m))
        } else {
            return [x + t, y]
        }
    }

    def addB(x,y,d,t) {
        def _m = n - d - 1

        if (t + y > _m) {
            return addC(x, _m, d, (t + y - _m))
        } else {
            return [x, y + t]
        }
    }
    def addC(x,y,d,t) {
        def _m = d
        if (x - t < _m) {
            return addD(_m, y, d, (t - y + _m))
        } else {
            return [x - t, y]
        }
    }
    def addD(x,y,d,t) {
        def _m = d

        if (y - t < _m) {
            return addA(x, _m, d, (t - x + _m))
        } else {
            return [x, y - t]
        }
    }
    def rotatePoint(x,y,d,n) {
        def k,c
        if (rules.a(x, y, d)) {
            (k,c) = addA(x,y,d,n)
        } else if (rules.b(x, y, d)) {
            (k,c) = addB(x,y,d,n)
        } else if (rules.c(x, y, d)) {
            (k,c) = addC(x,y,d,n)
        } else if (rules.d(x, y, d)) {
            (k,c) = addD(x,y,d,n)
        } else {
            throw new RuntimeException('NO SIDE AT ALL')
        }

        return [k,c]
    }

    def rotate(d, n) {
        def (x,y) = [d,d]
        def (k,c) = rotatePoint(x, y, d, n)
        newArray[k][c] = array[x][y]
        (x,y) = rotatePoint(x,y,d,1)

        while (x != d || y != d) {
            (k,c) = rotatePoint(x, y, d, n)
            newArray[k][c] = array[x][y]
            (x,y) = rotatePoint(x,y,d,1)
        }

    }

}

def mat = new Matrix(array: a, m: m, n: n)
def depth = Math.min(m,n) / 2


depth.times { d ->
    def rot = ((m - d * 2) * 2) + ((n - d * 2) * 2) - 4
    def times =  r % rot

    if (times > 0) {
        mat.rotate(d,times)
    }

}

mat.newArray.each {
    println it.join(' ')
}