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

def find_max(a, b, flag)
  max = max_sum = 0

  a.each do |k|
    next if k <= max
    b.each do |c|
      next if c < max
      g = gcd k, c
      if g > max
        max = g
        puts "#{k} #{c} #{g}"
        max_sum = k + c
        if flag && (max == k || max == c)
          return max_sum
        end
      elsif g == max && k + c > max_sum
        max_sum = k + c
      end
    end
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

puts find_max(first, second, true)
