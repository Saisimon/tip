from sys import argv

# 读取参数
script, filename = argv

# 打印参数并且提示
print "We're going to erase %r." % filename
print "If you don't want that, hit CTRL-C(^C)."
print "If you do want that, hit RETURN."

# 等待输入
raw_input("?")

print "Open the file..."
# 读取指定文件
# 'w'  写入模式
# 'r'  只读模式，默认
# 'a'  追加模式
target = open(filename, 'w')

print "Truncating the file. Goodbye!"
# 清空该文件
target.truncate()

print "Now I'm going to ask you for three lines."

# 接收输入
line1 = raw_input("line 1: ")
line2 = raw_input("line 2: ")
line3 = raw_input("line 3: ")

print "I'm going to write these to the file."

# 写入文件
target.write(line1)
target.write("\n")
target.write(line2)
target.write("\n")
target.write(line3)
target.write("\n\n")

context = line1 + "\n" + line2 + "\n" + line3
target.write(context)

print "And finally, we close it."
# 关闭文件
target.close()
