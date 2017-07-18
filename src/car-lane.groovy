//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def (n,t) = br.readLine().tokenize(' ').collect{ it as int }
def width = br.readLine().tokenize(' ').collect{ it as int }

t.times {
    def (i,j) = br.readLine().tokenize(' ').collect{ it as int }

    println width[i..j].min()
}