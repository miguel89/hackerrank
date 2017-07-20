#!/bin/ruby
n, q = gets.chomp.split(' ').map(&:to_i)
a = gets.chomp.split(' ').map(&:to_i)

def max_subarray_count(size)
  return size if size <= 1
  ((size * (size - 1)) / 2) + size
end

def max_subarrays_of(a, x)
  c = 0

  i = if a.index(x).zero?
        1
      else
        0
      end
  j = if i.zero?
        a.index(x)
      else
        a[1..-1].index(x) || a.size
      end

  diff = if a[i] == x && a[j] == x
           1
         else
           0
         end

  loop do
    c += max_subarray_count(j - i - diff)

    break if j == a.size - 1

    i = j
    j = a[(j + 1)..-1].index(x)

    if j.nil?
      j = a.size - 1
    else
      j += i + 1
    end

    diff = if a[i] == x && a[j] == x
             1
           else
             0
           end
  end
  c
end


q.times do
  x, y = gets.chomp.split(' ').map(&:to_i)
  c = 0

  if a.include?(x) && a.include?(y)
    i = [a.index(x), a.index(y)].min
    j = [a.rindex(x), a.rindex(y)].compact.max

    loop do
      c += 1 if a[i..j].count(x) == a[i..j].count(y)

      j = [a[0..(j - 1)].rindex(x), a[0..(j - 1)].rindex(y)].compact.max

      break if j.nil?

      if j == i
        i = [a[(i + 1)..-1].index(x), a[(i + 1)..-1].index(y)].compact.min + i + 1
        j = [a[0..-1].rindex(x), a[0..-1].rindex(y)].max
      end

      break if i == a.size - 1

    end

    puts c

  elsif a.include?(x)
    puts max_subarrays_of(a, x)
  elsif a.include?(y)
    puts max_subarrays_of(a, y)
  else
    puts max_subarray_count n
  end

end
