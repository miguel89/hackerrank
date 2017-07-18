//Enter your code here. Read input from STDIN. Print output to STDOUT
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
//Integer.valueOf(br.readLine())
//def a = br.readLine().tokenize(' ').collect { it as int }

def a = '1 2 4 3 5 6'.tokenize(' ').collect { it as int }

def idxOne = a.findIndexOf { it > a[a.indexOf(it) + 1] }
def sub = a[(idxOne + 1)..-1]
def idxTwo = sub.findIndexOf { sub.indexOf(it) && it < a[a.indexOf(it) - 1] }

idxTwo = idxTwo < 0 ? sub.size() + idxTwo : idxTwo

idxTwo += idxOne + 1

def b = a.clone()

b[idxTwo] = a[idxOne]
b[idxOne] = a[idxTwo]

if (a.sort(false) == b) {
	println 'yes'
    println "swap ${idxOne + 1} ${idxTwo + 1}"
	return
}

idxTwo = sub.findIndexOf { it < sub[sub.indexOf(it) + 1] }

idxTwo = idxTwo < 0 ? sub.size() + idxTwo : idxTwo

idxTwo += idxOne + 1

def subList = a.subList(idxOne, idxTwo + 1).reverse()

b = a.clone()
subList.eachWithIndex { it, idx ->
	b[idxOne + idx] = it
}


if (a.sort(false) == b) {
	println 'yes'
    println "reverse ${idxOne + 1} ${idxTwo + 1}"
	return
}

println 'no'



