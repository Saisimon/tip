from sys import argv

script, input_file = argv

# 打印文件中所有内容
def print_all(f):
    print f.read()

# 重置文件的偏移位置到文件开始
def rewind(f):
    f.seek(0)

# 打印文件中的某一行
def print_a_line(line_count, f):
    print line_count, f.readline()

current_file = open(input_file)

print "First let's print the whole file:\n"

print_all(current_file)

print "Now let's rewind, kind of like a tape."

rewind(current_file)

print "Let's print three lines:"

current_line = 1
print_a_line(current_line, current_file)

current_line = current_line + 1
print_a_line(current_line, current_file)

current_line += 1
print_a_line(current_line, current_file)