#!/bin/ruby
a = gets.chomp.to_i
b = gets.chomp.to_i


def gcd(x, y)
  x = x.abs
  y = y.abs

  return x if y.zero?

  while x % y != 0
    t = y
    y = x % y
    x = t
  end
  y
end

puts gcd(a, b)
