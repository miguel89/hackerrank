def n = Integer.valueOf(args[1])
def q = Integer.valueOf(args[2])
def a = [], qa = []
def r = new Random()
def limit1 = Integer.valueOf(args[3])
def limit2 = Integer.valueOf(args[4])

def file = new File(args[0])

n.times {
	a << r.nextInt(limit1)
}

q.times {
	qa << [r.nextInt(limit2), r.nextInt(limit2)]
}

file << "${n} ${q}\n"
file << a.join(' ') + '\n'

qa.each {
	file << it.join(' ') + '\n'
}
