//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream('input.txt')))
//Integer.valueOf(br.readLine())
//def arrayA = br.readLine().tokenize(' ').collect { it as int }
//def arrayB = br.readLine().tokenize(' ').collect { it as int }
def x = (5 * 10 ** 5) - 7
def random = new Random()
def arrayA = []
x.times { arrayA << random.nextInt(10**6) }
def arrayB = []
x.times { arrayB << random.nextInt(10**6) }

arrayA = arrayA.sort { - it }
arrayB = arrayB.sort { - it }

def gcdI = { m, n ->
    m = m.abs()
    n = n.abs()
    if (n == 0) {
        return m
    }
    while (m % n != 0) {
        def t = n
        n = m % n
        m = t
    }
    return n
}

def resultMap = [:]
def max = 0
def maxSum = 0
def r

arrayA.each { a ->
    if (a > max && a > maxSum) {
        arrayB.each { b ->
            if (b > max && a + b > maxSum) {
                r = gcdI(a, b)
                if (r > max) {
                    max = r
                    resultMap = [("${a}:${b}"): r]
                    maxSum = a + b
                } else if (r == max) {
                    resultMap << [("${a}:${b}"): r]
                    maxSum = a + b
                }
            }
        }
    }
}
def result = 0

resultMap.each { k, v ->
    def sum = k.tokenize(':').collect { it as int }.sum()

    if (k.tokenize(':').collect { it as int }.sum() > result) {
        result = sum
    }
}

println result
