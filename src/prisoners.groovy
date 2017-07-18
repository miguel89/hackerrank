//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def t = Integer.valueOf(br.readLine())

t.times {
    def (n,m,s) = br.readLine().tokenize(' ').collect{ it as int }

    def d = ((m % n) + s - 1)

    if (d <= 0) {
        d = n - d.abs()
    } else if (d > n) {
        d = d % n
    }

    println d
}