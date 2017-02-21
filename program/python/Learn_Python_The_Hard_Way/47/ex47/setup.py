try:
	from setuptools import setup
except ImportError:
	from distutils.core import setup
	
config = {
	'description': 'ex47',
	'author': 'Saisimon',
	'url': 'http://blog.saisimon.net',
	'version': '0.1',
	'install_requires': ['nose'],
	'packages': ['ex47'],
	'scripts': [],
	'name': 'ex47'
}

setup(**config)