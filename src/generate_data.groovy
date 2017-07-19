def n = Integer.valueOf(args[1])
def a = [], b = []
def r = new Random()
def limit1 =  454544//Integer.valueOf(args[2])
def limit2 =  999000 // Integer.valueOf(args[3])

def file = new File(args[0])
def t

n.times {
	t = r.nextInt(limit1)
	a << (t % 2 == 0 ? t + 1 : t)
	t = r.nextInt(limit1) + limit1
	b << (t % 2 == 0 ? t + 1 : t)
}

file << n + '\n'
file << b.join(' ') + '\n'
file << a.join(' ') + '\n'
