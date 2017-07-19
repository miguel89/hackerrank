#!/bin/ruby
n = gets
a = gets.chomp.split(' ').map(&:to_i).uniq.sort_by(&:-@)
b = gets.chomp.split(' ').map(&:to_i).uniq.sort_by(&:-@)


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

def find_max(a, b)
  max = max_sum = 0
  i = j = 0

  loop do
    g = gcd a[i], b[j]
    if g > max
      max = g
      puts "#{a[i]} #{b[j]} #{g}"
      max_sum = a[i] + b[j]
      return max_sum if max == a[i] || max == b[j]
    elsif g == max && a[i] + b[j] > max_sum
      max_sum = a[i] + b[j]
      return max_sum if max == a[i] || max == b[j]
    end
    if a[i] > b[j]
      i += 1
    else
      j += 1
    end

    break if i >= a.size || j >= b.size
  end
  max_sum
end

avg1 = a.instance_eval { reduce(:+) / size.to_f }
avg2 = b.instance_eval { reduce(:+) / size.to_f }

if avg1 > avg2
  first = b
  second = a
else
  first = a
  second = b
end

puts find_max first, second
