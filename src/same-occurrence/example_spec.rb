#!/bin/ruby
output = `ruby same-occurrence.rb < input.txt`
puts output
puts output == "2\n6\n"

output = `ruby same-occurrence.rb < input_2.txt`
puts output
