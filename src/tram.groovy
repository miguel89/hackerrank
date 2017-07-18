//Enter your code here. Read input from STDIN. Print output to STDOUT
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
def ticket = br.readLine()

while (true) {
    def firstSum = 0, lastSum = 0
    ticket = ((ticket as int) + 1).toString()

    3.times {
        firstSum += ticket[it] as int
        lastSum += ticket[-it-1] as int
    }
    if (lastSum == firstSum) {
        break
    }
}

println ticket
