#!/bin/ruby
output = `ruby same-occurrence.rb < input.txt`
puts output
puts output == "2\n6\n"

output = `ruby same-occurrence.rb < input_2.txt`
puts output
puts output == "27\n27\n32\n4\n8\n8\n"

output = `ruby same-occurrence.rb < input_3.txt`
puts output
puts output == "2\n3\n3\n3\n3\n2\n"