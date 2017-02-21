from sys import argv

# python ex13.py a1 b2 c3
# 引入 argv 模块，读取执行时输入的参数
script, first, second, third = argv

print "The script is called:", script
print "Your first variable is:", first
print "Your second variable is:", second
print "Your second variable is:", third