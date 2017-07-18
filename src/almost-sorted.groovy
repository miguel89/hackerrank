//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def s = Integer.valueOf(br.readLine())
def a = br.readLine().tokenize(' ').collect { it as int }

Integer idx1 = null, idx2 = null

for (int i = 0; i < s - 1; i++) {
    if (a[i] > a[i + 1]) {
        idx1 = i
        break
    }
}

if (idx1 != null) {
    for (int i = idx1 ; i < s; i++) {
        if (a[i] < a[i - 1]) {
            idx2 = i
        }
    }

    if (idx2 == null) {
        idx2 = 0
    }

    def b = a.clone()

    b[idx2] = a[idx1]
    b[idx1] = a[idx2]

    if (a.sort(false) == b) {
        println 'yes'
        println "swap ${idx1 + 1} ${idx2 + 1}"
        return
    }

    def subList = a.subList(idx1, idx2 + 1).reverse()

    b = a.clone()
    subList.eachWithIndex { it, idx ->
        b[idx1 + idx] = it
    }

    if (a.sort(false) == b) {
        println 'yes'
        println "reverse ${idx1 + 1} ${idx2 + 1}"
        return
    }
}

println 'no'
