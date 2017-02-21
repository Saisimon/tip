import web

urls = ('/', 'index', '/info', 'info')

app = web.application(urls, globals())

render = web.template.render('templates/')

class index:
	def GET(self):
		greeting = "Hello World"
		return render.index(greeting = greeting)
		
class info:
	def GET(self):
		info = "success"
		return render.info(info = info)

if __name__ == "__main__":
	app.run()