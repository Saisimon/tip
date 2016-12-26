from sys import argv

# 读取参数
script, filename = argv

# 读取文件
txt = open(filename)

# 打印提示信息
print "Here's your file %r:" % filename
# 打印文本内容
print txt.read()
# 关闭文件流
txt.close()

# 提示
print "Type the filename again:"
# 读取终端输入
file_again = raw_input("> ")

# 读取文件
txt_again = open(file_again)

# 打印文本内容
print txt_again.read()
# 关闭文件流
txt_again.close()