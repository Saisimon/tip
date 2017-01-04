import web

urls = ('/index', 'index', '/info', 'info', '/hello', 'hello', '/upload', 'upload')

app = web.application(urls, globals())

render = web.template.render('templates/', base='layout')

class hello:
	def GET(self):
		form = web.input(name = 'Nobody', greet = None)
		if form.greet:
			greeting = "%s, %s" % (form.greet, form.name)
			return render.hello(greeting = greeting)
		else:
			return "Error"
		
class info:
	def GET(self):
		info = web.input(info = 'fail').info
		return render.info(info = info)
		
class index:
	def GET(self):
		return render.index()
		
	def POST(self):
		form = web.input(name = 'Nobody', greet = "Hello")
		greeting = "%s, %s" % (form.greet, form.name)
		return render.hello(greeting = greeting)
		
class upload:
	def GET(self):
		return render.upload()
		
	def POST(self):
		form = web.input(uploadFile = {})
		filepath = form.uploadFile.filename.replace('\\', '/')
		filename = filepath.split('/')[-1]
		tmp = open(filename, 'w')
		tmp.write(form.uploadFile.file.read())
		tmp.close()
		return render.info(info = "success")
		
if __name__ == "__main__":
	app.run()