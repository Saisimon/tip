try:
	from setuptools import setup
except ImportError:
	from distutils.core import setup
	
config = {
	'description': 'Project',
	'author': 'Saisimon',
	'url': 'http://blog.saisimon.net',
	'version': '0.1',
	'install_requires': ['nose'],
	'packages': ['NAME'],
	'scripts': [],
	'name': 'Project'
}

setup(**config)