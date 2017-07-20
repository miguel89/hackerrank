#!/bin/ruby


output = `ruby same-occurrence.rb < input.txt`
puts output == "2\n6\n"
