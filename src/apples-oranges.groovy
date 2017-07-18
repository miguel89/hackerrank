//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def (s,t) = br.readLine().tokenize(' ').collect{ it as int }
def (a,b) = br.readLine().tokenize(' ').collect{ it as int }
def (m,n) = br.readLine().tokenize(' ').collect{ it as int }
def md = br.readLine().tokenize(' ').collect{ it as int }
def nd = br.readLine().tokenize(' ').collect{ it as int }
def cA = 0, cB = 0

md.each {
    def p = a + it
    if (it >= 0 && p >= s && p <= t) {
        cA++
    }
}


nd.each {
    def p = b + it
    if (it <= 0 && p >=s && p <= t) {
        cB++
    }
}

println cA
println cB