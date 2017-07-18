//Enter your code here. Read input from STDIN. Print output to STDOUT
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def (n,k,q) = '3 2 3'.tokenize(' ').collect{ it as int }
def a = '1 2 3'.tokenize(' ').collect{ it as int }
def diff = n-(k % n)

[0,1,2].each {
    def i = Integer.valueOf(it)

    println a[(i+diff) % n]
}