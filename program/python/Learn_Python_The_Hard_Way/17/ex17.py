from sys import argv
import  sys
from os.path import exists

# 读取参数
script, from_file, to_file = argv

# 提示
print "Copying from %s to %s" % (from_file, to_file)

# 判断是否存在
exist = exists(from_file)

# 不存在则提示并退出
if not exist:
    print "%s not exists!" % from_file
    sys.exit(1);

# 读取文件
in_file = open(from_file)
indata = in_file.read()

# 获取文件大小
print "The input file is %d bytes long" % len(indata)

# print "Does the output file exist? %r" % exists(to_file)
# print "Ready, hit RETURN to continue, CTRL-C to abort."
# raw_input()

# 打开输出文件
out_file = open(to_file, 'w')
# 写入文件
out_file.write(indata)

#提示完成
print "Alright, all done."

# 关闭资源
out_file.close()
in_file.close()