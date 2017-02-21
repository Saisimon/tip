my_name = 'Saismon'
my_age = 24 # LOL
my_height = 181

print "Name : %s." % my_name
print "Age : %d." % my_age
print "Height : %d CM." % my_height

# 多个格式化标记对应的值需用小括号括起来并用逗号隔开
# (value1, value2, value3...)
print "%d add %d is %d" % (my_age, my_height, my_age + my_height)

# 所以输出都可以使用 %s 来标记
print "%s %s %s" % (1, 2.3, ['one', 'two', 'three'])

# 类似 C 语言中的 printf
# %r 不管什么都打印出来
# %c 字符
# %d 整型数
# %u 无符号整型数
# %o 八进制数
# %x 十六进制数
# %f 浮点数
# %e 科学计数法
# %g 根据数值自动选择 %f 或者 %e
print "%c" % 'a'
print "%6.3f" % 1.2355
print "%06.3f" % 1.2355

print "%0*.*f" % (5, 3, 1.2355)
print "%r" % 2.444