try:
	from setuptools import setup
except ImportError:
	from distutils.core import setup
	
config = {
	'description': 'ex48',
	'author': 'Saisimon',
	'url': 'http://blog.saisimon.net',
	'version': '0.1',
	'install_requires': ['nose'],
	'packages': ['ex48'],
	'scripts': [],
	'name': 'ex48'
}

setup(**config)