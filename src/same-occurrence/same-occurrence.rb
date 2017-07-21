#!/bin/ruby
n, q = gets.chomp.split(' ').map(&:to_i)
z = -1
a = Hash[gets.chomp.split(' ').collect { |v| [(z += 1), v.to_i] }]

def max_subarray_count(size)
  return 1 if size == 1
  return 0 if size <= 0
  ((size * (size - 1)) / 2) + size
end

def max_subarrays_of(indexes, array_size)
  c = 0

  i = 0

  c += max_subarray_count indexes[i] unless indexes[i].zero?
  i += 1

  while i < indexes.size
    c += max_subarray_count(indexes[i] - indexes[i - 1] - 1)
    i += 1
  end
  c += max_subarray_count(array_size - indexes.last) unless indexes.last == (array_size - 1)

  c
end

def count_zero(a, all)
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

  size = a.size - all.last - 1
  c += max_subarray_count size unless all.last == a.size - 1

  c
end

def count_subarrays_of(a, n, all, x_indexes, y_indexes)
  c = 0

  i = 0
  j = (n * 2) - 1

  loop do
    selected = all[i..j]

    if x_indexes.count {|it| selected.include? it} == n && y_indexes.count {|it| selected.include? it} == n
      n_left = if i.zero?
                 all[i]
               else
                 all[i] - all[i - 1] - 1
               end

      n_right = if j == all.size - 1
                  if j == a.size - 1
                    0
                  else
                    a.size - all.last - 1
                  end
                else
                  all[j + 1] - all[j] - 1
                end

      c += 1 + n_left + n_right + if n_left != 0 && n_right != 0
                                    n_right + n_left
                                  else
                                    0
                                  end

    end
    i += 1
    j += 1

    break if j == all.size
  end
  c
end

max_count = max_subarray_count n
result = []

q.times do
  x, y = gets.chomp.split(' ').map(&:to_i)
  c = 0

  x_indexes = []
  y_indexes = []

  a.each_with_index do |it, idx|
    x_indexes << idx if it == x
    y_indexes << idx if it == y
  end

  if !x_indexes.empty? || !y_indexes.empty?
    min = [x_indexes.size, y_indexes.size].compact.min
    all_indexes = (x_indexes + y_indexes).sort

    c += count_zero(a, all_indexes)

    min.times do |it|
      c += count_subarrays_of(a, it + 1, all_indexes, x_indexes, y_indexes)
    end

    result << c
  else
    result << max_count
  end
end

puts result
