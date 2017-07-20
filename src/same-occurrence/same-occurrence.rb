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

def count_zero(a, x_indexes, y_indexes)
  all = (x_indexes + y_indexes).sort

  c = 0

  i = 0

  all.size.times do
    unless all[i].zero?
      size = if i.zero?
               all[i]
             else
               all[i] - all[i - 1] - 1
             end
      c += max_subarray_count size
    end
    i += 1
  end

  size = a.size - all.last
  c += max_subarray_count size unless all.last == a.size - 1

  c
end

def count_subarrays_of_count(a, n, x_indexes, y_indexes)
  all = (x_indexes + y_indexes).sort

  c = 0

  i = 0
  j = (n * 2) - 1

  all.size.times do
    selected = all[i..j]

    if x_indexes.count {|it| selected.include? it} == y_indexes.count {|it| selected.include? it}
      n_left = if i.zero?
                 all[i]
               else
                 all[i] - all[i - 1] - 1
               end

      n_right = if j == all.size - 1
                  if j == a.size - 1
                    0
                  else
                    a.size - all.last
                  end
                else
                  all[j + 1] - all[j] - 1
                end

      c += 1 + n_left + n_right + if n_left != 0 && n_right != 0
                                    n_right + n_left - 1
                                  else
                                    0
                                  end

    end
    i += 1
    j += 1
  end
  c
end

max_count = max_subarray_count n

q.times do
  x, y = gets.chomp.split(' ').map(&:to_i)
  c = 0

  if a.include?(x) && a.include?(y)
    x_indexes = []
    y_indexes = []

    a.each_with_index do |it, idx|
      x_indexes << idx if it == x
      y_indexes << idx if it == y
    end

    min = [a.count(x), a.count(y)].compact.min

    c += count_zero(a, x_indexes, y_indexes)

    min.times do |it|
      c += count_subarrays_of_count(a, it + 1, x_indexes, y_indexes)
    end

    puts c

  elsif a.include?(x)
    puts max_subarrays_of(a, x)
  elsif a.include?(y)
    puts max_subarrays_of(a, y)
  else
    puts max_count
  end

end
