import sys

collections = [
	{
		"north" : "direction",
		"south" : "direction",
		"east" : "direction",
		"west" : "direction",
		"down" : "direction",
		"up" : "direction",
		"left" : "direction",
		"right" : "direction",
		"back" : "direction"
	},{
		"go" : "verb",
		"stop" : "verb",
		"kill" : "verb",
		"eat" : "verb"
	},{
		"the" : "stop",
		"in" : "stop",
		"of" : "stop",
		"from" : "stop",
		"at" : "stop",
		"it" : "stop"
	},{
		"door" : "noun",
		"bear" : "noun",
		"princess" : "noun",
		"cabinet" : "noun"
	}
]

def scan(sentence):
	results = []
	words = sentence.split()
	for word in words:
		try:
			results.append(('number', int(word)))
		except ValueError:
			val = 'error'
			for collection in collections:
				val = collection.get(word, 'error')
				if val != 'error':
					break
			results.append((val, word))
	return results

dicts = {
	"direction" : [
		"north",
		"south",
		"east",
		"west",
		"down",
		"up",
		"left",
		"right",
		"back"
	], 
	"verb" : [
		"go",
		"stop",
		"kill",
		"eat"
	], 
	"stop" : [
		"the",
		"in",
		"of",
		"from",
		"at",
		"it"
	], 
	"noun" : [
		"door",
		"bear",
		"princess",
		"cabinet"
	]
}

# Python 没有提供跳出指定循环的方式
# 解决方法：
# 1. 抛出异常
# 2. 逐级跳出
# 3. 封装为函数，直接返回
def scan(sentence):
	results = []
	words = sentence.split()
	for word in words:
		if checkNumber(word):
			results.append(('number', int(word)))
		else:
			val = 'error'
			flag = False
			for (k, v) in dicts.items():
				if flag:
					break
				for w in v:
					if w == word:
						val = k
						flag = True
						break
			results.append((val, word))
	return results

def checkNumber(word):
	"""简单的判断输入是否为数字"""
	for letter in word:
		if letter < '0' or letter > '9':
			return False
	return True