import random
from urllib import urlopen
import sys

WORD_URL = "http://learncodethehardway.org/words.txt"
WORDS = []

PHRASES = {
    "class %%%(%%%):":
    "Make a class named %%% that is-a %%%.",
    "class %%%(object):\n\tdef __init__(self,	***)":						"class %%% has-a __init__ that takes self and *** parameters.",
    "class %%%(object):\n\tdef ***(self, @@@)":
    "class %%% has-a function named *** that takes self and @@@ parameters.",
    "*** = %%%()":
    "Set *** to an instance of class %%%.",
    "***.***(@@@)":
    "From *** get the *** function, and call it with parameters self, @@@.",
    "***.*** = '***'":
    "From *** get the *** attribute and set it to '***'."
}

if len(sys.argv) == 2 and sys.argv[1] == "english":
    PHRASES_FIRST = True
else:
    PHRASES_FIRST = False

for word in urlopen(WORD_URL).readlines():
    WORDS.append(word.strip())

def convert(snippet, phrase):
    class_names = [
        w.capitalize() for w in random.sample(WORDS, snippet.count("%%%"))
    ]
    other_names = random.sample(WORDS, snippet.count("***"))
    results = []
    param_names = []

    for i in range(0, snippet.count("@@@")):
        param_count = random.randint(1, 3)
        param_names.append(', '.join(random.sample(WORDS, param_count)))

    for sentence in snippet, phrase:
        result = sentence[:]

        for word in class_names:
            result = result.replace("%%%", word, 1)

        for word in other_names:
            result = result.replace("***", word, 1)

        for word in param_names:
            result = result.replace("@@@", word, 1)

        results.append(result)

    return results

try:
    while True:
        snippets = PHRASES.keys()
        random.shuffle(snippets)

        for snippet in snippets:
            phrase = PHRASES[snippet]
            question, answer = convert(snippet, phrase)
            if PHRASES_FIRST:
                question, answer = answer, question

            print question

            user_input = raw_input("> ")
            if user_input == "EXIT":
                print "\nBye"
                sys.exit(0)
            print "ANSWER: %s\n\n" % answer
except EOFError:
    print "\nBye"