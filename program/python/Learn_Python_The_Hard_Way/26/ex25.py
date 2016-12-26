def break_words(stuff):
    """This funcation will break up words for us.""" # 文档注解
    words = stuff.split(' ')
    return words

def sort_words(words):
    """Sort the words.""" # 文档注解
    return sorted(words)

def print_first_word(words):
    """Prints the first word after poping it off.""" # 文档注解
    word = words.pop(0)
    print word

def print_last_word(words):
    """Prints the last word after poping it off.""" # 文档注解
    word = words.pop(-1)
    print word

def sort_sentence(sentence):
    """Takes in a fullsentance and return the sorted words.""" # 文档注解
    words = break_words(sentence)
    return sort_words(words)

def print_first_and_last(sentence):
    """Prints the first and last words of the sentence.""" # 文档注解
    words = break_words(sentence)
    print_first_word(words)
    print_last_word(words)

def print_first_and_last_sorted(sentence):
    """Sorts the words then prints the first and last one.""" # 文档注解
    words = sort_sentence(sentence)
    print_first_word(words)
    print_last_word(words)

language = "PHP Java Python Ruby Javascript C C++ C# Lisp ObjectiveC Swift Perl"
print_first_and_last(language)
print_first_and_last_sorted(language)